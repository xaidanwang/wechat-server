create table adsl_account
(
   accountId            bigint not null auto_increment comment '主键ID',
   host                 varchar(32) comment '服务器地址',
   port                 int comment '登陆端口',
   username             varchar(32) comment '登陆名',
   useable              bool default 1 comment '账号是否可以使用',
   pwd                  varchar(32) comment '密码',
   primary key (accountId)
);

alter table adsl_account comment 'adsl账号信息表';