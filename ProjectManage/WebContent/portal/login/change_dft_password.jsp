
<%@ include file = "/includes/common.jsp" %>

<%@ page import="com.eis.cache.*" %> 
<%@ page import = "com.eis.portal.UserContext" %>
<%@ page import = "com.eis.util.*" %>

<%@ page contentType="text/html; charset=GBK"%> 
<html> 
 
<title>ÐÞ¸ÄÓÃ»§µÇÂ¼</title> 

<HEAD>

<%
	
	String msg = (String)request.getAttribute("message");
	if(msg!=null) {
	
	
%>


<script language="javascript">

	alert('<%=msg%>');
	window.location="<%=request.getContextPath()%>/portal/login/modifypwd.jsp";
	
</script>

<%
	}
	
	//request.getRequestDispatcher("/portal/login/modifypwd").forward(request,response);
%>




</HEAD>
<BODY   class="body_bg_grey1">

</BODY>
</HTML>
