<%@page import="com.huateng.blue.work.WorkList"%>
<%@ include file = "/includes/common.jsp" %>
<%@ page  contentType="text/html; charset=GBK" %>

<jsp:useBean id="pageResult" scope="request" class="com.eis.base.PageObject" />

<html>

<%int maxPage = 1;
if (pageResult != null)
	maxPage = pageResult.getTotalPage();
%>
<head>
<title>待审批任务列表</title>
<script language="javascript">  
function turnPage( pagenm ) {   
	document.forms[0].act.value = "gdw";  
	document.forms[0].pageNO.value = pagenm;     
	document.forms[0].submit(); 
} 
function doTrace(){
	if(document.forms[0].workId.value == null ||document.forms[0].workId.value == "") { 
		alert('请先选择纪录'); 
		return; 
	} 	
	document.forms[0].act.value="dl";
	document.forms[0].submit();
	
}
function setPKey(projectId_var) { 
	document.forms[0].workId.value=projectId_var; 
} 
</script>
</head>
<body>

<html:form method="post" action="WorkDistribute.do">
<input type=hidden name=act value="gdw">
<html:hidden property="workId"/>

<%=ViewUtil.getTitle("待审批任务列表")%>
	
	<table class=heightspace_top3>
		<tr>
			<td></td>
		</tr>
	</table>

	<table width="98%" class="dtPanel_Line1" border="0" cellspacing="1"
		align="center" cellpadding="0">
		<tr align="center" class="dtPanel_Top01" height="28">
			<td nowrap>任务编号</td>			
			<td nowrap>开始时间</td>
			<td nowrap>结束时间</td>
			<td>选择</td>
		</tr>
		<%if (pageResult != null) {
	List list = pageResult.getList();
	if (list != null) {
		Iterator iter = list.iterator();
		while (iter.hasNext()) {
			WorkList vo = (WorkList) iter.next();%>
		<tr align="center"  bgcolor="Beige" >
			<td><a href="WorkList.do?act=view&workId=<%=vo.getWorkId()%>"><%=vo.getWorkId()%></a></td>
			<td><%=vo.getStartDate()%></td>
			<td><%=vo.getEndDate()%></td>
			<td><label><input type="radio" name="param"	onClick="setPKey('<%=vo.getWorkId()%>')">
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
			<input type="button" value="任务跟踪" class="Button" onClick="doTrace()"/>		
		</tr>
	</table>
</html:form>

<p>&nbsp;</p>
</body>
</html>


