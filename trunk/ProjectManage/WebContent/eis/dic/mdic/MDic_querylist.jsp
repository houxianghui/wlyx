<%@ include file = "/includes/common.jsp" %> 
<%@ page import="com.eis.dic.mdic.MDicVO" %> 
<%@ page  contentType="text/html; charset=GBK" %> 
 
<jsp:useBean id="pageResult" scope="request" class="com.eis.base.PageObject" /> 
 
<html> 
 
<title>多级字典查询</title> 
 
 
<% 
	int maxPage = 1; 
	if(pageResult != null) 
		maxPage = pageResult.getTotalPage(); 
 
%> 
 
<script language="javascript"> 
 
 
function doQuery() { 
	//根据输入条件查询 
 
	//检查输入查询条件 
 
 
	document.forms[0].act.value = "ql"; 
	document.forms[0].requery.value='y'; 
	document.forms[0].submit(); 
} 
 
function turnPage( pagenm ) {   
    	document.forms[0].act.value = "ql";  
    	document.forms[0].pageNO.value = pagenm;     
    	document.forms[0].submit(); 
} 
 
</script> 
 
<body> 
 
<html:form method="post" action="MDic.do"> 
<input type=hidden name=act value="ql"> 
<input type=hidden name=requery > 
 
<table width="98%" class="dtPanel_Line" align="center" border="0" cellspacing="1" cellpadding="0"> 
  <tr> 
    <td class="dtPanel_Line"> 
    <table width="100%"  border="0" cellspacing="0" cellpadding="0"> 
         <tr class="dtPanel_Top02"> 
            <td>&nbsp; 系统编码：<html:text property="sys_id_f" styleClass="Textfield" size="12" maxlength="12" />&nbsp; <input	name="query" type="button" class="Button_Search"  onClick="doQuery()"> &nbsp;</td> 
          </tr> 
        </table> 
        </td> 
  </tr> 
</table> 
 
<table  class=heightspace_top3 ><tr><td></td></tr></table> 
 
		<table width="98%" class="dtPanel_Line1" border="0" cellspacing="1" align="center" cellpadding="0"> 
					<tr align="center" class="dtPanel_Top01"> 
 
						<td width="20%">系统编码</td> 
						<td width="20%">归类码</td> 
						<td width="20%">上级编码</td> 
						<td width="20%">选项编码</td> 
						<td width="20%">选项值</td> 
						<td width="20%">输出顺序</td> 
						<td width="20%">级次</td> 
						<td width="20%">逻辑代码</td> 
						<td width="20%">status</td> 
 
					</tr> 
			<% 
				if(pageResult != null)  { 
					List list = pageResult.getList(); 
					if(list != null ) { 
						Iterator iter = list.iterator(); 
						while (iter.hasNext()) { 
						MDicVO vo = (MDicVO) iter.next(); 
			%> 
					<tr align="center" class="dtPanel_Main" onclick="_clickTr( this )"> 
						<td><a class=fontlink1 href="MDic.do?act=r&sys_id=<%=vo.getSys_id()%>"><%=vo.getSys_id()%></a></td> 
						<td><%=vo.getType_id()%></td> 
						<td><%=vo.getParent_id()%></td> 
						<td><%=vo.getItem_id()%></td> 
						<td><%=vo.getItem_val()%></td> 
						<td><%=vo.getList_order()%></td> 
						<td><%=vo.getItem_level()%></td> 
						<td><%=vo.getLogic_id()%></td> 
						<td><%=vo.getStatus()%></td> 
 
					</tr> 
 
				<% 
							} 
						} 
					} 
				%> 
				</table> 
			<% 
				//产生翻页脚注 
				if(pageResult != null) { 
			%> 
			<table width="98%"  align="center"   border="0" cellspacing="0" cellpadding="0"> 
				<tr> 
					<td class="dtPanel_Pager"> <%=pageResult.getFooter()%> </td> 
				</tr> 
			</table> 
			<% 
				} 
			%> 
 
</html:form> 
 
<p>&nbsp;</p> 
</body> 
</html> 
 

