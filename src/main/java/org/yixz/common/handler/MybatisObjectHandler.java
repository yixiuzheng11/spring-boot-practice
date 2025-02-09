package org.yixz.common.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.yixz.common.util.UserUtil;
import org.yixz.entity.mysql.SysUser;
import org.yixz.service.SysUserRoleService;

import java.time.LocalDateTime;

/**
 * 描述
 *
 * @author yixiuzheng11
 * @date 2021年12月17日 16:27
 */
public class MybatisObjectHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        SysUser sysUser = UserUtil.getCurrentUser();
        setFieldValByName("createdBy", sysUser.getId(), metaObject);
        setFieldValByName("updatedBy", sysUser.getId(), metaObject);
        //setFieldValByName("createdDate", LocalDateTime.now(), metaObject);
        //setFieldValByName("updatedDate",LocalDateTime.now(),metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        SysUser sysUser = UserUtil.getCurrentUser();
        setFieldValByName("updatedBy", sysUser.getId(), metaObject);
        //setFieldValByName("updatedDate",LocalDateTime.now(),metaObject);
    }
}
