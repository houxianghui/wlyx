/*
 * @# QueryPageParam.java 2009-7-27 houxh
 *
 * Copyright  (c)  2009 	Huateng. All Right Reserv
 */

package com.eis.base;

public class QueryPageParam {
	
	static final int ROW_PER_PAGE = 15;

	public QueryPageParam() {
		this.rowPerPage = ROW_PER_PAGE;
	}

	private String sql;
	private int pageNum;
	private int rowPerPage;
	/**
	 * @return
	 */
	public int getPageNum() {
		return pageNum;
	}

	/**
	 * @return
	 */
	public int getRowPerPage() {
		return rowPerPage;
	}

	/**
	 * @return
	 */
	public String getSql() {
		return sql;
	}

	/**
	 * @param i
	 */
	public void setPageNum(int i) {
		pageNum = i;
	}

	/**
	 * @param i
	 */
	public void setRowPerPage(int i) {
		rowPerPage = i;
	}

	/**
	 * @param string
	 */
	public void setSql(String string) {
		sql = string;
	}
}
