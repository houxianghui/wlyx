<%@ include file = "/includes/common.jsp" %> 
<%@ page import="com.eis.portal.homepage.HomePageForm" %> 
<%@ page contentType="text/html; charset=GBK"%> 
<jsp:useBean id="homePageForm" scope="request" class="com.eis.portal.homepage.HomePageForm" /> 
 
<html> 
 
<title>首页模板信息</title>  

 
 
<body   class="body_bg_grey1"> 

 
<html:form method="post" action="HomePage.do"> 

<%=ViewUtil.getTitle("首页模板信息")%>

    <table align="center" width="98%"  class="dtPanel_Line3" border="0" cellspacing="1" cellpadding="0"> 
       
        <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left_v">&nbsp;模板名称：</td> 
         <td align="left" class="dtPanel_Main">&nbsp; 
          <%=homePageForm.getCaption()%> 
         </td> 
       </tr> 
 
        <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left_v">&nbsp;URL：</td> 
         <td align="left" class="dtPanel_Main">&nbsp; 
          <%=homePageForm.getUrl()%> 
         </td> 
       </tr> 
  </table> 
 
    <table  class=heightspace_bottom1  border="0" cellspacing="0" cellpadding="0"><tr><td></td></tr></table>  
    <table align="center" width="98%" border="0" cellspacing="0" cellpadding="0"> 
        <tr> 
				<td height="25" align="center" class="dtPanel_Bottom"> 
						<input name="return" type="button" class="Button" value="返回" onClick="history.back()">   
		 		</td> 
	    </tr> 
  </table> 
 
</html:form> 
 
</body> 
</html> 
 

