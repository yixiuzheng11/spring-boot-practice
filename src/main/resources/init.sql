INSERT INTO sys_menu (parent_id,name,`type`,route,url,icon,permission,sort_no,created_by,updated_by,created_date,updated_date) VALUES
       (0,'组件封装','CATALOG','/component','Layout','menu',NULL,1,'','',sysdate(),sysdate()),
       ((select bc.id from sys_menu bc where bc.name ='组件封装'),'增删改查','MENU','curd','demo/curd/index','',NULL,1,'','',sysdate(),sysdate()),
       ((select bc.id from sys_menu bc where bc.name ='组件封装'),'列表选择器','MENU','tableSelect','demo/table-select/index','',NULL,2,'','',sysdate(),sysdate()),
       ((select bc.id from sys_menu bc where bc.name ='组件封装'),'富文本编辑器','MENU','wangEditor','demo/wang-editor','',NULL,3,'','',sysdate(),sysdate()),
       ((select bc.id from sys_menu bc where bc.name ='组件封装'),'图片上传','MENU','upload','demo/upload','',NULL,4,'','',sysdate(),sysdate()),
       ((select bc.id from sys_menu bc where bc.name ='组件封装'),'图标选择器','MENU','iconSelector','demo/icon-selector','',NULL,5,'','',sysdate(),sysdate()),
       ((select bc.id from sys_menu bc where bc.name ='组件封装'),'字典组件','MENU','dictDemo','demo/dict','',NULL,6,'','',sysdate(),sysdate());

INSERT INTO sys_menu (parent_id,name,`type`,route,url,icon,permission,sort_no,created_by,updated_by,created_date,updated_date) VALUES
       (0,'功能演示','CATALOG','/function','Layout','menu',NULL,2,'','',sysdate(),sysdate()),
       ((select bc.id from sys_menu bc where bc.name ='功能演示'),'icons','MENU','iconDemo','demo/icons','el-icon-Notification',NULL,1,'','',sysdate(),sysdate()),
       ((select bc.id from sys_menu bc where bc.name ='功能演示'),'websocket','MENU','websocketDemo','demo/websocket','',NULL,2,'','',sysdate(),sysdate());

INSERT INTO sys_menu (parent_id,name,`type`,route,url,icon,permission,sort_no,created_by,updated_by,created_date,updated_date) VALUES
       (0,'系统管理','CATALOG','/system','Layout','system',NULL,3,'','',sysdate(),sysdate()),
       ((select bc.id from sys_menu bc where bc.name ='系统管理'),'用户管理','MENU','user','system/user/index','user',NULL,1,'','',sysdate(),sysdate()),
       ((select bc.id from sys_menu bc where bc.name ='系统管理'),'角色管理','MENU','role','system/role/index','role',NULL,2,'','',sysdate(),sysdate()),
       ((select bc.id from sys_menu bc where bc.name ='系统管理'),'菜单管理','MENU','menu','system/menu/index','menu',NULL,3,'','',sysdate(),sysdate()),
       ((select bc.id from sys_menu bc where bc.name ='系统管理'),'部门管理','MENU','dept','system/dept/index','tree',NULL,4,'','',sysdate(),sysdate()),
       ((select bc.id from sys_menu bc where bc.name ='系统管理'),'字典管理','MENU','dict','system/dict/index','dict',NULL,5,'','',sysdate(),sysdate());

INSERT INTO sys_dept (parent_id,name,status,sort_no,created_by,updated_by,created_date,updated_date) VALUES
     (0,'集团',1,1,'','',sysdate(),sysdate()),
     (1,'运维部门',1,1,'','',sysdate(),sysdate()),
     (1,'研发部门',1,2,'','',sysdate(),sysdate()),
     (1,'测试部门',1,3,'','',sysdate(),sysdate());

insert into sys_role(id, role_code, role_name)
values(1, 'admin','超级管理员');

INSERT INTO sys_user (id, user_name,full_name,password) VALUES
    (1,'admin','管理员','NYOIgVIztlZnnMaGzWnlfw==');

insert into sys_user_role(user_id, role_id)
values(1,1);