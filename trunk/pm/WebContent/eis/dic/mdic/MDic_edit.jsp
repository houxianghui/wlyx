<%@ include file = "/includes/common.jsp" %> 
<%@ page contentType="text/html; charset=GBK"%> 
<html> 
 
<title>�޸Ķ༶�ֵ�</title> 
 
<SCRIPT src="js/apply/cardApply.js" type="text/javascript"></SCRIPT> 
<script language="javascript"> 
 
function doSave(){ 
	//�޸� 
 
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
function doBack(){
	//location.href="MDic.do?act=list&type_id="+document.forms[0].type_id.value+"";
	history.back();
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
 
<body> 
<p>&nbsp;</p> 
 
<html:form method="post" action="MDic.do"> 
<input type=hidden name=act value=u> 
<input type=hidden name=step value="commit"> 
<html:hidden property="sys_id"/> 
 
<%=ViewUtil.getTitle("�޸Ķ༶�ֵ�")%> 
 
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
         <html:text property="type_id" styleClass="Textfield-READONLY" readonly="true" size="20" maxlength="4" /> 
         </td> 
       </tr> 
 
        <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left">&nbsp;<%=ViewUtil.must()%>�ϼ����룺</td> 
         <td align="left" class="dtPanel_Main2">&nbsp; 
         <html:text property="parent_id" styleClass="Textfield-READONLY"  readonly="true"  size="20" maxlength="12" /> 
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
         <html:text property="item_level" styleClass="Textfield-READONLY"  readonly="true"  size="20" maxlength="2" /> 
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
         <!--html:text property="status" styleClass="Textfield" size="20" maxlength="1" /--> 
         <html:select property="status"> 
         <html:option value= "1">����</html:option> 
         <html:option value= "2">����</html:option>
         </html:select>
         </td> 
       </tr> 
 
  </table> 
 
    <table  class=heightspace_bottom1  border="0" cellspacing="0" cellpadding="0"><tr><td></td></tr></table> 
 
    <table align="center" width="98%" border="0" cellspacing="0" cellpadding="0"> 
        <tr> 
				<td height="25" align="center" class="dtPanel_Bottom"> 
						<input	name="update" type="button" class="Button" value="����" onClick="doSave()"> &nbsp; 
						<input name="return" type="button" class="Button" value="����" onClick="doBack()">   
		 		</td> 
	    </tr> 
  </table> 
 
</html:form> 
 
</body> 
</html> 
 

