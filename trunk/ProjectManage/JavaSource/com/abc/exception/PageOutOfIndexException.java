package com.abc.exception;

import org.springframework.dao.DataAccessException;

/**
 * 
 * @author Administrator
 *
 */
public class PageOutOfIndexException extends DataAccessException
{
	private int curPage;
	private int totalPage;

	/**
	 * Constructor for InvalidDataAccessResourceUsageException.
	 * @param msg the detail message
	 */
	public PageOutOfIndexException(int curPage,int totalPage) 
	{
		super("��ҳ���ݴ���:"+"��ǰҳΪ "+curPage+": ��ѯ�������ҳ�� "+totalPage);
		this.curPage=curPage;
		this.totalPage=totalPage;
	}
	public int getCurPage() 
	{
		return curPage;
	}
	public int getTotalPage() {

		return totalPage;
	}
}
