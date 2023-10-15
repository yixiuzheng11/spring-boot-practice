package org.yixz.service;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.yixz.common.enums.MenuTypeEnum;
import org.yixz.entity.dto.SysMenuDto;
import org.yixz.entity.mysql.SysMenu;
import org.yixz.entity.vo.NavVo;
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
        queryWrapper.eq(StringUtils.isNotEmpty(dto.getMenuType()), SysMenu::getMenuType, dto.getMenuType());
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
    public NavVo getNav() {
        Integer userId = Integer.parseInt(StpUtil.getLoginId().toString());
        if(userId==null) {
            return new NavVo();
        }
        List<SysMenuVo> menuVoList = baseMapper.getAuthMenu(userId);
        //目录菜单
        List<SysMenuVo> menuList = menuVoList.stream().filter(item->!MenuTypeEnum.BTN_TYPE.equals(item.getMenuType())).collect(Collectors.toList());
        //按钮
        List<String> btnList = menuVoList.stream().filter(item->MenuTypeEnum.BTN_TYPE.equals(item.getMenuType())).map(item->item.getPerm()).collect(Collectors.toList());
        //生成树形结构
        //List<SysMenuVo> treeList = generateTrees(menuList);
        NavVo navVo = new NavVo();
        navVo.setMenuList(menuList);
        navVo.setPermList(btnList);
        return navVo;
    }

    /**
     * 根据所有树节点列表，生成含有所有树形结构的列表
     *
     * @param nodes 树形节点列表
     * @return 树形结构列表
     */
    public  List<SysMenuVo> generateTrees(List<SysMenuVo> nodes) {
        List<SysMenuVo> roots = new ArrayList<>();
        for (Iterator<SysMenuVo> ite = nodes.iterator(); ite.hasNext(); ) {
            SysMenuVo node = ite.next();
            if (node.getParentId()==null || node.getParentId()==0) {
                roots.add(node);
                // 从所有节点列表中删除该节点，以免后续重复遍历该节点
                ite.remove();
            }
        }

        roots.forEach(r -> {
            setChildren(r, nodes);
        });
        return roots;
    }

    public void setChildren(SysMenuVo parent, List<SysMenuVo> nodes) {
        List<SysMenuVo> children = new ArrayList<>();
        for (Iterator<SysMenuVo> ite = nodes.iterator(); ite.hasNext(); ) {
            SysMenuVo node = ite.next();
            if (Objects.equals(node.getParentId(), parent.getId())) {
                children.add(node);
                // 从所有节点列表中删除该节点，以免后续重复遍历该节点
                ite.remove();
            }
        }
        // 如果孩子为空，则直接返回,否则继续递归设置孩子的孩子
        if (children.isEmpty()) {
            return;
        }
        parent.setChildren(children);
        children.forEach(m -> {
            // 递归设置子节点
            setChildren(m, nodes);
        });
    }
}
