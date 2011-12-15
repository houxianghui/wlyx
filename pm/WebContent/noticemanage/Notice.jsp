<%@ page  contentType="text/html; charset=GBK" %> 
<%@ include file = "/includes/common.jsp" %> 

<html> 
<title>֪ͨ</title> 
 
<body bgcolor="#FFFBFF"> 
<br><br>
		<center><font size="3" color="#000000"><b>֪ͨ</b></font></center>
		<p>
		<font size="2" color="#000000">
		
       <%
       		String note = (String)request.getAttribute("info");
       		out.println(note);
       		
       %>
       </font>
	
</body> 
</html> 
 

