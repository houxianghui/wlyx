 
package com.eis.portal.homepage; 
 
import com.eis.base.BaseVO; 
 
/** 
 * 说明：首页模板的数据对象 
 */ 
public class HomePageVO extends BaseVO { 
 
	/** 
	 * 构造函数 
	 */ 
	public HomePageVO() { 
		super(); 
	} 
 
	/** 
	 * 模板编号 
	 */ 
	private int templ_id; 
 
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
	public int getTempl_id() {  
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
	public void  setTempl_id(int val) {  
		templ_id = val; 
	} 
 
	/** 
	 *  设置模板名称 
	 */ 
	public void  setCaption(String val) {  
		caption = val; 
	} 
 
	/** 
	 *  设置URL 
	 */ 
	public void  setUrl(String val) {  
		url = val; 
	} 
 
} 

