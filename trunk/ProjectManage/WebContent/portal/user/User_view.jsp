<%@ include file = "/includes/common.jsp" %> 
<%@ page import="com.eis.portal.user.UserForm" %>
<%@ page import="com.eis.cache.*" %>  
<%@ page contentType="text/html; charset=GBK"%> 
<jsp:useBean id="userForm" scope="request" class="com.eis.portal.user.UserForm" /> 
 
<html> 
 
 
<title>�û���Ϣ</title> 
 
 
<body  class="body_bg_grey1"> 
 
<html:form method="post" action="User.do"> 

    <%=ViewUtil.getTitle("�û���Ϣ")%>
 
    <table align="center" width="98%"   class="dtPanel_Line3" border="0" cellspacing="1" cellpadding="0">       
         
        <tr bgcolor="#FFFFFF"> 
        
          <td width="25%" align="right" class="dtPanel_Left_v">&nbsp;�û�����</td> 
          <td width="25%" align="left" class="dtPanel_Main">&nbsp;<%=userForm.getLogin_id()%></td>
          <td width="25%" align="right" class="dtPanel_Left_v">&nbsp;����������</td> 
          <td width="25%" align="left" class="dtPanel_Main">&nbsp;<%=userForm.getUser_name()%></td>
         </tr> 
 
        <tr bgcolor="#FFFFFF">          
          
          <td width="25%" align="right" class="dtPanel_Left_v">&nbsp;����ɫ��</td> 
          <td width="25%" align="left" class="dtPanel_Main">&nbsp;<%=ReDefSDicMap.getDicItemVal("0002",userForm.getRole_id())%></td>       

          <td width="25%" align="right" class="dtPanel_Left_v">&nbsp;</td> 
          <td width="25%" align="left" class="dtPanel_Main">&nbsp;</td> 
          
        </tr> 
 
        <tr bgcolor="#FFFFFF"> 
                            
          <td width="25%" align="right" class="dtPanel_Left_v">&nbsp;��ϵ�绰��</td> 
          <td width="25%" align="left" class="dtPanel_Main">&nbsp;<%=userForm.getPhone()%></td> 
          <td width="25%" align="right" class="dtPanel_Left_v">&nbsp;�ֻ���</td> 
          <td width="25%" align="left" class="dtPanel_Main">&nbsp;<%=userForm.getMobile()%></td> 
        </tr> 
 
        <tr bgcolor="#FFFFFF">        
        
          <td width="25%" align="right" class="dtPanel_Left_v">&nbsp;�����ʼ���</td> 
          <td width="25%" align="left" class="dtPanel_Main">&nbsp;<%=userForm.getEmail()%></td>           
          <td width="25%" align="right" class="dtPanel_Left_v">&nbsp;�ʱࣺ</td> 
          <td width="25%" align="left" class="dtPanel_Main">&nbsp;<%=userForm.getPostcode()%></td> 
          
         </tr> 
 
        <tr bgcolor="#FFFFFF">          
          
          <td width="25%" align="right" class="dtPanel_Left_v">&nbsp;��ϵ��ַ��</td> 
          <td colspan="3"  align="left" class="dtPanel_Main">&nbsp;<%=userForm.getAddress()%></td>
			
        </tr>
 
        <tr bgcolor="#FFFFFF"> 
          <td width="25%" align="right" class="dtPanel_Left_v">&nbsp;�������ڣ�</td> 
          <td width="25%" align="left" class="dtPanel_Main">&nbsp;<%=DateUtil.formatDate(userForm.getReg_dt())%></td> 
          <td width="25%" align="right" class="dtPanel_Left_v">&nbsp;��ʼ���ڣ�</td> 
          <td width="25%" align="left" class="dtPanel_Main">&nbsp;<%=DateUtil.formatDate(userForm.getBegin_dt())%></td> 
        </tr> 
 
        <tr bgcolor="#FFFFFF"> 
          <td width="25%" align="right" class="dtPanel_Left_v">&nbsp;ʧЧ���ڣ�</td> 
          <td width="25%" align="left" class="dtPanel_Main">&nbsp;<%=DateUtil.formatDate(userForm.getInvalid_dt())%></td> 
          <td width="25%" align="right" class="dtPanel_Left_v">&nbsp;����ʧЧ���ڣ�</td> 
          <td width="25%" align="left" class="dtPanel_Main">&nbsp;<%=DateUtil.formatDate(userForm.getModify_dt())%></td>
        </tr> 
 
        <tr bgcolor="#FFFFFF"> 
          <td width="25%" align="right" class="dtPanel_Left_v">&nbsp;��ע��</td> 
          <td width="25%" align="left" class="dtPanel_Main">&nbsp;<%=userForm.getMemo()%></td> 
          <td width="25%" align="right" class="dtPanel_Left_v">&nbsp;״̬��</td> 
          <td width="25%" align="left" class="dtPanel_Main">&nbsp;<%=SingleDicMap.getDicItemVal("0001",userForm.getStatus())%></td> 
        </tr> 
       
        <tr bgcolor="#FFFFFF"> 
          <td width="25%" align="right" class="dtPanel_Left_v">&nbsp;�������ڣ�</td> 
          <td width="25%" align="left" class="dtPanel_Main">&nbsp;<%=DateUtil.formatDate(userForm.getSt_chg_dt())%></td> 
          <td width="25%" align="right" class="dtPanel_Left_v">&nbsp;������Ա��</td> 
          <td width="25%" align="left" class="dtPanel_Main">&nbsp;<%=ReDefSDicMap.getDicItemVal("0003",userForm.getAdmin_id())%></td> 
        </tr> 
  </table>  

	<table  class=heightspace_bottom1  border="0" cellspacing="0" cellpadding="0"><tr><td></td></tr></table> 
    <table align="center" width="98%" border="0" cellspacing="0" cellpadding="0">  
        <tr> 
				<td height="25" align="center" class="dtPanel_Bottom"> 
						<input name="return" type="button" class="Button" value="����" onClick="history.back()">   
		 		</td> 
	    </tr> 
  </table> 
 
</html:form> 
 
</body> 
</html> 
 

