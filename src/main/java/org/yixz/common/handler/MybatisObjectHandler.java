package org.yixz.common.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
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
        setFieldValByName("createdBy", "admin", metaObject);
        setFieldValByName("updatedBy", "admin", metaObject);
        setFieldValByName("createdDate", LocalDateTime.now(), metaObject);
        setFieldValByName("updatedDate",LocalDateTime.now(),metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        setFieldValByName("updatedBy", "admin", metaObject);
        setFieldValByName("updatedDate",LocalDateTime.now(),metaObject);
    }
}
