INSERT INTO admin.sys_dictionary (id,create_date,create_user_id,create_user_name,update_date,update_user_id,update_user_name,version,data_key,data_value,describes,show_value,sort) VALUES 
('dic_0001',NULL,NULL,NULL,'2017-09-25',NULL,NULL,2,'STATUS','ENABLE','状态','启用',0)
,('dic_0002',NULL,NULL,NULL,'2017-09-25',NULL,NULL,2,'STATUS','DISABLE','状态','禁用',1)
,('dic_0005',NULL,NULL,NULL,NULL,NULL,NULL,0,'YES_OR_NO','YES','是或否','是',0)
,('dic_0006',NULL,NULL,NULL,NULL,NULL,NULL,0,'YES_OR_NO','NO','是或否','否',1)
,('dic_0007','2017-09-25','user_000001','admin',NULL,NULL,NULL,0,'OPERATION_TYPE','DEL','操作类型','删除',0)
,('dic_0008','2017-09-25','user_000001','admin',NULL,NULL,NULL,0,'OPERATION_TYPE','SAVE','操作类型','添加',1)
,('dic_0009','2017-09-25','user_000001','admin','2017-09-25','user_000001',NULL,1,'OPERATION_TYPE','UPDATE','操作类型','修改',2)
,('dic_0010','2017-09-25','user_000001','admin',NULL,NULL,NULL,0,'OPERATION_TYPE','SEARCH','操作类型','查询',3)
,('dic_0011','2017-09-25','user_000001','admin',NULL,NULL,NULL,0,'OPERATION_TYPE','OTHER','操作类型','其他',4)
,('dic_0012','2017-09-25','user_000001','admin',NULL,NULL,NULL,0,'RESULT_TYPE','SUCCESS','结果','成功',0)
;
INSERT INTO admin.sys_dictionary (id,create_date,create_user_id,create_user_name,update_date,update_user_id,update_user_name,version,data_key,data_value,describes,show_value,sort) VALUES 
('dic_0013','2017-09-25','user_000001','admin',NULL,NULL,NULL,0,'RESULT_TYPE','FAIL','结果','失败',1)
;