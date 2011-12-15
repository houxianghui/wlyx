/*********************************************************
 * File: FieldTitleTag.java
 * 
 * Version 1.0
 * 
 * Date     2005-12-1
 * 
 * Author   xin yong
 * 
 ********************************************************/

package com.eis.tag;

import java.util.HashMap;

import java.io.*;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import com.eis.util.*;

/**
 * ˵�����ֶα����ǩ�࣬����ʵ�ֶ��������ֶεı�����к�ɫ������ʾ
 * 
 */
public class FieldTitleTag extends TagSupport {
	/**
	 * �ֶα�ʶ	  
	 */
	private String fieldID;
	
	/**
	 * �ֶα���	  
	 */	
	private String title;

	/**
	 * Process the start of this tag.
	 *
	 * @exception JspException if a JSP exception has occurred
	 */
	public int doStartTag() throws JspException {
		String fieldTitle = null;
		HashMap map =
			(HashMap) pageContext.getRequest().getAttribute("errorField");
			
		if (map == null || !map.containsKey(fieldID)) { //��ǰ�ֶβ����ڴ���
			
			fieldTitle = title ;

		} else { //��ǰ�ֶδ��ڴ���

			fieldTitle = "<font color=\"#FF0000\">" + title + "</font>";

		}

		try {
			JspWriter out = pageContext.getOut();
			out.write(fieldTitle);
		} catch (IOException e) {
			SysLog.getLogger().error("�������ҳ���ֶα���ʧ�ܣ�");
			SysLog.getLogger().error(e.getMessage());
		}

		return (EVAL_BODY_INCLUDE);

	}

	/**
	 * Process the end of this tag.
	 *
	 * @exception JspException if a JSP exception has occurred
	 */
	public int doEndTag() throws JspException {

		return (EVAL_PAGE);

	}

	/**
	 * @return
	 */
	public String getFieldID() {
		return fieldID;
	}

	/**
	 * @param string
	 */
	public void setFieldID(String string) {
		fieldID = string;
	}

	/**
	 * @return
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param string
	 */
	public void setTitle(String string) {
		title = string;
	}

}
