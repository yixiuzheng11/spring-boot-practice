package org.yixz.common.exception;

import lombok.Data;
import org.yixz.common.response.ResponseCode;

/**
 * 描述
 *
 * @author yixiuzheng11
 * @date 2021年07月22日 18:42
 */
@Data
public class BizException extends RuntimeException{
    /**
     * 返回状态码
     */
    private String code;

    /**
     * 返回信息
     */
    private String msg;

    public BizException(String code, String msg) {
        super(msg);
        this.code = code;
        this.msg = msg;
    }

    public BizException(String msg) {
        super(msg);
        this.msg = msg;
    }
}
