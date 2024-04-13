# 数据库初始化


-- 创建库
create database if not exists health_care;

-- 切换库
use health_care;

-- 用户表
create table user
(
    id           bigint auto_increment comment '用户主键'
        primary key,
    userAccount  varchar(256)                           not null comment '账号',
    userPassword varchar(512)                           not null comment '密码',
    userName     varchar(256)                           null comment '用户昵称',
    userSex      varchar(256)                           null comment '用户性别',
    userAvatar   varchar(1024)                          null comment '用户头像',
    userPhone    varchar(512)                           null comment '用户电话',
    userRole     varchar(256) default 'user'            not null comment '用户角色：user/admin/ban',
    createTime   datetime     default CURRENT_TIMESTAMP not null comment '创建时间',
    updateTime   datetime     default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    isDelete     tinyint      default 0                 not null comment '是否删除'
)
    comment '用户' collate = utf8mb4_unicode_ci;



-- 社区活动表
create table activity
(
    id         bigint auto_increment comment '社区活动主键'
        primary key,
    title      varchar(255)                       null comment '活动主题',
    context    varchar(2000)                      null comment '活动内容',
    time       datetime                           null comment '举办时间',
    pic        varchar(1255)                      null comment '图片',
    promoterId bigint                             null comment '发起人id',
    isEnd      int      default 0                null comment '是否结束',
    createTime datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    updateTime datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    isDelete   tinyint  default 0                 not null comment '是否删除'
)
    comment '社区活动' collate = utf8mb4_unicode_ci;

-- 活动报名人表
create table activity_user
(
    id          bigint auto_increment comment '主键'
        primary key,
    activityId  bigint   null comment '活动id',
    applicantId bigint   null comment '报名人id',
    createTime  datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    updateTime  datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    isDelete    tinyint  default 0                 not null comment '是否删除'
)
    comment '活动报名人' collate = utf8mb4_unicode_ci;


-- 评论表
create table comment
(
    id          bigint auto_increment comment '评论主键'
        primary key,
    content     varchar(9999)                      not null comment '评论内容',
    userId bigint                             not null comment '评论人id',
    activityId  bigint                             not null comment '活动id',
    createTime  datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    updateTime  datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    isDelete    tinyint  default 0                 not null comment '是否删除'
)
    comment '评论' collate = utf8mb4_unicode_ci;


-- 健康服务表
create table healthy_service
(
    id          bigint auto_increment comment '健康服务主键'
        primary key,
    serviceName varchar(255)                       null comment '服务名称',
    intro       varchar(2000)                      null comment '服务介绍',
    cover       varchar(2550)                      null comment '服务封面',
    price       decimal(10, 1)                     null comment '服务费',
    tel         varchar(255)                       null comment '联系电话',
    createTime  datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    updateTime  datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    isDelete    tinyint  default 0                 not null comment '是否删除'
)
    comment '健康服务' collate = utf8mb4_unicode_ci;


-- 资讯表
create table information
(
    id         bigint auto_increment comment '资讯主键'
        primary key,
    title      varchar(255)                       not null comment '标题',
    content    text                               not null comment '内容',
    introduce  varchar(5000)                      not null comment '简介',
    type       varchar(255)                       null comment '类型',
    pic        varchar(255)                       not null comment '资讯图片',
    createTime datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    updateTime datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    isDelete   tinyint  default 0                 not null comment '是否删除'
)
    comment '资讯' collate = utf8mb4_unicode_ci;


-- 一键求救表
create table location
(
    id         bigint auto_increment comment '主键'
        primary key,
    lat        varchar(255)                       null comment '经度',
    lng        varchar(255)                       null comment '纬度',
    userId     bigint                             null comment '求救人Id',
    status     int                                null comment '状态',
    createTime datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    updateTime datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    isDelete   tinyint  default 0                 not null comment '是否删除'
)
    comment '一键求救' collate = utf8mb4_unicode_ci;


-- 留言表
create table question
(
    id         bigint auto_increment comment '留言主键'
        primary key,
    context    varchar(3000)                      null comment '具体问题',
    userId     bigint                             null comment '问诊人',
    pid        bigint                             null comment '回答者id',
    status     int                                null comment '状态',
    createTime datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    updateTime datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    isDelete   tinyint  default 0                 not null comment '是否删除'
)
    comment '留言' collate = utf8mb4_unicode_ci;


-- 健康档案表
create table record
(
    id         bigint auto_increment comment '健康档案主键'
        primary key,
    userId     bigint                             null comment '用户主键',
    record     varchar(5000)                      null comment '详细记录',
    report     varchar(2000)                      null comment '报告',
    opeId      bigint                             null comment '操作人',
    advise     varchar(500)                       null comment '建议',
    createTime datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    updateTime datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    isDelete   tinyint  default 0                 not null comment '是否删除'
)
    comment '健康档案' collate = utf8mb4_unicode_ci;


-- 服务预约表
create table reservation
(
    id         bigint auto_increment comment '服务预约主键'
        primary key,
    serviceId  bigint                             null comment '服务Id',
    userId     bigint                             null comment '预约人id',
    number     varchar(255)                       null comment '预约号',
    message    varchar(255)                       null comment '留言',
    status     int                                null comment '状态',
    createTime datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    updateTime datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    isDelete   tinyint  default 0                 not null comment '是否删除'
)
    comment '服务预约' collate = utf8mb4_unicode_ci;

-- 文章类别表
create table type
(
    id         bigint auto_increment comment '主键'
        primary key,
    typeName   varchar(255)                       null comment '分类名称',
    createTime datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    updateTime datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    isDelete   tinyint  default 0                 not null comment '是否删除'
)
    comment '文章类别' collate = utf8mb4_unicode_ci;

