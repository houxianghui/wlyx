<%@ include file = "/includes/common.jsp" %> 
<%@ page contentType="text/html; charset=GBK"%> 
<html> 
<head> 
<title>�޸ļ���֧�ַ����¼</title> 
 
 
<script language="javascript"> 
 
function doSave(){ 
	//�޸� 
 
	//ִ��У�� 
	var field = new Array("qus_date","qus_user","qus_info"); 
	var info = new Array("�������","����˻�������","��������"); 
 
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
<% 
	String success = (String)request.getAttribute("success"); 
	if(null != success && success.equals("y"))	{		 
		%> 
		alert("�����޸ĳɹ���"); 
		<% 
	} 
%> 
 
</script> 
</head>
<body> 
<p>&nbsp;</p> 
 
<html:form method="post" action="Maintain_record.do"> 
<input type=hidden name=act value=u> 
<input type=hidden name=step value="commit"> 
<html:hidden property="seq_no"/>

<html:hidden property="res_user"/>
<html:hidden property="res_result"/>
<html:hidden property="res_memo"/>
<html:hidden property="res_cost"/>
<html:hidden property="input_user"/>
<html:hidden property="input_time"/>
<html:hidden property="res_time"/>
 
<%=ViewUtil.getTitle("�޸ļ���֧�ַ����¼")%> 
 
    <table align="center" width="98%" class="dtPanel_Line3" border="0" cellspacing="1" cellpadding="0"> 
 
        <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left">&nbsp;<%=ViewUtil.must()%>������ڣ�</td> 
         <td align="left" class="dtPanel_Main2">&nbsp; 
         <html:text property="qus_date" styleClass="Textfield"  size="20" maxlength="8" /> 
         </td> 
       </tr> 
 
        <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left">&nbsp;<%=ViewUtil.must()%>����˻���������</td> 
         <td align="left" class="dtPanel_Main2">&nbsp; 
         <html:text property="qus_user" styleClass="Textfield"  size="20" maxlength="40" /> 
         </td> 
       </tr> 
 
        <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left">&nbsp;<%=ViewUtil.must()%>����������</td> 
         <td align="left" class="dtPanel_Main2">&nbsp; 
         <html:textarea property="qus_info" styleClass="Textfield"  cols="30" rows="5"/> 
         </td> 
       </tr> 
 
  </table> 
 
    <table  class=heightspace_bottom1  border="0" cellspacing="0" cellpadding="0"><tr><td></td></tr></table> 
 
    <table align="center" width="98%" border="0" cellspacing="0" cellpadding="0"> 
        <tr> 
				<td height="25" align="center" class="dtPanel_Bottom"> 
						<input	name="update" type="button" class="Button" value="����" onClick="doSave()"> &nbsp; 
						<input name="return" type="button" class="Button" value="����" onClick="location.href='Maintain_record.do?act=list';">   
		 		</td> 
	    </tr> 
  </table> 
 
</html:form> 
 
</body> 
</html> 
 

