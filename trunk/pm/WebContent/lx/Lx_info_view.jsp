<% 
response.setHeader("Cache-Control","no-cache"); 
response.setHeader("Pragma","no-cache"); 
response.setDateHeader("Expires",0); 
%> 
 
<%@ include file = "/includes/common.jsp" %> 
<%@ page import="com.lx.Lx_infoForm" %> 
<%@ page contentType="text/html; charset=GBK"%> 
<jsp:useBean id="lx_infoForm" scope="request" class="com.lx.Lx_infoForm" /> 
 
<html> 
 
<title>联系信息信息</title> 
 
 
<body> 
<p>&nbsp;</p> 
 
<html:form method="post" action="Lx_info.do"> 
 
<%=ViewUtil.getTitle("联系信息明细")%> 
 
    <table align="center" width="98%" class="dtPanel_Line3"  border="0" cellspacing="1" cellpadding="0"> 
        <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left_v">&nbsp;联系人编号：</td> 
         <td align="left" class="dtPanel_Main">&nbsp; 
          <%=lx_infoForm.getLx_id()%> 
         </td> 
       </tr> 
 
        <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left_v">&nbsp;部门：</td> 
         <td align="left" class="dtPanel_Main">&nbsp; 
            <%=SingleDicMap.getDicItemVal("9990",lx_infoForm.getDepart())%>
         </td> 
       </tr> 
 
        <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left_v">&nbsp;姓名：</td> 
         <td align="left" class="dtPanel_Main">&nbsp; 
          <%=lx_infoForm.getName()%> 
         </td> 
       </tr> 
 		<tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left_v">&nbsp;员工编号：</td> 
         <td align="left" class="dtPanel_Main">&nbsp; 
          <%=lx_infoForm.getStuff_id()%> 
         </td> 
       </tr> 
        <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left_v">&nbsp;办公电话：</td> 
         <td align="left" class="dtPanel_Main">&nbsp; 
          <%=lx_infoForm.getPhone()%> 
         </td> 
       </tr> 
 
        <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left_v">&nbsp;移动电话：</td> 
         <td align="left" class="dtPanel_Main">&nbsp; 
          <%=lx_infoForm.getMobile()%> 
         </td> 
       </tr> 
 
        <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left_v">&nbsp;Email地址：</td> 
         <td align="left" class="dtPanel_Main">&nbsp; 
          <%=lx_infoForm.getEmail()%> 
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
 

