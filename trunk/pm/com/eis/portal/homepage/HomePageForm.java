 
package com.eis.portal.homepage; 
 
import com.eis.base.BaseForm; 
 
/** 
 * ˵������ҳģ������ݶ��� 
 */ 
public class HomePageForm extends BaseForm { 
 
	/** 
	 * ���캯�� 
	 */ 
	public HomePageForm() { 
		super(); 
	} 
 
	/** 
	 * ģ���� 
	 */ 
	private String templ_id; 
 
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
	public String getTempl_id() {  
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
	public void  setTempl_id(String str) {  
		templ_id = str; 
	} 
 
	/** 
	 *  ����ģ������ 
	 */ 
	public void  setCaption(String str) {  
		caption = str; 
	} 
 
	/** 
	 *  ����URL 
	 */ 
	public void  setUrl(String str) {  
		url = str; 
	} 
 
} 

