<%@ page contentType="text/html; charset=GBK"%>
<%
	String msg = (String)request.getAttribute("message");
%>
<HTML>
<HEAD>


<TITLE>��ʾ</TITLE>
</HEAD>
<BODY>
<TABLE align="center">
<tr align="center" valign="middle"><td><img src="images/sorry01.gif"></img></td></tr>
<tr align="center"><td><%=msg%></td></tr>
<tr align="center"><td><input type="button" style="Button" value="�رմ���" onclick="window.close()"></td></tr>
</TABLE>
</BODY>
</HTML>