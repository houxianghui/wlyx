
<%@ page import="com.eis.cache.ErrorCodeMap" %> 
<%@ page import="com.eis.base.*" %> 

<%@ page contentType="text/html; charset=GBK"%>
<%
	BaseException ex = (BaseException)request.getAttribute("error");
	
	String url = null;
	
	Object backurl = request.getAttribute("backurl");
	
	if(backurl != null)
		url = (String)backurl;
	
%>

<HTML>
<HEAD>


<TITLE>信息提示</TITLE>
</HEAD>
<BODY>
<script language="javascript">
alert("<%=ErrorCodeMap.getErrorInfo(ex.getErrorCode())%>");
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
