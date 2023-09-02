package org.yixz.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.yixz.dto.SysMenuDto;
import org.yixz.entity.SysMenu;
import org.yixz.mapper.SysMenuMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

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
        queryWrapper.like(StringUtils.isNotEmpty(dto.getMenuName()), SysMenu::getMenuName, dto.getMenuName());
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
        updateWrapper.set(SysMenu::getMenuName, dto.getMenuName());
        updateWrapper.set(SysMenu::getUrl, dto.getUrl());
        updateWrapper.eq(SysMenu::getId, dto.getId());
        //这里传个new出来的user对象，确保FieldFill.INSERT_UPDATE属性在更新时自动填充值
        baseMapper.update(new SysMenu(), updateWrapper);
    }

    public void delete(Integer id) {
        baseMapper.deleteById(id);
    }
}
