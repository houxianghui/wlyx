<%@ include file = "/includes/common.jsp" %>

<%@ page contentType="text/html; charset=GBK"%>


<html>
<title>�޸Ĳ˵���Ϣ</title>


<script language="javascript">

function doSave(){
	//ִ��У��
	var field = new Array("menu_name","list_order");
	var info = new Array("�˵�����","��ʾ˳��");
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
	if(getStrLength(document.forms[0].menu_name.value)>40) { 		
	        	alert('�˵����Ƴ��ȳ���40���ַ�!'); 
	        	document.forms[0].menu_name.focus(); 
	        	return; 
	}
	//����ֶγ���
	if(getStrLength(document.forms[0].menu_url.value)>200) { 		
	        	alert('�˵�URL���ȳ���200���ַ�!'); 
	        	document.forms[0].menu_url.focus(); 
	        	return; 
	}
	
	if(!isInteger(document.forms[0].list_order.value)){
		alert("��ʾ˳��Ҫ����������!");
		document.forms[0].list_order.focus();
		return;
	}
	document.forms[0].submit();
	
}

//ˢ�����˵��б�
function reloadLeftMenu(){
		parent.leftFrame.location.reload();
}

<%
	String refresh = (String)request.getSession().getAttribute("refresh");
	if(null != refresh && refresh.equals("y"))	{		
		%>		
		reloadLeftMenu();		
		<%
	}
	session.removeAttribute("refresh");
	
%>



<%
	String success = (String)request.getSession().getAttribute("success");
	if(null != success && success.equals("y"))	{		
		%>		
		reloadLeft();
		<%
	}
	session.removeAttribute("success");
%>

</script>


<body  class="body_bg_grey1">

<p>&nbsp;</p>

<html:form method="post" action="Menu.do">
<input type=hidden name=act value="u">
<input type=hidden name=step value="commit">
<html:hidden property="parent_id"/>
 
 <%=ViewUtil.getTitle("�޸Ĳ˵���Ϣ")%>
 
    <table align="center"  class="dtPanel_Line3" width="98%" border="0" cellspacing="1" cellpadding="0">
        
       <tr bgcolor="#FFFFFF">
          <td width="35%" align="right"  class="dtPanel_Left">&nbsp;�˵���ţ�</td>
          <td align="left" class="dtPanel_Main2">&nbsp;
         <html:text property="menu_id" styleClass="Textfield-READONLY"  readonly="true" size="20" maxlength="30" />
         </td>
        </tr>
        <tr bgcolor="#FFFFFF">
          <td width="35%" align="right"  class="dtPanel_Left">&nbsp;�ϼ��˵���</td>
          <td align="left" class="dtPanel_Main2">&nbsp; 
          <html:text property="parent_name" styleClass="Textfield-READONLY" readonly="true" size="20" maxlength="40" />       
          </td>
        </tr>
        <tr bgcolor="#FFFFFF">
          <td width="35%" align="right"  class="dtPanel_Left">&nbsp;<%=ViewUtil.must()%>�˵����ƣ�</td>
          <td align="left" class="dtPanel_Main2">&nbsp;
          <html:text property="menu_name" styleClass="Textfield" size="20" maxlength="40" />
         </td>
        </tr>
        <html:hidden property="menu_mark"/>
        
        <tr bgcolor="#FFFFFF">
          <td width="35%" align="right"  class="dtPanel_Left">&nbsp;�˵����Σ�</td>
          <td align="left" class="dtPanel_Main2">&nbsp;
          <html:text property="menu_level" styleClass="Textfield-READONLY"  readonly="true"  size="20" maxlength="40" />
          </td>
        </tr>
        <tr bgcolor="#FFFFFF">
          <td width="35%" align="right"  class="dtPanel_Left">&nbsp;<%=ViewUtil.must()%>��ʾ˳��</td>
          <td align="left" class="dtPanel_Main2">&nbsp;
          <html:text property="list_order" styleClass="Textfield" size="20" maxlength="16" onkeypress="checknum()"  onkeyup="onlyNum(this)" />
          </td>
        </tr>        
         <tr bgcolor="#FFFFFF">
          <td width="35%" align="right"  class="dtPanel_Left">&nbsp;�˵�URL��</td>
          <td align="left" class="dtPanel_Main2">&nbsp;
          <html:text property="menu_url" styleClass="Textfield" size="36" maxlength="200" />
          </td>
        </tr>
   </table>       
     
   <table  class=heightspace_bottom1  border="0" cellspacing="0" cellpadding="0"><tr><td></td></tr></table>
    <table align="center" width="98%" border="0" cellspacing="0" cellpadding="0">
            <tr>
				<td height="25" align="center"  class="dtPanel_Bottom" >
						<input	name="update" type="button" class="Button" value="����" onClick="doSave()"> &nbsp;
						<input name="return" type="button" class="Button" value="����" onClick="history.back();">  
						
		  </td>
	  </tr>
  </table>
			
		
</html:form>


</body>
</html>
