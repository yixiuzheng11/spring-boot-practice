package org.yixz.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.yixz.dto.RoleMenuDto;
import org.yixz.entity.RoleMenu;
import org.yixz.mapper.RoleMenuMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
public class RoleMenuServiceImpl extends ServiceImpl<RoleMenuMapper, RoleMenu> {
    public Page<RoleMenu> getPage(RoleMenuDto dto) {
        Page page = new Page(dto.getPageNum(), dto.getPageSize());
        QueryWrapper<RoleMenu> queryWrapper = new QueryWrapper();
        queryWrapper.eq(dto.getRoleId()!=null, "role_id", dto.getRoleId());
        queryWrapper.eq(dto.getMenuId()!=null, "menu_id", dto.getMenuId());
        return baseMapper.selectPage(page, queryWrapper);
    }

    public Integer add(RoleMenuDto dto) {
        RoleMenu user = new RoleMenu();
        BeanUtils.copyProperties(dto, user);
        baseMapper.insert(user);
        return user.getId();
    }

    public void update(RoleMenuDto dto) {
        LambdaUpdateWrapper<RoleMenu> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.set(RoleMenu::getRoleId, dto.getRoleId());
        updateWrapper.set(RoleMenu::getMenuId, dto.getMenuId());
        updateWrapper.eq(RoleMenu::getId, dto.getId());
        //这里传个new出来的user对象，确保FieldFill.INSERT_UPDATE属性在更新时自动填充值
        baseMapper.update(new RoleMenu(), updateWrapper);
    }

    public void delete(Integer id) {
        baseMapper.deleteById(id);
    }
}
