package org.yixz.common.handler;

import org.yixz.common.exception.BizException;
import org.yixz.common.response.ResponseCode;
import org.yixz.common.response.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import javax.servlet.http.HttpServletRequest;

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
     * @param req
     * @param e
     * @return
     */
    @ExceptionHandler(value = BizException.class)
    public ResponseResult bizExceptionHandler(HttpServletRequest req, BizException e){
        log.error("业务异常", e);
        return ResponseResult.error(e.getCode(), e.getMsg());
    }

    /**
     * 处理空指针的异常
     * @param req
     * @param e
     * @return
     */
    @ExceptionHandler(value = NullPointerException.class)
    public ResponseResult exceptionHandler(HttpServletRequest req, NullPointerException e){
        log.error("空指针异常", e);
        return ResponseResult.error(ResponseCode.ERROR.code, "空指针异常");
    }

    /**
     * 处理其他异常
     * @param req
     * @param e
     * @return
     */
    @ExceptionHandler(value = Exception.class)
    public ResponseResult exceptionHandler(HttpServletRequest req, Exception e){
        log.error("系统异常", e);
        return ResponseResult.error(ResponseCode.ERROR.code, "系统异常");
    }
}
