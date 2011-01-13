<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<HTML>
<HEAD>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ page 
language="java"
contentType="text/html; charset=GBK"
pageEncoding="GBK"
%>

<TITLE>index.jsp</TITLE>
</HEAD>

<FRAMESET cols="29%,*"  frameborder="No" border="0" framespacing="0">
	<FRAME src="../../../MDic.do?act=list&type_id=<%=request.getParameter("type_id")%>"  name="leftFrame"  scrolling="auto"  >
	<FRAME src=""  name="rightFrame">
	<NOFRAMES>
	<BODY></BODY>
	</NOFRAMES>
</FRAMESET>

</HTML>
