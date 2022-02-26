package org.yixz.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.yixz.dto.UserRoleDto;
import org.yixz.entity.UserRole;
import org.yixz.mapper.UserRoleMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户角色表 服务实现类
 * </p>
 *
 * @author yixz
 * @since 2021-12-22
 */
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> {
    public Page<UserRole> getPage(UserRoleDto dto) {
        Page page = new Page(dto.getPageNum(), dto.getPageSize());
        QueryWrapper<UserRole> queryWrapper = new QueryWrapper();
        queryWrapper.eq(dto.getUserId()!=null, "user_id", dto.getUserId());
        queryWrapper.eq(dto.getRoleId()!=null, "role_id", dto.getRoleId());
        return baseMapper.selectPage(page, queryWrapper);
    }

    public Integer add(UserRoleDto dto) {
        UserRole user = new UserRole();
        BeanUtils.copyProperties(dto, user);
        baseMapper.insert(user);
        return user.getId();
    }

    public void update(UserRoleDto dto) {
        LambdaUpdateWrapper<UserRole> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.set(UserRole::getUserId, dto.getUserId());
        updateWrapper.set(UserRole::getRoleId, dto.getRoleId());
        updateWrapper.eq(UserRole::getId, dto.getId());
        //这里传个new出来的user对象，确保FieldFill.INSERT_UPDATE属性在更新时自动填充值
        baseMapper.update(new UserRole(), updateWrapper);
    }

    public void delete(Integer id) {
        baseMapper.deleteById(id);
    }
}
