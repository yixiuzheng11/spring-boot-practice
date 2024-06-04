package org.yixz.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.common.collect.Lists;
import org.yixz.common.enums.MenuTypeEnum;
import org.yixz.common.util.UserUtil;
import org.yixz.entity.dto.SysMenuDto;
import org.yixz.entity.mysql.SysMenu;
import org.yixz.entity.mysql.SysUser;
import org.yixz.entity.vo.MenuRouteVo;
import org.yixz.entity.vo.RouteVo;
import org.yixz.entity.vo.SysMenuVo;
import org.yixz.mapper.SysMenuMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author yixz
 * @since 2021-12-22
 */
@Service
public class SysMenuService extends ServiceImpl<SysMenuMapper, SysMenu> {

    public Page<SysMenu> getPage(SysMenuDto dto) {
        Page page = new Page(dto.getPageNum(), dto.getPageSize());
        LambdaQueryWrapper<SysMenu> queryWrapper = Wrappers.lambdaQuery(SysMenu.class);
        queryWrapper.eq(dto.getParentId()!=null, SysMenu::getParentId, dto.getParentId());
        queryWrapper.eq(StringUtils.isNotEmpty(dto.getUrl()), SysMenu::getUrl, dto.getUrl());
        queryWrapper.eq(StringUtils.isNotEmpty(dto.getMenuType()), SysMenu::getType, dto.getMenuType());
        queryWrapper.like(StringUtils.isNotEmpty(dto.getName()), SysMenu::getName, dto.getName());
        return baseMapper.selectPage(page, queryWrapper);
    }

    public Integer add(SysMenuDto dto) {
        SysMenu user = new SysMenu();
        BeanUtils.copyProperties(dto, user);
        baseMapper.insert(user);
        return user.getId();
    }

    public void update(SysMenuDto dto) {
        LambdaUpdateWrapper<SysMenu> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.set(SysMenu::getParentId, dto.getParentId());
        updateWrapper.set(SysMenu::getName, dto.getName());
        updateWrapper.set(SysMenu::getUrl, dto.getUrl());
        updateWrapper.eq(SysMenu::getId, dto.getId());
        //这里传个new出来的user对象，确保FieldFill.INSERT_UPDATE属性在更新时自动填充值
        baseMapper.update(new SysMenu(), updateWrapper);
    }

    public void delete(Integer id) {
        baseMapper.deleteById(id);
    }

    /**
     * 获取导航菜单
     * @return
     */
    public List<SysMenuVo> getMenuList(SysMenuDto dto) {
        List<SysMenu> menuList = this.getAuthMenus(dto);
        //目录菜单
        List<SysMenu> filterMenuList = menuList.stream().filter(item->!MenuTypeEnum.BUTTON.equals(item.getType())).collect(Collectors.toList());
        //生成树形结构
        List<SysMenuVo> menuVoList = generateTrees(filterMenuList);
        return menuVoList;
    }

    public List<SysMenu> getAuthMenus(SysMenuDto dto) {
        SysUser sysUser = UserUtil.getCurrentUser();
        if(sysUser==null) {
            return new ArrayList<>();
        }
        dto.setUserId(sysUser.getId());
        List<SysMenu> menuList = null;
        //超管查全部
        if(sysUser.getId().equals(1)){
            menuList = baseMapper.selectList(Wrappers.lambdaQuery(SysMenu.class)
                    .in(SysMenu::getType, Lists.newArrayList(MenuTypeEnum.MENU.getCode(), MenuTypeEnum.CATALOG.getCode()))
            );
        }else {
            menuList = baseMapper.getAuthMenus(dto);
        }
        return menuList;
    }

    /**
     * 根据所有树节点列表，生成含有所有树形结构的列表
     *
     * @param nodes 树形节点列表
     * @return 树形结构列表
     */
    public  List<SysMenuVo> generateTrees(List<SysMenu> nodes) {
        List<SysMenuVo> roots = new ArrayList<>();
        for (Iterator<SysMenu> ite = nodes.iterator(); ite.hasNext(); ) {
            SysMenu node = ite.next();
            if (node.getParentId()==null || node.getParentId()==0) {
                SysMenuVo sysMenuVo = menuToVo(node);
                roots.add(sysMenuVo);
                // 从所有节点列表中删除该节点，以免后续重复遍历该节点
                ite.remove();
            }
        }
        roots.forEach(r -> {
            setChildren(r, nodes);
        });
        return roots;
    }

    public void setChildren(SysMenuVo parent, List<SysMenu> nodes) {
        List<SysMenuVo> children = new ArrayList<>();
        for (Iterator<SysMenu> ite = nodes.iterator(); ite.hasNext(); ) {
            SysMenu node = ite.next();
            if (Objects.equals(node.getParentId(), parent.getId())) {
                SysMenuVo sysMenuVo = menuToVo(node);
                children.add(sysMenuVo);
                // 从所有节点列表中删除该节点，以免后续重复遍历该节点
                ite.remove();
            }
        }
        parent.setChildren(children);
        children.forEach(m -> {
            // 递归设置子节点
            setChildren(m, nodes);
        });
    }

    public RouteVo getMenuRoute() {
        SysUser sysUser = UserUtil.getCurrentUser();
        if(sysUser==null) {
            return new RouteVo();
        }
        SysMenuDto dto = new SysMenuDto();
        dto.setUserId(sysUser.getId());
        List<SysMenu> menuList = this.getAuthMenus(dto);
        //目录菜单
        List<SysMenu> filterMenuList = menuList.stream().filter(item->!MenuTypeEnum.BUTTON.equals(item.getType())).collect(Collectors.toList());
        //权限
        List<String> permList = filterMenuList.stream().map(item->item.getPermission()).collect(Collectors.toList());
        //生成树形结构
        List<MenuRouteVo> treeList = generateRouteTrees(filterMenuList);
        RouteVo vo = new RouteVo();
        vo.setMenuList(treeList);
        vo.setPermList(permList);
        return vo;
    }

    /**
     * 根据所有树节点列表，生成含有所有树形结构的列表
     *
     * @param nodes 树形节点列表
     * @return 树形结构列表
     */
    public  List<MenuRouteVo> generateRouteTrees(List<SysMenu> nodes) {
        List<MenuRouteVo> roots = new ArrayList<>();
        for (Iterator<SysMenu> ite = nodes.iterator(); ite.hasNext(); ) {
            SysMenu node = ite.next();
            if (node.getParentId()==null || node.getParentId()==0) {
                MenuRouteVo dataVo = menuToRoute(node);
                roots.add(dataVo);
                // 从所有节点列表中删除该节点，以免后续重复遍历该节点
                ite.remove();
            }
        }
        roots.forEach(r -> {
            setRouteChildren(r, nodes);
        });
        return roots;
    }

    public void setRouteChildren(MenuRouteVo parent, List<SysMenu> nodes) {
        List<MenuRouteVo> children = new ArrayList<>();
        for (Iterator<SysMenu> ite = nodes.iterator(); ite.hasNext(); ) {
            SysMenu node = ite.next();
            if (Objects.equals(node.getParentId(), parent.getId())) {
                MenuRouteVo dataVo = menuToRoute(node);
                children.add(dataVo);
                // 从所有节点列表中删除该节点，以免后续重复遍历该节点
                ite.remove();
            }
        }
        parent.setChildren(children);
        children.forEach(m -> {
            // 递归设置子节点
            setRouteChildren(m, nodes);
        });
    }

    public SysMenuVo menuToVo(SysMenu sysMenu) {
        SysMenuVo menuVo = new SysMenuVo();
        menuVo.setId(sysMenu.getId());
        menuVo.setParentId(sysMenu.getParentId());
        menuVo.setName(sysMenu.getName());
        menuVo.setPath(sysMenu.getRoute());
        menuVo.setComponent(sysMenu.getUrl());
        menuVo.setType(sysMenu.getType());
        menuVo.setIcon(sysMenu.getIcon());
        menuVo.setSortNo(sysMenu.getSortNo());
        return menuVo;
    }

    public MenuRouteVo menuToRoute(SysMenu sysMenu) {
        MenuRouteVo routeVo = new MenuRouteVo();
        routeVo.setId(sysMenu.getId());
        routeVo.setName(sysMenu.getRoute());
        routeVo.setPath(sysMenu.getRoute());
        routeVo.setComponent(sysMenu.getUrl());
        MenuRouteVo.MenuRouteMeta meta = new MenuRouteVo.MenuRouteMeta();
        meta.setPermission(sysMenu.getPermission());
        meta.setTitle(sysMenu.getName());
        meta.setIcon(sysMenu.getIcon());
        meta.setSortNo(sysMenu.getSortNo());
        routeVo.setMeta(meta);
        return routeVo;
    }
}
