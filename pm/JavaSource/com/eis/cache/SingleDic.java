/*
 * @(#) SingleDic.java V(1.0) 2007-7-23 houxh
 * 
 * Copyright  (c)  2007 	Huateng. All Right Reserved 
 */

package com.eis.cache;

/**
 * @author houxh
 * 用来保存单级数据字典常量，不要在程序中直接使用该数字
 * 
 */
public interface SingleDic {
	
	String LM_FLAG = "0014"; 			//联名卡标示
	String JUDGE_LMT = "0220";			//独立审批人默认额度字典
	String CREDIT_PLAN = "0232";		//信用计划
	String CREDIT_PLAN_STAT = "0233";	//额度主动提升资料状态
	String CARD_LOGO = "0128";			//卡种LOGO
	String CERT_TYPE = "0026";			//证件类型
	String FIX_TRANS_FEE = "0236";              //转存金额
	String INSTALLMENT_NUM_COLLECTION = "0237";	//分期付款期数
	String PHONE_CHECK_SUGGESTION = "0255";			//电话核实处理意见
	String PHONE_VERIFY_RESULT = "0135";		//电话核实情况
	String PROJECT_MANAGE_COLLECTION = "9994"; 	//项目维护
	String YES_OR_NO="0000";					//yes or no
	//自定义单级数据字典
	String PROD_KIND = "0007";	//外挂产品编号
	String PERSON_PRODUCT = "0015"; 	//个人产品
	String CARD_STYLE = "0256";				//卡级别（全国、地方、……）
	String GLS_CURRENCY = "1001";			//GLS 币种
	String APPLY_MAX_LINE = "5099";		//限制每次申请文件的最大条数
	String WORK_STATUS = "1001";		//任务状态
	String TITLE = "1002";				//报表标题
}
