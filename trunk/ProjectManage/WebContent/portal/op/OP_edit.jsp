<%@ include file = "/includes/common.jsp" %>

<%@ page contentType="text/html; charset=GBK"%>

<jsp:useBean id="opForm" scope="request" class="com.eis.portal.op.OPForm" />


<html>
<title>�޸Ĳ���Ȩ��</title>


<script language="javascript">

function doSave(){
	//ִ��У��
	var field = new Array("op_code","caption");
	var info = new Array("Ȩ����","Ȩ������");
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
	if(getStrLength(document.forms[0].caption.value)>40) { 		
	        	alert('�������Ƴ����벻Ҫ����40���ַ�!'); 
	        	document.forms[0].caption.focus(); 
	        	return; 
	}
	//�ύ
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

function doReturnList(){

  	document.forms[0].act.value='list';	
  	document.forms[0].caption.value='';
	document.forms[0].submit();	
	
}
</script>


<body  class="body_bg_grey1">

<p>&nbsp;</p>
<html:form method="post"   action="OP.do">
<input type=hidden name=act value="u">
<input type=hidden name=step value="commit">

 <%=ViewUtil.getTitle("�޸Ĳ���Ȩ�޶���")%>
 
    <table align="center" width="98%"  class="dtPanel_Line3" border="0" cellspacing="1" cellpadding="0"> 
    	     
        <tr bgcolor="#FFFFFF">
          <td width="30%" align="right" class="dtPanel_Left">&nbsp;���ܲ˵���</td>
          <td align="left" class="dtPanel_Main2">&nbsp;
         <html:text property="menu_name" styleClass="Textfield-READONLY"  readonly="true" size="30" maxlength="40" />
         <html:hidden property="menu_id" />
         </td>
        </tr>
        <tr bgcolor="#FFFFFF">
          <td width="30%" align="right" class="dtPanel_Left">&nbsp;Ȩ���룺</td>
          <td align="left" class="dtPanel_Main2">&nbsp; 
          <html:text property="op_code" styleClass="Textfield-READONLY"   readonly="true"  size="30" maxlength="20" />
          </td>
        </tr>
         <tr bgcolor="#FFFFFF">
          <td width="30%" align="right" class="dtPanel_Left">&nbsp;<%=ViewUtil.must()%>�������ƣ�</td>
          <td align="left" class="dtPanel_Main2">&nbsp; 
          <html:text property="caption" styleClass="Textfield" size="30" maxlength="40" />
          </td>
        </tr>        
  </table>        
    
    <table  class=heightspace_bottom1  border="0" cellspacing="0" cellpadding="0"><tr><td></td></tr></table>  
    <table align="center" width="98%" border="0" cellspacing="0" cellpadding="0">
        <tr>
				
				<td  height="25" align="center" class="dtPanel_Bottom">
						<input	name="update" type="button" class="Button" value="����" onClick="doSave()"> &nbsp;
						<input name="return" type="button" class="Button" value="����" onClick="doReturnList()">  
						
		  </td>
	  </tr>
  </table>
			
		
</html:form>
<p>&nbsp;</p>

</body>
</html>
