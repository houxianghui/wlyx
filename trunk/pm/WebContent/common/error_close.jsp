

<%@ page contentType="text/html; charset=GBK"%>
<%
	String msg = (String)request.getAttribute("message");	
%>

<HTML>
<HEAD>

<TITLE>��Ϣ��ʾ</TITLE>
</HEAD>
<BODY>
<script language="javascript">
alert("<%=msg%>");
window.close();
</script>
</BODY>
</HTML>
