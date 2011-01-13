<%@ include file = "/includes/common.jsp" %>
<%@ page  contentType="text/html; charset=GBK" %>

<html>

<head>
<title>员工工作表</title>
<%
	String date = (String)request.getAttribute("date");
	String worktable = (String)request.getAttribute("worktable");
%>
</head>
<body>

<table align="center">
	<tr align="center"><td align="center"><font size="4"><a href="ProjectDistribute.do?act=display&date=<%=date%>&flag=pre">&lt;&lt;&nbsp;</a></font>
	<font size="4" color="blue"><%=date%></font>
	<font size="4"><a href="ProjectDistribute.do?act=display&date=<%=date%>&flag=next">&gt;&gt;</a></font></td></tr>
	<%=ViewUtil.getTitle("员工工作表")%>
	<%
		out.println(worktable);
	%>
</table>



</body>
</html>


