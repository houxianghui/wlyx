
<HTML>
<HEAD>

<%@ page contentType="text/html; charset=GBK"%>
<%
	String msg = (String)request.getAttribute("message");
	String url = (String)request.getAttribute("backurl");
%>
<TITLE>成功信息提示</TITLE>
</HEAD>
<BODY>
<script language="javascript">
alert('<%=msg%>');
<%
	if(null == url || url.trim().length()<=0){
%>
history.back();
<%
	} else {
%>
    location.href ="<%=url%>";
<%
	}
%>
</script>
</BODY>
</HTML>
