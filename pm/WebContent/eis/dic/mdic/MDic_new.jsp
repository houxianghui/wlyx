<%@ include file = "/includes/common.jsp" %> 
<%@ page contentType="text/html; charset=GBK"%> 
<html> 
 
<title>���Ӷ༶�ֵ�</title> 
<SCRIPT src="js/apply/cardApply.js" type="text/javascript"></SCRIPT>
<script language="javascript"> 
 
function doAdd(){ 
	//���� 
 
	//ִ��У�� 
	var field = new Array("type_id","parent_id","item_id","item_val","list_order","item_level","status"); 
	var info = new Array("������","�ϼ�����","ѡ�����","ѡ��ֵ","���˳��","����","status"); 
 
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
 
<body> 
<p>&nbsp;</p> 
 
<html:form method="post" action="MDic.do"> 
<input type=hidden name=act value=c> 
<input type=hidden name=step value="commit"> 

 
<%=ViewUtil.getTitle("���Ӷ༶�ֵ�")%> 
 
    <table align="center" width="98%" class="dtPanel_Line3" border="0" cellspacing="1" cellpadding="0"> 
        <!--tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left">&nbsp;<%=ViewUtil.must()%>ϵͳ���룺</td> 
         <td align="left" class="dtPanel_Main2">&nbsp; 
         <html:text property="sys_id" styleClass="Textfield"  size="20" maxlength="12" /> 
         </td> 
       </tr--> 
 
        <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left">&nbsp;<%=ViewUtil.must()%>�����룺</td> 
         <td align="left" class="dtPanel_Main2">&nbsp; 
         <input name="type_id" class="Textfield-READONLY" value="<%=request.getParameter("type_id")%>" size="4" readonly> 
         </td> 
       </tr> 
 
        <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left">&nbsp;<%=ViewUtil.must()%>�ϼ����룺</td> 
         <td align="left" class="dtPanel_Main2">&nbsp; 
         <html:text property="parent_id" styleClass="Textfield-READONLY" readonly="true" size="20" maxlength="12" /> 
         </td> 
       </tr> 
 
        <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left">&nbsp;<%=ViewUtil.must()%>ѡ����룺</td> 
         <td align="left" class="dtPanel_Main2">&nbsp; 
         <html:text property="item_id" styleClass="Textfield"  size="20" maxlength="12" /> 
         </td> 
       </tr> 
 
        <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left">&nbsp;<%=ViewUtil.must()%>ѡ��ֵ��</td> 
         <td align="left" class="dtPanel_Main2">&nbsp; 
         <html:text property="item_val" styleClass="Textfield"  size="20" maxlength="40" /> 
         </td> 
       </tr> 
 
        <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left">&nbsp;<%=ViewUtil.must()%>���˳��</td> 
         <td align="left" class="dtPanel_Main2">&nbsp; 
         <html:text property="list_order" styleClass="Textfield"  size="20" maxlength="2"  onblur="onlyNum(this)" onkeyup="onlyNum(this)"/> 
         </td> 
       </tr> 
 
        <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left">&nbsp;<%=ViewUtil.must()%>���Σ�</td> 
         <td align="left" class="dtPanel_Main2">&nbsp; 
         <html:text property="item_level" styleClass="Textfield-READONLY" readonly="true" size="20" maxlength="2"/> 
         </td> 
       </tr> 
 
        <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left">&nbsp;�߼����룺</td> 
         <td align="left" class="dtPanel_Main2">&nbsp; 
         <html:text property="logic_id" styleClass="Textfield"  size="20" maxlength="12" /> 
         </td> 
       </tr> 
 
        <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left">&nbsp;<%=ViewUtil.must()%>״̬��</td> 
         <td align="left" class="dtPanel_Main2">&nbsp; 
         <!--html:text property="status" styleClass="Textfield"  size="20" maxlength="1" /--> 
         <select name="status">
         <option value="1">����</option>
         <option value="2">����</option>
         </select>
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
 

