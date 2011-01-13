# MySQL-Front 5.1  (Build 3.57)

/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE */;
/*!40101 SET SQL_MODE='' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES */;
/*!40103 SET SQL_NOTES='ON' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS */;
/*!40014 SET FOREIGN_KEY_CHECKS=0 */;


# Host: localhost    Database: djkwgdb
# ------------------------------------------------------
# Server version 5.5.8

DROP DATABASE IF EXISTS `djkwgdb`;
CREATE DATABASE `djkwgdb` /*!40100 DEFAULT CHARACTER SET gbk */;
USE `djkwgdb`;

#
# Source for table change_record
#

DROP TABLE IF EXISTS `change_record`;
CREATE TABLE `change_record` (
  `RECORD_ID` int(11) NOT NULL AUTO_INCREMENT,
  `PROJECT_ID` varchar(6) DEFAULT NULL,
  `USER_ID` char(8) DEFAULT NULL,
  `CHANGE_DATE` char(8) DEFAULT NULL,
  `CONTENT` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `REASON` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  PRIMARY KEY (`RECORD_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=gbk;

#
# Dumping data for table change_record
#
LOCK TABLES `change_record` WRITE;
/*!40000 ALTER TABLE `change_record` DISABLE KEYS */;

INSERT INTO `change_record` VALUES (1,'test','00006671','20110112','????:??-<strong>BRD</strong>;?????-<strong>?</strong>;????????-<strong>?</strong>;?????-<strong>kaj</strong><br>????:??-<strong>BRD</strong>;?????-<strong>?</strong>;????????-<strong>?</strong>;?????-<strong>kaj</strong>','adf');
/*!40000 ALTER TABLE `change_record` ENABLE KEYS */;
UNLOCK TABLES;

#
# Source for table check_items
#

DROP TABLE IF EXISTS `check_items`;
CREATE TABLE `check_items` (
  `CHECK_NO` char(2) NOT NULL,
  `CHECK_NAME` varchar(50) CHARACTER SET utf8 DEFAULT NULL,
  `MODULUS` decimal(5,2) DEFAULT NULL,
  `IN_USE` char(1) NOT NULL,
  `STAT` char(10) DEFAULT NULL,
  PRIMARY KEY (`CHECK_NO`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk;

#
# Dumping data for table check_items
#
LOCK TABLES `check_items` WRITE;
/*!40000 ALTER TABLE `check_items` DISABLE KEYS */;

INSERT INTO `check_items` VALUES ('1','难度系数',0.04,'1','');
INSERT INTO `check_items` VALUES ('10','工作态度',0.08,'1','');
INSERT INTO `check_items` VALUES ('11','团队合作',0.08,'1','');
INSERT INTO `check_items` VALUES ('12','纪律规范',0.03,'1','');
INSERT INTO `check_items` VALUES ('2','项目进度',0.1,'1','');
INSERT INTO `check_items` VALUES ('3','设计质量',0.25,'1','0,1');
INSERT INTO `check_items` VALUES ('4','开发质量',0.25,'1','2');
INSERT INTO `check_items` VALUES ('5','测试质量',0.25,'1','3,4');
INSERT INTO `check_items` VALUES ('6','文档质量',0.15,'1','');
INSERT INTO `check_items` VALUES ('7','专业技能',0.08,'1','');
INSERT INTO `check_items` VALUES ('8','沟通能力',0.08,'1','');
INSERT INTO `check_items` VALUES ('9','抗压能力',0.03,'1','');
/*!40000 ALTER TABLE `check_items` ENABLE KEYS */;
UNLOCK TABLES;

#
# Source for table ep_dic_type
#

DROP TABLE IF EXISTS `ep_dic_type`;
CREATE TABLE `ep_dic_type` (
  `TYPE_ID` char(4) NOT NULL,
  `TYPE_NAME` varchar(30) NOT NULL,
  `DIC_LEVEL` char(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=gbk;

#
# Dumping data for table ep_dic_type
#
LOCK TABLES `ep_dic_type` WRITE;
/*!40000 ALTER TABLE `ep_dic_type` DISABLE KEYS */;

INSERT INTO `ep_dic_type` VALUES ('0001','用户状态','1');
INSERT INTO `ep_dic_type` VALUES ('0002','角色状态','1');
INSERT INTO `ep_dic_type` VALUES ('0003','菜单叶子节点标志','1');
INSERT INTO `ep_dic_type` VALUES ('0004','机构级别','1');
INSERT INTO `ep_dic_type` VALUES ('0005','操作方式','1');
INSERT INTO `ep_dic_type` VALUES ('0006','事件类别','1');
INSERT INTO `ep_dic_type` VALUES ('9994','项目阶段','1');
INSERT INTO `ep_dic_type` VALUES ('9992','任务类别','1');
INSERT INTO `ep_dic_type` VALUES ('9993','技术支持服务处理结果','1');
INSERT INTO `ep_dic_type` VALUES ('9991','任务完成度','1');
INSERT INTO `ep_dic_type` VALUES ('9990','部门名称','1');
INSERT INTO `ep_dic_type` VALUES ('9989','项目阶段','1');
INSERT INTO `ep_dic_type` VALUES ('9988','项目归属','1');
INSERT INTO `ep_dic_type` VALUES ('9987','08-09年V+运维合同','1');
INSERT INTO `ep_dic_type` VALUES ('9986','08-09年外挂运维合同','1');
INSERT INTO `ep_dic_type` VALUES ('0000','是否','1');
/*!40000 ALTER TABLE `ep_dic_type` ENABLE KEYS */;
UNLOCK TABLES;

#
# Source for table ep_error_code
#

DROP TABLE IF EXISTS `ep_error_code`;
CREATE TABLE `ep_error_code` (
  `ERROR_CODE` char(8) NOT NULL,
  `CAPTION` varchar(60) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=gbk;

#
# Dumping data for table ep_error_code
#
LOCK TABLES `ep_error_code` WRITE;
/*!40000 ALTER TABLE `ep_error_code` DISABLE KEYS */;

INSERT INTO `ep_error_code` VALUES ('E010000','执行字符集转码错误！');
INSERT INTO `ep_error_code` VALUES ('E010001','数据查询失败！');
INSERT INTO `ep_error_code` VALUES ('E010002','数据修改失败！');
INSERT INTO `ep_error_code` VALUES ('E010004','释放数据库连接失败！');
INSERT INTO `ep_error_code` VALUES ('E010005','数据库事务控制错误！');
INSERT INTO `ep_error_code` VALUES ('E010011','增加数据失败！');
INSERT INTO `ep_error_code` VALUES ('E010012','批量增加数据失败！');
INSERT INTO `ep_error_code` VALUES ('E010013','删除数据失败！');
INSERT INTO `ep_error_code` VALUES ('E010014','更新数据失败！');
INSERT INTO `ep_error_code` VALUES ('E010015','数据库操作失败！');
INSERT INTO `ep_error_code` VALUES ('E010016','查询数据失败！');
INSERT INTO `ep_error_code` VALUES ('E010017','数据访问组件获得数据库连接失败！');
INSERT INTO `ep_error_code` VALUES ('E010018','查询翻页结果失败！');
INSERT INTO `ep_error_code` VALUES ('E010019','数据访问组件释放数据库连接失败！');
INSERT INTO `ep_error_code` VALUES ('E010020','数据访问组件释放数据库连接失败！');
INSERT INTO `ep_error_code` VALUES ('E010031','数据库连接池配置文件错误！');
INSERT INTO `ep_error_code` VALUES ('E010032','获取默认数据库连接失败！');
INSERT INTO `ep_error_code` VALUES ('E010033','从指定数据源获取数据库连接失败！');
INSERT INTO `ep_error_code` VALUES ('E010038','获取EJB对象失败！');
INSERT INTO `ep_error_code` VALUES ('E010039','从组件容器获取组件实例失败！');
INSERT INTO `ep_error_code` VALUES ('E010040','密码加密失败！');
INSERT INTO `ep_error_code` VALUES ('E010041','已存在重复纪录！');
INSERT INTO `ep_error_code` VALUES ('E010042','原密码错误！');
INSERT INTO `ep_error_code` VALUES ('E020001','您没有此操作权限！');
INSERT INTO `ep_error_code` VALUES ('E030001','默认评分体系只能有一个！');
INSERT INTO `ep_error_code` VALUES ('E030002','未找到客户资料下载模板！');
INSERT INTO `ep_error_code` VALUES ('E040001','记录操作日志失败！');
INSERT INTO `ep_error_code` VALUES ('E050001','生成PDF文件失败！确认上传文件的格式！');
INSERT INTO `ep_error_code` VALUES ('E060001','调整催收账户失败！');
/*!40000 ALTER TABLE `ep_error_code` ENABLE KEYS */;
UNLOCK TABLES;

#
# Source for table ep_homepage
#

DROP TABLE IF EXISTS `ep_homepage`;
CREATE TABLE `ep_homepage` (
  `TEMPL_ID` int(11) NOT NULL,
  `CAPTION` varchar(40) NOT NULL,
  `URL` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=gbk;

#
# Dumping data for table ep_homepage
#
LOCK TABLES `ep_homepage` WRITE;
/*!40000 ALTER TABLE `ep_homepage` DISABLE KEYS */;

INSERT INTO `ep_homepage` VALUES (67,'default','/blank.htm');
/*!40000 ALTER TABLE `ep_homepage` ENABLE KEYS */;
UNLOCK TABLES;

#
# Source for table ep_mdic
#

DROP TABLE IF EXISTS `ep_mdic`;
CREATE TABLE `ep_mdic` (
  `SYS_ID` decimal(12,0) NOT NULL,
  `TYPE_ID` char(4) NOT NULL,
  `PARENT_ID` decimal(12,0) NOT NULL,
  `ITEM_ID` varchar(12) NOT NULL,
  `ITEM_VAL` varchar(40) NOT NULL,
  `LIST_ORDER` smallint(6) NOT NULL,
  `ITEM_LEVEL` smallint(6) NOT NULL,
  `LOGIC_ID` varchar(12) DEFAULT NULL,
  `STAT` char(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=gbk;

#
# Dumping data for table ep_mdic
#
LOCK TABLES `ep_mdic` WRITE;
/*!40000 ALTER TABLE `ep_mdic` DISABLE KEYS */;

INSERT INTO `ep_mdic` VALUES (761,'0302',0,'1','正常',1,1,'A','1');
INSERT INTO `ep_mdic` VALUES (762,'0302',0,'2','关注',2,1,'A','1');
INSERT INTO `ep_mdic` VALUES (763,'0302',0,'3','次级',3,1,'A','1');
INSERT INTO `ep_mdic` VALUES (764,'0302',0,'4','可疑',4,1,'A','1');
INSERT INTO `ep_mdic` VALUES (765,'0302',0,'5','损失',5,1,'A','1');
INSERT INTO `ep_mdic` VALUES (766,'0302',761,'11','244010101',1,2,'11','1');
INSERT INTO `ep_mdic` VALUES (767,'0302',761,'12','244010102',2,2,'12','1');
INSERT INTO `ep_mdic` VALUES (768,'0302',762,'21','244010103',1,2,'21','1');
INSERT INTO `ep_mdic` VALUES (769,'0302',762,'22','244010104',2,2,'22','1');
INSERT INTO `ep_mdic` VALUES (770,'0302',763,'31','244010105',1,2,'31','1');
INSERT INTO `ep_mdic` VALUES (771,'0302',764,'41','244010106',1,2,'41','1');
INSERT INTO `ep_mdic` VALUES (772,'0302',764,'42','244010107',2,2,'42','1');
INSERT INTO `ep_mdic` VALUES (773,'0302',765,'51','244010108',1,2,'51','1');
/*!40000 ALTER TABLE `ep_mdic` ENABLE KEYS */;
UNLOCK TABLES;

#
# Source for table ep_menu
#

DROP TABLE IF EXISTS `ep_menu`;
CREATE TABLE `ep_menu` (
  `MENU_ID` char(8) NOT NULL,
  `PARENT_ID` char(8) NOT NULL,
  `MENU_NAME` varchar(40) NOT NULL,
  `MENU_MARK` char(1) NOT NULL,
  `MENU_LEVEL` smallint(6) NOT NULL,
  `LIST_ORDER` smallint(6) NOT NULL,
  `MENU_URL` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=gbk;

#
# Dumping data for table ep_menu
#
LOCK TABLES `ep_menu` WRITE;
/*!40000 ALTER TABLE `ep_menu` DISABLE KEYS */;

INSERT INTO `ep_menu` VALUES ('00000013','00000000','系统管理','0',1,6,' ');
INSERT INTO `ep_menu` VALUES ('00000015','00000013','门户管理','0',2,1,' ');
INSERT INTO `ep_menu` VALUES ('00000016','00000013','参数配置','0',2,2,' ');
INSERT INTO `ep_menu` VALUES ('00000022','00000015','首页模板维护','0',3,1,'HomePage.do?act=list');
INSERT INTO `ep_menu` VALUES ('00000023','00000015','用户管理','0',3,2,'User.do?act=list&init=true');
INSERT INTO `ep_menu` VALUES ('00000024','00000015','菜单管理','0',3,3,'portal/menu/index.jsp');
INSERT INTO `ep_menu` VALUES ('00000025','00000015','角色管理','0',3,4,'Role.do?act=list');
INSERT INTO `ep_menu` VALUES ('00000026','00000015','操作权限定义','0',3,5,'portal/op/OP_index.jsp');
INSERT INTO `ep_menu` VALUES ('00000045','00000016','主键管理','0',3,2,'PmKey.do?act=list');
INSERT INTO `ep_menu` VALUES ('00000046','00000016','数据字典维护','0',3,3,'DicType.do?act=list');
INSERT INTO `ep_menu` VALUES ('00000324','00000000','项目维护','0',1,1,' ');
INSERT INTO `ep_menu` VALUES ('00000325','00000324','当前项目列表','0',2,1,'ProjectMaintain.do?act=qpl');
INSERT INTO `ep_menu` VALUES ('00000326','00000324','所有项目列表','0',2,2,'ProjectMaintain.do?act=qal');
INSERT INTO `ep_menu` VALUES ('00000327','00000324','未填写工时信息查询','0',2,3,'NotWriteNoteStuff.do?act=list');
INSERT INTO `ep_menu` VALUES ('00000328','00000324','项目进度检查','0',2,4,'ProjectDistribute.do?act=getNotDoneWorks');
INSERT INTO `ep_menu` VALUES ('00000329','00000000','日报填写','0',1,3,'');
INSERT INTO `ep_menu` VALUES ('00000330','00000329','工时填写','0',2,1,'TASK_LIST.do?act=list');
INSERT INTO `ep_menu` VALUES ('00000331','00000329','历史工作查询','0',2,2,'TASK_LIST.do?act=ql');
INSERT INTO `ep_menu` VALUES ('00000332','00000016','自定义数据字典维护','0',3,3,'ReDefSDic.do?act=list');
INSERT INTO `ep_menu` VALUES ('00000333','00000324','员工工作查询','0',2,5,'TASK_LIST.do?act=qa');
INSERT INTO `ep_menu` VALUES ('00000334','00000329','我的任务','0',2,1,'ProjectDistribute.do?act=gmp');
/*!40000 ALTER TABLE `ep_menu` ENABLE KEYS */;
UNLOCK TABLES;

#
# Source for table ep_op_def
#

DROP TABLE IF EXISTS `ep_op_def`;
CREATE TABLE `ep_op_def` (
  `OP_CODE` char(20) NOT NULL,
  `MENU_ID` char(8) NOT NULL,
  `CAPTION` varchar(40) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=gbk;

#
# Dumping data for table ep_op_def
#
LOCK TABLES `ep_op_def` WRITE;
/*!40000 ALTER TABLE `ep_op_def` DISABLE KEYS */;

INSERT INTO `ep_op_def` VALUES ('user_c','00000023','增加用户');
INSERT INTO `ep_op_def` VALUES ('user_u','00000023','修改用户');
INSERT INTO `ep_op_def` VALUES ('user_d','00000023','删除用户');
INSERT INTO `ep_op_def` VALUES ('menu_c','00000024','增加菜单');
INSERT INTO `ep_op_def` VALUES ('menu_u','00000024','修改菜单');
INSERT INTO `ep_op_def` VALUES ('menu_d','00000024','删除菜单');
INSERT INTO `ep_op_def` VALUES ('user_enable','00000023','启用用户');
INSERT INTO `ep_op_def` VALUES ('user_disable','00000023','停用用户');
INSERT INTO `ep_op_def` VALUES ('user_terminate','00000023','作废用户');
INSERT INTO `ep_op_def` VALUES ('role_c','00000025','增加角色');
INSERT INTO `ep_op_def` VALUES ('role_u','00000025','修改角色');
INSERT INTO `ep_op_def` VALUES ('role_terminate','00000025','废除角色');
INSERT INTO `ep_op_def` VALUES ('role_d','00000025','删除角色');
INSERT INTO `ep_op_def` VALUES ('role_perm','00000025','角色权限管理');
INSERT INTO `ep_op_def` VALUES ('op_c','00000026','增加操作权限');
INSERT INTO `ep_op_def` VALUES ('op_d','00000026','删除操作权限');
INSERT INTO `ep_op_def` VALUES ('pmKey_c','00000045','增加主键');
INSERT INTO `ep_op_def` VALUES ('pmKey_u','00000045','修改主键');
INSERT INTO `ep_op_def` VALUES ('pmKey_d','00000045','删除主键');
INSERT INTO `ep_op_def` VALUES ('dicType_c','00000046','增加数据字典');
INSERT INTO `ep_op_def` VALUES ('dicType_u','00000046','修改数据字典');
INSERT INTO `ep_op_def` VALUES ('dicType_d','00000046','删除数据字典');
INSERT INTO `ep_op_def` VALUES ('dmField_c','00000061','增加域定义配置');
INSERT INTO `ep_op_def` VALUES ('dmField_u','00000061','修改域定义配置');
INSERT INTO `ep_op_def` VALUES ('dmField_d','00000061','删除域定义配置');
INSERT INTO `ep_op_def` VALUES ('dmField_uv','00000061','修改版本号');
INSERT INTO `ep_op_def` VALUES ('fileDef_c','00000062','增加平移文件配置');
INSERT INTO `ep_op_def` VALUES ('fileDef_u','00000062','修改平移文件配置');
INSERT INTO `ep_op_def` VALUES ('fileDef_d','00000062','删除平移文件配置');
INSERT INTO `ep_op_def` VALUES ('homepage_c','00000022','增加首页模板');
INSERT INTO `ep_op_def` VALUES ('homepage_u','00000022','修改首页模板');
INSERT INTO `ep_op_def` VALUES ('homepage_d','00000022','删除首页模板');
INSERT INTO `ep_op_def` VALUES ('product_c','00000077','增加产品定义');
INSERT INTO `ep_op_def` VALUES ('product_u','00000077','修改产品定义');
INSERT INTO `ep_op_def` VALUES ('product_d','00000077','删除产品定义');
INSERT INTO `ep_op_def` VALUES ('creditSys_c','00000078','增加评分体系');
INSERT INTO `ep_op_def` VALUES ('creditSys_u','00000078','修改评分体系');
INSERT INTO `ep_op_def` VALUES ('creditSys_d','00000078','删除评分体系');
INSERT INTO `ep_op_def` VALUES ('creditOrgProd_c','00000079','增加产品/机构评分体系');
INSERT INTO `ep_op_def` VALUES ('creditOrgProd_u','00000079','修改产品/机构评分体系');
INSERT INTO `ep_op_def` VALUES ('creditOrgProd_d','00000079','删除产品/机构评分体系');
INSERT INTO `ep_op_def` VALUES ('orgProduct_c','00000084','增加机构产品授权');
INSERT INTO `ep_op_def` VALUES ('orgProduct_u','00000084','修改机构产品授权');
INSERT INTO `ep_op_def` VALUES ('reDefSDic_c','00000332','增加自定义单级字典');
INSERT INTO `ep_op_def` VALUES ('reDefSDic_u','00000332','修改自定义单级字典');
INSERT INTO `ep_op_def` VALUES ('reDefSDic_d','00000332','删除自定义单级字典');
INSERT INTO `ep_op_def` VALUES ('sDic_c','00000046','增加数据字典明细');
INSERT INTO `ep_op_def` VALUES ('sDic_u','00000046','修改数据字典明细');
INSERT INTO `ep_op_def` VALUES ('sDic_d','00000046','删除数据字典明细');
INSERT INTO `ep_op_def` VALUES ('input_req','00000058','录入申请');
INSERT INTO `ep_op_def` VALUES ('add_per','00000058','单独增加附卡');
INSERT INTO `ep_op_def` VALUES ('modify_req','00000058','修改申请资料');
INSERT INTO `ep_op_def` VALUES ('del_req','00000058','删除申请资料');
INSERT INTO `ep_op_def` VALUES ('businessType_c','00000119','增加业务类型');
INSERT INTO `ep_op_def` VALUES ('businessType_u','00000119','修改业务类型');
INSERT INTO `ep_op_def` VALUES ('businessType_d','00000119','删除业务类型');
INSERT INTO `ep_op_def` VALUES ('get_back','00000058','取回(已处理申请列表)');
INSERT INTO `ep_op_def` VALUES ('query_check','00000086','审批信息');
INSERT INTO `ep_op_def` VALUES ('query_flowtrack','00000086','流程跟踪');
INSERT INTO `ep_op_def` VALUES ('check_getback','00000056',' 取回（复核）');
INSERT INTO `ep_op_def` VALUES ('check_todo','00000056','复核');
INSERT INTO `ep_op_def` VALUES ('abc_bus_saverr','00000056','商务卡总行保存错误项');
INSERT INTO `ep_op_def` VALUES ('txLetterInfo_c','00000218','名址退信管理增加');
INSERT INTO `ep_op_def` VALUES ('txLetterInfo_u','00000218','名址退信管理修改');
INSERT INTO `ep_op_def` VALUES ('txLetterInfo_d','00000218','名址退信管理删除');
INSERT INTO `ep_op_def` VALUES ('collActCode_c','00000123','增加属地催收行动码');
INSERT INTO `ep_op_def` VALUES ('collActCode_u','00000123','修改属地催收行动码');
INSERT INTO `ep_op_def` VALUES ('collActCode_d','00000123','删除属地催收行动码');
INSERT INTO `ep_op_def` VALUES ('query_del','00000087','删除（查询）');
INSERT INTO `ep_op_def` VALUES ('lockFuhe_u','00000132','锁定码调整复核');
INSERT INTO `ep_op_def` VALUES ('lockSure_u','00000133','锁定码调整确认');
INSERT INTO `ep_op_def` VALUES ('base','00000094','查看客户基本信息');
INSERT INTO `ep_op_def` VALUES ('base_dl','00000094','批量下载基本信息');
INSERT INTO `ep_op_def` VALUES ('acct','00000094','查看账户情况');
INSERT INTO `ep_op_def` VALUES ('acct_dl','00000094','批量下载账户信息');
INSERT INTO `ep_op_def` VALUES ('trade','00000094','查看账户交易量信息');
INSERT INTO `ep_op_def` VALUES ('trade_dl','00000094','批量下载账户交易量信息');
INSERT INTO `ep_op_def` VALUES ('apply','00000094','查看贡献信息');
INSERT INTO `ep_op_def` VALUES ('apply_dl','00000094','批量下载贡献信息');
INSERT INTO `ep_op_def` VALUES ('allcards','00000094','查看附卡信息');
INSERT INTO `ep_op_def` VALUES ('appinfo','00000094','查看原始申请信息');
INSERT INTO `ep_op_def` VALUES ('billDeal_up','00000034','上传文件');
INSERT INTO `ep_op_def` VALUES ('billDeal_cl','00000034','清除文件');
INSERT INTO `ep_op_def` VALUES ('collact_c','00000128','输入锁定码');
INSERT INTO `ep_op_def` VALUES ('collmemo_c','00000128','输入备忘信息');
INSERT INTO `ep_op_def` VALUES ('coll_dl','00000128','下载催收通知单');
INSERT INTO `ep_op_def` VALUES ('colllock_u','00000128','调整锁定码');
INSERT INTO `ep_op_def` VALUES ('coll_base','00000101','查看催收基本信息');
INSERT INTO `ep_op_def` VALUES ('coll_his','00000101','查看催收历史信息');
INSERT INTO `ep_op_def` VALUES ('collAcctAdjust_u','00000130','催收帐户调整');
INSERT INTO `ep_op_def` VALUES ('AttachCard_c','00000058','增加附卡');
INSERT INTO `ep_op_def` VALUES ('AttachCard_u','00000058','修改附卡');
INSERT INTO `ep_op_def` VALUES ('AttachCard_d','00000058','删除附卡');
INSERT INTO `ep_op_def` VALUES ('inputer_submit','00000058','录入员提交资料');
INSERT INTO `ep_op_def` VALUES ('check_submit','00000056','复核员提交申请');
INSERT INTO `ep_op_def` VALUES ('check_handback','00000056','复核员退回申请');
INSERT INTO `ep_op_def` VALUES ('check_saveerr','00000056','复核员保存打勾错误');
INSERT INTO `ep_op_def` VALUES ('pro_batpass','00000056','省行复核员批量通过资料');
INSERT INTO `ep_op_def` VALUES ('pro_batback','00000056','省行复核员批量退回资料');
INSERT INTO `ep_op_def` VALUES ('busPerCard_c','00000058','增加商务卡个人资料');
INSERT INTO `ep_op_def` VALUES ('busPerCard_u','00000058','修改商务卡个人资料');
INSERT INTO `ep_op_def` VALUES ('busPerCard_d','00000058','删除商务卡个人资料');
INSERT INTO `ep_op_def` VALUES ('busCardReq_c','00000058','增加商务卡单位资料');
INSERT INTO `ep_op_def` VALUES ('busCardReq_u','00000058','修改商务卡单位资料');
INSERT INTO `ep_op_def` VALUES ('bus_checksubmit','00000056','商务卡复核提交');
INSERT INTO `ep_op_def` VALUES ('bus_checkback','00000056','商务卡复核退回');
INSERT INTO `ep_op_def` VALUES ('bus_revise','00000056','商务卡复核修改资料');
INSERT INTO `ep_op_def` VALUES ('bus_saverr','00000056','商务卡复核保存打勾项');
INSERT INTO `ep_op_def` VALUES ('cityCodeDef_c','00000141','增加城市代码');
INSERT INTO `ep_op_def` VALUES ('cityCodeDef_u','00000141','修改城市代码');
INSERT INTO `ep_op_def` VALUES ('cityCodeDef_d','00000141','删除城市代码');
INSERT INTO `ep_op_def` VALUES ('rewrite_dl','00000094','批量下载资料变动情况信息');
INSERT INTO `ep_op_def` VALUES ('wipsend_file','00000147','上传审批资料');
INSERT INTO `ep_op_def` VALUES ('send_file','00000144','上传文件');
INSERT INTO `ep_op_def` VALUES ('busiDefine_c','00000119','增加业务定义');
INSERT INTO `ep_op_def` VALUES ('busiDefine_u','00000119','修改业务定义');
INSERT INTO `ep_op_def` VALUES ('busiDefine_d','00000119','删除业务定义');
INSERT INTO `ep_op_def` VALUES ('batchconv','00000119','批量格式配置');
INSERT INTO `ep_op_def` VALUES ('fileDefine_c','00000119','增加输入文件定义');
INSERT INTO `ep_op_def` VALUES ('fileDefine_u','00000119','修改输入文件定义');
INSERT INTO `ep_op_def` VALUES ('fileDefine_d','00000119','删除输入文件定义');
INSERT INTO `ep_op_def` VALUES ('inFileField_c','00000119','增加输入文件域定义');
INSERT INTO `ep_op_def` VALUES ('inFileField_u','00000119','修改输入文件域定义');
INSERT INTO `ep_op_def` VALUES ('inFileField_d','00000119','删除输入文件域定义');
INSERT INTO `ep_op_def` VALUES ('outFileField_c','00000119','增加输出文件域定义');
INSERT INTO `ep_op_def` VALUES ('outFileField_u','00000119','修改输出文件域定义');
INSERT INTO `ep_op_def` VALUES ('outFileField_d','00000119','删除输出文件域定义');
INSERT INTO `ep_op_def` VALUES ('fieldMap_c','00000119','增加数据域映射');
INSERT INTO `ep_op_def` VALUES ('fieldMap_u','00000119','修改数据域映射');
INSERT INTO `ep_op_def` VALUES ('fieldMap_d','00000119','删除数据域映射');
INSERT INTO `ep_op_def` VALUES ('fixfileField_c','00000119','增加AML1文件域定义');
INSERT INTO `ep_op_def` VALUES ('fixfileField_u','00000119','修改AML1文件域定义');
INSERT INTO `ep_op_def` VALUES ('fixfileField_d','00000119','删除AML1文件域定义');
INSERT INTO `ep_op_def` VALUES ('fixfieldMap_c','00000119','增加AML1数据域映射');
INSERT INTO `ep_op_def` VALUES ('fixfieldMap_u','00000119','修改AML1数据域映射');
INSERT INTO `ep_op_def` VALUES ('fixfieldMap_d','00000119','删除AML1数据域映射');
INSERT INTO `ep_op_def` VALUES ('trans_dl','00000018','下载贷记卡交易查询结果');
INSERT INTO `ep_op_def` VALUES ('lockNew_c','00000101','添加锁定码');
INSERT INTO `ep_op_def` VALUES ('lockNew_rel','00000101','解除锁定码');
INSERT INTO `ep_op_def` VALUES ('jobConfig_c','00000064','增加批量作业');
INSERT INTO `ep_op_def` VALUES ('jobConfig_u','00000064','修改批量作业');
INSERT INTO `ep_op_def` VALUES ('jobConfig_d','00000064','删除批量作业');
INSERT INTO `ep_op_def` VALUES ('collacct_dl','00000129','下载催收名单');
INSERT INTO `ep_op_def` VALUES ('collquery_dl','00000122','下载催收账户');
INSERT INTO `ep_op_def` VALUES ('reset_pwd','00000023','重置密码');
INSERT INTO `ep_op_def` VALUES ('check_batpass','00000056','批量通过');
INSERT INTO `ep_op_def` VALUES ('expandManager','00000158','拓展人维护');
INSERT INTO `ep_op_def` VALUES ('ep_notice_c','00000162','首页通知管理');
INSERT INTO `ep_op_def` VALUES ('busratio_u','00000058','新增或修改审批条件');
INSERT INTO `ep_op_def` VALUES ('company_dl','00000094','商务卡单位资料信息下载');
INSERT INTO `ep_op_def` VALUES ('company','00000094','查看商务卡单位资料信息');
INSERT INTO `ep_op_def` VALUES ('relaccount','00000094','关系账户信息');
INSERT INTO `ep_op_def` VALUES ('usb','00000094','查看外币账户信息');
INSERT INTO `ep_op_def` VALUES ('rmb','00000094','查看人民币账户信息');
INSERT INTO `ep_op_def` VALUES ('collprimary_dl','00000167','商务卡分账户下载列表');
INSERT INTO `ep_op_def` VALUES ('collprimaryrmb','00000167','查看双币种人民币账户');
INSERT INTO `ep_op_def` VALUES ('collprimaryusb','00000167','查看双币种外币账户');
INSERT INTO `ep_op_def` VALUES ('collcompany_base','00000167','商务卡催收账户信息');
INSERT INTO `ep_op_def` VALUES ('collcompany_his','00000167','商务卡催收历史信息');
INSERT INTO `ep_op_def` VALUES ('collclassify_dl','00000167','下载商务卡分类查询催收账户');
INSERT INTO `ep_op_def` VALUES ('collcompanyadmin_dl','00000168','商务卡催收账户管理下载');
INSERT INTO `ep_op_def` VALUES ('collcompanyadjust_u','00000168','调整商务卡催收账户');
INSERT INTO `ep_op_def` VALUES ('collcompanyact_c','00000168','增加商务卡行动码');
INSERT INTO `ep_op_def` VALUES ('collcompanymemo_c','00000168','增加商务卡备忘信息');
INSERT INTO `ep_op_def` VALUES ('collcompany_dl','00000168','下载商务卡催收通知单');
INSERT INTO `ep_op_def` VALUES ('collcompanylock_u','00000169','调整商务卡锁定码');
INSERT INTO `ep_op_def` VALUES ('lockcompanynew_c','00000169','添加关系级锁定码');
INSERT INTO `ep_op_def` VALUES ('lockcompanynew_rel','00000169','解除关系级锁定码');
INSERT INTO `ep_op_def` VALUES ('billInfo_c','00000179','增加商务卡公司卡对账单信息参数配置');
INSERT INTO `ep_op_def` VALUES ('repayInfo_c','00000180','增加约定还款资料');
INSERT INTO `ep_op_def` VALUES ('repayInfo_u','00000180','修改约定还款资料');
INSERT INTO `ep_op_def` VALUES ('repayInfo_d','00000180','删除约定还款资料');
INSERT INTO `ep_op_def` VALUES ('repayInfo_check','00000181','复核约定还款资料');
INSERT INTO `ep_op_def` VALUES ('sms_sub','00000187','短信订阅');
INSERT INTO `ep_op_def` VALUES ('sms_can','00000188','短信撤销');
INSERT INTO `ep_op_def` VALUES ('sms_send','00000189','实时发送短信');
INSERT INTO `ep_op_def` VALUES ('ep_role_level','00000193','角色等级调整');
INSERT INTO `ep_op_def` VALUES ('ep_amount_adjust','00000190','临时额度调整录入');
INSERT INTO `ep_op_def` VALUES ('ep_amount_adjust_c','00000191','临时额度调整审批');
INSERT INTO `ep_op_def` VALUES ('stmtRegen_m','00000194','补打本外币合并账单');
INSERT INTO `ep_op_def` VALUES ('undoLock','00000131','锁定码撤销');
INSERT INTO `ep_op_def` VALUES ('amsd_c','00000197','增加修改行部关系');
INSERT INTO `ep_op_def` VALUES ('amsd_s','00000197','增加省行机构');
INSERT INTO `ep_op_def` VALUES ('amsd_l','00000197','调整机构上级');
INSERT INTO `ep_op_def` VALUES ('ep_stmt_rule_c','00000200','账单拆分参数维护');
INSERT INTO `ep_op_def` VALUES ('check_batdeal','00000056','批量处理');
INSERT INTO `ep_op_def` VALUES ('check_single','00000056','单笔复核');
INSERT INTO `ep_op_def` VALUES ('abc_check_saveerr','00000056','总行保存打勾项');
INSERT INTO `ep_op_def` VALUES ('abc_check_submit','00000056','总行提交申请');
INSERT INTO `ep_op_def` VALUES ('abc_check_handback','00000056','总行退回申请');
INSERT INTO `ep_op_def` VALUES ('confirm_error','00000056','总行确认错误点');
INSERT INTO `ep_op_def` VALUES ('abc_bus_checkback','00000056','商务卡总行退回');
INSERT INTO `ep_op_def` VALUES ('abc_bus_checksubmit','00000056','商务卡总行提交');
INSERT INTO `ep_op_def` VALUES ('abc_getback','00000056','总行取回');
INSERT INTO `ep_op_def` VALUES ('indepInfo_d','00000205','独立审批人录入');
INSERT INTO `ep_op_def` VALUES ('indepInfo_f','00000206','独立审批人复核');
INSERT INTO `ep_op_def` VALUES ('indepInfo_s','00000207','独立审批人管理');
INSERT INTO `ep_op_def` VALUES ('pro_batjudged','00000056','批量上送已审批资料');
INSERT INTO `ep_op_def` VALUES ('revise','00000056','复核员修改申请资料');
INSERT INTO `ep_op_def` VALUES ('ep_perrole_level','00000210','永久额度角色等级调整');
INSERT INTO `ep_op_def` VALUES ('ep_peramount_adjust','00000211','永久额度调整录入');
INSERT INTO `ep_op_def` VALUES ('ep_pamount_adjust_c','00000212','永久额度调整审批');
INSERT INTO `ep_op_def` VALUES ('translist_dl','00000011','下载贷记卡入账交易查询结果');
INSERT INTO `ep_op_def` VALUES ('fixtrans_s','00000238','固定转存查询');
INSERT INTO `ep_op_def` VALUES ('fixtrans_u','00000238','固定转存修改');
INSERT INTO `ep_op_def` VALUES ('ep_amt_control_c','00000239','额度控制参数增加');
INSERT INTO `ep_op_def` VALUES ('ep_amt_control_u','00000239','额度控制参数修改');
INSERT INTO `ep_op_def` VALUES ('ep_amt_control_d','00000239','额度控制参数删除');
INSERT INTO `ep_op_def` VALUES ('ep_amount_force','00000192','临时额度强制通过');
INSERT INTO `ep_op_def` VALUES ('ep_peramount_force','00000213','永久额度强制通过');
INSERT INTO `ep_op_def` VALUES ('OrgProduct_d','00000084','删除机构产品授权');
INSERT INTO `ep_op_def` VALUES ('allowInPlan_c','00000244','准入条件增加');
INSERT INTO `ep_op_def` VALUES ('allowInPlan_d','00000244','准入条件删除');
INSERT INTO `ep_op_def` VALUES ('allowInPlan_u','00000244','准入条件修改');
INSERT INTO `ep_op_def` VALUES ('condConf_c','00000245','调整条件增加');
INSERT INTO `ep_op_def` VALUES ('condConf_d','00000245','调整条件删除');
INSERT INTO `ep_op_def` VALUES ('condConf_u','00000245','调整条件修改');
INSERT INTO `ep_op_def` VALUES ('stardConf_c','00000246','额度提升标准增加');
INSERT INTO `ep_op_def` VALUES ('stardConf_d','00000246','额度提升标准删除');
INSERT INTO `ep_op_def` VALUES ('stardConf_u','00000246','额度提升标准修改');
INSERT INTO `ep_op_def` VALUES ('query','00000247','查询项目');
INSERT INTO `ep_op_def` VALUES ('itemmaintain','00000247','项目信息维护');
INSERT INTO `ep_op_def` VALUES ('tradequery','00000248','查询商户');
INSERT INTO `ep_op_def` VALUES ('trademaintain','00000248','商户信息维护');
INSERT INTO `ep_op_def` VALUES ('linkquery','00000249','查询项目与商户关联信息');
INSERT INTO `ep_op_def` VALUES ('linkmaintain','00000249','项目与商户关联信息维护');
INSERT INTO `ep_op_def` VALUES ('op_u','00000026','修改操作权限');
INSERT INTO `ep_op_def` VALUES ('billInfo_u','00000179','修改商务卡公司卡对账单信息参数配置');
INSERT INTO `ep_op_def` VALUES ('billInfo_d','00000179','删除商务卡公司卡对账单信息参数配置');
INSERT INTO `ep_op_def` VALUES ('HisSearchQuery','00000250','历史交易明细查询');
INSERT INTO `ep_op_def` VALUES ('HisstmtRegen_Print','00000250','历史信息打印');
INSERT INTO `ep_op_def` VALUES ('batch_start','00000252','批量作业启动');
INSERT INTO `ep_op_def` VALUES ('phone','00000258','电话核实');
INSERT INTO `ep_op_def` VALUES ('assign_apply','00000261','申请资料指转派');
INSERT INTO `ep_op_def` VALUES ('taskEdit','00000265','待删除任务修改');
INSERT INTO `ep_op_def` VALUES ('toBeCheckEdit','00000266','待复核任务修改');
INSERT INTO `ep_op_def` VALUES ('checkToBeChecked','00000266','待复核任务复核');
INSERT INTO `ep_op_def` VALUES ('financeedit','00000288','当日查询修改');
INSERT INTO `ep_op_def` VALUES ('financedelete','00000288','当日查询删除');
INSERT INTO `ep_op_def` VALUES ('fscheck','00000288','当日查询复核');
INSERT INTO `ep_op_def` VALUES ('auditdelete','00000288','当日查询审批删除');
INSERT INTO `ep_op_def` VALUES ('financeaudit','00000288','当日查询审批');
INSERT INTO `ep_op_def` VALUES ('queryActStat','00000293','行动码统计查询');
INSERT INTO `ep_op_def` VALUES ('queryCollExitedHis','00000294','已退出催收系统账户的催收历史查询');
INSERT INTO `ep_op_def` VALUES ('collAcctAssign_u','00000295','催收账户分配比例调整修改');
INSERT INTO `ep_op_def` VALUES ('mDic_c','00000046','多级数据字典增加');
INSERT INTO `ep_op_def` VALUES ('mDic_u','00000046','修改多级数据字典');
INSERT INTO `ep_op_def` VALUES ('mDic_d','00000046','删除多级数据字典');
INSERT INTO `ep_op_def` VALUES ('cleanAdjust_input','00000304','清算调账录入');
INSERT INTO `ep_op_def` VALUES ('cleanAdjust_verify','00000304','清算调账复核');
INSERT INTO `ep_op_def` VALUES ('cleanAdjust_audit','00000304','清算调账审核');
INSERT INTO `ep_op_def` VALUES ('synConfig_a','00000322','增加数据同步项');
INSERT INTO `ep_op_def` VALUES ('synConfig_d','00000322','删除数据同步项');
INSERT INTO `ep_op_def` VALUES ('synConfig_e','00000322','编辑数据同步项');
INSERT INTO `ep_op_def` VALUES ('synMonitor_retry','00000323','数据同步重提');
INSERT INTO `ep_op_def` VALUES ('synMonitor_stop','00000323','数据同步停止');
INSERT INTO `ep_op_def` VALUES ('pick','00000088','挑选卡号');
INSERT INTO `ep_op_def` VALUES ('autoCommit','00000342','自动提交');
INSERT INTO `ep_op_def` VALUES ('orgProduct_all','00000084','全国机构统一维护产品');
INSERT INTO `ep_op_def` VALUES ('12345','00000125','锁定码调整');
INSERT INTO `ep_op_def` VALUES ('tASK_LIST_c','00000330','工作日报填写');
/*!40000 ALTER TABLE `ep_op_def` ENABLE KEYS */;
UNLOCK TABLES;

#
# Source for table ep_op_log
#

DROP TABLE IF EXISTS `ep_op_log`;
CREATE TABLE `ep_op_log` (
  `SYS_ID` decimal(16,0) NOT NULL,
  `EVENT_TIME` char(14) NOT NULL,
  `USER_ID` char(8) NOT NULL,
  `EVENT_TYPE` char(12) NOT NULL,
  `ORG_ID` char(9) NOT NULL,
  `ROLE_ID` char(8) DEFAULT NULL,
  `OP_ID` char(8) NOT NULL,
  `MEMO` varchar(60) NOT NULL,
  `OP_KEY` char(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=gbk;

#
# Dumping data for table ep_op_log
#
LOCK TABLES `ep_op_log` WRITE;
/*!40000 ALTER TABLE `ep_op_log` DISABLE KEYS */;

/*!40000 ALTER TABLE `ep_op_log` ENABLE KEYS */;
UNLOCK TABLES;

#
# Source for table ep_pmkey
#

DROP TABLE IF EXISTS `ep_pmkey`;
CREATE TABLE `ep_pmkey` (
  `TB_NAME` varchar(20) NOT NULL,
  `KEY_FIELD` varchar(20) NOT NULL,
  `STEP_LEN` int(11) NOT NULL,
  `MAX_VAL` decimal(20,0) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=gbk;

#
# Dumping data for table ep_pmkey
#
LOCK TABLES `ep_pmkey` WRITE;
/*!40000 ALTER TABLE `ep_pmkey` DISABLE KEYS */;

INSERT INTO `ep_pmkey` VALUES ('ep_sdic','SYS_ID',100,24500);
INSERT INTO `ep_pmkey` VALUES ('ep_homepage','TEMPL_ID',6,72);
INSERT INTO `ep_pmkey` VALUES ('ep_mdic','SYS_ID',50,760);
INSERT INTO `ep_pmkey` VALUES ('TASK_LIST','TASK_NO',50,41725);
INSERT INTO `ep_pmkey` VALUES ('lx_info','lx_id',10,90);
/*!40000 ALTER TABLE `ep_pmkey` ENABLE KEYS */;
UNLOCK TABLES;

#
# Source for table ep_redef_mdic
#

DROP TABLE IF EXISTS `ep_redef_mdic`;
CREATE TABLE `ep_redef_mdic` (
  `TYPE_ID` char(4) NOT NULL,
  `CAPTION` varchar(20) NOT NULL,
  `STMT` varchar(400) NOT NULL,
  `MEMO` varchar(80) DEFAULT NULL,
  `REG_DT` char(8) NOT NULL,
  `USER_ID` char(8) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=gbk;

#
# Dumping data for table ep_redef_mdic
#
LOCK TABLES `ep_redef_mdic` WRITE;
/*!40000 ALTER TABLE `ep_redef_mdic` DISABLE KEYS */;

/*!40000 ALTER TABLE `ep_redef_mdic` ENABLE KEYS */;
UNLOCK TABLES;

#
# Source for table ep_redef_sdic
#

DROP TABLE IF EXISTS `ep_redef_sdic`;
CREATE TABLE `ep_redef_sdic` (
  `TYPE_ID` char(4) NOT NULL,
  `CAPTION` varchar(30) NOT NULL,
  `STMT` varchar(400) NOT NULL,
  `MEMO` varchar(80) DEFAULT NULL,
  `REG_DT` char(8) NOT NULL,
  `USER_ID` char(8) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=gbk;

#
# Dumping data for table ep_redef_sdic
#
LOCK TABLES `ep_redef_sdic` WRITE;
/*!40000 ALTER TABLE `ep_redef_sdic` DISABLE KEYS */;

INSERT INTO `ep_redef_sdic` VALUES ('0001','首页模板字典','select TEMPL_ID as ITEM_CODE,TEMPL_ID  as LOGIC_ID,CAPTION as ITEM_VAL  from ep_homepage',' ','20060615','00000100');
INSERT INTO `ep_redef_sdic` VALUES ('0002','角色字典','select ROLE_ID as ITEM_CODE,LOGIC_ID  as LOGIC_ID,ROLE_NAME as ITEM_VAL  from ep_role','角色信息缓存','20060623','00000100');
INSERT INTO `ep_redef_sdic` VALUES ('0003','用户字典','select USER_ID as ITEM_CODE,USER_ID as LOGIC_ID,USER_NAME as ITEM_VAL from ep_user','用户信息缓存字典','20060116','00000100');
INSERT INTO `ep_redef_sdic` VALUES ('9999','员工','select a.USER_ID as ITEM_CODE,a.USER_ID as LOGIC_ID,a.USER_NAME as ITEM_VAL from ep_user a,ep_user_role b,ep_role c\r\nwhere a.USER_ID=b.USER_ID and b.ROLE_ID=c.ROLE_ID and c.LOGIC_ID=\'stuff\'','','20110113','00000200');
/*!40000 ALTER TABLE `ep_redef_sdic` ENABLE KEYS */;
UNLOCK TABLES;

#
# Source for table ep_role
#

DROP TABLE IF EXISTS `ep_role`;
CREATE TABLE `ep_role` (
  `ROLE_ID` char(8) NOT NULL,
  `ROLE_NAME` varchar(40) NOT NULL,
  `LOGIC_ID` char(8) DEFAULT NULL,
  `TEMPL_ID` int(11) NOT NULL,
  `SESN_TIME` int(11) NOT NULL,
  `STAT` char(1) NOT NULL DEFAULT '',
  `USER_ID` char(8) NOT NULL,
  `REG_DT` char(8) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=gbk;

#
# Dumping data for table ep_role
#
LOCK TABLES `ep_role` WRITE;
/*!40000 ALTER TABLE `ep_role` DISABLE KEYS */;

INSERT INTO `ep_role` VALUES ('00000001','管理员','00000000',67,1,'1','00000200','20110113');
INSERT INTO `ep_role` VALUES ('00000098','项目版本管理人员','manager',67,60000,'1','00000200','20110113');
INSERT INTO `ep_role` VALUES ('00000099','员工','stuff',67,60000,'1','00000200','20110113');
/*!40000 ALTER TABLE `ep_role` ENABLE KEYS */;
UNLOCK TABLES;

#
# Source for table ep_role_auth
#

DROP TABLE IF EXISTS `ep_role_auth`;
CREATE TABLE `ep_role_auth` (
  `ROLE_ID` char(8) NOT NULL,
  `OP_CODE` char(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=gbk;

#
# Dumping data for table ep_role_auth
#
LOCK TABLES `ep_role_auth` WRITE;
/*!40000 ALTER TABLE `ep_role_auth` DISABLE KEYS */;

INSERT INTO `ep_role_auth` VALUES ('00000071','mDic_c');
INSERT INTO `ep_role_auth` VALUES ('00000071','mDic_d');
INSERT INTO `ep_role_auth` VALUES ('00000071','mDic_u');
INSERT INTO `ep_role_auth` VALUES ('00000071','menu_c');
INSERT INTO `ep_role_auth` VALUES ('00000071','menu_d');
INSERT INTO `ep_role_auth` VALUES ('00000071','menu_u');
INSERT INTO `ep_role_auth` VALUES ('00000002','reset_pwd');
INSERT INTO `ep_role_auth` VALUES ('00000002','user_c');
INSERT INTO `ep_role_auth` VALUES ('00000002','user_disable');
INSERT INTO `ep_role_auth` VALUES ('00000002','user_enable');
INSERT INTO `ep_role_auth` VALUES ('00000002','user_terminate');
INSERT INTO `ep_role_auth` VALUES ('00000002','user_u');
INSERT INTO `ep_role_auth` VALUES ('00000071','op_c');
INSERT INTO `ep_role_auth` VALUES ('00000071','op_d');
INSERT INTO `ep_role_auth` VALUES ('00000071','op_u');
INSERT INTO `ep_role_auth` VALUES ('00000071','pmKey_c');
INSERT INTO `ep_role_auth` VALUES ('00000071','pmKey_d');
INSERT INTO `ep_role_auth` VALUES ('00000071','pmKey_u');
INSERT INTO `ep_role_auth` VALUES ('00000071','reDefSDic_c');
INSERT INTO `ep_role_auth` VALUES ('00000071','reDefSDic_d');
INSERT INTO `ep_role_auth` VALUES ('00000071','reDefSDic_u');
INSERT INTO `ep_role_auth` VALUES ('00000071','relaccount');
INSERT INTO `ep_role_auth` VALUES ('00000071','reset_pwd');
INSERT INTO `ep_role_auth` VALUES ('00000071','revise');
INSERT INTO `ep_role_auth` VALUES ('00000001','user_c');
INSERT INTO `ep_role_auth` VALUES ('00000001','user_u');
INSERT INTO `ep_role_auth` VALUES ('00000001','user_d');
INSERT INTO `ep_role_auth` VALUES ('00000001','menu_c');
INSERT INTO `ep_role_auth` VALUES ('00000001','menu_u');
INSERT INTO `ep_role_auth` VALUES ('00000001','menu_d');
INSERT INTO `ep_role_auth` VALUES ('00000001','user_enable');
INSERT INTO `ep_role_auth` VALUES ('00000001','user_disable');
INSERT INTO `ep_role_auth` VALUES ('00000001','user_terminate');
INSERT INTO `ep_role_auth` VALUES ('00000001','role_c');
INSERT INTO `ep_role_auth` VALUES ('00000001','role_u');
INSERT INTO `ep_role_auth` VALUES ('00000001','role_terminate');
INSERT INTO `ep_role_auth` VALUES ('00000001','role_d');
INSERT INTO `ep_role_auth` VALUES ('00000001','role_perm');
INSERT INTO `ep_role_auth` VALUES ('00000001','op_c');
INSERT INTO `ep_role_auth` VALUES ('00000001','op_d');
INSERT INTO `ep_role_auth` VALUES ('00000001','pmKey_c');
INSERT INTO `ep_role_auth` VALUES ('00000001','pmKey_u');
INSERT INTO `ep_role_auth` VALUES ('00000001','pmKey_d');
INSERT INTO `ep_role_auth` VALUES ('00000001','dicType_c');
INSERT INTO `ep_role_auth` VALUES ('00000001','dicType_u');
INSERT INTO `ep_role_auth` VALUES ('00000001','dicType_d');
INSERT INTO `ep_role_auth` VALUES ('00000001','homepage_c');
INSERT INTO `ep_role_auth` VALUES ('00000001','homepage_u');
INSERT INTO `ep_role_auth` VALUES ('00000001','homepage_d');
INSERT INTO `ep_role_auth` VALUES ('00000001','sDic_c');
INSERT INTO `ep_role_auth` VALUES ('00000001','sDic_u');
INSERT INTO `ep_role_auth` VALUES ('00000001','sDic_d');
INSERT INTO `ep_role_auth` VALUES ('00000001','reset_pwd');
INSERT INTO `ep_role_auth` VALUES ('00000001','op_u');
INSERT INTO `ep_role_auth` VALUES ('00000001','mDic_c');
INSERT INTO `ep_role_auth` VALUES ('00000001','mDic_u');
INSERT INTO `ep_role_auth` VALUES ('00000001','mDic_d');
INSERT INTO `ep_role_auth` VALUES ('00000001','reDefSDic_c');
INSERT INTO `ep_role_auth` VALUES ('00000001','reDefSDic_u');
INSERT INTO `ep_role_auth` VALUES ('00000001','reDefSDic_d');
INSERT INTO `ep_role_auth` VALUES ('00000099','tASK_LIST_c');
/*!40000 ALTER TABLE `ep_role_auth` ENABLE KEYS */;
UNLOCK TABLES;

#
# Source for table ep_role_menu
#

DROP TABLE IF EXISTS `ep_role_menu`;
CREATE TABLE `ep_role_menu` (
  `ROLE_ID` char(8) NOT NULL,
  `MENU_ID` char(8) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=gbk;

#
# Dumping data for table ep_role_menu
#
LOCK TABLES `ep_role_menu` WRITE;
/*!40000 ALTER TABLE `ep_role_menu` DISABLE KEYS */;

INSERT INTO `ep_role_menu` VALUES ('00000098','00000324');
INSERT INTO `ep_role_menu` VALUES ('00000098','00000325');
INSERT INTO `ep_role_menu` VALUES ('00000098','00000326');
INSERT INTO `ep_role_menu` VALUES ('00000001','00000324');
INSERT INTO `ep_role_menu` VALUES ('00000001','00000013');
INSERT INTO `ep_role_menu` VALUES ('00000001','00000016');
INSERT INTO `ep_role_menu` VALUES ('00000001','00000015');
INSERT INTO `ep_role_menu` VALUES ('00000001','00000022');
INSERT INTO `ep_role_menu` VALUES ('00000001','00000023');
INSERT INTO `ep_role_menu` VALUES ('00000001','00000024');
INSERT INTO `ep_role_menu` VALUES ('00000001','00000025');
INSERT INTO `ep_role_menu` VALUES ('00000001','00000026');
INSERT INTO `ep_role_menu` VALUES ('00000001','00000045');
INSERT INTO `ep_role_menu` VALUES ('00000001','00000046');
INSERT INTO `ep_role_menu` VALUES ('00000001','00000332');
INSERT INTO `ep_role_menu` VALUES ('00000001','00000325');
INSERT INTO `ep_role_menu` VALUES ('00000001','00000326');
INSERT INTO `ep_role_menu` VALUES ('00000001','00000327');
INSERT INTO `ep_role_menu` VALUES ('00000001','00000328');
INSERT INTO `ep_role_menu` VALUES ('00000001','00000333');
INSERT INTO `ep_role_menu` VALUES ('00000099','00000329');
INSERT INTO `ep_role_menu` VALUES ('00000099','00000330');
INSERT INTO `ep_role_menu` VALUES ('00000099','00000331');
INSERT INTO `ep_role_menu` VALUES ('00000099','00000334');
/*!40000 ALTER TABLE `ep_role_menu` ENABLE KEYS */;
UNLOCK TABLES;

#
# Source for table ep_sdic
#

DROP TABLE IF EXISTS `ep_sdic`;
CREATE TABLE `ep_sdic` (
  `SYS_ID` decimal(12,0) NOT NULL,
  `TYPE_ID` char(4) DEFAULT NULL,
  `ITEM_CODE` varchar(12) NOT NULL,
  `ITEM_VAL` varchar(40) NOT NULL,
  `LIST_ORDER` smallint(6) NOT NULL,
  `LOGIC_ID` varchar(12) DEFAULT NULL,
  `STAT` char(1) NOT NULL DEFAULT ''
) ENGINE=InnoDB DEFAULT CHARSET=gbk;

#
# Dumping data for table ep_sdic
#
LOCK TABLES `ep_sdic` WRITE;
/*!40000 ALTER TABLE `ep_sdic` DISABLE KEYS */;

INSERT INTO `ep_sdic` VALUES (1,'0004','5','总行',1,'5','1');
INSERT INTO `ep_sdic` VALUES (2,'0004','3','省分行',2,'3','1');
INSERT INTO `ep_sdic` VALUES (3,'0001','0','作废',3,'0','1');
INSERT INTO `ep_sdic` VALUES (4,'0001','1','正常',1,'1','1');
INSERT INTO `ep_sdic` VALUES (5,'0001','2','停用',2,'2','1');
INSERT INTO `ep_sdic` VALUES (7,'0002','0','作废',2,'0','1');
INSERT INTO `ep_sdic` VALUES (8,'0002','1','正常',1,'1','1');
INSERT INTO `ep_sdic` VALUES (101,'0003','0','文件夹',1,'0','1');
INSERT INTO `ep_sdic` VALUES (102,'0003','1','叶子节点',2,'1','1');
INSERT INTO `ep_sdic` VALUES (201,'0007','0','待复核',1,'0','1');
INSERT INTO `ep_sdic` VALUES (202,'0007','1','锁定',2,'1','1');
INSERT INTO `ep_sdic` VALUES (203,'0007','2','退回',3,'2','1');
INSERT INTO `ep_sdic` VALUES (302,'0008','1','本币',1,'1','1');
INSERT INTO `ep_sdic` VALUES (303,'0008','2','外币',2,'2','1');
INSERT INTO `ep_sdic` VALUES (501,'0005','c','增加',1,'c','1');
INSERT INTO `ep_sdic` VALUES (502,'0005','u','修改',2,'u','1');
INSERT INTO `ep_sdic` VALUES (503,'0005','r','查询',3,'r','1');
INSERT INTO `ep_sdic` VALUES (504,'0005','d','删除',4,'d','1');
INSERT INTO `ep_sdic` VALUES (508,'0006','01','登录',1,'01','1');
INSERT INTO `ep_sdic` VALUES (509,'0006','02','业务处理',2,'02','1');
INSERT INTO `ep_sdic` VALUES (510,'0006','03','系统维护',3,'03','1');
INSERT INTO `ep_sdic` VALUES (601,'0005','login','登录',6,'l','1');
INSERT INTO `ep_sdic` VALUES (602,'0005','logout','签退',7,'lo','1');
INSERT INTO `ep_sdic` VALUES (1551,'0000','1','是',1,'1','1');
INSERT INTO `ep_sdic` VALUES (1552,'0000','0','否',2,'0','1');
INSERT INTO `ep_sdic` VALUES (12851,'9994','0','BRD',0,'0','1');
INSERT INTO `ep_sdic` VALUES (12852,'9994','1','FTS',1,'1','1');
INSERT INTO `ep_sdic` VALUES (12853,'9994','2','DEV',2,'2','1');
INSERT INTO `ep_sdic` VALUES (12854,'9994','3','SIT',3,'3','1');
INSERT INTO `ep_sdic` VALUES (12855,'9994','4','UAT',4,'4','1');
INSERT INTO `ep_sdic` VALUES (12856,'9994','5','PRD',5,'5','1');
INSERT INTO `ep_sdic` VALUES (12858,'9994','7','PAUSE',7,'7','1');
INSERT INTO `ep_sdic` VALUES (12859,'9992','1','需求分析',1,'1','1');
INSERT INTO `ep_sdic` VALUES (12860,'9992','2','程序开发',2,'2','1');
INSERT INTO `ep_sdic` VALUES (12861,'9992','3','集成测试',3,'3','1');
INSERT INTO `ep_sdic` VALUES (12862,'9992','4','文档',4,'4','1');
INSERT INTO `ep_sdic` VALUES (12863,'9992','5','会议',5,'5','1');
INSERT INTO `ep_sdic` VALUES (12864,'9992','6','研析',6,'6','1');
INSERT INTO `ep_sdic` VALUES (12865,'9992','7','维护',7,'7','1');
INSERT INTO `ep_sdic` VALUES (12866,'9992','8','问题单处理',8,'8','1');
INSERT INTO `ep_sdic` VALUES (12951,'9994','8','无',8,'8','1');
INSERT INTO `ep_sdic` VALUES (13051,'9992','9','咨询报告',9,'9','1');
INSERT INTO `ep_sdic` VALUES (13052,'9992','A','其它',10,'A','1');
INSERT INTO `ep_sdic` VALUES (13351,'9993','0','未解决',1,'0','1');
INSERT INTO `ep_sdic` VALUES (13352,'9993','1','以后解决',2,'1','1');
INSERT INTO `ep_sdic` VALUES (13353,'9993','2','已解决',3,'2','1');
INSERT INTO `ep_sdic` VALUES (13354,'9993','3','已删除',4,'3','1');
INSERT INTO `ep_sdic` VALUES (13451,'9992','B','进度计划',11,'B','1');
INSERT INTO `ep_sdic` VALUES (13452,'9992','C','需求设计',12,'C','1');
INSERT INTO `ep_sdic` VALUES (13453,'9992','D','UAT支持',13,'D','1');
INSERT INTO `ep_sdic` VALUES (13454,'9992','E','投产实施',14,'E','1');
INSERT INTO `ep_sdic` VALUES (13455,'9992','F','问题处理',15,'F','1');
INSERT INTO `ep_sdic` VALUES (13456,'9992','G','方案建议',16,'G','1');
INSERT INTO `ep_sdic` VALUES (13457,'9992','H','数据提取',17,'H','1');
INSERT INTO `ep_sdic` VALUES (13458,'9992','I','FIX分析',18,'I','1');
INSERT INTO `ep_sdic` VALUES (13459,'9992','J','版本升级',19,'J','1');
INSERT INTO `ep_sdic` VALUES (13460,'9992','K','版本管理',20,'K','1');
INSERT INTO `ep_sdic` VALUES (13461,'9992','L','成果归档',21,'L','1');
INSERT INTO `ep_sdic` VALUES (13462,'9992','M','培训',22,'M','1');
INSERT INTO `ep_sdic` VALUES (13463,'9992','N','工时统计',23,'N','1');
INSERT INTO `ep_sdic` VALUES (13464,'9992','O','验收',24,'O','1');
INSERT INTO `ep_sdic` VALUES (13465,'9991','1','10%',1,'1','1');
INSERT INTO `ep_sdic` VALUES (13466,'9991','2','20%',2,'2','1');
INSERT INTO `ep_sdic` VALUES (13467,'9991','3','30%',3,'3','1');
INSERT INTO `ep_sdic` VALUES (13468,'9991','4','40%',4,'4','1');
INSERT INTO `ep_sdic` VALUES (13469,'9991','5','50%',5,'5','1');
INSERT INTO `ep_sdic` VALUES (13470,'9991','6','60%',6,'6','1');
INSERT INTO `ep_sdic` VALUES (13471,'9991','7','70%',7,'7','1');
INSERT INTO `ep_sdic` VALUES (13472,'9991','8','80%',8,'8','1');
INSERT INTO `ep_sdic` VALUES (13473,'9991','9','90%',9,'9','1');
INSERT INTO `ep_sdic` VALUES (13474,'9991','0','100%',10,'0','1');
INSERT INTO `ep_sdic` VALUES (13851,'9990','A','北分副总',1,'A','1');
INSERT INTO `ep_sdic` VALUES (13853,'9990','C','北分行政',3,'C','1');
INSERT INTO `ep_sdic` VALUES (13854,'9990','D','北分人事',4,'D','1');
INSERT INTO `ep_sdic` VALUES (13855,'9990','E','北分IT',5,'E','1');
INSERT INTO `ep_sdic` VALUES (13856,'9990','F','北分会计',6,'F','1');
INSERT INTO `ep_sdic` VALUES (13857,'9990','G','北分出纳',7,'G','1');
INSERT INTO `ep_sdic` VALUES (13861,'9990','K','农行贷记卡系统运维Y2008',11,'K','1');
INSERT INTO `ep_sdic` VALUES (13862,'9990','L','农行外挂系统运维Y2008',12,'L','1');
INSERT INTO `ep_sdic` VALUES (13951,'9990','M','其它',13,'M','1');
INSERT INTO `ep_sdic` VALUES (13952,'9990','N','农行贷记卡客服二期接口',14,'N','1');
INSERT INTO `ep_sdic` VALUES (14551,'9989','1','DEV',1,'1','1');
INSERT INTO `ep_sdic` VALUES (14552,'9989','2','SIT',2,'2','1');
INSERT INTO `ep_sdic` VALUES (14553,'9989','3','UAT',3,'3','1');
INSERT INTO `ep_sdic` VALUES (14554,'9989','4','REL',4,'4','1');
INSERT INTO `ep_sdic` VALUES (14555,'9989','0','BRD',0,'0','1');
INSERT INTO `ep_sdic` VALUES (14651,'9989','9','删除',9,'9','1');
INSERT INTO `ep_sdic` VALUES (14563,'9992','P','病假',25,'P','1');
INSERT INTO `ep_sdic` VALUES (14564,'9992','Q','事假',26,'Q','1');
INSERT INTO `ep_sdic` VALUES (14565,'9992','R','年休假',27,'R','1');
INSERT INTO `ep_sdic` VALUES (14566,'9992','S','值班调休',28,'S','1');
INSERT INTO `ep_sdic` VALUES (14567,'9992','T','工作报告',29,'T','1');
INSERT INTO `ep_sdic` VALUES (14568,'9992','U','资源报告',30,'U','1');
INSERT INTO `ep_sdic` VALUES (14569,'9992','V','版本核对',31,'V','1');
INSERT INTO `ep_sdic` VALUES (14570,'9992','W','代码归档',32,'W','1');
INSERT INTO `ep_sdic` VALUES (14853,'9994','9','删除',9,'9','1');
INSERT INTO `ep_sdic` VALUES (16751,'9990','K','外挂系统升级Y08及Y09',11,'K','1');
INSERT INTO `ep_sdic` VALUES (17251,'9988','1','07-08年V+运维合同',1,'1','1');
INSERT INTO `ep_sdic` VALUES (17252,'9988','2','08-09年V+运维合同',2,'2','1');
INSERT INTO `ep_sdic` VALUES (17253,'9988','3','08-09年外挂运维合同',3,'3','1');
INSERT INTO `ep_sdic` VALUES (17254,'9988','4','V+二期开发合同',4,'4','1');
INSERT INTO `ep_sdic` VALUES (17351,'9988','5','客服二期合同',5,'5','1');
INSERT INTO `ep_sdic` VALUES (17352,'9988','6','V+开发合同外',6,'6','1');
INSERT INTO `ep_sdic` VALUES (17353,'9988','7','外挂开发合同外',7,'7','1');
INSERT INTO `ep_sdic` VALUES (19151,'9986','1','问题单处理',1,'1','1');
INSERT INTO `ep_sdic` VALUES (19152,'9986','2','客户化问题修正',2,'2','1');
INSERT INTO `ep_sdic` VALUES (19153,'9986','3','产品问题修正',3,'3','1');
INSERT INTO `ep_sdic` VALUES (19154,'9986','4','数据提取',4,'4','1');
INSERT INTO `ep_sdic` VALUES (19155,'9986','5','配合系统环境变更等',5,'5','1');
INSERT INTO `ep_sdic` VALUES (19156,'9986','6','V+产品FIX分析',6,'6','1');
INSERT INTO `ep_sdic` VALUES (19157,'9986','7','V+升级',7,'7','1');
INSERT INTO `ep_sdic` VALUES (19158,'9986','8','国际组织升级（VISA、MasterCard各两次）',8,'8','1');
INSERT INTO `ep_sdic` VALUES (19159,'9986','9','国际组织专用软件升级',9,'9','1');
INSERT INTO `ep_sdic` VALUES (19160,'9986','10','客户化升级',10,'10','1');
INSERT INTO `ep_sdic` VALUES (19161,'9986','11','新功能新产品咨询',11,'11','1');
INSERT INTO `ep_sdic` VALUES (19162,'9986','12','新功能新产品启用',12,'12','1');
INSERT INTO `ep_sdic` VALUES (19163,'9986','13','参数调整分析/测试',13,'13','1');
INSERT INTO `ep_sdic` VALUES (19164,'9986','14','数据文件预防检查',14,'14','1');
INSERT INTO `ep_sdic` VALUES (19165,'9986','15','批量运行优化',15,'15','1');
INSERT INTO `ep_sdic` VALUES (19166,'9986','16','灾难预案',16,'16','1');
INSERT INTO `ep_sdic` VALUES (19167,'9986','17','其他监控分析',17,'17','1');
INSERT INTO `ep_sdic` VALUES (19168,'9986','18','数据清理及备份',18,'18','1');
INSERT INTO `ep_sdic` VALUES (19169,'9987','1','问题单处理',1,'1','1');
INSERT INTO `ep_sdic` VALUES (19170,'9987','2','程序问题修正',2,'2','1');
INSERT INTO `ep_sdic` VALUES (19171,'9987','3','配合系统环境变更',3,'3','1');
INSERT INTO `ep_sdic` VALUES (19172,'9987','4','系统监控分析',4,'4','1');
INSERT INTO `ep_sdic` VALUES (19173,'9987','5','联名卡参数方案及技术配合',5,'5','1');
INSERT INTO `ep_sdic` VALUES (19174,'9987','6','程序或系统优化',6,'6','1');
INSERT INTO `ep_sdic` VALUES (19175,'9987','7','数据清理及备份',7,'7','1');
INSERT INTO `ep_sdic` VALUES (19176,'9987','8','联机数据库拆分',8,'8','1');
INSERT INTO `ep_sdic` VALUES (19177,'9987','9','历史数据库拆分',9,'9','1');
/*!40000 ALTER TABLE `ep_sdic` ENABLE KEYS */;
UNLOCK TABLES;

#
# Source for table ep_user
#

DROP TABLE IF EXISTS `ep_user`;
CREATE TABLE `ep_user` (
  `USER_ID` char(8) NOT NULL,
  `DEPART_ID` char(8) DEFAULT NULL,
  `ROLE_ID` char(8) NOT NULL,
  `LOGIN_ID` char(20) NOT NULL,
  `USER_NAME` char(18) NOT NULL,
  `PASSWORD` char(32) NOT NULL,
  `PHONE` varchar(30) DEFAULT NULL,
  `MOBILE` char(11) DEFAULT NULL,
  `EMAIL` varchar(60) DEFAULT NULL,
  `POSTCODE` char(6) DEFAULT NULL,
  `ADDRESS` varchar(200) DEFAULT NULL,
  `STAT` char(1) NOT NULL DEFAULT '',
  `REG_DT` char(8) NOT NULL,
  `BEGIN_DT` char(8) NOT NULL,
  `INVALID_DT` char(8) NOT NULL,
  `MODIFY_DT` char(8) NOT NULL,
  `MEMO` char(200) DEFAULT NULL,
  `ST_CHG_DT` char(8) NOT NULL,
  `ADMIN_ID` char(18) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=gbk;

#
# Dumping data for table ep_user
#
LOCK TABLES `ep_user` WRITE;
/*!40000 ALTER TABLE `ep_user` DISABLE KEYS */;

INSERT INTO `ep_user` VALUES ('00000200','01','00000001','houxh','houxh','e99a18c428cb38d5f260853678922e03',' ','00000000000',' ','',' ','1','20061019','20061019','20991212','20991212','','20100831','00000200');
INSERT INTO `ep_user` VALUES ('00000201','01','00000099','test','test','e99a18c428cb38d5f260853678922e03','','','','','','1','20110113','20110113','20130113','20110423','','20110113','00000200');
/*!40000 ALTER TABLE `ep_user` ENABLE KEYS */;
UNLOCK TABLES;

#
# Source for table ep_user_login
#

DROP TABLE IF EXISTS `ep_user_login`;
CREATE TABLE `ep_user_login` (
  `USER_ID` char(8) NOT NULL,
  `LOGIN_TIME` char(14) NOT NULL,
  `CLIENT_IP` char(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=gbk;

#
# Dumping data for table ep_user_login
#
LOCK TABLES `ep_user_login` WRITE;
/*!40000 ALTER TABLE `ep_user_login` DISABLE KEYS */;

/*!40000 ALTER TABLE `ep_user_login` ENABLE KEYS */;
UNLOCK TABLES;

#
# Source for table ep_user_role
#

DROP TABLE IF EXISTS `ep_user_role`;
CREATE TABLE `ep_user_role` (
  `USER_ID` char(8) NOT NULL,
  `ROLE_ID` char(8) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=gbk;

#
# Dumping data for table ep_user_role
#
LOCK TABLES `ep_user_role` WRITE;
/*!40000 ALTER TABLE `ep_user_role` DISABLE KEYS */;

INSERT INTO `ep_user_role` VALUES ('00000200','00000001');
INSERT INTO `ep_user_role` VALUES ('00000201','00000099');
/*!40000 ALTER TABLE `ep_user_role` ENABLE KEYS */;
UNLOCK TABLES;

#
# Source for table grade_define
#

DROP TABLE IF EXISTS `grade_define`;
CREATE TABLE `grade_define` (
  `CHECK_NO` char(2) NOT NULL DEFAULT '',
  `GRADE` char(1) NOT NULL DEFAULT '',
  `SCORE` int(11) DEFAULT NULL,
  `LOWER_SCORE` int(11) DEFAULT NULL,
  `MEMO` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `IN_USE` char(1) NOT NULL,
  PRIMARY KEY (`CHECK_NO`,`GRADE`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk;

#
# Dumping data for table grade_define
#
LOCK TABLES `grade_define` WRITE;
/*!40000 ALTER TABLE `grade_define` DISABLE KEYS */;

INSERT INTO `grade_define` VALUES ('1','1',100,91,'难度大，项目复杂，涉及系统架构的改造，需要深入研究','1');
INSERT INTO `grade_define` VALUES ('1','2',90,81,'难度大，涉及多支程序的修改，有些复杂逻辑','1');
INSERT INTO `grade_define` VALUES ('1','3',80,71,'难度适中，涉及多支程序修改','1');
INSERT INTO `grade_define` VALUES ('1','4',70,61,'正常，对现有架构程序进行逻辑调整','1');
INSERT INTO `grade_define` VALUES ('1','5',60,51,'简单程序改动或测试、无难度','1');
INSERT INTO `grade_define` VALUES ('1','6',50,1,'调整参数、无程序修改','1');
INSERT INTO `grade_define` VALUES ('10','1',100,91,'乐于接受工作任务安排、不拈轻怕重、积极思考、认真处理、主动要求承担额外任务和更多责任、善于发现问题并能够对本职工作提出建设性的建议','1');
INSERT INTO `grade_define` VALUES ('10','2',90,81,'能够接受工作任务安排、认真负责的完成、勇于承担责任，对个人的细小过失不是特别认真对待','1');
INSERT INTO `grade_define` VALUES ('10','3',80,71,'能够接受工作任务安排、有责任感、但做事欠周全和认真','1');
INSERT INTO `grade_define` VALUES ('10','4',70,61,'虽能够完成工作任务安排，缺乏责任感','1');
INSERT INTO `grade_define` VALUES ('10','5',60,31,'对工作任务的安排偶有抵触情绪、做事欠认真、盲于应付','1');
INSERT INTO `grade_define` VALUES ('10','6',50,1,'对工作任务的安排挑三拣四、推卸责任、无法按照要求完成工作目标','1');
INSERT INTO `grade_define` VALUES ('11','1',100,91,'有很强的团队合作意识、能够在团队内主动分享相关的技术和业务经验、能够积极协调相关的其他模块人员','1');
INSERT INTO `grade_define` VALUES ('11','2',90,81,'有团队合作意识、未能积极协助团队成员完成设计和开发工作','1');
INSERT INTO `grade_define` VALUES ('11','3',80,71,'能够独立完成自己的任务、团队意识缺乏','1');
INSERT INTO `grade_define` VALUES ('11','4',70,21,'孤立做事、不愿分享或帮助团队其他成员','1');
INSERT INTO `grade_define` VALUES ('11','5',20,1,'人际关系紧张、与人合作困难、我行我素','1');
INSERT INTO `grade_define` VALUES ('12','1',100,81,'严格遵守项目组规章制度、准时出勤、上班时间不做其他任何与工作无关的事情','1');
INSERT INTO `grade_define` VALUES ('12','2',80,61,'工作时间长期做工作无关的事情（上网\\聊天\\炒股\\游戏\\听歌等）','1');
INSERT INTO `grade_define` VALUES ('12','3',60,31,'无故迟到早退、工作时间擅自离岗外出、每日工时未满7.5小时','1');
INSERT INTO `grade_define` VALUES ('12','4',30,1,'在工作环境发表对客户、公司或同事的不满或个人抱怨，对违纪屡次提醒不予改正的','1');
INSERT INTO `grade_define` VALUES ('12','5',0,0,'故意破坏开发系统环境、泄密客户以及公司的内部资料','1');
INSERT INTO `grade_define` VALUES ('2','1',100,96,'提前30％ (含)及以上','1');
INSERT INTO `grade_define` VALUES ('2','2',95,91,'提前10％～30％','1');
INSERT INTO `grade_define` VALUES ('2','3',90,81,'准时完成','1');
INSERT INTO `grade_define` VALUES ('2','4',80,71,'延迟10％','1');
INSERT INTO `grade_define` VALUES ('2','5',70,61,'延迟20％','1');
INSERT INTO `grade_define` VALUES ('2','6',60,31,'延迟30％','1');
INSERT INTO `grade_define` VALUES ('2','7',30,1,'延迟30％到50％','1');
INSERT INTO `grade_define` VALUES ('2','8',0,0,'延迟50％以上','1');
INSERT INTO `grade_define` VALUES ('3','1',100,91,'功能涵盖全面，需求变更控制完整，技术方案描述思路清楚无异议，业务测试功能描述全面，性能、效率、可靠性、可用性考虑全面','1');
INSERT INTO `grade_define` VALUES ('3','2',90,81,'功能涵盖全面，技术方案描述清楚，程序、作业、参数、数据移植、接口等考虑全面，缺少较复杂逻辑流程图，程序无伪代码','1');
INSERT INTO `grade_define` VALUES ('3','3',80,71,'功能涵盖全面，技术方案描述清楚，无流程图，程序无伪代码，程序、作业、参数、数据移植接口等数据项描述不全','1');
INSERT INTO `grade_define` VALUES ('3','4',70,61,'技术方案描述不清楚，影响模块考虑不全，功能测试点考虑不全，重要项修改不全，需求及方案变更未补充','1');
INSERT INTO `grade_define` VALUES ('3','5',60,31,'功能涵盖不全，需求点有遗漏，功能测试点不全','1');
INSERT INTO `grade_define` VALUES ('3','6',30,1,'技术方案与业务需求存在偏差较大','1');
INSERT INTO `grade_define` VALUES ('4','1',100,91,'代码无BUG，性能优异，考虑复用，对方案提出修改建议，后续阶段无问题','1');
INSERT INTO `grade_define` VALUES ('4','2',90,81,'代码实现正确，流程清晰，存在部分不符合项目编码规范','1');
INSERT INTO `grade_define` VALUES ('4','3',80,71,'代码实现正确，对异常处理不完整','1');
INSERT INTO `grade_define` VALUES ('4','4',70,61,'单元测试成果（单元测试报告、一次出库清单、升级操作步骤、拷屏）提交不完整，质量有欠缺','1');
INSERT INTO `grade_define` VALUES ('4','5',60,31,'提供的程序、作业、脚本、环境部署存在严重问题','1');
INSERT INTO `grade_define` VALUES ('4','6',30,1,'开发的程序存在严重低级错误，无法正常运行','1');
INSERT INTO `grade_define` VALUES ('5','1',100,91,'测试点覆盖全面、条理清晰、UAT和投产无问题出现','1');
INSERT INTO `grade_define` VALUES ('5','2',90,81,'测试成果不达标（模板、测试内容描述、拷屏完整性），退回1次（多退回1次再扣10分）','1');
INSERT INTO `grade_define` VALUES ('5','3',80,71,'基本功能测试正确，未考虑特殊功能','1');
INSERT INTO `grade_define` VALUES ('5','4',70,61,'对测试范围理解出现偏差，有遗漏，必要点未测试','1');
INSERT INTO `grade_define` VALUES ('5','5',60,31,'测试案例已经整理，但部分案例未执行或没有检查结果','1');
INSERT INTO `grade_define` VALUES ('5','6',50,1,'测试发现问题未及时反馈并修改测试文档，造成二次出错','1');
INSERT INTO `grade_define` VALUES ('6','1',100,91,'针对性（用户群）、精确性（行文确切、无二异）、清晰性（简明扼要、图表等）、完整性（相关目录、附录、模板标题等）、可追溯性（修订记录），字体、大小、标点符号、格式规范统一，无错别字','1');
INSERT INTO `grade_define` VALUES ('6','2',90,81,'格式规范（模板）不达标，退回1次扣10分','1');
INSERT INTO `grade_define` VALUES ('6','3',80,71,'完整性有所欠缺','1');
INSERT INTO `grade_define` VALUES ('6','4',70,61,'可追溯性有所欠缺','1');
INSERT INTO `grade_define` VALUES ('6','5',60,31,'精确性、清晰性、完整性、可追溯性有所欠缺','1');
INSERT INTO `grade_define` VALUES ('6','6',30,1,'针对性、精确性、清晰性、完整性、可追溯性有所欠缺','1');
INSERT INTO `grade_define` VALUES ('7','1',100,91,'全面掌握完成本项目所需要的V+或外挂的专业技能和知识储备，对项目的设计方式有深刻的理解，做到知其所以然','1');
INSERT INTO `grade_define` VALUES ('7','2',90,81,'具备独立完成本项目的编码和测试的能力','1');
INSERT INTO `grade_define` VALUES ('7','3',80,61,'对本项目了解一般，初步理解部分业务需求和关键逻辑','1');
INSERT INTO `grade_define` VALUES ('7','4',60,1,'对大部分的业务和字段逻辑没有了解，只限于简单的程序修改','1');
INSERT INTO `grade_define` VALUES ('8','1',100,91,'口头沟通简明扼要、书面表达段落清晰、能够有效准确传递信息并且能够给农行做合理的技术或业务方面的解释说明','1');
INSERT INTO `grade_define` VALUES ('8','2',90,81,'能够抓住重点内容进行沟通、能够积极的采用适当的方式向团队成员或农行人员进行咨询或解释','1');
INSERT INTO `grade_define` VALUES ('8','3',80,71,'主要意图能够表述清楚、但沟通主动性不强、文字表达不够顺畅、需要做补充说明或者逻辑整理','1');
INSERT INTO `grade_define` VALUES ('8','4',70,61,'口头沟通需反复解释，书面表达意图不清，逻辑层次混乱','1');
INSERT INTO `grade_define` VALUES ('8','5',60,1,'沟通不够顺畅、传达信息错误','1');
INSERT INTO `grade_define` VALUES ('9','1',100,91,'能够独立承担各种额外开发任务、有自我约束力、并努力按时完成','1');
INSERT INTO `grade_define` VALUES ('9','2',90,81,'能够承担额外开发任务、但没有按时完成','1');
INSERT INTO `grade_define` VALUES ('9','3',80,71,'在完成本职工作的情况下，无法承担额外工作，也未努力提升自我价值和专业能力','1');
INSERT INTO `grade_define` VALUES ('9','4',70,61,'对无法承担工作轻易放弃，也不继续努力','1');
INSERT INTO `grade_define` VALUES ('9','5',60,1,'消极怠工、放任自流、情绪低落、不愿面对和积极解决问题','1');
/*!40000 ALTER TABLE `grade_define` ENABLE KEYS */;
UNLOCK TABLES;

#
# Source for table issue_record
#

DROP TABLE IF EXISTS `issue_record`;
CREATE TABLE `issue_record` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `PROJECT_ID` varchar(6) DEFAULT NULL,
  `STAT` char(1) DEFAULT NULL,
  `USER_ID` char(8) DEFAULT NULL,
  `MEMO` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `CHECK_USER` char(8) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk;

#
# Dumping data for table issue_record
#
LOCK TABLES `issue_record` WRITE;
/*!40000 ALTER TABLE `issue_record` DISABLE KEYS */;

INSERT INTO `issue_record` VALUES (1,'test','0','00000201','debug','00000200');
/*!40000 ALTER TABLE `issue_record` ENABLE KEYS */;
UNLOCK TABLES;

#
# Source for table lx_info
#

DROP TABLE IF EXISTS `lx_info`;
CREATE TABLE `lx_info` (
  `lx_id` char(8) NOT NULL,
  `depart` char(8) DEFAULT NULL,
  `name` char(8) DEFAULT NULL,
  `phone` char(20) DEFAULT NULL,
  `mobile` char(19) DEFAULT NULL,
  `email` char(30) DEFAULT NULL,
  `stuff_id` char(8) DEFAULT NULL,
  PRIMARY KEY (`lx_id`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk;

#
# Dumping data for table lx_info
#
LOCK TABLES `lx_info` WRITE;
/*!40000 ALTER TABLE `lx_info` DISABLE KEYS */;

/*!40000 ALTER TABLE `lx_info` ENABLE KEYS */;
UNLOCK TABLES;

#
# Source for table program_list
#

DROP TABLE IF EXISTS `program_list`;
CREATE TABLE `program_list` (
  `PROJECT_ID` varchar(6) DEFAULT NULL,
  `PROGRAM` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=gbk;

#
# Dumping data for table program_list
#
LOCK TABLES `program_list` WRITE;
/*!40000 ALTER TABLE `program_list` DISABLE KEYS */;

INSERT INTO `program_list` VALUES ('test','1213');
/*!40000 ALTER TABLE `program_list` ENABLE KEYS */;
UNLOCK TABLES;

#
# Source for table project_distribute
#

DROP TABLE IF EXISTS `project_distribute`;
CREATE TABLE `project_distribute` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `PROJECT_ID` varchar(6) DEFAULT NULL,
  `USER_ID` char(8) DEFAULT NULL,
  `STAT` char(1) DEFAULT NULL,
  `START_DATE` char(8) DEFAULT NULL,
  `END_DATE` char(8) DEFAULT NULL,
  `MEMO` varchar(255) DEFAULT NULL,
  `IS_DONE` char(1) DEFAULT NULL,
  `FINAL_END_DATE` char(8) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=gbk;

#
# Dumping data for table project_distribute
#
LOCK TABLES `project_distribute` WRITE;
/*!40000 ALTER TABLE `project_distribute` DISABLE KEYS */;

INSERT INTO `project_distribute` VALUES (1,'test','00000201','0','20110113','20110114','','0',NULL);
INSERT INTO `project_distribute` VALUES (2,'test','00000201','1','20110101','20110103','','0',NULL);
/*!40000 ALTER TABLE `project_distribute` ENABLE KEYS */;
UNLOCK TABLES;

#
# Source for table project_list
#

DROP TABLE IF EXISTS `project_list`;
CREATE TABLE `project_list` (
  `PROJECT_ID` varchar(6) NOT NULL,
  `PROJECT_NAME` varchar(40) DEFAULT NULL,
  `STAT` char(1) DEFAULT NULL,
  `USER_ID` char(9) DEFAULT NULL,
  `MEMO` varchar(40) CHARACTER SET utf8 DEFAULT NULL,
  `ISS_ID` varchar(200) DEFAULT NULL,
  `IS_IN_CONTRACT` char(1) DEFAULT NULL,
  `NEED_DEV` char(1) DEFAULT NULL,
  `OWNING` char(1) DEFAULT NULL,
  `PROJECT_CLASS` char(2) DEFAULT NULL,
  PRIMARY KEY (`PROJECT_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk;

#
# Dumping data for table project_list
#
LOCK TABLES `project_list` WRITE;
/*!40000 ALTER TABLE `project_list` DISABLE KEYS */;

INSERT INTO `project_list` VALUES ('test','test','0','00006671','123','kaj','1','1','4','');
/*!40000 ALTER TABLE `project_list` ENABLE KEYS */;
UNLOCK TABLES;

#
# Source for table project_relation
#

DROP TABLE IF EXISTS `project_relation`;
CREATE TABLE `project_relation` (
  `PROJECT_ID` varchar(6) DEFAULT NULL,
  `PRE_PROJECT` varchar(6) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=gbk;

#
# Dumping data for table project_relation
#
LOCK TABLES `project_relation` WRITE;
/*!40000 ALTER TABLE `project_relation` DISABLE KEYS */;

/*!40000 ALTER TABLE `project_relation` ENABLE KEYS */;
UNLOCK TABLES;

#
# Source for table require_changes
#

DROP TABLE IF EXISTS `require_changes`;
CREATE TABLE `require_changes` (
  `PROJECT_ID` varchar(6) NOT NULL DEFAULT '',
  `VERSION` int(11) NOT NULL DEFAULT '0',
  `CONTENT` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `REASON` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `CHANGE_DATE` char(8) DEFAULT NULL,
  `USER_ID` char(8) DEFAULT NULL,
  PRIMARY KEY (`PROJECT_ID`,`VERSION`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk;

#
# Dumping data for table require_changes
#
LOCK TABLES `require_changes` WRITE;
/*!40000 ALTER TABLE `require_changes` DISABLE KEYS */;

INSERT INTO `require_changes` VALUES ('test',1,'他','天天','20110113','00000200');
/*!40000 ALTER TABLE `require_changes` ENABLE KEYS */;
UNLOCK TABLES;

#
# Source for table score_info
#

DROP TABLE IF EXISTS `score_info`;
CREATE TABLE `score_info` (
  `PROJECT_ID` varchar(6) NOT NULL DEFAULT '',
  `STAT` char(1) NOT NULL DEFAULT '',
  `USER_ID` char(8) NOT NULL DEFAULT '',
  `CHECK_NO` char(2) NOT NULL DEFAULT '',
  `GRADE` char(1) DEFAULT NULL,
  `SCORE` int(11) DEFAULT NULL,
  `CHECK_USER` char(8) DEFAULT NULL,
  PRIMARY KEY (`PROJECT_ID`,`STAT`,`USER_ID`,`CHECK_NO`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk;

#
# Dumping data for table score_info
#
LOCK TABLES `score_info` WRITE;
/*!40000 ALTER TABLE `score_info` DISABLE KEYS */;

INSERT INTO `score_info` VALUES ('test','0','00000201','1','1',100,'00000200');
INSERT INTO `score_info` VALUES ('test','0','00000201','10','3',80,'00000200');
INSERT INTO `score_info` VALUES ('test','0','00000201','11','2',90,'00000200');
INSERT INTO `score_info` VALUES ('test','0','00000201','12','2',80,'00000200');
INSERT INTO `score_info` VALUES ('test','0','00000201','2','1',100,'00000200');
INSERT INTO `score_info` VALUES ('test','0','00000201','3','1',100,'00000200');
INSERT INTO `score_info` VALUES ('test','0','00000201','6','2',90,'00000200');
INSERT INTO `score_info` VALUES ('test','0','00000201','7','1',100,'00000200');
INSERT INTO `score_info` VALUES ('test','0','00000201','8','1',100,'00000200');
INSERT INTO `score_info` VALUES ('test','0','00000201','9','1',100,'00000200');
/*!40000 ALTER TABLE `score_info` ENABLE KEYS */;
UNLOCK TABLES;

#
# Source for table task_list
#

DROP TABLE IF EXISTS `task_list`;
CREATE TABLE `task_list` (
  `TASK_NO` char(20) NOT NULL,
  `PROJECT_NO` char(5) DEFAULT NULL,
  `TASK_DATE` char(8) DEFAULT NULL,
  `TASK_STEP` char(1) DEFAULT NULL,
  `TASK_TYPE` char(1) DEFAULT NULL,
  `TASK_GOAL` char(1) DEFAULT NULL,
  `TASK_COST` decimal(5,2) DEFAULT NULL,
  `TASK_USER` char(9) DEFAULT NULL,
  `UPDATE_DATE` char(8) DEFAULT NULL,
  `TASK_MEMO` char(250) DEFAULT NULL,
  `ID` int(11) NOT NULL,
  PRIMARY KEY (`TASK_NO`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk;

#
# Dumping data for table task_list
#
LOCK TABLES `task_list` WRITE;
/*!40000 ALTER TABLE `task_list` DISABLE KEYS */;

INSERT INTO `task_list` VALUES ('00000000000000041676','test','20110113','0','1','0',8,'00000201','20110113','789',1);
/*!40000 ALTER TABLE `task_list` ENABLE KEYS */;
UNLOCK TABLES;

/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
