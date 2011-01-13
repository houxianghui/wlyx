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

INSERT INTO `check_items` VALUES ('1','�Ѷ�ϵ��',0.04,'1','');
INSERT INTO `check_items` VALUES ('10','����̬��',0.08,'1','');
INSERT INTO `check_items` VALUES ('11','�ŶӺ���',0.08,'1','');
INSERT INTO `check_items` VALUES ('12','���ɹ淶',0.03,'1','');
INSERT INTO `check_items` VALUES ('2','��Ŀ����',0.1,'1','');
INSERT INTO `check_items` VALUES ('3','�������',0.25,'1','0,1');
INSERT INTO `check_items` VALUES ('4','��������',0.25,'1','2');
INSERT INTO `check_items` VALUES ('5','��������',0.25,'1','3,4');
INSERT INTO `check_items` VALUES ('6','�ĵ�����',0.15,'1','');
INSERT INTO `check_items` VALUES ('7','רҵ����',0.08,'1','');
INSERT INTO `check_items` VALUES ('8','��ͨ����',0.08,'1','');
INSERT INTO `check_items` VALUES ('9','��ѹ����',0.03,'1','');
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

INSERT INTO `ep_dic_type` VALUES ('0001','�û�״̬','1');
INSERT INTO `ep_dic_type` VALUES ('0002','��ɫ״̬','1');
INSERT INTO `ep_dic_type` VALUES ('0003','�˵�Ҷ�ӽڵ��־','1');
INSERT INTO `ep_dic_type` VALUES ('0004','��������','1');
INSERT INTO `ep_dic_type` VALUES ('0005','������ʽ','1');
INSERT INTO `ep_dic_type` VALUES ('0006','�¼����','1');
INSERT INTO `ep_dic_type` VALUES ('9994','��Ŀ�׶�','1');
INSERT INTO `ep_dic_type` VALUES ('9992','�������','1');
INSERT INTO `ep_dic_type` VALUES ('9993','����֧�ַ�������','1');
INSERT INTO `ep_dic_type` VALUES ('9991','������ɶ�','1');
INSERT INTO `ep_dic_type` VALUES ('9990','��������','1');
INSERT INTO `ep_dic_type` VALUES ('9989','��Ŀ�׶�','1');
INSERT INTO `ep_dic_type` VALUES ('9988','��Ŀ����','1');
INSERT INTO `ep_dic_type` VALUES ('9987','08-09��V+��ά��ͬ','1');
INSERT INTO `ep_dic_type` VALUES ('9986','08-09�������ά��ͬ','1');
INSERT INTO `ep_dic_type` VALUES ('0000','�Ƿ�','1');
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

INSERT INTO `ep_error_code` VALUES ('E010000','ִ���ַ���ת�����');
INSERT INTO `ep_error_code` VALUES ('E010001','���ݲ�ѯʧ�ܣ�');
INSERT INTO `ep_error_code` VALUES ('E010002','�����޸�ʧ�ܣ�');
INSERT INTO `ep_error_code` VALUES ('E010004','�ͷ����ݿ�����ʧ�ܣ�');
INSERT INTO `ep_error_code` VALUES ('E010005','���ݿ�������ƴ���');
INSERT INTO `ep_error_code` VALUES ('E010011','��������ʧ�ܣ�');
INSERT INTO `ep_error_code` VALUES ('E010012','������������ʧ�ܣ�');
INSERT INTO `ep_error_code` VALUES ('E010013','ɾ������ʧ�ܣ�');
INSERT INTO `ep_error_code` VALUES ('E010014','��������ʧ�ܣ�');
INSERT INTO `ep_error_code` VALUES ('E010015','���ݿ����ʧ�ܣ�');
INSERT INTO `ep_error_code` VALUES ('E010016','��ѯ����ʧ�ܣ�');
INSERT INTO `ep_error_code` VALUES ('E010017','���ݷ������������ݿ�����ʧ�ܣ�');
INSERT INTO `ep_error_code` VALUES ('E010018','��ѯ��ҳ���ʧ�ܣ�');
INSERT INTO `ep_error_code` VALUES ('E010019','���ݷ�������ͷ����ݿ�����ʧ�ܣ�');
INSERT INTO `ep_error_code` VALUES ('E010020','���ݷ�������ͷ����ݿ�����ʧ�ܣ�');
INSERT INTO `ep_error_code` VALUES ('E010031','���ݿ����ӳ������ļ�����');
INSERT INTO `ep_error_code` VALUES ('E010032','��ȡĬ�����ݿ�����ʧ�ܣ�');
INSERT INTO `ep_error_code` VALUES ('E010033','��ָ������Դ��ȡ���ݿ�����ʧ�ܣ�');
INSERT INTO `ep_error_code` VALUES ('E010038','��ȡEJB����ʧ�ܣ�');
INSERT INTO `ep_error_code` VALUES ('E010039','�����������ȡ���ʵ��ʧ�ܣ�');
INSERT INTO `ep_error_code` VALUES ('E010040','�������ʧ�ܣ�');
INSERT INTO `ep_error_code` VALUES ('E010041','�Ѵ����ظ���¼��');
INSERT INTO `ep_error_code` VALUES ('E010042','ԭ�������');
INSERT INTO `ep_error_code` VALUES ('E020001','��û�д˲���Ȩ�ޣ�');
INSERT INTO `ep_error_code` VALUES ('E030001','Ĭ��������ϵֻ����һ����');
INSERT INTO `ep_error_code` VALUES ('E030002','δ�ҵ��ͻ���������ģ�壡');
INSERT INTO `ep_error_code` VALUES ('E040001','��¼������־ʧ�ܣ�');
INSERT INTO `ep_error_code` VALUES ('E050001','����PDF�ļ�ʧ�ܣ�ȷ���ϴ��ļ��ĸ�ʽ��');
INSERT INTO `ep_error_code` VALUES ('E060001','���������˻�ʧ�ܣ�');
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

INSERT INTO `ep_mdic` VALUES (761,'0302',0,'1','����',1,1,'A','1');
INSERT INTO `ep_mdic` VALUES (762,'0302',0,'2','��ע',2,1,'A','1');
INSERT INTO `ep_mdic` VALUES (763,'0302',0,'3','�μ�',3,1,'A','1');
INSERT INTO `ep_mdic` VALUES (764,'0302',0,'4','����',4,1,'A','1');
INSERT INTO `ep_mdic` VALUES (765,'0302',0,'5','��ʧ',5,1,'A','1');
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

INSERT INTO `ep_menu` VALUES ('00000013','00000000','ϵͳ����','0',1,6,' ');
INSERT INTO `ep_menu` VALUES ('00000015','00000013','�Ż�����','0',2,1,' ');
INSERT INTO `ep_menu` VALUES ('00000016','00000013','��������','0',2,2,' ');
INSERT INTO `ep_menu` VALUES ('00000022','00000015','��ҳģ��ά��','0',3,1,'HomePage.do?act=list');
INSERT INTO `ep_menu` VALUES ('00000023','00000015','�û�����','0',3,2,'User.do?act=list&init=true');
INSERT INTO `ep_menu` VALUES ('00000024','00000015','�˵�����','0',3,3,'portal/menu/index.jsp');
INSERT INTO `ep_menu` VALUES ('00000025','00000015','��ɫ����','0',3,4,'Role.do?act=list');
INSERT INTO `ep_menu` VALUES ('00000026','00000015','����Ȩ�޶���','0',3,5,'portal/op/OP_index.jsp');
INSERT INTO `ep_menu` VALUES ('00000045','00000016','��������','0',3,2,'PmKey.do?act=list');
INSERT INTO `ep_menu` VALUES ('00000046','00000016','�����ֵ�ά��','0',3,3,'DicType.do?act=list');
INSERT INTO `ep_menu` VALUES ('00000324','00000000','��Ŀά��','0',1,1,' ');
INSERT INTO `ep_menu` VALUES ('00000325','00000324','��ǰ��Ŀ�б�','0',2,1,'ProjectMaintain.do?act=qpl');
INSERT INTO `ep_menu` VALUES ('00000326','00000324','������Ŀ�б�','0',2,2,'ProjectMaintain.do?act=qal');
INSERT INTO `ep_menu` VALUES ('00000327','00000324','δ��д��ʱ��Ϣ��ѯ','0',2,3,'NotWriteNoteStuff.do?act=list');
INSERT INTO `ep_menu` VALUES ('00000328','00000324','��Ŀ���ȼ��','0',2,4,'ProjectDistribute.do?act=getNotDoneWorks');
INSERT INTO `ep_menu` VALUES ('00000329','00000000','�ձ���д','0',1,3,'');
INSERT INTO `ep_menu` VALUES ('00000330','00000329','��ʱ��д','0',2,1,'TASK_LIST.do?act=list');
INSERT INTO `ep_menu` VALUES ('00000331','00000329','��ʷ������ѯ','0',2,2,'TASK_LIST.do?act=ql');
INSERT INTO `ep_menu` VALUES ('00000332','00000016','�Զ��������ֵ�ά��','0',3,3,'ReDefSDic.do?act=list');
INSERT INTO `ep_menu` VALUES ('00000333','00000324','Ա��������ѯ','0',2,5,'TASK_LIST.do?act=qa');
INSERT INTO `ep_menu` VALUES ('00000334','00000329','�ҵ�����','0',2,1,'ProjectDistribute.do?act=gmp');
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

INSERT INTO `ep_op_def` VALUES ('user_c','00000023','�����û�');
INSERT INTO `ep_op_def` VALUES ('user_u','00000023','�޸��û�');
INSERT INTO `ep_op_def` VALUES ('user_d','00000023','ɾ���û�');
INSERT INTO `ep_op_def` VALUES ('menu_c','00000024','���Ӳ˵�');
INSERT INTO `ep_op_def` VALUES ('menu_u','00000024','�޸Ĳ˵�');
INSERT INTO `ep_op_def` VALUES ('menu_d','00000024','ɾ���˵�');
INSERT INTO `ep_op_def` VALUES ('user_enable','00000023','�����û�');
INSERT INTO `ep_op_def` VALUES ('user_disable','00000023','ͣ���û�');
INSERT INTO `ep_op_def` VALUES ('user_terminate','00000023','�����û�');
INSERT INTO `ep_op_def` VALUES ('role_c','00000025','���ӽ�ɫ');
INSERT INTO `ep_op_def` VALUES ('role_u','00000025','�޸Ľ�ɫ');
INSERT INTO `ep_op_def` VALUES ('role_terminate','00000025','�ϳ���ɫ');
INSERT INTO `ep_op_def` VALUES ('role_d','00000025','ɾ����ɫ');
INSERT INTO `ep_op_def` VALUES ('role_perm','00000025','��ɫȨ�޹���');
INSERT INTO `ep_op_def` VALUES ('op_c','00000026','���Ӳ���Ȩ��');
INSERT INTO `ep_op_def` VALUES ('op_d','00000026','ɾ������Ȩ��');
INSERT INTO `ep_op_def` VALUES ('pmKey_c','00000045','��������');
INSERT INTO `ep_op_def` VALUES ('pmKey_u','00000045','�޸�����');
INSERT INTO `ep_op_def` VALUES ('pmKey_d','00000045','ɾ������');
INSERT INTO `ep_op_def` VALUES ('dicType_c','00000046','���������ֵ�');
INSERT INTO `ep_op_def` VALUES ('dicType_u','00000046','�޸������ֵ�');
INSERT INTO `ep_op_def` VALUES ('dicType_d','00000046','ɾ�������ֵ�');
INSERT INTO `ep_op_def` VALUES ('dmField_c','00000061','������������');
INSERT INTO `ep_op_def` VALUES ('dmField_u','00000061','�޸���������');
INSERT INTO `ep_op_def` VALUES ('dmField_d','00000061','ɾ����������');
INSERT INTO `ep_op_def` VALUES ('dmField_uv','00000061','�޸İ汾��');
INSERT INTO `ep_op_def` VALUES ('fileDef_c','00000062','����ƽ���ļ�����');
INSERT INTO `ep_op_def` VALUES ('fileDef_u','00000062','�޸�ƽ���ļ�����');
INSERT INTO `ep_op_def` VALUES ('fileDef_d','00000062','ɾ��ƽ���ļ�����');
INSERT INTO `ep_op_def` VALUES ('homepage_c','00000022','������ҳģ��');
INSERT INTO `ep_op_def` VALUES ('homepage_u','00000022','�޸���ҳģ��');
INSERT INTO `ep_op_def` VALUES ('homepage_d','00000022','ɾ����ҳģ��');
INSERT INTO `ep_op_def` VALUES ('product_c','00000077','���Ӳ�Ʒ����');
INSERT INTO `ep_op_def` VALUES ('product_u','00000077','�޸Ĳ�Ʒ����');
INSERT INTO `ep_op_def` VALUES ('product_d','00000077','ɾ����Ʒ����');
INSERT INTO `ep_op_def` VALUES ('creditSys_c','00000078','����������ϵ');
INSERT INTO `ep_op_def` VALUES ('creditSys_u','00000078','�޸�������ϵ');
INSERT INTO `ep_op_def` VALUES ('creditSys_d','00000078','ɾ��������ϵ');
INSERT INTO `ep_op_def` VALUES ('creditOrgProd_c','00000079','���Ӳ�Ʒ/����������ϵ');
INSERT INTO `ep_op_def` VALUES ('creditOrgProd_u','00000079','�޸Ĳ�Ʒ/����������ϵ');
INSERT INTO `ep_op_def` VALUES ('creditOrgProd_d','00000079','ɾ����Ʒ/����������ϵ');
INSERT INTO `ep_op_def` VALUES ('orgProduct_c','00000084','���ӻ�����Ʒ��Ȩ');
INSERT INTO `ep_op_def` VALUES ('orgProduct_u','00000084','�޸Ļ�����Ʒ��Ȩ');
INSERT INTO `ep_op_def` VALUES ('reDefSDic_c','00000332','�����Զ��嵥���ֵ�');
INSERT INTO `ep_op_def` VALUES ('reDefSDic_u','00000332','�޸��Զ��嵥���ֵ�');
INSERT INTO `ep_op_def` VALUES ('reDefSDic_d','00000332','ɾ���Զ��嵥���ֵ�');
INSERT INTO `ep_op_def` VALUES ('sDic_c','00000046','���������ֵ���ϸ');
INSERT INTO `ep_op_def` VALUES ('sDic_u','00000046','�޸������ֵ���ϸ');
INSERT INTO `ep_op_def` VALUES ('sDic_d','00000046','ɾ�������ֵ���ϸ');
INSERT INTO `ep_op_def` VALUES ('input_req','00000058','¼������');
INSERT INTO `ep_op_def` VALUES ('add_per','00000058','�������Ӹ���');
INSERT INTO `ep_op_def` VALUES ('modify_req','00000058','�޸���������');
INSERT INTO `ep_op_def` VALUES ('del_req','00000058','ɾ����������');
INSERT INTO `ep_op_def` VALUES ('businessType_c','00000119','����ҵ������');
INSERT INTO `ep_op_def` VALUES ('businessType_u','00000119','�޸�ҵ������');
INSERT INTO `ep_op_def` VALUES ('businessType_d','00000119','ɾ��ҵ������');
INSERT INTO `ep_op_def` VALUES ('get_back','00000058','ȡ��(�Ѵ��������б�)');
INSERT INTO `ep_op_def` VALUES ('query_check','00000086','������Ϣ');
INSERT INTO `ep_op_def` VALUES ('query_flowtrack','00000086','���̸���');
INSERT INTO `ep_op_def` VALUES ('check_getback','00000056',' ȡ�أ����ˣ�');
INSERT INTO `ep_op_def` VALUES ('check_todo','00000056','����');
INSERT INTO `ep_op_def` VALUES ('abc_bus_saverr','00000056','�������б��������');
INSERT INTO `ep_op_def` VALUES ('txLetterInfo_c','00000218','��ַ���Ź�������');
INSERT INTO `ep_op_def` VALUES ('txLetterInfo_u','00000218','��ַ���Ź����޸�');
INSERT INTO `ep_op_def` VALUES ('txLetterInfo_d','00000218','��ַ���Ź���ɾ��');
INSERT INTO `ep_op_def` VALUES ('collActCode_c','00000123','�������ش����ж���');
INSERT INTO `ep_op_def` VALUES ('collActCode_u','00000123','�޸����ش����ж���');
INSERT INTO `ep_op_def` VALUES ('collActCode_d','00000123','ɾ�����ش����ж���');
INSERT INTO `ep_op_def` VALUES ('query_del','00000087','ɾ������ѯ��');
INSERT INTO `ep_op_def` VALUES ('lockFuhe_u','00000132','�������������');
INSERT INTO `ep_op_def` VALUES ('lockSure_u','00000133','���������ȷ��');
INSERT INTO `ep_op_def` VALUES ('base','00000094','�鿴�ͻ�������Ϣ');
INSERT INTO `ep_op_def` VALUES ('base_dl','00000094','�������ػ�����Ϣ');
INSERT INTO `ep_op_def` VALUES ('acct','00000094','�鿴�˻����');
INSERT INTO `ep_op_def` VALUES ('acct_dl','00000094','���������˻���Ϣ');
INSERT INTO `ep_op_def` VALUES ('trade','00000094','�鿴�˻���������Ϣ');
INSERT INTO `ep_op_def` VALUES ('trade_dl','00000094','���������˻���������Ϣ');
INSERT INTO `ep_op_def` VALUES ('apply','00000094','�鿴������Ϣ');
INSERT INTO `ep_op_def` VALUES ('apply_dl','00000094','�������ع�����Ϣ');
INSERT INTO `ep_op_def` VALUES ('allcards','00000094','�鿴������Ϣ');
INSERT INTO `ep_op_def` VALUES ('appinfo','00000094','�鿴ԭʼ������Ϣ');
INSERT INTO `ep_op_def` VALUES ('billDeal_up','00000034','�ϴ��ļ�');
INSERT INTO `ep_op_def` VALUES ('billDeal_cl','00000034','����ļ�');
INSERT INTO `ep_op_def` VALUES ('collact_c','00000128','����������');
INSERT INTO `ep_op_def` VALUES ('collmemo_c','00000128','���뱸����Ϣ');
INSERT INTO `ep_op_def` VALUES ('coll_dl','00000128','���ش���֪ͨ��');
INSERT INTO `ep_op_def` VALUES ('colllock_u','00000128','����������');
INSERT INTO `ep_op_def` VALUES ('coll_base','00000101','�鿴���ջ�����Ϣ');
INSERT INTO `ep_op_def` VALUES ('coll_his','00000101','�鿴������ʷ��Ϣ');
INSERT INTO `ep_op_def` VALUES ('collAcctAdjust_u','00000130','�����ʻ�����');
INSERT INTO `ep_op_def` VALUES ('AttachCard_c','00000058','���Ӹ���');
INSERT INTO `ep_op_def` VALUES ('AttachCard_u','00000058','�޸ĸ���');
INSERT INTO `ep_op_def` VALUES ('AttachCard_d','00000058','ɾ������');
INSERT INTO `ep_op_def` VALUES ('inputer_submit','00000058','¼��Ա�ύ����');
INSERT INTO `ep_op_def` VALUES ('check_submit','00000056','����Ա�ύ����');
INSERT INTO `ep_op_def` VALUES ('check_handback','00000056','����Ա�˻�����');
INSERT INTO `ep_op_def` VALUES ('check_saveerr','00000056','����Ա����򹴴���');
INSERT INTO `ep_op_def` VALUES ('pro_batpass','00000056','ʡ�и���Ա����ͨ������');
INSERT INTO `ep_op_def` VALUES ('pro_batback','00000056','ʡ�и���Ա�����˻�����');
INSERT INTO `ep_op_def` VALUES ('busPerCard_c','00000058','�������񿨸�������');
INSERT INTO `ep_op_def` VALUES ('busPerCard_u','00000058','�޸����񿨸�������');
INSERT INTO `ep_op_def` VALUES ('busPerCard_d','00000058','ɾ�����񿨸�������');
INSERT INTO `ep_op_def` VALUES ('busCardReq_c','00000058','�������񿨵�λ����');
INSERT INTO `ep_op_def` VALUES ('busCardReq_u','00000058','�޸����񿨵�λ����');
INSERT INTO `ep_op_def` VALUES ('bus_checksubmit','00000056','���񿨸����ύ');
INSERT INTO `ep_op_def` VALUES ('bus_checkback','00000056','���񿨸����˻�');
INSERT INTO `ep_op_def` VALUES ('bus_revise','00000056','���񿨸����޸�����');
INSERT INTO `ep_op_def` VALUES ('bus_saverr','00000056','���񿨸��˱������');
INSERT INTO `ep_op_def` VALUES ('cityCodeDef_c','00000141','���ӳ��д���');
INSERT INTO `ep_op_def` VALUES ('cityCodeDef_u','00000141','�޸ĳ��д���');
INSERT INTO `ep_op_def` VALUES ('cityCodeDef_d','00000141','ɾ�����д���');
INSERT INTO `ep_op_def` VALUES ('rewrite_dl','00000094','�����������ϱ䶯�����Ϣ');
INSERT INTO `ep_op_def` VALUES ('wipsend_file','00000147','�ϴ���������');
INSERT INTO `ep_op_def` VALUES ('send_file','00000144','�ϴ��ļ�');
INSERT INTO `ep_op_def` VALUES ('busiDefine_c','00000119','����ҵ����');
INSERT INTO `ep_op_def` VALUES ('busiDefine_u','00000119','�޸�ҵ����');
INSERT INTO `ep_op_def` VALUES ('busiDefine_d','00000119','ɾ��ҵ����');
INSERT INTO `ep_op_def` VALUES ('batchconv','00000119','������ʽ����');
INSERT INTO `ep_op_def` VALUES ('fileDefine_c','00000119','���������ļ�����');
INSERT INTO `ep_op_def` VALUES ('fileDefine_u','00000119','�޸������ļ�����');
INSERT INTO `ep_op_def` VALUES ('fileDefine_d','00000119','ɾ�������ļ�����');
INSERT INTO `ep_op_def` VALUES ('inFileField_c','00000119','���������ļ�����');
INSERT INTO `ep_op_def` VALUES ('inFileField_u','00000119','�޸������ļ�����');
INSERT INTO `ep_op_def` VALUES ('inFileField_d','00000119','ɾ�������ļ�����');
INSERT INTO `ep_op_def` VALUES ('outFileField_c','00000119','��������ļ�����');
INSERT INTO `ep_op_def` VALUES ('outFileField_u','00000119','�޸�����ļ�����');
INSERT INTO `ep_op_def` VALUES ('outFileField_d','00000119','ɾ������ļ�����');
INSERT INTO `ep_op_def` VALUES ('fieldMap_c','00000119','����������ӳ��');
INSERT INTO `ep_op_def` VALUES ('fieldMap_u','00000119','�޸�������ӳ��');
INSERT INTO `ep_op_def` VALUES ('fieldMap_d','00000119','ɾ��������ӳ��');
INSERT INTO `ep_op_def` VALUES ('fixfileField_c','00000119','����AML1�ļ�����');
INSERT INTO `ep_op_def` VALUES ('fixfileField_u','00000119','�޸�AML1�ļ�����');
INSERT INTO `ep_op_def` VALUES ('fixfileField_d','00000119','ɾ��AML1�ļ�����');
INSERT INTO `ep_op_def` VALUES ('fixfieldMap_c','00000119','����AML1������ӳ��');
INSERT INTO `ep_op_def` VALUES ('fixfieldMap_u','00000119','�޸�AML1������ӳ��');
INSERT INTO `ep_op_def` VALUES ('fixfieldMap_d','00000119','ɾ��AML1������ӳ��');
INSERT INTO `ep_op_def` VALUES ('trans_dl','00000018','���ش��ǿ����ײ�ѯ���');
INSERT INTO `ep_op_def` VALUES ('lockNew_c','00000101','���������');
INSERT INTO `ep_op_def` VALUES ('lockNew_rel','00000101','���������');
INSERT INTO `ep_op_def` VALUES ('jobConfig_c','00000064','����������ҵ');
INSERT INTO `ep_op_def` VALUES ('jobConfig_u','00000064','�޸�������ҵ');
INSERT INTO `ep_op_def` VALUES ('jobConfig_d','00000064','ɾ��������ҵ');
INSERT INTO `ep_op_def` VALUES ('collacct_dl','00000129','���ش�������');
INSERT INTO `ep_op_def` VALUES ('collquery_dl','00000122','���ش����˻�');
INSERT INTO `ep_op_def` VALUES ('reset_pwd','00000023','��������');
INSERT INTO `ep_op_def` VALUES ('check_batpass','00000056','����ͨ��');
INSERT INTO `ep_op_def` VALUES ('expandManager','00000158','��չ��ά��');
INSERT INTO `ep_op_def` VALUES ('ep_notice_c','00000162','��ҳ֪ͨ����');
INSERT INTO `ep_op_def` VALUES ('busratio_u','00000058','�������޸���������');
INSERT INTO `ep_op_def` VALUES ('company_dl','00000094','���񿨵�λ������Ϣ����');
INSERT INTO `ep_op_def` VALUES ('company','00000094','�鿴���񿨵�λ������Ϣ');
INSERT INTO `ep_op_def` VALUES ('relaccount','00000094','��ϵ�˻���Ϣ');
INSERT INTO `ep_op_def` VALUES ('usb','00000094','�鿴����˻���Ϣ');
INSERT INTO `ep_op_def` VALUES ('rmb','00000094','�鿴������˻���Ϣ');
INSERT INTO `ep_op_def` VALUES ('collprimary_dl','00000167','���񿨷��˻������б�');
INSERT INTO `ep_op_def` VALUES ('collprimaryrmb','00000167','�鿴˫����������˻�');
INSERT INTO `ep_op_def` VALUES ('collprimaryusb','00000167','�鿴˫��������˻�');
INSERT INTO `ep_op_def` VALUES ('collcompany_base','00000167','���񿨴����˻���Ϣ');
INSERT INTO `ep_op_def` VALUES ('collcompany_his','00000167','���񿨴�����ʷ��Ϣ');
INSERT INTO `ep_op_def` VALUES ('collclassify_dl','00000167','�������񿨷����ѯ�����˻�');
INSERT INTO `ep_op_def` VALUES ('collcompanyadmin_dl','00000168','���񿨴����˻���������');
INSERT INTO `ep_op_def` VALUES ('collcompanyadjust_u','00000168','�������񿨴����˻�');
INSERT INTO `ep_op_def` VALUES ('collcompanyact_c','00000168','���������ж���');
INSERT INTO `ep_op_def` VALUES ('collcompanymemo_c','00000168','�������񿨱�����Ϣ');
INSERT INTO `ep_op_def` VALUES ('collcompany_dl','00000168','�������񿨴���֪ͨ��');
INSERT INTO `ep_op_def` VALUES ('collcompanylock_u','00000169','��������������');
INSERT INTO `ep_op_def` VALUES ('lockcompanynew_c','00000169','��ӹ�ϵ��������');
INSERT INTO `ep_op_def` VALUES ('lockcompanynew_rel','00000169','�����ϵ��������');
INSERT INTO `ep_op_def` VALUES ('billInfo_c','00000179','�������񿨹�˾�����˵���Ϣ��������');
INSERT INTO `ep_op_def` VALUES ('repayInfo_c','00000180','����Լ����������');
INSERT INTO `ep_op_def` VALUES ('repayInfo_u','00000180','�޸�Լ����������');
INSERT INTO `ep_op_def` VALUES ('repayInfo_d','00000180','ɾ��Լ����������');
INSERT INTO `ep_op_def` VALUES ('repayInfo_check','00000181','����Լ����������');
INSERT INTO `ep_op_def` VALUES ('sms_sub','00000187','���Ŷ���');
INSERT INTO `ep_op_def` VALUES ('sms_can','00000188','���ų���');
INSERT INTO `ep_op_def` VALUES ('sms_send','00000189','ʵʱ���Ͷ���');
INSERT INTO `ep_op_def` VALUES ('ep_role_level','00000193','��ɫ�ȼ�����');
INSERT INTO `ep_op_def` VALUES ('ep_amount_adjust','00000190','��ʱ��ȵ���¼��');
INSERT INTO `ep_op_def` VALUES ('ep_amount_adjust_c','00000191','��ʱ��ȵ�������');
INSERT INTO `ep_op_def` VALUES ('stmtRegen_m','00000194','������Һϲ��˵�');
INSERT INTO `ep_op_def` VALUES ('undoLock','00000131','�����볷��');
INSERT INTO `ep_op_def` VALUES ('amsd_c','00000197','�����޸��в���ϵ');
INSERT INTO `ep_op_def` VALUES ('amsd_s','00000197','����ʡ�л���');
INSERT INTO `ep_op_def` VALUES ('amsd_l','00000197','���������ϼ�');
INSERT INTO `ep_op_def` VALUES ('ep_stmt_rule_c','00000200','�˵���ֲ���ά��');
INSERT INTO `ep_op_def` VALUES ('check_batdeal','00000056','��������');
INSERT INTO `ep_op_def` VALUES ('check_single','00000056','���ʸ���');
INSERT INTO `ep_op_def` VALUES ('abc_check_saveerr','00000056','���б������');
INSERT INTO `ep_op_def` VALUES ('abc_check_submit','00000056','�����ύ����');
INSERT INTO `ep_op_def` VALUES ('abc_check_handback','00000056','�����˻�����');
INSERT INTO `ep_op_def` VALUES ('confirm_error','00000056','����ȷ�ϴ����');
INSERT INTO `ep_op_def` VALUES ('abc_bus_checkback','00000056','���������˻�');
INSERT INTO `ep_op_def` VALUES ('abc_bus_checksubmit','00000056','���������ύ');
INSERT INTO `ep_op_def` VALUES ('abc_getback','00000056','����ȡ��');
INSERT INTO `ep_op_def` VALUES ('indepInfo_d','00000205','����������¼��');
INSERT INTO `ep_op_def` VALUES ('indepInfo_f','00000206','���������˸���');
INSERT INTO `ep_op_def` VALUES ('indepInfo_s','00000207','���������˹���');
INSERT INTO `ep_op_def` VALUES ('pro_batjudged','00000056','������������������');
INSERT INTO `ep_op_def` VALUES ('revise','00000056','����Ա�޸���������');
INSERT INTO `ep_op_def` VALUES ('ep_perrole_level','00000210','���ö�Ƚ�ɫ�ȼ�����');
INSERT INTO `ep_op_def` VALUES ('ep_peramount_adjust','00000211','���ö�ȵ���¼��');
INSERT INTO `ep_op_def` VALUES ('ep_pamount_adjust_c','00000212','���ö�ȵ�������');
INSERT INTO `ep_op_def` VALUES ('translist_dl','00000011','���ش��ǿ����˽��ײ�ѯ���');
INSERT INTO `ep_op_def` VALUES ('fixtrans_s','00000238','�̶�ת���ѯ');
INSERT INTO `ep_op_def` VALUES ('fixtrans_u','00000238','�̶�ת���޸�');
INSERT INTO `ep_op_def` VALUES ('ep_amt_control_c','00000239','��ȿ��Ʋ�������');
INSERT INTO `ep_op_def` VALUES ('ep_amt_control_u','00000239','��ȿ��Ʋ����޸�');
INSERT INTO `ep_op_def` VALUES ('ep_amt_control_d','00000239','��ȿ��Ʋ���ɾ��');
INSERT INTO `ep_op_def` VALUES ('ep_amount_force','00000192','��ʱ���ǿ��ͨ��');
INSERT INTO `ep_op_def` VALUES ('ep_peramount_force','00000213','���ö��ǿ��ͨ��');
INSERT INTO `ep_op_def` VALUES ('OrgProduct_d','00000084','ɾ��������Ʒ��Ȩ');
INSERT INTO `ep_op_def` VALUES ('allowInPlan_c','00000244','׼����������');
INSERT INTO `ep_op_def` VALUES ('allowInPlan_d','00000244','׼������ɾ��');
INSERT INTO `ep_op_def` VALUES ('allowInPlan_u','00000244','׼�������޸�');
INSERT INTO `ep_op_def` VALUES ('condConf_c','00000245','������������');
INSERT INTO `ep_op_def` VALUES ('condConf_d','00000245','��������ɾ��');
INSERT INTO `ep_op_def` VALUES ('condConf_u','00000245','���������޸�');
INSERT INTO `ep_op_def` VALUES ('stardConf_c','00000246','���������׼����');
INSERT INTO `ep_op_def` VALUES ('stardConf_d','00000246','���������׼ɾ��');
INSERT INTO `ep_op_def` VALUES ('stardConf_u','00000246','���������׼�޸�');
INSERT INTO `ep_op_def` VALUES ('query','00000247','��ѯ��Ŀ');
INSERT INTO `ep_op_def` VALUES ('itemmaintain','00000247','��Ŀ��Ϣά��');
INSERT INTO `ep_op_def` VALUES ('tradequery','00000248','��ѯ�̻�');
INSERT INTO `ep_op_def` VALUES ('trademaintain','00000248','�̻���Ϣά��');
INSERT INTO `ep_op_def` VALUES ('linkquery','00000249','��ѯ��Ŀ���̻�������Ϣ');
INSERT INTO `ep_op_def` VALUES ('linkmaintain','00000249','��Ŀ���̻�������Ϣά��');
INSERT INTO `ep_op_def` VALUES ('op_u','00000026','�޸Ĳ���Ȩ��');
INSERT INTO `ep_op_def` VALUES ('billInfo_u','00000179','�޸����񿨹�˾�����˵���Ϣ��������');
INSERT INTO `ep_op_def` VALUES ('billInfo_d','00000179','ɾ�����񿨹�˾�����˵���Ϣ��������');
INSERT INTO `ep_op_def` VALUES ('HisSearchQuery','00000250','��ʷ������ϸ��ѯ');
INSERT INTO `ep_op_def` VALUES ('HisstmtRegen_Print','00000250','��ʷ��Ϣ��ӡ');
INSERT INTO `ep_op_def` VALUES ('batch_start','00000252','������ҵ����');
INSERT INTO `ep_op_def` VALUES ('phone','00000258','�绰��ʵ');
INSERT INTO `ep_op_def` VALUES ('assign_apply','00000261','��������ָת��');
INSERT INTO `ep_op_def` VALUES ('taskEdit','00000265','��ɾ�������޸�');
INSERT INTO `ep_op_def` VALUES ('toBeCheckEdit','00000266','�����������޸�');
INSERT INTO `ep_op_def` VALUES ('checkToBeChecked','00000266','���������񸴺�');
INSERT INTO `ep_op_def` VALUES ('financeedit','00000288','���ղ�ѯ�޸�');
INSERT INTO `ep_op_def` VALUES ('financedelete','00000288','���ղ�ѯɾ��');
INSERT INTO `ep_op_def` VALUES ('fscheck','00000288','���ղ�ѯ����');
INSERT INTO `ep_op_def` VALUES ('auditdelete','00000288','���ղ�ѯ����ɾ��');
INSERT INTO `ep_op_def` VALUES ('financeaudit','00000288','���ղ�ѯ����');
INSERT INTO `ep_op_def` VALUES ('queryActStat','00000293','�ж���ͳ�Ʋ�ѯ');
INSERT INTO `ep_op_def` VALUES ('queryCollExitedHis','00000294','���˳�����ϵͳ�˻��Ĵ�����ʷ��ѯ');
INSERT INTO `ep_op_def` VALUES ('collAcctAssign_u','00000295','�����˻�������������޸�');
INSERT INTO `ep_op_def` VALUES ('mDic_c','00000046','�༶�����ֵ�����');
INSERT INTO `ep_op_def` VALUES ('mDic_u','00000046','�޸Ķ༶�����ֵ�');
INSERT INTO `ep_op_def` VALUES ('mDic_d','00000046','ɾ���༶�����ֵ�');
INSERT INTO `ep_op_def` VALUES ('cleanAdjust_input','00000304','�������¼��');
INSERT INTO `ep_op_def` VALUES ('cleanAdjust_verify','00000304','������˸���');
INSERT INTO `ep_op_def` VALUES ('cleanAdjust_audit','00000304','����������');
INSERT INTO `ep_op_def` VALUES ('synConfig_a','00000322','��������ͬ����');
INSERT INTO `ep_op_def` VALUES ('synConfig_d','00000322','ɾ������ͬ����');
INSERT INTO `ep_op_def` VALUES ('synConfig_e','00000322','�༭����ͬ����');
INSERT INTO `ep_op_def` VALUES ('synMonitor_retry','00000323','����ͬ������');
INSERT INTO `ep_op_def` VALUES ('synMonitor_stop','00000323','����ͬ��ֹͣ');
INSERT INTO `ep_op_def` VALUES ('pick','00000088','��ѡ����');
INSERT INTO `ep_op_def` VALUES ('autoCommit','00000342','�Զ��ύ');
INSERT INTO `ep_op_def` VALUES ('orgProduct_all','00000084','ȫ������ͳһά����Ʒ');
INSERT INTO `ep_op_def` VALUES ('12345','00000125','���������');
INSERT INTO `ep_op_def` VALUES ('tASK_LIST_c','00000330','�����ձ���д');
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

INSERT INTO `ep_redef_sdic` VALUES ('0001','��ҳģ���ֵ�','select TEMPL_ID as ITEM_CODE,TEMPL_ID  as LOGIC_ID,CAPTION as ITEM_VAL  from ep_homepage',' ','20060615','00000100');
INSERT INTO `ep_redef_sdic` VALUES ('0002','��ɫ�ֵ�','select ROLE_ID as ITEM_CODE,LOGIC_ID  as LOGIC_ID,ROLE_NAME as ITEM_VAL  from ep_role','��ɫ��Ϣ����','20060623','00000100');
INSERT INTO `ep_redef_sdic` VALUES ('0003','�û��ֵ�','select USER_ID as ITEM_CODE,USER_ID as LOGIC_ID,USER_NAME as ITEM_VAL from ep_user','�û���Ϣ�����ֵ�','20060116','00000100');
INSERT INTO `ep_redef_sdic` VALUES ('9999','Ա��','select a.USER_ID as ITEM_CODE,a.USER_ID as LOGIC_ID,a.USER_NAME as ITEM_VAL from ep_user a,ep_user_role b,ep_role c\r\nwhere a.USER_ID=b.USER_ID and b.ROLE_ID=c.ROLE_ID and c.LOGIC_ID=\'stuff\'','','20110113','00000200');
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

INSERT INTO `ep_role` VALUES ('00000001','����Ա','00000000',67,1,'1','00000200','20110113');
INSERT INTO `ep_role` VALUES ('00000098','��Ŀ�汾������Ա','manager',67,60000,'1','00000200','20110113');
INSERT INTO `ep_role` VALUES ('00000099','Ա��','stuff',67,60000,'1','00000200','20110113');
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

INSERT INTO `ep_sdic` VALUES (1,'0004','5','����',1,'5','1');
INSERT INTO `ep_sdic` VALUES (2,'0004','3','ʡ����',2,'3','1');
INSERT INTO `ep_sdic` VALUES (3,'0001','0','����',3,'0','1');
INSERT INTO `ep_sdic` VALUES (4,'0001','1','����',1,'1','1');
INSERT INTO `ep_sdic` VALUES (5,'0001','2','ͣ��',2,'2','1');
INSERT INTO `ep_sdic` VALUES (7,'0002','0','����',2,'0','1');
INSERT INTO `ep_sdic` VALUES (8,'0002','1','����',1,'1','1');
INSERT INTO `ep_sdic` VALUES (101,'0003','0','�ļ���',1,'0','1');
INSERT INTO `ep_sdic` VALUES (102,'0003','1','Ҷ�ӽڵ�',2,'1','1');
INSERT INTO `ep_sdic` VALUES (201,'0007','0','������',1,'0','1');
INSERT INTO `ep_sdic` VALUES (202,'0007','1','����',2,'1','1');
INSERT INTO `ep_sdic` VALUES (203,'0007','2','�˻�',3,'2','1');
INSERT INTO `ep_sdic` VALUES (302,'0008','1','����',1,'1','1');
INSERT INTO `ep_sdic` VALUES (303,'0008','2','���',2,'2','1');
INSERT INTO `ep_sdic` VALUES (501,'0005','c','����',1,'c','1');
INSERT INTO `ep_sdic` VALUES (502,'0005','u','�޸�',2,'u','1');
INSERT INTO `ep_sdic` VALUES (503,'0005','r','��ѯ',3,'r','1');
INSERT INTO `ep_sdic` VALUES (504,'0005','d','ɾ��',4,'d','1');
INSERT INTO `ep_sdic` VALUES (508,'0006','01','��¼',1,'01','1');
INSERT INTO `ep_sdic` VALUES (509,'0006','02','ҵ����',2,'02','1');
INSERT INTO `ep_sdic` VALUES (510,'0006','03','ϵͳά��',3,'03','1');
INSERT INTO `ep_sdic` VALUES (601,'0005','login','��¼',6,'l','1');
INSERT INTO `ep_sdic` VALUES (602,'0005','logout','ǩ��',7,'lo','1');
INSERT INTO `ep_sdic` VALUES (1551,'0000','1','��',1,'1','1');
INSERT INTO `ep_sdic` VALUES (1552,'0000','0','��',2,'0','1');
INSERT INTO `ep_sdic` VALUES (12851,'9994','0','BRD',0,'0','1');
INSERT INTO `ep_sdic` VALUES (12852,'9994','1','FTS',1,'1','1');
INSERT INTO `ep_sdic` VALUES (12853,'9994','2','DEV',2,'2','1');
INSERT INTO `ep_sdic` VALUES (12854,'9994','3','SIT',3,'3','1');
INSERT INTO `ep_sdic` VALUES (12855,'9994','4','UAT',4,'4','1');
INSERT INTO `ep_sdic` VALUES (12856,'9994','5','PRD',5,'5','1');
INSERT INTO `ep_sdic` VALUES (12858,'9994','7','PAUSE',7,'7','1');
INSERT INTO `ep_sdic` VALUES (12859,'9992','1','�������',1,'1','1');
INSERT INTO `ep_sdic` VALUES (12860,'9992','2','���򿪷�',2,'2','1');
INSERT INTO `ep_sdic` VALUES (12861,'9992','3','���ɲ���',3,'3','1');
INSERT INTO `ep_sdic` VALUES (12862,'9992','4','�ĵ�',4,'4','1');
INSERT INTO `ep_sdic` VALUES (12863,'9992','5','����',5,'5','1');
INSERT INTO `ep_sdic` VALUES (12864,'9992','6','����',6,'6','1');
INSERT INTO `ep_sdic` VALUES (12865,'9992','7','ά��',7,'7','1');
INSERT INTO `ep_sdic` VALUES (12866,'9992','8','���ⵥ����',8,'8','1');
INSERT INTO `ep_sdic` VALUES (12951,'9994','8','��',8,'8','1');
INSERT INTO `ep_sdic` VALUES (13051,'9992','9','��ѯ����',9,'9','1');
INSERT INTO `ep_sdic` VALUES (13052,'9992','A','����',10,'A','1');
INSERT INTO `ep_sdic` VALUES (13351,'9993','0','δ���',1,'0','1');
INSERT INTO `ep_sdic` VALUES (13352,'9993','1','�Ժ���',2,'1','1');
INSERT INTO `ep_sdic` VALUES (13353,'9993','2','�ѽ��',3,'2','1');
INSERT INTO `ep_sdic` VALUES (13354,'9993','3','��ɾ��',4,'3','1');
INSERT INTO `ep_sdic` VALUES (13451,'9992','B','���ȼƻ�',11,'B','1');
INSERT INTO `ep_sdic` VALUES (13452,'9992','C','�������',12,'C','1');
INSERT INTO `ep_sdic` VALUES (13453,'9992','D','UAT֧��',13,'D','1');
INSERT INTO `ep_sdic` VALUES (13454,'9992','E','Ͷ��ʵʩ',14,'E','1');
INSERT INTO `ep_sdic` VALUES (13455,'9992','F','���⴦��',15,'F','1');
INSERT INTO `ep_sdic` VALUES (13456,'9992','G','��������',16,'G','1');
INSERT INTO `ep_sdic` VALUES (13457,'9992','H','������ȡ',17,'H','1');
INSERT INTO `ep_sdic` VALUES (13458,'9992','I','FIX����',18,'I','1');
INSERT INTO `ep_sdic` VALUES (13459,'9992','J','�汾����',19,'J','1');
INSERT INTO `ep_sdic` VALUES (13460,'9992','K','�汾����',20,'K','1');
INSERT INTO `ep_sdic` VALUES (13461,'9992','L','�ɹ��鵵',21,'L','1');
INSERT INTO `ep_sdic` VALUES (13462,'9992','M','��ѵ',22,'M','1');
INSERT INTO `ep_sdic` VALUES (13463,'9992','N','��ʱͳ��',23,'N','1');
INSERT INTO `ep_sdic` VALUES (13464,'9992','O','����',24,'O','1');
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
INSERT INTO `ep_sdic` VALUES (13851,'9990','A','���ָ���',1,'A','1');
INSERT INTO `ep_sdic` VALUES (13853,'9990','C','��������',3,'C','1');
INSERT INTO `ep_sdic` VALUES (13854,'9990','D','��������',4,'D','1');
INSERT INTO `ep_sdic` VALUES (13855,'9990','E','����IT',5,'E','1');
INSERT INTO `ep_sdic` VALUES (13856,'9990','F','���ֻ��',6,'F','1');
INSERT INTO `ep_sdic` VALUES (13857,'9990','G','���ֳ���',7,'G','1');
INSERT INTO `ep_sdic` VALUES (13861,'9990','K','ũ�д��ǿ�ϵͳ��άY2008',11,'K','1');
INSERT INTO `ep_sdic` VALUES (13862,'9990','L','ũ�����ϵͳ��άY2008',12,'L','1');
INSERT INTO `ep_sdic` VALUES (13951,'9990','M','����',13,'M','1');
INSERT INTO `ep_sdic` VALUES (13952,'9990','N','ũ�д��ǿ��ͷ����ڽӿ�',14,'N','1');
INSERT INTO `ep_sdic` VALUES (14551,'9989','1','DEV',1,'1','1');
INSERT INTO `ep_sdic` VALUES (14552,'9989','2','SIT',2,'2','1');
INSERT INTO `ep_sdic` VALUES (14553,'9989','3','UAT',3,'3','1');
INSERT INTO `ep_sdic` VALUES (14554,'9989','4','REL',4,'4','1');
INSERT INTO `ep_sdic` VALUES (14555,'9989','0','BRD',0,'0','1');
INSERT INTO `ep_sdic` VALUES (14651,'9989','9','ɾ��',9,'9','1');
INSERT INTO `ep_sdic` VALUES (14563,'9992','P','����',25,'P','1');
INSERT INTO `ep_sdic` VALUES (14564,'9992','Q','�¼�',26,'Q','1');
INSERT INTO `ep_sdic` VALUES (14565,'9992','R','���ݼ�',27,'R','1');
INSERT INTO `ep_sdic` VALUES (14566,'9992','S','ֵ�����',28,'S','1');
INSERT INTO `ep_sdic` VALUES (14567,'9992','T','��������',29,'T','1');
INSERT INTO `ep_sdic` VALUES (14568,'9992','U','��Դ����',30,'U','1');
INSERT INTO `ep_sdic` VALUES (14569,'9992','V','�汾�˶�',31,'V','1');
INSERT INTO `ep_sdic` VALUES (14570,'9992','W','����鵵',32,'W','1');
INSERT INTO `ep_sdic` VALUES (14853,'9994','9','ɾ��',9,'9','1');
INSERT INTO `ep_sdic` VALUES (16751,'9990','K','���ϵͳ����Y08��Y09',11,'K','1');
INSERT INTO `ep_sdic` VALUES (17251,'9988','1','07-08��V+��ά��ͬ',1,'1','1');
INSERT INTO `ep_sdic` VALUES (17252,'9988','2','08-09��V+��ά��ͬ',2,'2','1');
INSERT INTO `ep_sdic` VALUES (17253,'9988','3','08-09�������ά��ͬ',3,'3','1');
INSERT INTO `ep_sdic` VALUES (17254,'9988','4','V+���ڿ�����ͬ',4,'4','1');
INSERT INTO `ep_sdic` VALUES (17351,'9988','5','�ͷ����ں�ͬ',5,'5','1');
INSERT INTO `ep_sdic` VALUES (17352,'9988','6','V+������ͬ��',6,'6','1');
INSERT INTO `ep_sdic` VALUES (17353,'9988','7','��ҿ�����ͬ��',7,'7','1');
INSERT INTO `ep_sdic` VALUES (19151,'9986','1','���ⵥ����',1,'1','1');
INSERT INTO `ep_sdic` VALUES (19152,'9986','2','�ͻ�����������',2,'2','1');
INSERT INTO `ep_sdic` VALUES (19153,'9986','3','��Ʒ��������',3,'3','1');
INSERT INTO `ep_sdic` VALUES (19154,'9986','4','������ȡ',4,'4','1');
INSERT INTO `ep_sdic` VALUES (19155,'9986','5','���ϵͳ���������',5,'5','1');
INSERT INTO `ep_sdic` VALUES (19156,'9986','6','V+��ƷFIX����',6,'6','1');
INSERT INTO `ep_sdic` VALUES (19157,'9986','7','V+����',7,'7','1');
INSERT INTO `ep_sdic` VALUES (19158,'9986','8','������֯������VISA��MasterCard�����Σ�',8,'8','1');
INSERT INTO `ep_sdic` VALUES (19159,'9986','9','������֯ר���������',9,'9','1');
INSERT INTO `ep_sdic` VALUES (19160,'9986','10','�ͻ�������',10,'10','1');
INSERT INTO `ep_sdic` VALUES (19161,'9986','11','�¹����²�Ʒ��ѯ',11,'11','1');
INSERT INTO `ep_sdic` VALUES (19162,'9986','12','�¹����²�Ʒ����',12,'12','1');
INSERT INTO `ep_sdic` VALUES (19163,'9986','13','������������/����',13,'13','1');
INSERT INTO `ep_sdic` VALUES (19164,'9986','14','�����ļ�Ԥ�����',14,'14','1');
INSERT INTO `ep_sdic` VALUES (19165,'9986','15','���������Ż�',15,'15','1');
INSERT INTO `ep_sdic` VALUES (19166,'9986','16','����Ԥ��',16,'16','1');
INSERT INTO `ep_sdic` VALUES (19167,'9986','17','������ط���',17,'17','1');
INSERT INTO `ep_sdic` VALUES (19168,'9986','18','������������',18,'18','1');
INSERT INTO `ep_sdic` VALUES (19169,'9987','1','���ⵥ����',1,'1','1');
INSERT INTO `ep_sdic` VALUES (19170,'9987','2','������������',2,'2','1');
INSERT INTO `ep_sdic` VALUES (19171,'9987','3','���ϵͳ�������',3,'3','1');
INSERT INTO `ep_sdic` VALUES (19172,'9987','4','ϵͳ��ط���',4,'4','1');
INSERT INTO `ep_sdic` VALUES (19173,'9987','5','�����������������������',5,'5','1');
INSERT INTO `ep_sdic` VALUES (19174,'9987','6','�����ϵͳ�Ż�',6,'6','1');
INSERT INTO `ep_sdic` VALUES (19175,'9987','7','������������',7,'7','1');
INSERT INTO `ep_sdic` VALUES (19176,'9987','8','�������ݿ���',8,'8','1');
INSERT INTO `ep_sdic` VALUES (19177,'9987','9','��ʷ���ݿ���',9,'9','1');
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

INSERT INTO `grade_define` VALUES ('1','1',100,91,'�Ѷȴ���Ŀ���ӣ��漰ϵͳ�ܹ��ĸ��죬��Ҫ�����о�','1');
INSERT INTO `grade_define` VALUES ('1','2',90,81,'�Ѷȴ��漰��֧������޸ģ���Щ�����߼�','1');
INSERT INTO `grade_define` VALUES ('1','3',80,71,'�Ѷ����У��漰��֧�����޸�','1');
INSERT INTO `grade_define` VALUES ('1','4',70,61,'�����������мܹ���������߼�����','1');
INSERT INTO `grade_define` VALUES ('1','5',60,51,'�򵥳���Ķ�����ԡ����Ѷ�','1');
INSERT INTO `grade_define` VALUES ('1','6',50,1,'�����������޳����޸�','1');
INSERT INTO `grade_define` VALUES ('10','1',100,91,'���ڽ��ܹ��������š����������ء�����˼�������洦������Ҫ��е���������͸������Ρ����ڷ������Ⲣ�ܹ��Ա�ְ������������ԵĽ���','1');
INSERT INTO `grade_define` VALUES ('10','2',90,81,'�ܹ����ܹ��������š����渺�����ɡ����ڳе����Σ��Ը��˵�ϸС��ʧ�����ر�����Դ�','1');
INSERT INTO `grade_define` VALUES ('10','3',80,71,'�ܹ����ܹ��������š������θС�������Ƿ��ȫ������','1');
INSERT INTO `grade_define` VALUES ('10','4',70,61,'���ܹ���ɹ��������ţ�ȱ�����θ�','1');
INSERT INTO `grade_define` VALUES ('10','5',60,31,'�Թ�������İ���ż�еִ�����������Ƿ���桢ä��Ӧ��','1');
INSERT INTO `grade_define` VALUES ('10','6',50,1,'�Թ�������İ����������ġ���ж���Ρ��޷�����Ҫ����ɹ���Ŀ��','1');
INSERT INTO `grade_define` VALUES ('11','1',100,91,'�к�ǿ���ŶӺ�����ʶ���ܹ����Ŷ�������������صļ�����ҵ���顢�ܹ�����Э����ص�����ģ����Ա','1');
INSERT INTO `grade_define` VALUES ('11','2',90,81,'���ŶӺ�����ʶ��δ�ܻ���Э���Ŷӳ�Ա�����ƺͿ�������','1');
INSERT INTO `grade_define` VALUES ('11','3',80,71,'�ܹ���������Լ��������Ŷ���ʶȱ��','1');
INSERT INTO `grade_define` VALUES ('11','4',70,21,'�������¡���Ը���������Ŷ�������Ա','1');
INSERT INTO `grade_define` VALUES ('11','5',20,1,'�˼ʹ�ϵ���š����˺������ѡ���������','1');
INSERT INTO `grade_define` VALUES ('12','1',100,81,'�ϸ�������Ŀ������ƶȡ�׼ʱ���ڡ��ϰ�ʱ�䲻�������κ��빤���޹ص�����','1');
INSERT INTO `grade_define` VALUES ('12','2',80,61,'����ʱ�䳤���������޹ص����飨����\\����\\����\\��Ϸ\\����ȣ�','1');
INSERT INTO `grade_define` VALUES ('12','3',60,31,'�޹ʳٵ����ˡ�����ʱ��������������ÿ�չ�ʱδ��7.5Сʱ','1');
INSERT INTO `grade_define` VALUES ('12','4',30,1,'�ڹ�����������Կͻ�����˾��ͬ�µĲ�������˱�Թ����Υ���Ŵ����Ѳ��������','1');
INSERT INTO `grade_define` VALUES ('12','5',0,0,'�����ƻ�����ϵͳ������й�ܿͻ��Լ���˾���ڲ�����','1');
INSERT INTO `grade_define` VALUES ('2','1',100,96,'��ǰ30�� (��)������','1');
INSERT INTO `grade_define` VALUES ('2','2',95,91,'��ǰ10����30��','1');
INSERT INTO `grade_define` VALUES ('2','3',90,81,'׼ʱ���','1');
INSERT INTO `grade_define` VALUES ('2','4',80,71,'�ӳ�10��','1');
INSERT INTO `grade_define` VALUES ('2','5',70,61,'�ӳ�20��','1');
INSERT INTO `grade_define` VALUES ('2','6',60,31,'�ӳ�30��','1');
INSERT INTO `grade_define` VALUES ('2','7',30,1,'�ӳ�30����50��','1');
INSERT INTO `grade_define` VALUES ('2','8',0,0,'�ӳ�50������','1');
INSERT INTO `grade_define` VALUES ('3','1',100,91,'���ܺ���ȫ�棬����������������������������˼·��������飬ҵ����Թ�������ȫ�棬���ܡ�Ч�ʡ��ɿ��ԡ������Կ���ȫ��','1');
INSERT INTO `grade_define` VALUES ('3','2',90,81,'���ܺ���ȫ�棬�����������������������ҵ��������������ֲ���ӿڵȿ���ȫ�棬ȱ�ٽϸ����߼�����ͼ��������α����','1');
INSERT INTO `grade_define` VALUES ('3','3',80,71,'���ܺ���ȫ�棬�����������������������ͼ��������α���룬������ҵ��������������ֲ�ӿڵ�������������ȫ','1');
INSERT INTO `grade_define` VALUES ('3','4',70,61,'�������������������Ӱ��ģ�鿼�ǲ�ȫ�����ܲ��Ե㿼�ǲ�ȫ����Ҫ���޸Ĳ�ȫ�����󼰷������δ����','1');
INSERT INTO `grade_define` VALUES ('3','5',60,31,'���ܺ��ǲ�ȫ�����������©�����ܲ��Ե㲻ȫ','1');
INSERT INTO `grade_define` VALUES ('3','6',30,1,'����������ҵ���������ƫ��ϴ�','1');
INSERT INTO `grade_define` VALUES ('4','1',100,91,'������BUG���������죬���Ǹ��ã��Է�������޸Ľ��飬�����׶�������','1');
INSERT INTO `grade_define` VALUES ('4','2',90,81,'����ʵ����ȷ���������������ڲ��ֲ�������Ŀ����淶','1');
INSERT INTO `grade_define` VALUES ('4','3',80,71,'����ʵ����ȷ�����쳣��������','1');
INSERT INTO `grade_define` VALUES ('4','4',70,61,'��Ԫ���Գɹ�����Ԫ���Ա��桢һ�γ����嵥�������������衢�������ύ��������������Ƿȱ','1');
INSERT INTO `grade_define` VALUES ('4','5',60,31,'�ṩ�ĳ�����ҵ���ű����������������������','1');
INSERT INTO `grade_define` VALUES ('4','6',30,1,'�����ĳ���������صͼ������޷���������','1');
INSERT INTO `grade_define` VALUES ('5','1',100,91,'���Ե㸲��ȫ�桢����������UAT��Ͷ�����������','1');
INSERT INTO `grade_define` VALUES ('5','2',90,81,'���Գɹ�����꣨ģ�塢�����������������������ԣ����˻�1�Σ����˻�1���ٿ�10�֣�','1');
INSERT INTO `grade_define` VALUES ('5','3',80,71,'�������ܲ�����ȷ��δ�������⹦��','1');
INSERT INTO `grade_define` VALUES ('5','4',70,61,'�Բ��Է�Χ������ƫ�����©����Ҫ��δ����','1');
INSERT INTO `grade_define` VALUES ('5','5',60,31,'���԰����Ѿ����������ְ���δִ�л�û�м����','1');
INSERT INTO `grade_define` VALUES ('5','6',50,1,'���Է�������δ��ʱ�������޸Ĳ����ĵ�����ɶ��γ���','1');
INSERT INTO `grade_define` VALUES ('6','1',100,91,'����ԣ��û�Ⱥ������ȷ�ԣ�����ȷ�С��޶��죩�������ԣ�������Ҫ��ͼ��ȣ��������ԣ����Ŀ¼����¼��ģ�����ȣ�����׷���ԣ��޶���¼�������塢��С�������š���ʽ�淶ͳһ���޴����','1');
INSERT INTO `grade_define` VALUES ('6','2',90,81,'��ʽ�淶��ģ�壩����꣬�˻�1�ο�10��','1');
INSERT INTO `grade_define` VALUES ('6','3',80,71,'����������Ƿȱ','1');
INSERT INTO `grade_define` VALUES ('6','4',70,61,'��׷��������Ƿȱ','1');
INSERT INTO `grade_define` VALUES ('6','5',60,31,'��ȷ�ԡ������ԡ������ԡ���׷��������Ƿȱ','1');
INSERT INTO `grade_define` VALUES ('6','6',30,1,'����ԡ���ȷ�ԡ������ԡ������ԡ���׷��������Ƿȱ','1');
INSERT INTO `grade_define` VALUES ('7','1',100,91,'ȫ��������ɱ���Ŀ����Ҫ��V+����ҵ�רҵ���ܺ�֪ʶ����������Ŀ����Ʒ�ʽ����̵���⣬����֪������Ȼ','1');
INSERT INTO `grade_define` VALUES ('7','2',90,81,'�߱�������ɱ���Ŀ�ı���Ͳ��Ե�����','1');
INSERT INTO `grade_define` VALUES ('7','3',80,61,'�Ա���Ŀ�˽�һ�㣬������ⲿ��ҵ������͹ؼ��߼�','1');
INSERT INTO `grade_define` VALUES ('7','4',60,1,'�Դ󲿷ֵ�ҵ����ֶ��߼�û���˽⣬ֻ���ڼ򵥵ĳ����޸�','1');
INSERT INTO `grade_define` VALUES ('8','1',100,91,'��ͷ��ͨ������Ҫ������������������ܹ���Ч׼ȷ������Ϣ�����ܹ���ũ��������ļ�����ҵ����Ľ���˵��','1');
INSERT INTO `grade_define` VALUES ('8','2',90,81,'�ܹ�ץס�ص����ݽ��й�ͨ���ܹ������Ĳ����ʵ��ķ�ʽ���Ŷӳ�Ա��ũ����Ա������ѯ�����','1');
INSERT INTO `grade_define` VALUES ('8','3',80,71,'��Ҫ��ͼ�ܹ��������������ͨ�����Բ�ǿ�����ֱ�ﲻ��˳������Ҫ������˵�������߼�����','1');
INSERT INTO `grade_define` VALUES ('8','4',70,61,'��ͷ��ͨ�跴�����ͣ���������ͼ���壬�߼���λ���','1');
INSERT INTO `grade_define` VALUES ('8','5',60,1,'��ͨ����˳����������Ϣ����','1');
INSERT INTO `grade_define` VALUES ('9','1',100,91,'�ܹ������е����ֶ��⿪������������Լ��������Ŭ����ʱ���','1');
INSERT INTO `grade_define` VALUES ('9','2',90,81,'�ܹ��е����⿪�����񡢵�û�а�ʱ���','1');
INSERT INTO `grade_define` VALUES ('9','3',80,71,'����ɱ�ְ����������£��޷��е����⹤����ҲδŬ���������Ҽ�ֵ��רҵ����','1');
INSERT INTO `grade_define` VALUES ('9','4',70,61,'���޷��е��������׷�����Ҳ������Ŭ��','1');
INSERT INTO `grade_define` VALUES ('9','5',60,1,'���������������������������䡢��Ը��Ժͻ����������','1');
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

INSERT INTO `require_changes` VALUES ('test',1,'��','����','20110113','00000200');
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
