package org.yixz.common.handler;

import cn.dev33.satoken.exception.NotLoginException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.yixz.common.exception.BizException;
import org.yixz.common.response.ResponseCode;
import org.yixz.common.response.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import javax.validation.ConstraintViolationException;

/**
 * 全局异常处理
 *
 * @author yixiuzheng11
 * @date 2021年07月22日 18:37
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    /**
     * 处理自定义的业务异常
     * @param e
     * @return
     */
    @ExceptionHandler(value = BizException.class)
    public ResponseResult bizExceptionHandler(Exception e){
        log.error("业务异常", e);
        return ResponseResult.error(ResponseCode.BUSINESS_ERROR.code, e.getMessage());
    }

    /**
     * 未登录的异常
     * @param e
     * @return
     */
    @ExceptionHandler(value = NotLoginException.class)
    public ResponseResult notLoginExceptionHandler(Exception e){
        log.error("请求未认证", e);
        return ResponseResult.error(ResponseCode.UNAUTHORIZED.code, ResponseCode.UNAUTHORIZED.msg);
    }

    /**
     * 参数异常 -- ConstraintViolationException()
     * 用于处理类似http://localhost:8080/user/getUser?age=30&name=yoyo请求中age和name的校验引发的异常
     * @param e
     * @return
     */
    @ExceptionHandler(value = ConstraintViolationException.class)
    public ResponseResult urlParametersExceptionHandle(Exception e){
        log.error("请求参数异常", e);
        return ResponseResult.error(ResponseCode.ILLEGAL_ARGUMENT.code, ResponseCode.ILLEGAL_ARGUMENT.msg);
    }

    /***
     * 参数异常 --- MethodArgumentNotValidException和BindException
     * MethodArgumentNotValidException --- 用于处理请求参数为实体类时校验引发的异常 --- Content-Type为application/json
     * BindException --- 用于处理请求参数为实体类时校验引发的异常  --- Content-Type为application/x-www-form-urlencoded
     * @param e
     * @return
     */
    @ExceptionHandler(value = {MethodArgumentNotValidException.class, BindException.class})
    public ResponseResult bodyExceptionHandle(Exception e) {
        log.error("请求参数异常", e);
        return ResponseResult.error(ResponseCode.ILLEGAL_ARGUMENT.code, ResponseCode.ILLEGAL_ARGUMENT.msg);
    }

    /**
     * 处理其他异常
     * @param e
     * @return
     */
    @ExceptionHandler(value = Exception.class)
    public ResponseResult exceptionHandler(Exception e){
        log.error("操作失败", e);
        return ResponseResult.error(ResponseCode.ERROR.code, ResponseCode.ERROR.msg);
    }
}
