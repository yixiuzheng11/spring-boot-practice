package org.yixz.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.yixz.dto.RoleDto;
import org.yixz.entity.Role;
import org.yixz.mapper.RoleMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 角色表 服务实现类
 * </p>
 *
 * @author yixz
 * @since 2021-11-26
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> {
    public Page<Role> getPage(RoleDto dto) {
        Page page = new Page(dto.getPageNum(), dto.getPageSize());
        QueryWrapper<Role> queryWrapper = new QueryWrapper();
        queryWrapper.eq(StringUtils.isNotEmpty(dto.getRoleCode()), "role_code", dto.getRoleCode());
        queryWrapper.eq(StringUtils.isNotEmpty(dto.getRoleName()), "role_name", dto.getRoleName());
        return baseMapper.selectPage(page, queryWrapper);
    }

    public Integer add(RoleDto dto) {
        Role user = new Role();
        BeanUtils.copyProperties(dto, user);
        baseMapper.insert(user);
        return user.getId();
    }

    public void update(RoleDto dto) {
        LambdaUpdateWrapper<Role> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.set(Role::getRoleCode, dto.getRoleCode());
        updateWrapper.set(Role::getRoleName, dto.getRoleName());
        updateWrapper.eq(Role::getId, dto.getId());
        //这里传个new出来的user对象，确保FieldFill.INSERT_UPDATE属性在更新时自动填充值
        baseMapper.update(new Role(), updateWrapper);
    }

    public void delete(Integer id) {
        baseMapper.deleteById(id);
    }
}
