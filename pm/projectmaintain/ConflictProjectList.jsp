
<%@ include file = "/includes/common.jsp" %>
<%@ page  contentType="text/html; charset=GBK" %>

<%@ page import="com.eis.base.PageObject" %>
<head>
<title>冲突项目列表</title>

</head>
<body class="body_bg_grey1">
<html:form method="post" action="ProjectMaintain.do">

	<%=ViewUtil.getTitle("冲突项目列表")%>

	<table width="98%" align=center class="dtPanel_Line1" border="0"
		cellspacing="1" cellpadding="1">
		<%
			String s = (String)request.getAttribute("conflictProjects");
			out.println(s);
		%>
	</table>

	<table class=heightspace_bottom1 border="0" cellspacing="0"
		cellpadding="0">
		<tr>
			<td></td>
		</tr>
	</table>
	<table align=center width="98%" border="0" cellspacing="0"
		cellpadding="0">
		<tr>
			<td height="25" align="center" class="dtPanel_Bottom">
			 <input name="return" type="button"	class="Button" value="返回" onClick="history.back()"></td>
		</tr>
	</table>

</html:form>

</body>
</html>
