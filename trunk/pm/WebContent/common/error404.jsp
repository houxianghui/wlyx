<%@ include file = "/includes/common.jsp" %>
<%@ page  contentType="text/html; charset=GBK" %>


<HTML>
<HEAD>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<TITLE>404错误</TITLE>

</HEAD>
<BODY>
<p>&nbsp;</p>
<p>&nbsp;</p>
<div align="center">
  <img src="<%=request.getContextPath()%>/images/error.jpg">
</div>
<br><br>
 <div align="center">
 <font style="color:#A9A9A9;font-size:20px;font-weight:bold;">找不到您访问的页面</font>
 </div>  
 <br><br>   
 <div align="center"><a href = "javascript:history.back();"><img src="<%=request.getContextPath()%>/images/error_back.gif" border="0" ></a></div>     

</BODY>
</HTML>
