--��Ŀ�б�

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
--�����б�

create table program_list(
PROJECT_ID varchar(6),
PROGRAM varchar(255))

--��Ŀ��ϵ��

create table project_relation(
PROJECT_ID varchar(6),
PRE_PROJECT varchar(6))

--��Ŀ�����

create table project_distribute(
ID int identity primary key,
PROJECT_ID varchar(6),
USER_ID char(8)��
STATUS char(1),
START_DATE char(8) null,
END_DATE char(8) null,
MEMO varchar(255) null��
IS_DONE char(1) null,
FINAL_END_DATE char(8) null)

alter table project_distribute add constraint pk
primary key (PROJECT_ID,USER_ID,STATUS)

--�����ʷ��
create table change_record(
RECORD_ID int identity primary key,
PROJECT_ID varchar(6),
USER_ID char(8),
CHANGE_DATE char(8),
CONTENT nvarchar(255),
REASON nvarchar(255)
)


--��ʱ��д��
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

--������壺��������|����������|�Ƿ�ʹ�ã�1��true��0��false��
create table check_items(
  CHECK_NO char(2) primary key ,
  CHECK_NAME nvarchar(50),
  MODULUS decimal(5,2),  
  IN_USE char(1) not null,
  STATUS char(10) null
)
lock datarows
--���˵ȼ����壺��������|�ȼ�����|��������|���޷���|��������|�Ƿ�ʹ�ã�1��true��0��false��
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
--������Ϣ����Ŀ���|��Ŀ�׶�|Ա�����|��������|���ֵȼ�|����|������
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
--���������¼�� ���к�|��Ŀ���|�׶�|Ա�����|��������|��¼��Ա
create table issue_record(
	ID int identity primary key,
	PROJECT_ID varchar(6),
	STATUS char(1),
	USER_ID char(8),
	MEMO nvarchar(255),
	CHECK_USER char(8)
)
lock datarows

--��������ʷ��¼
create table require_changes(
	PROJECT_ID varchar(6),
	VERSION int,
	CONTENT nvarchar(255),
	REASON nvarchar(255),
	CHANGE_DATE char(8),
	USER_ID char(8),
	primary key (PROJECT_ID,VERSION)
	
)

--��ϵ��Ϣ
create table lx_info(
	lx_id char(8) primary key,
	depart char(8) ,
	name char(8),
	phone char(20),
	mobile char(19),
	email char(30)
)

alter table lx_info add stuff_id char(8) null