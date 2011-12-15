<%@page import="com.huateng.blue.work.WorkDistribute"%>
<%@ include file = "/includes/common.jsp" %>
<%@ page  contentType="text/html; charset=GBK" %>

<jsp:useBean id="pageResult" scope="request" class="com.eis.base.PageObject" />

<html>

<%int maxPage = 1;
if (pageResult != null)
	maxPage = pageResult.getTotalPage();
%>
<head>
<title>任务分配</title>
<script language="javascript">  
function turnPage( pagenm ) {   
    	document.forms[0].act.value = "qd";  
    	document.forms[0].pageNO.value = pagenm;     
    	document.forms[0].submit(); 
} 
 
</script>
</head>
<body>

<html:form method="post" action="WorkDistribute.do">
<input type=hidden name=act value="qd">
<html:hidden property="workId"/>
<table width="98%" class="dtPanel_Line1" border="0" cellspacing="1"
		align="center" cellpadding="0">
		<tr align="center" class="dtPanel_Top01">
			<td nowrap>任务编号</td>
			<td>人员</td>
			<td>开始日期</td>
			<td>结束日期</td>
			<td>任务状态</td>
			<td>备注</td>
			<td>选择</td>

		</tr>
		<%if (pageResult != null) {
	List list = pageResult.getList();
	if (list != null) {
		Iterator iter = list.iterator();
		while (iter.hasNext()) {
			WorkDistribute vo = (WorkDistribute) iter.next();%>
		<%
		if(vo.getWorkStatus().equals("F")){
			out.print("<tr align='center' bgcolor=\"DarkSeaGreen\"");
		}else{
			out.print("<tr align=\"center\" class=\"dtPanel_Main\" onclick=\"_clickTr( this )\"");
		}
		%>>
			<td><a href="WorkDistribute.do?act=detail&id=<%=vo.getId()%>"><%=vo.getWorkId()%></a></td>
			<td><%=ReDefSDicMap.getDicItemVal("0003",vo.getUserId())%></td>
			<td><%=vo.getStartDate()%></td>
			<td><%=vo.getEndDate()%></td>
			<td><%=SingleDicMap.getDicItemVal(SingleDic.WORK_STATUS,vo.getWorkStatus())%> </td>	
			<td><%=vo.getContent()%></td>	
			<td><label><input type="radio" name="param"	onClick="setPKey('<%=vo.getId()%>','<%=vo.getWorkId()%>')">
			</label></td>
		</tr>

		<%}
}
}%>

</table>
	<%
//产生翻页脚注 
if (pageResult != null) {%>
	<table width="98%" align="center" border="0" cellspacing="0"
		cellpadding="0">
		<tr>
			<td class="dtPanel_Pager"><%=pageResult.getFooter()%></td>
		</tr>
	</table>
	<%}%>
	<br>

	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td height="25" align="center">			
			<input type="button" value="返回" class="Button" onclick="history.back()"></td>
		</tr>
	</table>

</html:form>

<p>&nbsp;</p>
</body>
</html>


