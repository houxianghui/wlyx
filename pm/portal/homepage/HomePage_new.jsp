<%@ include file = "/includes/common.jsp" %> 
<%@ page contentType="text/html; charset=GBK"%> 
<html> 
 
<title>������ҳģ��</title> 

 
<script language="javascript">  

function doAdd(){ 
	//���� 
 
	//ִ��У�� 
	var field = new Array("caption","url"); 
	var info = new Array("ģ������","URL"); 
 
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
	if(getStrLength(document.forms[0].caption.value)>40) { 		
	        	alert('ģ�����Ƴ��������ַ����ܳ���20��Ӣ���ַ����ܳ���40!'); 
	        	document.forms[0].caption.focus(); 
	        	return; 
	}
	//����ֶγ���
	if(getStrLength(document.forms[0].url.value)>200) { 		
	        	alert('url���Ȳ��ܳ���200���ַ�!'); 
	        	document.forms[0].url.focus(); 
	        	return; 
	}    

	document.forms[0].submit(); 
}  
</script> 
 
<body  class="body_bg_grey1"> 

<html:form method="post" action="HomePage.do"> 
<input type=hidden name=act value=c> 
<input type=hidden name=step value="commit"> 


 <%=ViewUtil.getTitle("������ҳģ��")%> 
 
    <table align="center" width="98%"  class="dtPanel_Line3" border="0" cellspacing="1" cellpadding="0"> 
 	 
          
        <tr bgcolor="#FFFFFF"> 
          <td width="25%" align="right" class="dtPanel_Left">&nbsp;<%=ViewUtil.must()%>ģ�����ƣ�</td> 
         <td align="left" class="dtPanel_Main2">&nbsp; 
         <html:text property="caption" styleClass="Textfield"  size="40" maxlength="40" /> 
         </td> 
       </tr> 
 
        <tr bgcolor="#FFFFFF"> 
          <td width="25%" align="right" class="dtPanel_Left">&nbsp;<%=ViewUtil.must()%>URL��</td> 
         <td align="left" class="dtPanel_Main2">&nbsp; 
         <html:text property="url" styleClass="Textfield"  size="50" maxlength="200" /> 
         </td> 
       </tr> 
  </table>
  
  
   <table  class=heightspace_bottom1  border="0" cellspacing="0" cellpadding="0"><tr><td></td></tr></table>
    <table align="center" width="98%" border="0" cellspacing="0" cellpadding="0">  
        <tr> 
				<td  height="25" align="center" class="dtPanel_Bottom"> 
						<input	name="add" type="button" class="Button" value="����" onClick="doAdd()"> &nbsp; 
						<input name="return" type="button" class="Button" value="����" onClick="history.back()">   
		 		</td> 
	    </tr> 
  </table> 
 
</html:form> 
 
</body> 
</html> 
 

