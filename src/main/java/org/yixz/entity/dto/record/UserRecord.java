package org.yixz.entity.dto.record;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

@Data
public class UserRecord {
    /**
     * 用户名
     */
    @Excel(name = "用户名")
    private String userName;

    /**
     * 用户姓名
     */
    @Excel(name = "用户姓名")
    private String fullName;

}
