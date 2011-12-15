
/*********************************************************
 * File: PageObject.java
 * 
 * Version 1.0
 * 
 * Date     2005-8-30
 * 
 * Author   xin yong
 *  
 ********************************************************/

package com.eis.base;

import java.util.*;

import com.eis.config.*;
import com.eis.util.*;

/**
 * ˵������ҳ��Ϣ����
 */
public class PageObject {

	/**
	 * ÿҳ��¼������ϵͳ�������ã�
	 */
	private static int amountPerPage = 0;

	/**
	 * ��Ų�ѯ�������map
	 */
	private HashMap map = null;
	
	/**
	 * ��Ų�ѯ�����map
	 */
	private HashMap property = null;

	/**
	 * �б���
	 */
	private List list = null;

	/**
	 * ��ǰҳ
	 */
	private int curPage;

	/**
	 * ��ҳ��
	 */
	private int totalPage;

	/**
	 * �����ܼ�¼��
	 */
	private int totalRecord;

	/**
	 * ����¼�������ܴ��ڷ��ص��ܼ�¼��
	 */
	private int maxRecords;

	/**
	 * ��ǰ��¼��ʼλ��
	 */
	private int startPosition;
	private int maxFetchRow = -1;
	
	/*
	 * author ljp 2009/09/17
	 * footer ��ҳ�ű� ͨ�� setter��getter������ȡ
	 */
	private String footer;
	

	/**
	 * ���캯��
	 *
	 */
	public PageObject() {

		//��ѯ����һ�㲻����6��
		map = new HashMap(6);
		property = new HashMap(3);

		list = new ArrayList();

	}

	/**
	 * �����б�����ע
	 * ���磺 ��21��30����¼ ��76����¼
	 * @return String 
	 */
	public String getResultFooter() {
		if (getMaxRecords() > getTotalRecord())
			return "��"
				+ getStartPosition()
				+ "��"
				+ getEndPosition()
				+ "����¼ ��"
				+ getMaxRecords()
				+ "����¼ <font color=\"#FF0000\">��ϵͳֻ����ǰ"
				+ getMaxFetchRows()
				+ "����</font>";

		//+ "<script language=javascript >alert(' ��Ϣ��ʾ������ѯ����Ѿ�������ϵͳ���������¼��! \\n ϵͳ����ϵͳֻ����ǰ"
		//+ getMaxFetchRows()
		//+ "����¼!\\n ����취�������뾫ȷ��ѯ��������С��ѯ��Χ��');</script> ";
		else
			return "��"
				+ getStartPosition()
				+ "��"
				+ getEndPosition()
				+ "����¼ ��"
				+ getMaxRecords()
				+ "����¼";

	}

	/**
	 * ��ý���������ŵ�����¼������
	 * @return ����¼���� 
	 */
	public int getMaxFetchRows() {
		if(maxFetchRow == -1){
			return Integer.parseInt(SysConfig.getProperty("maxFetchRows"));
		}else
		return getMaxFetchRow();
		
	}

	/**
	 * ���ɷ�ҳ�ű�
	 * @return String 
	 */
	public String getPageFooter() {
		String pageFooter = null;
		String firstDisable =
			"<IMG src='images/page/PagerICO_First.gif' alt=��ҳ width='16' height='16' align='absmiddle' border=0>";
		String first =
			"<A href=\"javascript:turnPage(1)\">" + firstDisable + "</A>";

		String previousDisable =
			"<IMG src='images/page/PagerICO_Prev.gif' alt=��ҳ width='16' height='16' align='absmiddle' border=0>";
		String previous =
			"<A href=\"javascript:turnPage("
				+ (getCurPage() - 1)
				+ ")\">"
				+ previousDisable
				+ "</A>";

		String pp = getCurPage() + "/" + getTotalPage() + "ҳ";

		String nextDisable =
			"<IMG src='images/page/PagerICO_Next.gif' alt=��ҳ width='16' height='16' align='absmiddle' border=0>";
		String next =
			"<A href=\"javascript:turnPage("
				+ (getCurPage() + 1)
				+ ")\">"
				+ nextDisable
				+ "</A>";

		String lastDisable =
			"<IMG src='images/page/PagerICO_Final.gif' alt=βҳ width='16' height='16' align='absmiddle' border=0>";
		String last =
			"<A href=\"javascript:turnPage("
				+ getTotalPage()
				+ ")\">"
				+ lastDisable
				+ "</A>";

		if (totalPage <= 1) {
			pageFooter =
				firstDisable + previousDisable + pp + nextDisable + lastDisable;
		} else {
			if (curPage == 1) {
				pageFooter = firstDisable + previousDisable + pp + next + last;
			} else if (curPage == totalPage) {
				pageFooter = first + previous + pp + nextDisable + lastDisable;
			} else {
				//curPage<totalPage && curPage>1
				pageFooter = first + previous + pp + next + last;
			}
		}
		//��ת
		String goImage =
			"<IMG src='images/page/PagerICO_Go.gif' width=16  height=16 border=0  align='absmiddle'>";

		pageFooter = pageFooter + "  ��ת��";
		pageFooter =
			pageFooter
				+ "<INPUT	name=\"pageNO\" type=\"text\" class=\"Textfield\" size=3>";
		pageFooter =
			pageFooter
				+ "<A href=\"javascript:if (checkPageNO(document.forms[0].pageNO.value,'"
				+ getTotalPage()
				+ "')){turnPage(document.forms[0].pageNO.value)}\">";
		pageFooter = pageFooter + goImage + "</A>";

		return pageFooter;
	}

	/**
	 * @return
	 */
	public static int getAmountPerPage() {
		if (amountPerPage <= 0) {
			try {
				String rowsPerPage = SysConfig.getProperty("rowsPerPage");

				SysLog.debug("ÿҳ��¼����" + rowsPerPage);

				amountPerPage = Integer.parseInt(rowsPerPage);

			} catch (Exception ex) {
				SysLog.error("��ȡ��ҳ������Ϣʧ�ܣ�");
				SysLog.error(ex.getMessage());
			}
		}
		return amountPerPage;
	}

	/**
	 * @return
	 */
	public int getCurPage() {
		return curPage;
	}

	/**
	 * ��õ�ǰҳ�Ľ�����¼��
	 * @return int ������¼��
	 */
	public int getEndPosition() {
		int end = curPage * getAmountPerPage();
		return ((totalRecord < end) ? totalRecord : end);
	}

	/**
	 * @return
	 */
	public int getStartPosition() {
		return startPosition;
	}

	/**
	 * @return �����ܼ�¼��
	 */
	public int getTotalRecord() {
		return totalRecord;
	}

	/**
	 * @return
	 */
	public int getTotalPage() {
		return totalPage;
	}

	/**
	 * @param i
	 */
	public void setCurPage(int curPage) {
		this.curPage = curPage;

		//������ʼ��¼λ��
		setStartPosition((curPage - 1) * getAmountPerPage() + 1);

	}

	/**
	 * ������ʼ��¼λ��
	 * @param start
	 */
	public void setStartPosition(int start) {
		startPosition = start;
	}

	/**
	 * @param page
	 */
	public void setTotalRecord(int total) {
		totalRecord = total;

		//������ҳ��
		this.setTotalPage(
			((totalRecord % amountPerPage) == 0)
				? (totalRecord / amountPerPage)
				: (totalRecord / amountPerPage + 1));

	}

	/**
	 * @param page
	 */
	public void setTotalPage(int page) {
		totalPage = page;
	}

	/**
	 * @return
	 */
	public List getList() {
		return list;
	}

	/**
	 * @param list
	 */
	public void setList(List list) {
		this.list = list;
	}

	/**
	 * ��ò�ѯ������
	 * @param filterName ��ѯ����������
	 * @return String ��ѯ�����������ֵ
	 */

	public Object getFilter(String filterName) {
		return map.get(filterName);
	}

	/**
	 * �����ѯ������
	 * @param filterName ��ѯ����������
	 * @param filterVar ��ѯ�����������ֵ
	 */
	public void addFilter(String filterName, Object filterVar) {
		map.put(filterName, filterVar);
	}
	
	
	/**
	 * ��ò�ѯ���
	 * @param propName ��ѯ�����ֵ
	 * @return ��ѯ�����ֵ
	 */

	public Object getProperty(String propName) {
		return property.get(propName);
	}

	/**
	 * �����ѯ���
	 * @param propName ��ѯ�����ֵ
	 * @param prop ��ѯ�����ֵ
	 */
	public void setProperty(String propName, Object prop) {
		property.put(propName, prop);
	}

	/**
	* ��������ķ�ҳ��ע
	* @return ��ҳ��ע
	*/
	public String getFooter() {
		this.footer="<table width=\"98%\" border=0 cellspacing=0 cellpadding=0> <tr class=\"dtPanel_Pager\"><td>"
			+ getResultFooter()
			+ "</td><td align=\"right\">"
			+ getPageFooter()
			+ "</table>";
		
		return this.footer; 

	}

	public void setFooter(String footer){
		
		this.footer="<table width=\"98%\" border=0 cellspacing=0 cellpadding=0> <tr class=\"dtPanel_Pager\"><td>"
			+ getResultFooter()
			+ "</td><td align=\"right\">"
			+ getPageFooter()
			+ "</table>";
	}
	/**
	 * �����󷵻ؼ�¼��
	 * @return
	 */
	public int getMaxRecords() {
		return maxRecords;
	}

	/**
	 * @param i
	 */
	public void setMaxRecords(int i) {
		maxRecords = i;
	}

    /**
     * @author houxh 2008-1-2
     * 
     * @return 
     */
    public int getMaxFetchRow() {
        return maxFetchRow;
    }

    /**
     * @author houxh 2008-1-2
     * 
     * @param i 
     */
    public void setMaxFetchRow(int i) {
        maxFetchRow = i;
    }

}
