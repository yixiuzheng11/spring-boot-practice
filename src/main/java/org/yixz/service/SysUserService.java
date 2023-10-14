package org.yixz.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.yixz.entity.dto.SysUserDto;
import org.yixz.entity.mysql.SysUser;
import org.yixz.mapper.SysUserMapper;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author yixz
 * @since 2021-11-26
 */
@Service
public class SysUserService extends ServiceImpl<SysUserMapper, SysUser>{
    public Page<SysUser> getPage(SysUserDto dto) {
        Page page = new Page(dto.getPageNum(), dto.getPageSize());
        QueryWrapper<SysUser> queryWrapper = new QueryWrapper();
        queryWrapper.select("id, user_name, full_name, created_date, created_by");
        queryWrapper.eq(StringUtils.isNotEmpty(dto.getUserName()), "user_name", dto.getUserName());
        queryWrapper.like(StringUtils.isNotEmpty(dto.getFullName()), "full_name", dto.getFullName());
        return baseMapper.selectPage(page, queryWrapper);
    }

    public Integer add(SysUserDto dto) {
        SysUser user = new SysUser();
        BeanUtils.copyProperties(dto, user);
        baseMapper.insert(user);
        return user.getId();
    }

    public void update(SysUserDto dto) {
        LambdaUpdateWrapper<SysUser> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.set(SysUser::getUserName, dto.getUserName());
        updateWrapper.set(SysUser::getFullName, dto.getFullName());
        updateWrapper.eq(SysUser::getId, dto.getId());
        //这里传个new出来的user对象，确保FieldFill.INSERT_UPDATE属性在更新时自动填充值
        baseMapper.update(new SysUser(), updateWrapper);
    }

    public void delete(Integer id) {
        baseMapper.deleteById(id);
    }

    public SysUser getByUserName(String userName) {
        return this.getOne(Wrappers.lambdaQuery(SysUser.class).eq(SysUser::getUserName, userName).last("limit 1"));
    }
}
