<%@ include file = "/includes/common.jsp" %> 
<%@ page contentType="text/html; charset=GBK"%> 
<html> 
 
<title>�����ֵ������Ϣ</title> 
 

 
<script language="javascript"> 
 
function doAdd(){ 
	//���� 
	//ִ��У�� 
	var field = new Array("type_id","type_name","dic_level"); 
	var info = new Array("������","����","�����־"); 
 
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
	
	//����ֶγ���
	if(getStrLength(document.forms[0].type_id.value)>4) { 		
	        	alert('�����볤���벻Ҫ����4λ!'); 
	        	document.forms[0].type_id.focus(); 
	        	return; 
	} 
	if(getStrLength(document.forms[0].type_name.value)>30) { 		
	        	alert('���Ƴ����벻Ҫ����30���ַ�!'); 
	        	document.forms[0].type_name.focus(); 
	        	return; 
	} 
		 
	document.forms[0].submit(); 
} 
</script> 
 
<body  class="body_bg_grey1"> 

 
<html:form method="post" action="DicType.do"> 
<input type=hidden name=act value=c> 
<input type=hidden name=step value="commit"> 

<%=ViewUtil.getTitle("�����ֵ������Ϣ")%>

    <table align="center" width="98%"  class="dtPanel_Line3"  border="0" cellspacing="1" cellpadding="0">
             
        <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left">&nbsp;<%=ViewUtil.must()%>�����룺</td> 
         <td align="left" class="dtPanel_Main2">&nbsp; 
         <html:text property="type_id" styleClass="Textfield"  size="20" maxlength="4" /> 
         </td> 
       </tr> 
 
        <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left">&nbsp;<%=ViewUtil.must()%>���ƣ�</td> 
         <td align="left" class="dtPanel_Main2">&nbsp; 
         <html:text property="type_name" styleClass="Textfield"  size="30" maxlength="30" /> 
         </td> 
       </tr> 
 
        <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left">&nbsp;�����־��</td> 
         <td align="left" class="dtPanel_Main2">&nbsp; 
           <html:select property="dic_level">
              <html:option value="1">�����ֵ�</html:option>
              <html:option value="2">�༶�ֵ�</html:option>
           </html:select>
          
         </td> 
       </tr>       
  </table> 
  
    <table  class=heightspace_bottom1  border="0" cellspacing="0" cellpadding="0"><tr><td></td></tr></table>
   
    <table align="center" width="98%" border="0" cellspacing="0" cellpadding="0">      
        <tr> 
				<td height="25" align="center"  class="dtPanel_Bottom"> 
						<input	name="add" type="button" class="Button" value="����" onClick="doAdd()"> &nbsp; 
						<input name="return" type="button" class="Button" value="����" onClick="location.href='DicType.do?act=list';">  
		 		</td> 
	    </tr> 
  </table> 
 
</html:form> 
 
</body> 
</html> 
 

