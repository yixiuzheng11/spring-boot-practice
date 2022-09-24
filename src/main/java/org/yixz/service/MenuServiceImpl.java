package org.yixz.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.yixz.dto.MenuDto;
import org.yixz.entity.Menu;
import org.yixz.enums.MenuTypeEnum;
import org.yixz.mapper.MenuMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.yixz.vo.MenuVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
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
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> {

    public Page<Menu> getPage(MenuDto dto) {
        Page page = new Page(dto.getPageNum(), dto.getPageSize());
        QueryWrapper<Menu> queryWrapper = new QueryWrapper();
        queryWrapper.eq(dto.getParentId()!=null, "parent_id", dto.getParentId());
        queryWrapper.eq(StringUtils.isNotEmpty(dto.getUrl()), "url", dto.getUrl());
        queryWrapper.eq(StringUtils.isNotEmpty(dto.getType()), "type", dto.getType());
        queryWrapper.like(StringUtils.isNotEmpty(dto.getMenuName()), "name", dto.getMenuName());
        return baseMapper.selectPage(page, queryWrapper);
    }

    public Integer add(MenuDto dto) {
        Menu user = new Menu();
        BeanUtils.copyProperties(dto, user);
        baseMapper.insert(user);
        return user.getId();
    }

    public void update(MenuDto dto) {
        LambdaUpdateWrapper<Menu> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.set(Menu::getParentId, dto.getParentId());
        updateWrapper.set(Menu::getMenuName, dto.getMenuName());
        updateWrapper.set(Menu::getUrl, dto.getUrl());
        updateWrapper.eq(Menu::getId, dto.getId());
        //这里传个new出来的user对象，确保FieldFill.INSERT_UPDATE属性在更新时自动填充值
        baseMapper.update(new Menu(), updateWrapper);
    }

    public void delete(Integer id) {
        baseMapper.deleteById(id);
    }
}
