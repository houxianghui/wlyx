--项目列表

create table project_list(
PROJECT_ID varchar(6) primary key,
PROJECT_NAME varchar(40),
STATUS char(1),
USER_ID char(9),
MEMO nvarchar(40),
ISS_ID varchar(200),
IS_IN_CONTRACT char(1),
NEED_DEV char(1) null,
OWNING char(1) null)

alter table project_list add PROJECT_CLASS char(2) null
--程序列表

create table program_list(
PROJECT_ID varchar(6),
PROGRAM varchar(255))

--项目关系表

create table project_relation(
PROJECT_ID varchar(6),
PRE_PROJECT varchar(6))

--项目分配表

create table project_distribute(
ID int identity primary key,
PROJECT_ID varchar(6),
USER_ID char(8)，
STATUS char(1),
START_DATE char(8) null,
END_DATE char(8) null,
MEMO varchar(255) null，
IS_DONE char(1) null,
FINAL_END_DATE char(8) null)

alter table project_distribute add constraint pk
primary key (PROJECT_ID,USER_ID,STATUS)

--变更历史表
create table change_record(
RECORD_ID int identity primary key,
PROJECT_ID varchar(6),
USER_ID char(8),
CHANGE_DATE char(8),
CONTENT nvarchar(255),
REASON nvarchar(255)
)


--工时填写：
create table PROJECT_LIST (
	USER_ID char(9),
	PROJECT_NO char(5),
	PROJECT_NAME char(40),
	CURR_STEP char(1),
	START_DATE char(8),
	END_DATE char(8),
	primary key(USER_ID,PROJECT_NO)
)
lock datarows

alter table PROJECT_LIST 

create table TASK_LIST(
	TASK_NO char(20) primary key,
	PROJECT_NO char(5),
	TASK_DATE char(8),
	TASK_STEP char(1),
	TASK_TYPE char(1),
	TASK_GOAL char(1),
	TASK_COST decimal(5,2),
	TASK_USER char(9),
	UPDATE_DATE char(8),
	TASK_MEMO char(250),
	ID int not null
)
lock datarows

--考核项定义：考核项编号|考核项名称|是否使用（1：true，0：false）
create table check_items(
  CHECK_NO char(2) primary key ,
  CHECK_NAME nvarchar(50),
  MODULUS decimal(5,2),  
  IN_USE char(1) not null,
  STATUS char(10) null
)
lock datarows
--考核等级定义：考核项编号|等级划分|基础分数|下限分数|规则描述|是否使用（1：true，0：false）
create table grade_define(
  CHECK_NO char(2),
  GRADE char(1),
  SCORE int,
  LOWER_SCORE int,
  MEMO nvarchar(255) null,
  IN_USE char(1) not null,
  primary key(CHECK_NO,GRADE) 
)
lock datarows
--评分信息，项目编号|项目阶段|员工编号|考核项编号|评分等级|评分|评分人
create table score_info(
  PROJECT_ID varchar(6),
  STATUS char(1),
  USER_ID char(8),
  CHECK_NO char(2),
  GRADE char(1),
  SCORE int,
  CHECK_USER char(8),
  primary key(PROJECT_ID,STATUS,USER_ID,CHECK_NO)  
)
lock datarows
--开发问题记录表 序列号|项目编号|阶段|员工编号|错误内容|记录人员
create table issue_record(
	ID int identity primary key,
	PROJECT_ID varchar(6),
	STATUS char(1),
	USER_ID char(8),
	MEMO nvarchar(255),
	CHECK_USER char(8)
)
lock datarows

--需求变更历史记录
create table require_changes(
	PROJECT_ID varchar(6),
	VERSION int,
	CONTENT nvarchar(255),
	REASON nvarchar(255),
	CHANGE_DATE char(8),
	USER_ID char(8),
	primary key (PROJECT_ID,VERSION)
	
)

--联系信息
create table lx_info(
	lx_id char(8) primary key,
	depart char(8) ,
	name char(8),
	phone char(20),
	mobile char(19),
	email char(30)
)

alter table lx_info add stuff_id char(8) null