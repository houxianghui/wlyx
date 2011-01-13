 
package com.eis.dic.redefsdic; 

import java.util.*;
import org.apache.struts.util.*;
import com.eis.base.BaseForm; 
 
/** 
 * 说明：自定义单级字典的数据对象 
 */ 
public class ReDefSDicForm extends BaseForm { 
 
	/** 
	 * 构造函数 
	 */ 
	public ReDefSDicForm() { 
		super(); 
	} 
 
	/** 
	 * 规类码 
	 */ 
	private String type_id; 
 
	/** 
	 * 字典内容 
	 */ 
	private String caption; 
	
	
	/** 
	 * 查询条件 
	 */ 
	private String caption_f; 
 
	/** 
	 * 数据查询语句 
	 */ 
	private String stmt; 
 
	/** 
	 * 备注 
	 */ 
	private String memo; 
 
	/** 
	 * 更新人员 
	 */ 
	private String user_id; 
 
	/** 
	 * 更新日期 
	 */ 
	private String reg_dt; 
 
   
    private String tt="1";
    
    private Collection ttoptions;
 
 
 
	/** 
	 *  获得规类码 
	 */ 
	public String getType_id() {  
		return type_id; 
	} 
 
	/** 
	 *  获得字典内容 
	 */ 
	public String getCaption() {  
		return caption; 
	} 
 
	/** 
	 *  获得数据查询语句 
	 */ 
	public String getStmt() {  
		return stmt; 
	} 
 
	/** 
	 *  获得备注 
	 */ 
	public String getMemo() {  
		return memo; 
	} 
 
	/** 
	 *  获得更新人员 
	 */ 
	public String getUser_id() {  
		return user_id; 
	} 
 
	/** 
	 *  获得更新日期 
	 */ 
	public String getReg_dt() {  
		return reg_dt; 
	} 
 
	/** 
	 *  设置规类码 
	 */ 
	public void  setType_id(String str) {  
		type_id = str; 
	} 
 
	/** 
	 *  设置字典内容 
	 */ 
	public void  setCaption(String str) {  
		caption = str; 
	} 
 
	/** 
	 *  设置数据查询语句 
	 */ 
	public void  setStmt(String str) {  
		stmt = str; 
	} 
 
	/** 
	 *  设置备注 
	 */ 
	public void  setMemo(String str) {  
		memo = str; 
	} 
 
	/** 
	 *  设置更新人员 
	 */ 
	public void  setUser_id(String str) {  
		user_id = str; 
	} 
 
	/** 
	 *  设置更新日期 
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

