insert into `smart_house_test`.`user`
(`id`, `login`, `name`, `password`, `role`)
VALUES (1, 'login', 'vitaly', 'password', 'user');
insert into `smart_house_test`.`device`
(`id`, `ipAddress`, `location`, `name`, `serialNumber`)
VALUES (1, '1111', 'localhost', '', 'cond001');
insert into `smart_house_test`.`user_device`
(`User_id`,`devices_id`)
VALUES(1,1);
insert into `smart_house_test`.`sensor`
(`id`, `name`,`device_id`)
VALUE(1,'power',1);
insert into `smart_house_test`.`sensor`
(`id`, `name`,`device_id`)
VALUE(2,'temp',1);