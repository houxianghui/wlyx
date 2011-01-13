
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
 * 说明：翻页信息对象
 */
public class PageObject {

	/**
	 * 每页记录数（由系统参数配置）
	 */
	private static int amountPerPage = 0;

	/**
	 * 存放查询条件域的map
	 */
	private HashMap map = null;
	
	/**
	 * 存放查询结果的map
	 */
	private HashMap property = null;

	/**
	 * 列表结果
	 */
	private List list = null;

	/**
	 * 当前页
	 */
	private int curPage;

	/**
	 * 总页数
	 */
	private int totalPage;

	/**
	 * 返回总记录数
	 */
	private int totalRecord;

	/**
	 * 最大纪录数，可能大于返回的总纪录数
	 */
	private int maxRecords;

	/**
	 * 当前记录起始位置
	 */
	private int startPosition;
	private int maxFetchRow = -1;
	
	/*
	 * author ljp 2009/09/17
	 * footer 翻页脚本 通过 setter和getter方法获取
	 */
	private String footer;
	

	/**
	 * 构造函数
	 *
	 */
	public PageObject() {

		//查询条件一般不超过6项
		map = new HashMap(6);
		property = new HashMap(3);

		list = new ArrayList();

	}

	/**
	 * 生成列表结果脚注
	 * 例如： 第21到30条记录 共76条记录
	 * @return String 
	 */
	public String getResultFooter() {
		if (getMaxRecords() > getTotalRecord())
			return "第"
				+ getStartPosition()
				+ "到"
				+ getEndPosition()
				+ "条记录 共"
				+ getMaxRecords()
				+ "条记录 <font color=\"#FF0000\">（系统只返回前"
				+ getMaxFetchRows()
				+ "条）</font>";

		//+ "<script language=javascript >alert(' 信息提示：您查询结果已经超过了系统允许的最大记录数! \\n 系统处理：系统只返回前"
		//+ getMaxFetchRows()
		//+ "条纪录!\\n 解决办法：请输入精确查询条件，缩小查询范围！');</script> ";
		else
			return "第"
				+ getStartPosition()
				+ "到"
				+ getEndPosition()
				+ "条记录 共"
				+ getMaxRecords()
				+ "条记录";

	}

	/**
	 * 获得结果集允许存放的最大纪录数量。
	 * @return 最大纪录数量 
	 */
	public int getMaxFetchRows() {
		if(maxFetchRow == -1){
			return Integer.parseInt(SysConfig.getProperty("maxFetchRows"));
		}else
		return getMaxFetchRow();
		
	}

	/**
	 * 生成翻页脚本
	 * @return String 
	 */
	public String getPageFooter() {
		String pageFooter = null;
		String firstDisable =
			"<IMG src='images/page/PagerICO_First.gif' alt=首页 width='16' height='16' align='absmiddle' border=0>";
		String first =
			"<A href=\"javascript:turnPage(1)\">" + firstDisable + "</A>";

		String previousDisable =
			"<IMG src='images/page/PagerICO_Prev.gif' alt=上页 width='16' height='16' align='absmiddle' border=0>";
		String previous =
			"<A href=\"javascript:turnPage("
				+ (getCurPage() - 1)
				+ ")\">"
				+ previousDisable
				+ "</A>";

		String pp = getCurPage() + "/" + getTotalPage() + "页";

		String nextDisable =
			"<IMG src='images/page/PagerICO_Next.gif' alt=下页 width='16' height='16' align='absmiddle' border=0>";
		String next =
			"<A href=\"javascript:turnPage("
				+ (getCurPage() + 1)
				+ ")\">"
				+ nextDisable
				+ "</A>";

		String lastDisable =
			"<IMG src='images/page/PagerICO_Final.gif' alt=尾页 width='16' height='16' align='absmiddle' border=0>";
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
		//跳转
		String goImage =
			"<IMG src='images/page/PagerICO_Go.gif' width=16  height=16 border=0  align='absmiddle'>";

		pageFooter = pageFooter + "  跳转到";
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

				SysLog.debug("每页纪录数：" + rowsPerPage);

				amountPerPage = Integer.parseInt(rowsPerPage);

			} catch (Exception ex) {
				SysLog.error("读取翻页配置信息失败！");
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
	 * 获得当前页的截至纪录数
	 * @return int 截至记录数
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
	 * @return 返回总纪录数
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

		//设置起始记录位置
		setStartPosition((curPage - 1) * getAmountPerPage() + 1);

	}

	/**
	 * 设置起始记录位置
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

		//设置总页数
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
	 * 获得查询条件项
	 * @param filterName 查询条件参数名
	 * @return String 查询条件输入项的值
	 */

	public Object getFilter(String filterName) {
		return map.get(filterName);
	}

	/**
	 * 保存查询条件项
	 * @param filterName 查询条件参数名
	 * @param filterVar 查询条件输入项的值
	 */
	public void addFilter(String filterName, Object filterVar) {
		map.put(filterName, filterVar);
	}
	
	
	/**
	 * 获得查询结果
	 * @param propName 查询结果键值
	 * @return 查询结果的值
	 */

	public Object getProperty(String propName) {
		return property.get(propName);
	}

	/**
	 * 保存查询结果
	 * @param propName 查询结果键值
	 * @param prop 查询结果的值
	 */
	public void setProperty(String propName, Object prop) {
		property.put(propName, prop);
	}

	/**
	* 输出完整的翻页脚注
	* @return 翻页脚注
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
	 * 获得最大返回纪录数
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
