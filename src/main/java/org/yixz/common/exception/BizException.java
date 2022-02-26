package org.yixz.common.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 描述
 *
 * @author YIXIUZHENG741
 * @date 2021年07月22日 18:42
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BizException extends RuntimeException{
    /**
     * 返回状态码
     */
    private String code;

    /**
     * 返回信息
     */
    private String msg;
}
