<%@ include file = "/includes/common.jsp" %> 
<%@ page contentType="text/html; charset=GBK"%> 
<html> 
 
<title>������������</title>  

 
<script language="javascript"> 
 
function doAdd(){ 
	//���� 
	//ִ��У�� 
	var field = new Array("tb_name","key_field","step_len","max_val"); 
	var info = new Array("���ݿ����","�����ֶ�","����","��ǰ���ֵ"); 
 
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
 
<body   class="body_bg_grey1"> 
 
<html:form method="post" action="PmKey.do"> 
<input type=hidden name=act value=c> 
<input type=hidden name=step value="commit"> 
 
 <%=ViewUtil.getTitle("������������")%>
  
    <table align="center" width="98%"  class="dtPanel_Line3"  border="0" cellspacing="1" cellpadding="0"> 
                
        <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left">&nbsp;<%=ViewUtil.must()%>���ݿ������</td> 
         <td align="left" class="dtPanel_Main2">&nbsp; 
         <html:text property="tb_name" styleClass="Textfield"  size="20" maxlength="20" /> 
         </td> 
       </tr> 
 
        <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left">&nbsp;<%=ViewUtil.must()%>�����ֶΣ�</td> 
         <td align="left" class="dtPanel_Main2">&nbsp; 
         <html:text property="key_field" styleClass="Textfield"  size="20" maxlength="20" /> 
         </td> 
       </tr> 
 
        <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left">&nbsp;<%=ViewUtil.must()%>������</td> 
         <td align="left" class="dtPanel_Main2">&nbsp; 
         <html:text property="step_len" styleClass="Textfield"  size="20" maxlength="4" onkeypress="checknum()" onkeyup="onlyNum(this)"/> 
         </td> 
       </tr> 
 
        <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left">&nbsp;<%=ViewUtil.must()%>��ǰ���ֵ��</td> 
         <td align="left" class="dtPanel_Main2">&nbsp; 
         <html:text property="max_val" styleClass="Textfield"  size="20" maxlength="20" onkeypress="checknum()"  onkeyup="onlyNum(this)"/> 
         </td> 
       </tr> 
  </table>
  
    	<table  class=heightspace_bottom1  border="0" cellspacing="0" cellpadding="0"><tr><td></td></tr></table>
   
    <table align="center" width="98%" border="0" cellspacing="0" cellpadding="0">      
        <tr> 
				<td height="25" align="center"  class="dtPanel_Bottom"> 
						<input	name="add" type="button" class="Button" value="����" onClick="doAdd()"> &nbsp; 
						<input name="return" type="button" class="Button" value="����" onClick="history.back()">   
		 		</td> 
	    </tr> 
  </table>  
 
</html:form> 
 
</body> 
</html> 
 

