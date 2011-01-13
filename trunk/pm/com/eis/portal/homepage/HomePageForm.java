 
package com.eis.portal.homepage; 
 
import com.eis.base.BaseForm; 
 
/** 
 * 说明：首页模板的数据对象 
 */ 
public class HomePageForm extends BaseForm { 
 
	/** 
	 * 构造函数 
	 */ 
	public HomePageForm() { 
		super(); 
	} 
 
	/** 
	 * 模板编号 
	 */ 
	private String templ_id; 
 
	/** 
	 * 模板名称 
	 */ 
	private String caption; 
 
	/** 
	 * URL 
	 */ 
	private String url; 
 
	/** 
	 *  获得模板编号 
	 */ 
	public String getTempl_id() {  
		return templ_id; 
	} 
 
	/** 
	 *  获得模板名称 
	 */ 
	public String getCaption() {  
		return caption; 
	} 
 
	/** 
	 *  获得URL 
	 */ 
	public String getUrl() {  
		return url; 
	} 
 
	/** 
	 *  设置模板编号 
	 */ 
	public void  setTempl_id(String str) {  
		templ_id = str; 
	} 
 
	/** 
	 *  设置模板名称 
	 */ 
	public void  setCaption(String str) {  
		caption = str; 
	} 
 
	/** 
	 *  设置URL 
	 */ 
	public void  setUrl(String str) {  
		url = str; 
	} 
 
} 

