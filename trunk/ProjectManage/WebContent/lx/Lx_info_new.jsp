<%@ include file = "/includes/common.jsp" %> 
<%@ page contentType="text/html; charset=GBK"%> 
<jsp:useBean id="lx_infoForm" scope="request" class="com.lx.Lx_infoForm"/>
<html> 
 <head>
<title>������ϵ��Ϣ</title> 
 
<script language="javascript"> 
 
function doAdd(){ 
	//���� 
 
	//ִ��У�� 
	var field = new Array("depart","name","phone","mobile","email"); 
	var info = new Array("����","����","�칫�绰","�ƶ��绰","Email��ַ"); 
 
	//���������Ϣ�Ƿ�Ϊ�� 
	var tmp; 
	for(var i=0;i<field.length;i++)	{ 
	        tmp ="document.forms[0]."+field[i]+".value"; 
	        if(isEmpty(eval(tmp))) { 
	        	alert('������'+info[i]); 
	        	eval("document.forms[0]."+field[i]+".focus()"); 
	        	return; 
	        } 
	} 
 
 
 
	document.forms[0].submit(); 
} 
</script> 
 </head>
<body> 
<p>&nbsp;</p> 
 
<html:form method="post" action="Lx_info.do"> 
<input type=hidden name=act value=c> 
<input type=hidden name=step value="commit"> 
 
<%=ViewUtil.getTitle("������ϵ��Ϣ")%> 
 
    <table align="center" width="98%" class="dtPanel_Line3" border="0" cellspacing="1" cellpadding="0"> 
 
 
        <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left">&nbsp;���ţ�</td> 
         <td align="left" class="dtPanel_Main2">&nbsp; 
          <html:select property="depart"> 
         	<html:optionsCollection name="lx_infoForm" property="depart_f_options" />               
            </html:select>               
         </td> 
       </tr> 
 
        <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left">&nbsp;������</td> 
         <td align="left" class="dtPanel_Main2">&nbsp; 
         <html:text property="name" styleClass="Textfield"  size="8" maxlength="8" /> 
         </td> 
       </tr> 
  		<tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left">&nbsp;Ա����ţ�</td> 
         <td align="left" class="dtPanel_Main2">&nbsp; 
         <html:text property="stuff_id" styleClass="Textfield"  size="8" maxlength="8" /> 
         </td> 
       </tr> 	
        <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left">&nbsp;�칫�绰��</td> 
         <td align="left" class="dtPanel_Main2">&nbsp; 
         <html:text property="phone" styleClass="Textfield"  size="15" maxlength="20" /> 
         </td> 
       </tr> 
 
        <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left">&nbsp;�ƶ��绰��</td> 
         <td align="left" class="dtPanel_Main2">&nbsp; 
         <html:text property="mobile" styleClass="Textfield"  size="11" maxlength="11" /> 
         </td> 
       </tr> 
 
        <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left">&nbsp;Email��ַ��</td> 
         <td align="left" class="dtPanel_Main2">&nbsp; 
         <html:text property="email" styleClass="Textfield"  size="25" maxlength="30" /> 
         </td> 
       </tr> 
 
  </table> 
 
    <table  class=heightspace_bottom1  border="0" cellspacing="0" cellpadding="0"><tr><td></td></tr></table> 
 
    <table align="center" width="98%" border="0" cellspacing="0" cellpadding="0"> 
        <tr> 
				<td height="25" align="center" class="dtPanel_Bottom"> 
						<input	name="add" type="button" class="Button" value="����" onClick="doAdd()"> &nbsp; 
						<input name="return" type="button" class="Button" value="����" onClick="history.back()">   
		 		</td> 
	    </tr> 
  </table> 
 
</html:form> 
 
</body> 
</html> 
 

