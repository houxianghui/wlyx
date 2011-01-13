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
<META http-equiv="Content-Type" content="text/html; charset=GBK">
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">
<LINK href="theme/Master.css" rel="stylesheet"
	type="text/css">
<TITLE>blank.jsp</TITLE>

<script language="javascript">
//刷新左侧菜单列表
function reloadLeftMenu(){
		parent.leftFrame.location.reload();
}

<%
	String refresh = (String)request.getAttribute("refresh");
System.err.println("--"+refresh+"--");
	if(null != refresh && refresh.equals("y"))	{		
		%>		
		reloadLeftMenu();		
		<%
	}
	session.removeAttribute("refresh");
	
%>
				
	
</script>
</HEAD>
<BODY>

</BODY>
</HTML>
