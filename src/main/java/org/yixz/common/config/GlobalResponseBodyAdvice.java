package org.yixz.common.config;

import com.alibaba.fastjson.JSON;
import org.yixz.common.annotation.IgnoreRestBody;
import org.yixz.common.response.ResponseCode;
import org.yixz.common.response.ResponseResult;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;
import java.util.HashMap;
import java.util.Map;

/**
 * 描述
 *
 * @author YIXIUZHENG741
 * @date 2021年07月22日 16:38
 */
@RestControllerAdvice("org.yixz.controller")
public class GlobalResponseBodyAdvice implements ResponseBodyAdvice<Object> {

    /**
     * 使用了@IgnoreRestBody注解
     */
    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
        //return AnnotatedElementUtils.hasAnnotation(returnType.getContainingClass(), ANNOTATION_TYPE) || returnType.hasMethodAnnotation(ANNOTATION_TYPE);
        return !returnType.hasMethodAnnotation(IgnoreRestBody.class);
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        // 防止重复包裹的问题出现
        if (body instanceof ResponseResult) {
            return body;
        }
        if(body instanceof String) {
            Map<String, Object> map = new HashMap<>();
            map.put("code", ResponseCode.SUCCESS.code);
            map.put("msg", ResponseCode.SUCCESS.msg);
            map.put("data", body);
            return JSON.toJSONString(map);
        }
        return ResponseResult.success(body);
    }
}
