package org.yixz.enums;

/**
 * @author YIXIUZHENG741
 * @date 2021年12月22日 14:46
 */
public enum MenuTypeEnum {
    DIR_TYPE(1, "目录"),
    FUNC_TYPE(2, "菜单"),
    BTN_TYPE(3, "按钮");

    private MenuTypeEnum(Integer code, String text) {
        this.code = code;
        this.text = text;
    }

    private Integer code;

    private String text;

    public Integer getCode() {
        return code;
    }

    public String getText() {
        return text;
    }
}
