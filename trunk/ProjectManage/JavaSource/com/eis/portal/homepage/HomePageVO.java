 
package com.eis.portal.homepage; 
 
import com.eis.base.BaseVO; 
 
/** 
 * ˵������ҳģ������ݶ��� 
 */ 
public class HomePageVO extends BaseVO { 
 
	/** 
	 * ���캯�� 
	 */ 
	public HomePageVO() { 
		super(); 
	} 
 
	/** 
	 * ģ���� 
	 */ 
	private int templ_id; 
 
	/** 
	 * ģ������ 
	 */ 
	private String caption; 
 
	/** 
	 * URL 
	 */ 
	private String url; 
 
	/** 
	 *  ���ģ���� 
	 */ 
	public int getTempl_id() {  
		return templ_id; 
	} 
 
	/** 
	 *  ���ģ������ 
	 */ 
	public String getCaption() {  
		return caption; 
	} 
 
	/** 
	 *  ���URL 
	 */ 
	public String getUrl() {  
		return url; 
	} 
 
	/** 
	 *  ����ģ���� 
	 */ 
	public void  setTempl_id(int val) {  
		templ_id = val; 
	} 
 
	/** 
	 *  ����ģ������ 
	 */ 
	public void  setCaption(String val) {  
		caption = val; 
	} 
 
	/** 
	 *  ����URL 
	 */ 
	public void  setUrl(String val) {  
		url = val; 
	} 
 
} 

