<% 
response.setHeader("Cache-Control","no-cache"); 
response.setHeader("Pragma","no-cache"); 
response.setDateHeader("Expires",0); 
%> 
 
<%@ include file = "/includes/common.jsp" %> 
<%@ page import="com.eis.portal.userlogin.UserLoginForm" %> 
<%@ page contentType="text/html; charset=GBK"%> 
<jsp:useBean id="userLoginForm" scope="request" class="com.eis.portal.userlogin.UserLoginForm" /> 
 
<html> 
 
<title>管理用户登录记录表信息</title>  
 
<body  class="body_bg_grey1"> 

 <%=ViewUtil.getTitle("管理用户登录记录表信息")%>
  
<html:form method="post" action="UserLogin.do"> 
 
    <table align="center" width="98%"   class="dtPanel_Line3"  border="0" cellspacing="1" cellpadding="0"> 
       
        <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left_v">&nbsp;用户编号：</td> 
         <td align="left" class="dtPanel_Main">&nbsp; 
          <%=userLoginForm.getUser_id()%> 
         </td> 
       </tr> 
 
        <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left_v">&nbsp;登录时间：</td> 
         <td align="left" class="dtPanel_Main">&nbsp; 
          <%=userLoginForm.getLogin_time()%> 
         </td> 
       </tr> 
 
 
	<table  class=heightspace_bottom1  border="0" cellspacing="0" cellpadding="0"><tr><td></td></tr></table> 
    <table align="center" width="98%" border="0" cellspacing="0" cellpadding="0">  
        <tr>  
				<td colspan="2" height="25" align="center" class="dtPanel_Bottom"> 
						<input name="return" type="button" class="Button" value="返回" onClick="history.back()">   
		 		</td> 
	    </tr> 
  </table> 
 
</html:form> 
 
</body> 
</html> 
 

