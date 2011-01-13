<%@ page contentType="text/html; charset=GBK"%>
<%
	String msg = (String)request.getAttribute("message");
%>
<HTML>
<HEAD>


<TITLE>提示</TITLE>
</HEAD>
<BODY>
<TABLE align="center">
<tr align="center" valign="middle"><td><img src="images/sorry01.gif"></img></td></tr>
<tr align="center"><td><%=msg%></td></tr>
<tr align="center"><td><input type="button" style="Button" value="关闭窗口" onclick="window.close()"></td></tr>
</TABLE>
</BODY>
</HTML>