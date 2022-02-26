package org.yixz.mapper;

import org.yixz.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.yixz.vo.UserRolePermVo;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author yixz
 * @since 2021-11-26
 */
public interface UserMapper extends BaseMapper<User> {
    List<UserRolePermVo> getUserPermByUserName(@Param("userName") String userName, @Param("menuType") Integer menuType);
}
