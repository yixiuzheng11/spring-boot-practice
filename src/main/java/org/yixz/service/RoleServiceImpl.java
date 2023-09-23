package org.yixz.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.yixz.entity.dto.SysRoleDto;
import org.yixz.entity.mysql.SysRole;
import org.yixz.mapper.SysRoleMapper;
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
public class RoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> {
    public Page<SysRole> getPage(SysRoleDto dto) {
        Page page = new Page(dto.getPageNum(), dto.getPageSize());
        QueryWrapper<SysRole> queryWrapper = new QueryWrapper();
        queryWrapper.eq(StringUtils.isNotEmpty(dto.getRoleCode()), "role_code", dto.getRoleCode());
        queryWrapper.eq(StringUtils.isNotEmpty(dto.getRoleName()), "role_name", dto.getRoleName());
        return baseMapper.selectPage(page, queryWrapper);
    }

    public Integer add(SysRoleDto dto) {
        SysRole user = new SysRole();
        BeanUtils.copyProperties(dto, user);
        baseMapper.insert(user);
        return user.getId();
    }

    public void update(SysRoleDto dto) {
        LambdaUpdateWrapper<SysRole> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.set(SysRole::getRoleName, dto.getRoleName());
        updateWrapper.eq(SysRole::getId, dto.getId());
        //这里传个new出来的user对象，确保FieldFill.INSERT_UPDATE属性在更新时自动填充值
        baseMapper.update(new SysRole(), updateWrapper);
    }

    public void delete(Integer id) {
        baseMapper.deleteById(id);
    }
}
