<%@ include file = "/includes/common.jsp" %>

<%@ page import="com.eis.cache.*" %>

<%@ page contentType="text/html; charset=GBK"%>

<jsp:useBean id="roleForm" scope="request" class="com.eis.portal.role.RoleForm" /> 

<html>
<title>�޸Ľ�ɫ��Ϣ</title>

<script language="javascript">

function doSave(){
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
        alert('�Ự��ʱ������������������');
        document.forms[0].sesn_time.focus();
        return;
    } 
     
	
	//�ύ
	document.forms[0].act.value='u';
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


<body  class="body_bg_grey1">

<html:form method="post" action="Role.do">
<input type=hidden name=act value="u">
<input type=hidden name=step value="commit">
<html:hidden property="status"/>

<br>
<%=ViewUtil.getTitle("�޸Ľ�ɫ��Ϣ")%>

    <table align="center" width="98%"  class="dtPanel_Line3" border="0" cellspacing="1" cellpadding="0">
        	     
        <tr bgcolor="#FFFFFF">
          <td width="30%" align="right" class="dtPanel_Left">&nbsp;��ɫ���룺</td>
          <td align="left" class="dtPanel_Main2">&nbsp;
         <html:text property="role_id" styleClass="Textfield-READONLY" readonly="true" size="20"  maxlength="8"/>
         </td>
        </tr>
        <tr bgcolor="#FFFFFF">
          <td  align="right" class="dtPanel_Left">&nbsp;<%=ViewUtil.must()%>��ɫ���ƣ�</td>
          <td align="left" class="dtPanel_Main2">&nbsp; 
          <html:text property="role_name" styleClass="Textfield" size="30" maxlength="40" />
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
          <html:text property="sesn_time" styleClass="Textfield" size="20" maxlength="20" onkeypress="checknum()"  onkeyup="onlyNum(this)" />&nbsp;��
          </td>
        </tr>
        
                 <tr bgcolor="#FFFFFF">
          <td  align="right" class="dtPanel_Left">&nbsp;<%=ViewUtil.must()%>���ƴ��룺</td>
          <td align="left" class="dtPanel_Main2">&nbsp; 
          <html:text property="logic_id" styleClass="Textfield" size="20" maxlength="8" />
          </td>
        </tr>
  </table>

  <table  class=heightspace_bottom1  border="0" cellspacing="0" cellpadding="0"><tr><td></td></tr></table>
   <table align="center" width="98%" border="0" cellspacing="0" cellpadding="0">        
        <tr>	
				<td height="25" align="center" class="dtPanel_Bottom">
						<input	name="update" type="button" class="Button" value="����" onClick="doSave()"> &nbsp;
						<html:reset value="����" styleClass="Button"/>  &nbsp; 
						<input name="return" type="button" class="Button" value="����" onClick="location.href='Role.do?act=list';">  
						
		  </td>
	  </tr>
  </table>
			
		
</html:form>
<p>&nbsp;</p>

</body>
</html>
