<%
response.setHeader("Cache-Control","no-cache");
response.setHeader("Pragma","no-cache");
response.setDateHeader("Expires",0);
%>

<%@ include file = "/includes/common.jsp" %>

<%
	String pixel = (String)session.getAttribute("PIXEL");
	

		request.getRequestDispatcher("/home1024.jsp").forward(request,response);

%>
