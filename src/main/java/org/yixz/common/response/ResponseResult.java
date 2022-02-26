package org.yixz.common.response;

import lombok.Builder;
import lombok.Data;
import java.io.Serializable;

/**
 * 描述
 *
 * @author YIXIUZHENG741
 * @date 2021年07月22日 16:21
 */
@Data
@Builder
public class ResponseResult implements Serializable {
    private static final long serialVersionUID = -1497068160063504869L;
    /**
     * 返回状态码
     */
    private String code;
    /**
     * 返回信息
     */
    private String msg;
    /**
     * 数据
     */
    private Object data = null;

    public static ResponseResult success(Object data) {
        return ResponseResult.builder().code(ResponseCode.SUCCESS.code).msg(ResponseCode.SUCCESS.msg).data(data).build();
    }

    public static ResponseResult error(String code, String msg) {
        return ResponseResult.builder().code(code).msg(msg).build();
    }
}