<%@ include file = "/includes/common.jsp" %> 
<%@ page import="com.eis.portal.role.RoleForm" %>
<%@ page import="com.eis.cache.*" %>  
<%@ page contentType="text/html; charset=GBK"%> 
<jsp:useBean id="roleForm" scope="request" class="com.eis.portal.role.RoleForm" /> 
 
<html> 
 
 
<title>��ɫ��Ϣ</title> 
 
 
<body  class="body_bg_grey1"> 
 
<html:form method="post" action="Role.do"> 

    <%=ViewUtil.getTitle("��ɫ��Ϣ")%>
 
    <table align="center" width="98%"   class="dtPanel_Line3" border="0" cellspacing="1" cellpadding="0">       
         
        <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left_v">&nbsp;��ɫ��</td> 
         <td align="left" class="dtPanel_Main">&nbsp; 
          <%=roleForm.getRole_id()%> 
         </td> 
       </tr>     
     
       
         <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left_v">&nbsp;��ɫ���ƣ�</td> 
         <td align="left" class="dtPanel_Main">&nbsp; 
          <%=roleForm.getRole_name()%> 
         </td> 
       </tr> 
 
        <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left_v">&nbsp;��ҳģ�壺</td> 
         <td align="left" class="dtPanel_Main">&nbsp; 
          <%=ReDefSDicMap.getDicItemVal("0001",roleForm.getTempl_id())%>          
         </td> 
       </tr> 
 
        <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left_v">&nbsp;���ƴ��룺</td> 
         <td align="left" class="dtPanel_Main">&nbsp; 
          <%=roleForm.getLogic_id()%> 
         </td> 
       </tr> 
 
        <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left_v">&nbsp;�Ự��ʱ���ƣ�</td> 
         <td align="left" class="dtPanel_Main">&nbsp; 
          <%=roleForm.getSesn_time()%>��
         </td> 
       </tr> 
 
        <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left_v">&nbsp;״̬��</td> 
         <td align="left" class="dtPanel_Main">&nbsp;           
          <%=SingleDicMap.getDicItemVal("0002",roleForm.getStatus())%>         
         </td> 
       </tr> 
 

 
        <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left_v">&nbsp;�������ڣ�</td> 
         <td align="left" class="dtPanel_Main">&nbsp; 
          <%=DateUtil.formatDate(roleForm.getReg_dt())%> 
         </td> 
       </tr> 
 
        <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left_v">&nbsp;������Ա��</td> 
         <td align="left" class="dtPanel_Main">&nbsp;
          <%=ReDefSDicMap.getDicItemVal("0003",roleForm.getUser_id())%> 
         </td> 
       </tr>     
  </table>  

	<table  class=heightspace_bottom1  border="0" cellspacing="0" cellpadding="0"><tr><td></td></tr></table> 
    <table align="center" width="98%" border="0" cellspacing="0" cellpadding="0">  
        <tr> 
				<td height="25" align="center" class="dtPanel_Bottom"> 
						<input name="return" type="button" class="Button" value="����" onClick="location.href='Role.do?act=list';">   
		 		</td> 
	    </tr> 
  </table> 
 
</html:form> 
 
</body> 
</html> 
 

