 
package com.eis.dic.redefsdic; 

import java.util.*;
import org.apache.struts.util.*;
import com.eis.base.BaseForm; 
 
/** 
 * ˵�����Զ��嵥���ֵ�����ݶ��� 
 */ 
public class ReDefSDicForm extends BaseForm { 
 
	/** 
	 * ���캯�� 
	 */ 
	public ReDefSDicForm() { 
		super(); 
	} 
 
	/** 
	 * ������ 
	 */ 
	private String type_id; 
 
	/** 
	 * �ֵ����� 
	 */ 
	private String caption; 
	
	
	/** 
	 * ��ѯ���� 
	 */ 
	private String caption_f; 
 
	/** 
	 * ���ݲ�ѯ��� 
	 */ 
	private String stmt; 
 
	/** 
	 * ��ע 
	 */ 
	private String memo; 
 
	/** 
	 * ������Ա 
	 */ 
	private String user_id; 
 
	/** 
	 * �������� 
	 */ 
	private String reg_dt; 
 
   
    private String tt="1";
    
    private Collection ttoptions;
 
 
 
	/** 
	 *  ��ù����� 
	 */ 
	public String getType_id() {  
		return type_id; 
	} 
 
	/** 
	 *  ����ֵ����� 
	 */ 
	public String getCaption() {  
		return caption; 
	} 
 
	/** 
	 *  ������ݲ�ѯ��� 
	 */ 
	public String getStmt() {  
		return stmt; 
	} 
 
	/** 
	 *  ��ñ�ע 
	 */ 
	public String getMemo() {  
		return memo; 
	} 
 
	/** 
	 *  ��ø�����Ա 
	 */ 
	public String getUser_id() {  
		return user_id; 
	} 
 
	/** 
	 *  ��ø������� 
	 */ 
	public String getReg_dt() {  
		return reg_dt; 
	} 
 
	/** 
	 *  ���ù����� 
	 */ 
	public void  setType_id(String str) {  
		type_id = str; 
	} 
 
	/** 
	 *  �����ֵ����� 
	 */ 
	public void  setCaption(String str) {  
		caption = str; 
	} 
 
	/** 
	 *  �������ݲ�ѯ��� 
	 */ 
	public void  setStmt(String str) {  
		stmt = str; 
	} 
 
	/** 
	 *  ���ñ�ע 
	 */ 
	public void  setMemo(String str) {  
		memo = str; 
	} 
 
	/** 
	 *  ���ø�����Ա 
	 */ 
	public void  setUser_id(String str) {  
		user_id = str; 
	} 
 
	/** 
	 *  ���ø������� 
	 */ 
	public void  setReg_dt(String str) {  
		reg_dt = str; 
	} 
 
	/**
	 * @return
	 */
	public String getCaption_f() {
		return caption_f;
	}

	/**
	 * @param string
	 */
	public void setCaption_f(String string) {
		caption_f = string;
	}

	/**
	 * @return
	 */
	public String getTt() {
		return tt;
	}

	/**
	 * @return
	 */
	public Collection getTtoptions() {
		ttoptions = new ArrayList();
		ttoptions.add(new LabelValueBean("Label 1", "0"));
		ttoptions.add(new LabelValueBean("Label 2", "1"));
		ttoptions.add(new LabelValueBean("Label 3", "2"));
		
		return ttoptions;
	}

	/**
	 * @param string
	 */
	public void setTt(String string) {
		tt = string;
	}

	/**
	 * @param collection
	 */
	public void setTtoptions(Collection collection) {
		ttoptions = collection;
	}

} 

