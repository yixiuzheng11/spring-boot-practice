package org.yixz.common.enums;

/**
 * @author yixiuzheng11
 * @date 2021年12月22日 14:46
 */
public enum MenuTypeEnum {
    DIR_TYPE("node", "目录"),
    FUNC_TYPE("menu", "菜单"),
    BTN_TYPE("btn", "按钮");

    private MenuTypeEnum(String code, String text) {
        this.code = code;
        this.text = text;
    }

    private String code;

    private String text;

    public String getCode() {
        return code;
    }

    public String getText() {
        return text;
    }
}
