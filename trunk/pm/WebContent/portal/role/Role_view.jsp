<%@ include file = "/includes/common.jsp" %> 
<%@ page import="com.eis.portal.role.RoleForm" %>
<%@ page import="com.eis.cache.*" %>  
<%@ page contentType="text/html; charset=GBK"%> 
<jsp:useBean id="roleForm" scope="request" class="com.eis.portal.role.RoleForm" /> 
 
<html> 
 
 
<title>角色信息</title> 
 
 
<body  class="body_bg_grey1"> 
 
<html:form method="post" action="Role.do"> 

    <%=ViewUtil.getTitle("角色信息")%>
 
    <table align="center" width="98%"   class="dtPanel_Line3" border="0" cellspacing="1" cellpadding="0">       
         
        <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left_v">&nbsp;角色：</td> 
         <td align="left" class="dtPanel_Main">&nbsp; 
          <%=roleForm.getRole_id()%> 
         </td> 
       </tr>     
     
       
         <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left_v">&nbsp;角色名称：</td> 
         <td align="left" class="dtPanel_Main">&nbsp; 
          <%=roleForm.getRole_name()%> 
         </td> 
       </tr> 
 
        <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left_v">&nbsp;首页模板：</td> 
         <td align="left" class="dtPanel_Main">&nbsp; 
          <%=ReDefSDicMap.getDicItemVal("0001",roleForm.getTempl_id())%>          
         </td> 
       </tr> 
 
        <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left_v">&nbsp;控制代码：</td> 
         <td align="left" class="dtPanel_Main">&nbsp; 
          <%=roleForm.getLogic_id()%> 
         </td> 
       </tr> 
 
        <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left_v">&nbsp;会话超时限制：</td> 
         <td align="left" class="dtPanel_Main">&nbsp; 
          <%=roleForm.getSesn_time()%>秒
         </td> 
       </tr> 
 
        <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left_v">&nbsp;状态：</td> 
         <td align="left" class="dtPanel_Main">&nbsp;           
          <%=SingleDicMap.getDicItemVal("0002",roleForm.getStatus())%>         
         </td> 
       </tr> 
 

 
        <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left_v">&nbsp;更新日期：</td> 
         <td align="left" class="dtPanel_Main">&nbsp; 
          <%=DateUtil.formatDate(roleForm.getReg_dt())%> 
         </td> 
       </tr> 
 
        <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left_v">&nbsp;更新人员：</td> 
         <td align="left" class="dtPanel_Main">&nbsp;
          <%=ReDefSDicMap.getDicItemVal("0003",roleForm.getUser_id())%> 
         </td> 
       </tr>     
  </table>  

	<table  class=heightspace_bottom1  border="0" cellspacing="0" cellpadding="0"><tr><td></td></tr></table> 
    <table align="center" width="98%" border="0" cellspacing="0" cellpadding="0">  
        <tr> 
				<td height="25" align="center" class="dtPanel_Bottom"> 
						<input name="return" type="button" class="Button" value="返回" onClick="location.href='Role.do?act=list';">   
		 		</td> 
	    </tr> 
  </table> 
 
</html:form> 
 
</body> 
</html> 
 

