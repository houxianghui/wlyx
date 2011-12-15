<%@ include file = "/includes/common.jsp" %>
<%@ page import="com.projectmaintain.ChangeRecordVO" %>
<%@ page  contentType="text/html; charset=GBK" %>

<jsp:useBean id="pageResult" scope="request" class="com.eis.base.PageObject" />

<html>

<%int maxPage = 1;
if (pageResult != null)
	maxPage = pageResult.getTotalPage();
%>
<head>
<title>��Ŀ/��������ʷ</title>
<script language="javascript"> 
 
function turnPage( pagenm ) {   
    	document.forms[0].act.value = "gc";  
    	document.forms[0].pageNO.value = pagenm;     
    	document.forms[0].submit(); 
} 
 
</script>
</head>
<body>

<html:form method="post" action="ProjectMaintain.do">
<input type=hidden name=act value="gc">
<html:hidden property="projectId"/>
<html:hidden property="id"/>
<%=ViewUtil.getTitle("��Ŀ/��������ʷ")%>
	
	<table class=heightspace_top3>
		<tr>
			<td></td>
		</tr>
	</table>

	<table width="98%" class="dtPanel_Line1" border="0" cellspacing="1"
		align="center" cellpadding="0">
		<tr align="center" class="dtPanel_Top01" height="28">
			<td>�������</td>
			<td>�����Ա</td>
			<td>���ԭ��</td>
			<td>�������</td>
		</tr>
		<%if (pageResult != null) {
	List list = pageResult.getList();
	if (list != null) {
		Iterator iter = list.iterator();
		while (iter.hasNext()) {
			ChangeRecordVO vo = (ChangeRecordVO) iter.next();%>
		<tr align="center" class="dtPanel_Main" onclick="_clickTr( this )">
			<td><%=vo.getChangeDate()%></td>
			<td><%=ReDefSDicMap.getDicItemVal("0003",vo.getUserId())%></td>			
			<td><%=vo.getReason()%> </td>	
			<td><%=vo.getContent()%></td>				
		</tr>

		<%}
}
}%>

</table>
	<%
//������ҳ��ע 
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
			<input type="button" class="Button" value="����" onclick="history.back()"/>
		</tr>
	</table>
</html:form>

<p>&nbsp;</p>
</body>
</html>


