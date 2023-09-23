package org.yixz.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.yixz.entity.dto.SysRoleMenuDto;
import org.yixz.entity.mysql.SysRoleMenu;
import org.yixz.mapper.SysRoleMenuMapper;
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
public class SysRoleMenuService extends ServiceImpl<SysRoleMenuMapper, SysRoleMenu> {
    public Page<SysRoleMenu> getPage(SysRoleMenuDto dto) {
        Page page = new Page(dto.getPageNum(), dto.getPageSize());
        QueryWrapper<SysRoleMenu> queryWrapper = new QueryWrapper();
        queryWrapper.eq(dto.getRoleId()!=null, "role_id", dto.getRoleId());
        queryWrapper.eq(dto.getMenuId()!=null, "menu_id", dto.getMenuId());
        return baseMapper.selectPage(page, queryWrapper);
    }

    public Integer add(SysRoleMenuDto dto) {
        SysRoleMenu user = new SysRoleMenu();
        BeanUtils.copyProperties(dto, user);
        baseMapper.insert(user);
        return user.getId();
    }

    public void update(SysRoleMenuDto dto) {
        LambdaUpdateWrapper<SysRoleMenu> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.set(SysRoleMenu::getRoleId, dto.getRoleId());
        updateWrapper.set(SysRoleMenu::getMenuId, dto.getMenuId());
        updateWrapper.eq(SysRoleMenu::getId, dto.getId());
        //这里传个new出来的user对象，确保FieldFill.INSERT_UPDATE属性在更新时自动填充值
        baseMapper.update(new SysRoleMenu(), updateWrapper);
    }

    public void delete(Integer id) {
        baseMapper.deleteById(id);
    }
}
