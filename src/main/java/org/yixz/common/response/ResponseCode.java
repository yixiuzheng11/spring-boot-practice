package org.yixz.common.response;

/**
 * 返回状态码
 */
public enum ResponseCode {
    /**
     * 成功返回的状态码
     */
    SUCCESS("SUCCESS", "操作成功"),

    ERROR("ERROR", "操作失败"),

    DATA_TOO_MUSH("DATA_TOO_MUSH", "时间区间过大, 建议线下处理"),

    TOO_MANY_REQUEST("TOO_MANY_REQUEST", "系统繁忙,请稍后再试"),

    UNAUTHORIZED("UNAUTHORIZED", "请求未认证"),

    ILLEGAL_ARGUMENT("ILLEGAL_ARGUMENT", "无效的参数"),

    FORBIDDEN("FORBIDDEN", "请求未授权"),

    SYS_FORBIDDEN("SYS_FORBIDDEN", "系统未授权"),

    NOT_FOUND("NOT_FOUND", "找不到请求"),

    EXPIRED("REQUEST_ERROR", "请求已失效"),

    WRONG_IMAGE_CODE("WRONG_IMAGE_CODE", "验证码错误"),

    NEED_RAND_CODE("NEED_RAND_CODE", "请输入验证码"),

    SERVER_ERROR("SERVER_ERROR", "服务异常，请联系管理员"),

    WRONG_PASSWORD_OR_USERNAME("WRONG_PASSWORD_OR_USERNAME", "用户或密码错误"),

    EXCEL_IMPORT_ERROR("EXCEL_IMPORT_ERROR","导入数据失败"),

    GRAPH_HAS_CYCLE("GRAPH_HAS_CYCLE","构建流动图失败,线路已成环");

    /**
     * 状态码
     */
    public final String code;
    /**
     * 返回信息
     */
    public final String msg;

    ResponseCode(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}