create table wechat_account
(
   id                   bigint not null auto_increment comment '数据记录id',
   username             varchar(32) not null comment '账号',
   password             varchar(32) comment '密码',
   data                 mediumtext,
   token                varchar(255),
   createTime           timestamp NULL DEFAULT CURRENT_TIMESTAMP,
   updateTime           timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
   isUsable             bool comment '账号是否可用',
   status               int not null comment '账号状态标记',
   remark               varchar(255) comment '注释',
   primary key (id),
   unique key AK_Key_2 (username)
);

/*==============================================================*/
/* Index: Index_1                                               */
/*==============================================================*/
create index Index_1 on wechat_account
(
   id
);

/*==============================================================*/
/* Index: Index_2                                               */
/*==============================================================*/
create index Index_2 on wechat_account
(
   username
);

/*==============================================================*/
/* Index: Index_3                                               */
/*==============================================================*/
create index Index_3 on wechat_account
(
   isUsable
);

/*==============================================================*/
/* Index: Index_4                                               */
/*==============================================================*/
create index Index_4 on wechat_account
(
   status
);

/*==============================================================*/
/* Index: Index_5                                               */
/*==============================================================*/
create index Index_5 on wechat_account
(
   remark
);