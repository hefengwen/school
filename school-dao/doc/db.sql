-- drop database if exists laibin;
-- create database laibin default character set utf8 collate utf8_general_ci;
-- use laibin;

drop table if exists dict;
create table dict(
  dict_type  integer  not null  comment  '字段类型 1：课程类型 2：资源类型',
  dict_id      integer  not null  comment '字段KEY',
  name       varchar(20)  not null  comment  '字段值',
  detail     varchar(120)  not null  comment  '字段描述',
  primary key(dict_type,dict_id)
)ENGINE=InnoDB default charset=utf8 comment  '数据字典表'; 
insert into dict(dict_type,dict_id,name,detail) values(1,1,'公共基础课','');
insert into dict(dict_type,dict_id,name,detail) values(1,2,'专业必修课','');
insert into dict(dict_type,dict_id,name,detail) values(1,3,'专业选修课','');
insert into dict(dict_type,dict_id,name,detail) values(1,4,'实践课','');

insert into dict(dict_type,dict_id,name,detail) values(2,1,'课程标准','');
insert into dict(dict_type,dict_id,name,detail) values(2,2,'电子教案','');
insert into dict(dict_type,dict_id,name,detail) values(2,3,'教学课件','');
insert into dict(dict_type,dict_id,name,detail) values(2,4,'课程习题','');
insert into dict(dict_type,dict_id,name,detail) values(2,5,'动画/视频','');
insert into dict(dict_type,dict_id,name,detail) values(2,6,'微课/慕课','');
insert into dict(dict_type,dict_id,name,detail) values(2,7,'仿真软件','');
insert into dict(dict_type,dict_id,name,detail) values(2,8,'其它','');

insert into dict values(3,1,'course_id','默认');
insert into dict values(3,2,'create_time','上传时间');
insert into dict values(3,3,'save_cnt','收藏次数');
insert into dict values(3,4,'view_cnt','浏览次数');
insert into dict values(3,5,'score','评分');

insert into dict values(4,1,'resource_id','默认');
insert into dict values(4,2,'create_time','上传时间');
insert into dict values(4,3,'save_cnt','收藏次数');
insert into dict values(4,4,'load_cnt','下载次数');
insert into dict values(4,5,'score','评分');

drop table if exists notice;
create table notice(
  notice_id  bigint  not null  auto_increment  comment  '公告ID',
  title  varchar(128)  not null  default ''  comment  '公告标题',
  author  varchar(30)  not null  default ''  comment  '公告作者',
  content  varchar(1024)  not null default ''  comment  '公告内容',
  type    char(1)         not null  comment '公告类型 S:系统公告 C:课程公告',
  create_time  datetime                        comment '创建时间',
  statue  char(1)  not null     default 'Y'    comment 'Y:可见;N:已删除',
  primary key(notice_id)
)ENGINE=InnoDB default charset=utf8 comment  '公告表';

drop table if exists user;
create table user(
  user_id      varchar(20)     not null    comment '用户ID',
  name    varchar(100)    not null    comment '用户名称',
  phone   varchar(20)     not null    default ''    comment '联系方式',
  passwd  varchar(120)    not null                  comment '用户密码',
  type    integer         not null    default 2     comment '0:管理员;1:教师;2:学生',
  head    varchar(128)    not null    default ''    comment '头像',
  create_time   datetime                                 comment '创建时间',
  teacher_level    varchar(128)    not null      default ''     comment '教师职称',
  teacher_detail   varchar(512)    not null     default ''     comment '教师简介',
  major_id  bigint                 not null     default 0    comment '专业ID',
  teacher_id  varchar(20)          not null     default ''   comment '学生所属教师ID',
  locked      char(1)              not null     default 'N'  comment '是否冻结 Y:是 N:否',
  score            integer         not null     default 0    comment '用户积分',
  statue           char(1)             not null   default 'Y'  comment 'Y:可见;N:已删除',
  can_upload       char(1)             not null     default 'N' comment 'Y:可上传;N:不可上传',
  can_download     char(1)             not null     default 'N' comment 'Y:可下载;N:不可下载',
  upload_limit     bigint                                            comment '上传总量限制',
  download_limit   bigint                                            comment '下载总量限制',
  primary key(user_id)
)ENGINE=InnoDB default charset=utf8 comment  '用户表';
INSERT INTO user (user_id, name, phone, passwd, type, head, create_time, teacher_level, teacher_detail, major_id, teacher_id, locked, score, statue, can_upload, can_download, upload_limit, download_limit) VALUES ('admin', 'admin', '13888888888', '21232f297a57a5a743894a0e4a801fc3', 0, '', null, '', '', 0, '', 'N', 0, 'Y', 'N', 'N', null, null);


drop table if exists major;
create table major(
  major_id  bigint  not null  auto_increment  comment  '专业ID',
  name  varchar(120)  not null default ''  comment  '专业名称',
  parent_id bigint  not null  default 0  comment  '父专业ID',
  create_time   datetime                                                comment '创建时间',
  statue           char(1)             not null   default 'Y'  comment 'Y:可见;N:已删除',
  primary key(major_id)
)ENGINE=InnoDB default charset=utf8 comment  '专业表';

drop table if exists course;
create table course(
  course_id  bigint  not null  auto_increment  comment  '课程ID',
  name  varchar(120)  not null default ''  comment  '课程名称',
  note varchar(1024)  not null  default ''  comment  '课程简介',
  user_id  varchar(20)  not null  default ''  comment  '教师ID',
  catalog  mediumtext                                 comment  '课程目录',
  standard  mediumtext                              comment  '课程标准',
  picture   varchar(128)                      comment  '课程封面',
  save_cnt  bigint      not null  default 0   comment  '收藏量',
  view_cnt     bigint      not null  default 0   comment  '浏览量',
  load_cnt     bigint      not null  default 0   comment  '下载量',
  score_cnt     int              not null    default 0    comment '评价次数',
  score            int             not null    default 0    comment '评价分数',
  type      int            not null       default 0    comment '课程类型 1:公共基础课 2:专业必修课 3:专业选修课 4:实践课',
  major_id  bigint  not null    comment  '专业ID',
  create_time   datetime                                 comment '创建时间',
  statue           char(1)             not null   default 'Y'  comment 'Y:可见;N:已删除',
  primary key(course_id)
)ENGINE=InnoDB default charset=utf8 comment  '课程表';

drop table if exists book;
create table book(
    book_id    bigint(20)    not null auto_increment    comment '教材ID',
    name  varchar(128)   not null   default ''    comment '教材名称',
	ori_url    varchar(128)    not null  default ''    comment '教材地址',
    swf_url    varchar(128)    not null  default ''    comment '教材地址',
    type   varchar(10)    not null  default ''    comment '教材类型',
    file_size       bigint(20)         not null     default 0    comment '教材大小',
    course_id    bigint(20)    not null default 0    comment '课程ID',
    create_time   datetime                                          comment '创建时间',
    statue           char(1)            not null     default 'Y' comment 'Y:可见;N:删除',
    primary key(`book_id`)
)ENGINE=InnoDB default charset=utf8 comment  '教材表';

drop table if exists save;
create table save(
    save_id       bigint       not null     auto_increment    comment '收藏ID',
    ref_id        bigint       not null                 comment '引用ID',
	ref_type      char(1)      not null                 comment '引用类型 C:课程 R:资源',
    user_id       varchar(20)   not null                               comment '用户ID',
    create_time datetime                                                comment '创建时间',
    primary key(save_id)
)ENGINE=InnoDB default charset=utf8 comment  '收藏表';

drop table if exists download;
create table download(
    load_id       bigint       not null     auto_increment    comment '下载ID',
    ref_id        bigint       not null                 comment '引用ID',
	ref_type      char(1)      not null                 comment '引用类型 C:课程 R:资源',
    user_id       varchar(20)   not null                               comment '用户ID',
    create_time datetime                                                comment '创建时间',
    primary key(load_id)
)ENGINE=InnoDB default charset=utf8 comment  '下载表';

drop table if exists score;
create table score(
    score_id       bigint       not null     auto_increment    comment '评价ID',
    ref_id        bigint       not null                 comment '引用ID',
	ref_type      char(1)      not null                 comment '引用类型 C:课程 R:资源',
    user_id       varchar(20)   not null                               comment '用户ID',
    score            int           not null      default 0           comment '评价分数',
    create_time   datetime                                                comment '创建时间',
    primary key(score_id)
)ENGINE=InnoDB default charset=utf8 comment  '评价表';

drop table if exists views;
create table views(
    view_id       bigint       not null     auto_increment    comment '浏览ID',
    ref_id        bigint       not null                 comment '引用ID',
	ref_type      char(1)      not null                 comment '引用类型 C:课程 R:资源',
    user_id       varchar(20)   not null                               comment '用户ID',
    create_time datetime                                                comment '创建时间',
    primary key(view_id)
)ENGINE=InnoDB default charset=utf8 comment  '浏览表';

drop table if exists resource;
create table resource(
    resource_id          bigint         not null    auto_increment    comment '资源ID',
	name     varchar(200)    not null    default ''    comment '资源名称',
	suffix      varchar(10)       not null    default ''    comment '文件后缀',
	ori_url    varchar(128)    not null  default ''    comment '资源地址',
    swf_url    varchar(128)    not null  default ''    comment '资源地址',
	major_id  bigint              not null    default 0    comment '专业ID',
	type      int            not null       default 0    comment '资源类型 1:课程标准 2:电子教案 3:教学课件 4:课程习题 5:动画/视频 6:微课/慕课 7:仿真软件 8:其它',
	note      varchar(1024)                 default ''    comment '资源简介',
	user_id         varchar(20)      not null    default 0    comment '资源上传者',
	file_size       bigint         not null     default 0    comment '资源大小',
	save_cnt       int             not null    default 0    comment '收藏次数',
	load_cnt       int              not null    default 0    comment '下载次数',
	score_cnt     int              not null    default 0    comment '评价次数',
	score            int             not null    default 0    comment '评价分数',
	view_cnt       int              not null    default 0    comment '浏览次数',
	create_time  datetime                                         comment '上传时间',
    person_visible char(1)           not null     default 'N' comment 'Y:自己可见;N:全部可见',
	statue           char(1)            not null     default 'Y' comment 'Y:可见;N:删除',
	primary key(resource_id)
)ENGINE=InnoDB default charset=utf8 comment  '资源信息表';

drop table if exists question;
create table question(
    ques_id    bigint    not null    auto_increment    comment '提问id',
	title    varchar(100)    not null    comment '提问标题',
	content  varchar(500)    not null    comment '提问内容',
	course_id  bigint  not null  comment  '课程ID', 
	user_id       varchar(20)   not null  comment '用户ID',
	create_time  datetime    comment '提问时间',
	statue           char(1)    not null     default 'Y' comment 'Y:可见;N:删除',
	primary key(ques_id)
)ENGINE=InnoDB default charset=utf8 comment  '提问表';

drop table if exists answer;
create table answer(
    ans_id    bigint    not null    auto_increment    comment '回复id',
	content  varchar(500)    not null    comment '回复内容',
	ques_id    bigint    not null    comment '提问id',
	user_id       varchar(20)   not null  comment '用户ID',
	create_time  datetime    comment '回复时间',
	statue           char(1)    not null     default 'Y' comment 'Y:可见;N:删除',
	primary key(ans_id)
)ENGINE=InnoDB default charset=utf8 comment  '回答表';
