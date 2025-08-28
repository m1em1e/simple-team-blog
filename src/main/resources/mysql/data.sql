use mkdir;
create table if not exists `user` (
    id bigint primary key comment '主键id',
    nickname varchar(255) comment '昵称',
    username varchar(255) not null comment '用户名',
    password varchar(255) not null comment '密码',
    salt varchar(255) not null comment '密码盐',
    sex tinyint comment '性别',
    birthday date comment '生日',
    avatar varchar(255) comment '头像',
    tags varchar(255) comment '标签，用逗号隔开',
    description varchar(255) comment '描述',
    create_time datetime not null comment '创建时间',
    update_time datetime not null comment '更新时间',
    last_login_time datetime comment '最后登录时间'
);
create table if not exists `article` (
    id bigint primary key comment '主键id',
    author bigint not null comment '作者id',
    cover_id varchar(255) not null comment '封面id',
    title varchar(255) not null comment '标题',
    introduction varchar(255) not null comment '介绍',
    content text not null comment '内容',
    status tinyint not null comment '状态',
    create_time datetime not null comment '创建时间',
    update_time datetime not null comment '更新时间'
);
create table if not exists `category` (
    id bigint primary key comment '主键id',
    category_name varchar(255) not null comment '标签id',
    status tinyint not null comment '状态',
    description varchar(255) default null comment '描述',
    create_time datetime not null comment '创建时间'
);
create table if not exists `article_category` (
    id bigint primary key comment '主键id',
    article_id bigint not null comment '文章id',
    category_id bigint not null comment '标签id',
    create_time datetime not null comment '创建时间'
);
create table if not exists `image` (
    id bigint primary key comment '主键id',
    name varchar(255) not null comment '名称',
    `type` varchar(64) not null comment '类型',
    data mediumblob not null comment '数据'
)