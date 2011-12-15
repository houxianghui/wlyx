<%@ include file = "/includes/common.jsp" %> 
<%@ page import="com.eis.portal.user.UserForm" %>
<%@ page import="com.eis.cache.*" %>  
<%@ page contentType="text/html; charset=GBK"%> 
<jsp:useBean id="userForm" scope="request" class="com.eis.portal.user.UserForm" /> 
 
<html> 
 
 
<title>用户信息</title> 
 
 
<body  class="body_bg_grey1"> 
 
<html:form method="post" action="User.do"> 

    <%=ViewUtil.getTitle("用户信息")%>
 
    <table align="center" width="98%"   class="dtPanel_Line3" border="0" cellspacing="1" cellpadding="0">       
         
        <tr bgcolor="#FFFFFF"> 
        
          <td width="25%" align="right" class="dtPanel_Left_v">&nbsp;用户名：</td> 
          <td width="25%" align="left" class="dtPanel_Main">&nbsp;<%=userForm.getLogin_id()%></td>
          <td width="25%" align="right" class="dtPanel_Left_v">&nbsp;中文姓名：</td> 
          <td width="25%" align="left" class="dtPanel_Main">&nbsp;<%=userForm.getUser_name()%></td>
         </tr> 
 
        <tr bgcolor="#FFFFFF">          
          
          <td width="25%" align="right" class="dtPanel_Left_v">&nbsp;主角色：</td> 
          <td width="25%" align="left" class="dtPanel_Main">&nbsp;<%=ReDefSDicMap.getDicItemVal("0002",userForm.getRole_id())%></td>       

          <td width="25%" align="right" class="dtPanel_Left_v">&nbsp;</td> 
          <td width="25%" align="left" class="dtPanel_Main">&nbsp;</td> 
          
        </tr> 
 
        <tr bgcolor="#FFFFFF"> 
                            
          <td width="25%" align="right" class="dtPanel_Left_v">&nbsp;联系电话：</td> 
          <td width="25%" align="left" class="dtPanel_Main">&nbsp;<%=userForm.getPhone()%></td> 
          <td width="25%" align="right" class="dtPanel_Left_v">&nbsp;手机：</td> 
          <td width="25%" align="left" class="dtPanel_Main">&nbsp;<%=userForm.getMobile()%></td> 
        </tr> 
 
        <tr bgcolor="#FFFFFF">        
        
          <td width="25%" align="right" class="dtPanel_Left_v">&nbsp;电子邮件：</td> 
          <td width="25%" align="left" class="dtPanel_Main">&nbsp;<%=userForm.getEmail()%></td>           
          <td width="25%" align="right" class="dtPanel_Left_v">&nbsp;邮编：</td> 
          <td width="25%" align="left" class="dtPanel_Main">&nbsp;<%=userForm.getPostcode()%></td> 
          
         </tr> 
 
        <tr bgcolor="#FFFFFF">          
          
          <td width="25%" align="right" class="dtPanel_Left_v">&nbsp;联系地址：</td> 
          <td colspan="3"  align="left" class="dtPanel_Main">&nbsp;<%=userForm.getAddress()%></td>
			
        </tr>
 
        <tr bgcolor="#FFFFFF"> 
          <td width="25%" align="right" class="dtPanel_Left_v">&nbsp;建立日期：</td> 
          <td width="25%" align="left" class="dtPanel_Main">&nbsp;<%=DateUtil.formatDate(userForm.getReg_dt())%></td> 
          <td width="25%" align="right" class="dtPanel_Left_v">&nbsp;起始日期：</td> 
          <td width="25%" align="left" class="dtPanel_Main">&nbsp;<%=DateUtil.formatDate(userForm.getBegin_dt())%></td> 
        </tr> 
 
        <tr bgcolor="#FFFFFF"> 
          <td width="25%" align="right" class="dtPanel_Left_v">&nbsp;失效日期：</td> 
          <td width="25%" align="left" class="dtPanel_Main">&nbsp;<%=DateUtil.formatDate(userForm.getInvalid_dt())%></td> 
          <td width="25%" align="right" class="dtPanel_Left_v">&nbsp;密码失效日期：</td> 
          <td width="25%" align="left" class="dtPanel_Main">&nbsp;<%=DateUtil.formatDate(userForm.getModify_dt())%></td>
        </tr> 
 
        <tr bgcolor="#FFFFFF"> 
          <td width="25%" align="right" class="dtPanel_Left_v">&nbsp;备注：</td> 
          <td width="25%" align="left" class="dtPanel_Main">&nbsp;<%=userForm.getMemo()%></td> 
          <td width="25%" align="right" class="dtPanel_Left_v">&nbsp;状态：</td> 
          <td width="25%" align="left" class="dtPanel_Main">&nbsp;<%=SingleDicMap.getDicItemVal("0001",userForm.getStatus())%></td> 
        </tr> 
       
        <tr bgcolor="#FFFFFF"> 
          <td width="25%" align="right" class="dtPanel_Left_v">&nbsp;更新日期：</td> 
          <td width="25%" align="left" class="dtPanel_Main">&nbsp;<%=DateUtil.formatDate(userForm.getSt_chg_dt())%></td> 
          <td width="25%" align="right" class="dtPanel_Left_v">&nbsp;更新人员：</td> 
          <td width="25%" align="left" class="dtPanel_Main">&nbsp;<%=ReDefSDicMap.getDicItemVal("0003",userForm.getAdmin_id())%></td> 
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
 

