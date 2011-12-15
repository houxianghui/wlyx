

<%@ page contentType="text/html; charset=GBK"%>
<%
	String msg = (String)request.getAttribute("message");	
%>

<HTML>
<HEAD>

<TITLE>信息提示</TITLE>
</HEAD>
<BODY>
<script language="javascript">
alert("<%=msg%>");
window.close();
</script>
</BODY>
</HTML>
