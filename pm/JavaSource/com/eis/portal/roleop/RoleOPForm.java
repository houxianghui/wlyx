/*
 * 创建日期 2005-9-24
 *
 */
package com.eis.portal.roleop;
import com.eis.base.BaseForm;

/**
 * @author Administrator
 *
 * 更改所生成类型注释的模板为
 * 窗口 > 首选项 > Java > 代码生成 > 代码和注释
 */
public class RoleOPForm extends BaseForm {

	/**
	 * 构造函数
	 */
	public RoleOPForm() {
		super();

	}

	private String role_id; //角色代码

	private String role_name; //角色名称

	/**
	 * @return
	 */
	public String getRole_id() {
		return role_id;
	}

	/**
	 * @return
	 */
	public String getRole_name() {
		return role_name;
	}

	/**
	 * @param string
	 */
	public void setRole_id(String string) {
		role_id = string;
	}

	/**
	 * @param string
	 */
	public void setRole_name(String string) {
		role_name = string;
	}

}
