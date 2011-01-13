<% 
response.setHeader("Cache-Control","no-cache"); 
response.setHeader("Pragma","no-cache"); 
response.setDateHeader("Expires",0); 
%> 
 
<%@ include file = "/includes/common.jsp" %> 
<%@ page import="com.work.PROJECT_LISTForm" %> 
<%@ page contentType="text/html; charset=GBK"%> 
<jsp:useBean id="pROJECT_LISTForm" scope="request" class="com.work.PROJECT_LISTForm" /> 
 
<html> 
 
<title>项目信息</title> 
 
 
<body> 
<p>&nbsp;</p> 
 
<html:form method="post" action="PROJECT_LIST.do"> 
 
<%=ViewUtil.getTitle("项目信息")%> 
 
    <table align="center" width="98%" class="dtPanel_Line3"  border="0" cellspacing="1" cellpadding="0"> 
       
        <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left_v">&nbsp项目编号：</td> 
         <td align="left" class="dtPanel_Main">&nbsp; 
          <%=pROJECT_LISTForm.getProject_no()%> 
         </td> 
       </tr> 
 
        <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left_v">&nbsp;项目名称：</td> 
         <td align="left" class="dtPanel_Main">&nbsp; 
          <%=pROJECT_LISTForm.getProject_name()%> 
         </td> 
       </tr> 
 
        <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left_v">&nbsp;当前阶段：</td> 
         <td align="left" class="dtPanel_Main">&nbsp; 
          <%=SingleDicMap.getDicItemVal("9994",pROJECT_LISTForm.getCurr_step())%> 
         </td> 
       </tr> 
 
        <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left_v">&nbsp;开始日期：</td> 
         <td align="left" class="dtPanel_Main">&nbsp; 
          <%=pROJECT_LISTForm.getStart_date()%> 
         </td> 
       </tr> 
 
        <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left_v">&nbsp;结束日期：</td> 
         <td align="left" class="dtPanel_Main">&nbsp; 
          <%=pROJECT_LISTForm.getEnd_date()%> 
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
 

