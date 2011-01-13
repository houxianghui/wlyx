/*
 * 创建日期 2009-2-27
 *
 * 更改所生成文件模板为
 * 窗口 > 首选项 > Java > 代码生成 > 代码和注释
 */
package com.projectmaintain;

import com.eis.base.BaseVO;

/**
 * @author doria
 *
 * 更改所生成类型注释的模板为
 * 窗口 > 首选项 > Java > 代码生成 > 代码和注释
 */
public class BlankWorkVO extends BaseVO {
	private String sdate;
	private String edate;
	private String user;
	/**
	 * @return
	 */
	public String getEdate() {
		return edate;
	}

	/**
	 * @return
	 */
	public String getSdate() {
		return sdate;
	}

	/**
	 * @return
	 */
	public String getUser() {
		return user;
	}

	/**
	 * @param string
	 */
	public void setEdate(String string) {
		edate = string;
	}

	/**
	 * @param string
	 */
	public void setSdate(String string) {
		sdate = string;
	}

	/**
	 * @param string
	 */
	public void setUser(String string) {
		user = string;
	}

}
