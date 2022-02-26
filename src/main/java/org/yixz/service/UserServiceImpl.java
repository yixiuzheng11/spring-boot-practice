package org.yixz.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.yixz.dto.UserDto;
import org.yixz.entity.User;
import org.yixz.mapper.UserMapper;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author yixz
 * @since 2021-11-26
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>{
    public Page<User> getPage(UserDto dto) {
        Page page = new Page(dto.getPageNum(), dto.getPageSize());
        QueryWrapper<User> queryWrapper = new QueryWrapper();
        queryWrapper.select("id, user_name, full_name, created_date, created_by");
        queryWrapper.eq(StringUtils.isNotEmpty(dto.getUserName()), "user_name", dto.getUserName());
        queryWrapper.like(StringUtils.isNotEmpty(dto.getFullName()), "full_name", dto.getFullName());
        return baseMapper.selectPage(page, queryWrapper);
    }

    public Integer add(UserDto dto) {
        User user = new User();
        BeanUtils.copyProperties(dto, user);
        baseMapper.insert(user);
        return user.getId();
    }

    public void update(UserDto dto) {
        LambdaUpdateWrapper<User> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.set(User::getUserName, dto.getUserName());
        updateWrapper.set(User::getFullName, dto.getFullName());
        updateWrapper.eq(User::getId, dto.getId());
        //这里传个new出来的user对象，确保FieldFill.INSERT_UPDATE属性在更新时自动填充值
        baseMapper.update(new User(), updateWrapper);
    }

    public void delete(Integer id) {
        baseMapper.deleteById(id);
    }
}
