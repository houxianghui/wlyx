<%@ include file = "/includes/common.jsp" %>

<%@ page contentType="text/html; charset=GBK"%>
<html>
<head>
<title>���ӽ�ɫ</title>


<script language="javascript">

function doAdd(){
	//ִ��У��
	var field = new Array("role_name","templ_id","sesn_time","logic_id");
	var info = new Array("��ɫ����","��¼��ҳ","�Ự��ʱ����","���ƴ���");
	var tmp;
	

	//���������Ϣ�Ƿ�Ϊ��
	for(var i=0;i<field.length;i++)	
	{
	
        tmp ="document.forms[0]."+field[i]+".value";
        if(isEmpty(eval(tmp))) {
        	alert('������'+info[i]);
        	eval("document.forms[0]."+field[i]+".focus()");
        	return;
        }	
        
	}
	//����ֶγ���
	if(getStrLength(document.forms[0].role_name.value)>40) { 		
	        	alert('��ɫ���Ƴ����벻Ҫ����40���ַ�!'); 
	        	document.forms[0].role_name.focus(); 
	        	return; 
	}
	//����ֶγ���
	if(getStrLength(document.forms[0].logic_id.value)>8) { 		
	        	alert('���ƴ��볤���벻Ҫ����8���ַ�!'); 
	        	document.forms[0].logic_id.focus(); 
	        	return; 
	}
	
	//��������Ƿ�Ϊ������
	if(!isPositiveInteger(document.forms[0].sesn_time.value)){
        alert('�Ự��ʱ�������������֣�');
        document.forms[0].sesn_time.focus();
        return;
    }	
	
	//����
	document.forms[0].act.value='c';
	document.forms[0].submit();
	
}

function doPopOrg() {
	openWin("OrgPop.do?act=list&id_field=amsd_store&name_field=org_name","org_pop");
}

function doClearOrg(){
	document.forms[0].amsd_store.value="";
	document.forms[0].org_name.value="";	
	
}


</script>

</head>

<SCRIPT src="js/ChangeStyle.js" type=text/javascript></SCRIPT>

<body  class="body_bg_grey1">


<html:form  method="post"  action="Role.do">
<input type=hidden name=act value="c">
<input type=hidden name=step value="commit">

<br>
<%=ViewUtil.getTitle("���ӽ�ɫ")%>

    <table align="center" width="98%"  class="dtPanel_Line3" border="0" cellspacing="1" cellpadding="0">
           
         <tr bgcolor="#FFFFFF">
          <td width="30%" align="right" class="dtPanel_Left">&nbsp;<%=ViewUtil.must()%>��ɫ���ƣ�</td>
          <td align="left" class="dtPanel_Main2">&nbsp;
         <input type="text" name="role_name" size="30" maxlength="40" value="" class="Textfield">
         </td>
        </tr>
        
  
        <tr bgcolor="#FFFFFF">
          <td  align="right" class="dtPanel_Left">&nbsp;<%=ViewUtil.must()%>��¼��ҳ��</td>
          <td align="left" class="dtPanel_Main2">&nbsp;
          <html:select property="templ_id" >
            <html:optionsCollection name="roleForm" property="templ_id_options" />
          </html:select>         
        </td>
        </tr>
        <tr bgcolor="#FFFFFF">
          <td  align="right" class="dtPanel_Left">&nbsp;<%=ViewUtil.must()%>�Ự��ʱ���ƣ�</td>
          <td align="left" class="dtPanel_Main2">&nbsp;
          <input type="text" name="sesn_time" maxlength="20" size="20" value="" class="Textfield" onkeypress="checknum()"  onkeyup="onlyNum(this)" >&nbsp;��
          </td>
        </tr>

        <tr bgcolor="#FFFFFF">
          <td  align="right" class="dtPanel_Left">&nbsp;<%=ViewUtil.must()%>���ƴ��룺</td>
          <td align="left" class="dtPanel_Main2">&nbsp;
         <input type="text" name="logic_id" size="20" maxlength="8"  value="" class="Textfield">
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
<p>&nbsp;</p>

</body>
</html>
