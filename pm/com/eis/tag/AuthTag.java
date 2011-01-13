/*********************************************************
 * File: AuthTag.java
 * 
 * Version 1.0
 * 
 * Date     2005-10-18
 * 
 * Author   xin yong
 * 
 ********************************************************/

package com.eis.tag;
import java.io.*;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import com.eis.portal.UserContext;
import com.eis.cache.*;
import com.eis.util.*;

/**
 * 说明：需要进行权限校验的按钮标签类
 * 
 */
public class AuthTag extends TagSupport {

	protected String id = null;
	protected String value = null;
	protected String name = null;
	protected String onClick = null;
	protected boolean disabled = false;
	protected String style = null;
	protected String styleClass = null;
	protected String size = null;

	/**
	 * Process the start of this tag.
	 *
	 * @exception JspException if a JSP exception has occurred
	 */
	public int doStartTag() throws JspException {

		UserContext user =
			(UserContext) pageContext.getSession().getAttribute("user");

		//根据用户角色判断权限
		String roleID = user.getRoleID();

		//如果用户有权限，输出按钮	    
		if (OpMap.checkRoleAuth(roleID, id)) {
			StringBuffer sb = new StringBuffer("<input type=\"button\"");
			sb.append(this.name == null ? "" : " name=\"" + this.name + "\"");
			sb.append(
				this.value == null ? "" : " value=\"" + this.value + "\"");
			sb.append(
				this.styleClass == null
					? " class=\"Button\""
					: " class=\"" + this.styleClass + "\"");
			//sb.append(this.style==null?getDefaultInputInfo("style"):" style=\"" + this.style + "\"");
			//sb.append(this.id==null?"":" id=\"" + this.id + "\"");
			sb.append(
				this.onClick == null
					? ""
					: " onClick=\"" + this.onClick + "\"");
			sb.append(this.disabled ? " disabled" : "");
			sb.append(this.size == null ? "" : " size=\"" + this.size + "\"");

			sb.append(">");

			try {
				JspWriter out = pageContext.getOut();
				out.write(sb.toString());				
			} catch (IOException e) {
				SysLog.getLogger().error("输出权限操作按钮失败！");
				SysLog.getLogger().error(e.getMessage());				
			}
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
	public boolean isDisabled() {
		return disabled;
	}

	/**
	 * @return
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return
	 */
	public String getOnClick() {
		return onClick;
	}

	/**
	 * @return
	 */
	public String getSize() {
		return size;
	}

	/**
	 * @return
	 */
	public String getStyle() {
		return style;
	}

	/**
	 * @return
	 */
	public String getStyleClass() {
		return styleClass;
	}

	/**
	 * @return
	 */
	public String getValue() {
		return value;
	}

	/**
	 * @param b
	 */
	public void setDisabled(boolean b) {
		disabled = b;
	}

	/**
	 * @param string
	 */
	public void setName(String string) {
		name = string;
	}

	/**
	 * @param string
	 */
	public void setOnClick(String string) {
		onClick = string;
	}

	/**
	 * @param string
	 */
	public void setSize(String string) {
		size = string;
	}

	/**
	 * @param string
	 */
	public void setStyle(String string) {
		style = string;
	}

	/**
	 * @param string
	 */
	public void setStyleClass(String string) {
		styleClass = string;
	}

	/**
	 * @param string
	 */
	public void setValue(String string) {
		value = string;
	}

	/**
	 * @return
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param string
	 */
	public void setId(String string) {
		id = string;
	}

}
