package org.yixz.common.annotation;

import org.springframework.web.bind.annotation.ResponseBody;
import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
@ResponseBody
public @interface IgnoreRestBody {

}
