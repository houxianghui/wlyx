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
 * 说明：字段标题标签类，用于实现对有问题字段的标题进行红色加亮显示
 * 
 */
public class FieldTitleTag extends TagSupport {
	/**
	 * 字段标识	  
	 */
	private String fieldID;
	
	/**
	 * 字段标题	  
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
			
		if (map == null || !map.containsKey(fieldID)) { //当前字段不存在错误
			
			fieldTitle = title ;

		} else { //当前字段存在错误

			fieldTitle = "<font color=\"#FF0000\">" + title + "</font>";

		}

		try {
			JspWriter out = pageContext.getOut();
			out.write(fieldTitle);
		} catch (IOException e) {
			SysLog.getLogger().error("输出申请页面字段标题失败！");
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
