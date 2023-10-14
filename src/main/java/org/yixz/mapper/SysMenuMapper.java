package org.yixz.mapper;

import org.apache.ibatis.annotations.Param;
import org.yixz.entity.mysql.SysMenu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.yixz.entity.vo.SysMenuVo;

import java.util.List;

/**
 * <p>
 * 菜单表 Mapper 接口
 * </p>
 *
 * @author yixz
 * @since 2022-09-24
 */
public interface SysMenuMapper extends BaseMapper<SysMenu> {

    List<SysMenuVo> getAuthMenu(@Param("userName") String userName);
}
