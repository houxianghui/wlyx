<% 
response.setHeader("Cache-Control","no-cache"); 
response.setHeader("Pragma","no-cache"); 
response.setDateHeader("Expires",0); 
%> 
 
<%@ include file = "/includes/common.jsp" %> 
<%@ page contentType="text/html; charset=GBK"%> 
<html> 
 
<title>���ӹ����û���¼��¼��</title>  

 
<script language="javascript"> 
 
function doAdd(){ 
	//���� 
 
		//ִ��У�� 
	document.forms[0].submit(); 
} 
</script> 
 
<body   class="body_bg_grey1"> 

 
<html:form method="post" action="UserLogin.do"> 
<input type=hidden name=act value=c> 
<input type=hidden name=step value="commit"> 

<%=ViewUtil.getTitle("���ӹ����û���¼��¼��")%>
 
    <table align="center" width="98%"  class="dtPanel_Line3" border="0" cellspacing="1" cellpadding="0"> 
        
        <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left">&nbsp;�û���ţ�</td> 
         <td align="left" class="dtPanel_Main2">&nbsp; 
         <html:text property="user_id" styleClass="Textfield"  size="20" maxlength="8" /> 
         </td> 
       </tr> 
 
        <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left">&nbsp;��¼ʱ�䣺</td> 
         <td align="left" class="dtPanel_Main2">&nbsp; 
         <html:text property="login_time" styleClass="Textfield"  size="20" maxlength="14" /> 
         </td> 
       </tr> 
 
  	<table  class=heightspace_bottom1  border="0" cellspacing="0" cellpadding="0"><tr><td></td></tr></table>
    <table   align=center width="98%" border="0" cellspacing="0" cellpadding="0">  
        <tr> 
				<td colspan="2" height="25" align="center" class="dtPanel_Bottom"> 
						<input	name="add" type="button" class="Button" value="����" onClick="doAdd()"> &nbsp; 
						<input name="return" type="button" class="Button" value="����" onClick="history.back()">   
		 		</td> 
	    </tr> 
  </table> 
 
</html:form> 
 
</body> 
</html> 
 

