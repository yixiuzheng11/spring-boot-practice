drop table if exists sys_role;
CREATE TABLE `sys_role` (
    `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
    `role_code` varchar(50) DEFAULT '0' COMMENT '角色编码',
    `role_name` varchar(50) NOT NULL COMMENT '角色名称',
    `status` tinyint NOT NULL DEFAULT '1' COMMENT '状态，1-有效，0-无效',
    `created_by` varchar(100) NOT NULL DEFAULT '' COMMENT '提交人员',
    `updated_by` varchar(100) NOT NULL DEFAULT '' COMMENT '修改人员',
    `created_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updated_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='角色表';

drop table if exists sys_dept;
CREATE TABLE `sys_dept` (
    `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
    `parent_id` int DEFAULT '0' COMMENT '父id',
    `name` varchar(50) NOT NULL COMMENT '部门名称',
    `status` tinyint NOT NULL DEFAULT '1' COMMENT '状态，1-有效，0-无效',
    `sort_no` int NOT NULL DEFAULT '0' COMMENT '排序',
    `created_by` varchar(100) NOT NULL DEFAULT '' COMMENT '提交人员',
    `updated_by` varchar(100) NOT NULL DEFAULT '' COMMENT '修改人员',
    `created_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updated_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='部门表';