package org.yixz.service;

import com.alibaba.druid.wall.WallProvider;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.yixz.common.enums.MenuTypeEnum;
import org.yixz.common.util.UserUtil;
import org.yixz.entity.dto.SysDeptDto;
import org.yixz.entity.mysql.SysDept;
import org.yixz.entity.mysql.SysUser;
import org.yixz.entity.vo.MenuRouteVo;
import org.yixz.entity.vo.RouteVo;
import org.yixz.entity.vo.SysDeptVo;
import org.yixz.mapper.SysDeptMapper;

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
public class SysDeptService extends ServiceImpl<SysDeptMapper, SysDept> {
    public Integer add(SysDeptDto dto) {
        SysDept user = new SysDept();
        BeanUtils.copyProperties(dto, user);
        baseMapper.insert(user);
        return user.getId();
    }

    public void update(SysDeptDto dto) {
        LambdaUpdateWrapper<SysDept> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.set(SysDept::getParentId, dto.getParentId());
        updateWrapper.set(SysDept::getName, dto.getName());
        updateWrapper.eq(SysDept::getId, dto.getId());
        //这里传个new出来的user对象，确保FieldFill.INSERT_UPDATE属性在更新时自动填充值
        baseMapper.update(new SysDept(), updateWrapper);
    }

    public void delete(Integer id) {
        baseMapper.deleteById(id);
    }

    /**
     * 获取导航菜单
     * @return
     */
    public List<SysDeptVo> getDeptList(SysDeptDto dto) {
        List<SysDept> menuList = baseMapper.selectList(Wrappers.lambdaQuery(SysDept.class));
        //生成树形结构
        List<SysDeptVo> menuVoList = generateTrees(menuList);
        return menuVoList;
    }

    /**
     * 根据所有树节点列表，生成含有所有树形结构的列表
     *
     * @param nodes 树形节点列表
     * @return 树形结构列表
     */
    public  List<SysDeptVo> generateTrees(List<SysDept> nodes) {
        List<SysDeptVo> roots = new ArrayList<>();
        for (Iterator<SysDept> ite = nodes.iterator(); ite.hasNext(); ) {
            SysDept node = ite.next();
            if (node.getParentId()==null || node.getParentId()==0) {
                SysDeptVo SysDeptVo = deptToVo(node);
                roots.add(SysDeptVo);
                // 从所有节点列表中删除该节点，以免后续重复遍历该节点
                ite.remove();
            }
        }
        roots.forEach(r -> {
            setChildren(r, nodes);
        });
        return roots;
    }

    public void setChildren(SysDeptVo parent, List<SysDept> nodes) {
        List<SysDeptVo> children = new ArrayList<>();
        for (Iterator<SysDept> ite = nodes.iterator(); ite.hasNext(); ) {
            SysDept node = ite.next();
            if (Objects.equals(node.getParentId(), parent.getId())) {
                SysDeptVo SysDeptVo = deptToVo(node);
                children.add(SysDeptVo);
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

    public SysDeptVo deptToVo(SysDept SysDept) {
        SysDeptVo deptVo = new SysDeptVo();
        deptVo.setId(SysDept.getId());
        deptVo.setParentId(SysDept.getParentId());
        deptVo.setName(SysDept.getName());
        deptVo.setStatus(SysDept.getStatus());
        deptVo.setSortNo(SysDept.getSortNo());
        return deptVo;
    }
}
