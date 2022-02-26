package org.yixz.mapper;

import org.yixz.entity.Menu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.yixz.vo.MenuVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author yixz
 * @since 2021-12-22
 */
public interface MenuMapper extends BaseMapper<Menu> {
    List<MenuVo> getMyMenu(@Param("userId") Integer userId);
}
