<%@ include file = "/includes/common.jsp" %> 
<%@ page contentType="text/html; charset=GBK"%> 
<jsp:useBean id="dicTypeForm" scope="request" class="com.eis.dic.dictype.DicTypeForm" /> 
<html> 
 
<title>�޸��ֵ������Ϣ</title>  

 
<script language="javascript"> 
 
function doSave(){ 
	//�޸� 
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
	if(getStrLength(document.forms[0].type_name.value)>30) { 		
	        	alert('���Ƴ����벻Ҫ����30���ַ�!'); 
	        	document.forms[0].type_name.focus(); 
	        	return; 
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
 
 
function doManage(){ 
	//ά����ϸ
	//��Ҫ�޸ĵ��ǵ����ֵ䣬��ôת�������ֵ��޸�ҳ�档
	if(document.forms[0].dic_level_flag.value == 1){
		location.href='SDic.do?act=list&type_id=<%=dicTypeForm.getType_id()%>';
	}
	//��Ҫ�޸ĵ��Ƕ༶�ֵ䣬��ôת���༶�ֵ��޸�ҳ�档
	if(document.forms[0].dic_level_flag.value == 2){
		location.href="eis/dic/mdic/index.jsp?type_id="+document.forms[0].type_id.value+"";
	}
	
}
</script> 
 
<body  class="body_bg_grey1"> 

 
<html:form method="post" action="DicType.do"> 
<input type=hidden name=act value=u> 
<input type=hidden name=step value="commit"> 
<input type=hidden name=dic_level_flag value="<%=dicTypeForm.getDic_level()%>">

<%=ViewUtil.getTitle("�޸��ֵ������Ϣ")%>

    <table align="center" width="98%"   class="dtPanel_Line3"  border="0" cellspacing="1" cellpadding="0"> 
        	
        <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left">&nbsp;�����룺</td> 
         <td align="left" class="dtPanel_Main2">&nbsp; 
         <html:text property="type_id" styleClass="Textfield-READONLY" readonly="true" size="20" maxlength="4" /> 
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
				        <input	name="proinfo" type="button" class="Button" value="ά����ϸ" onClick="doManage()"> &nbsp; 
						<input	name="update" type="button" class="Button" value="����" onClick="doSave()"> &nbsp; 
						<input name="return" type="button" class="Button" value="����" onClick="location.href='DicType.do?act=list';">   
		 		</td> 
	    </tr> 
  </table> 
 
</html:form> 
 
</body> 
</html> 
 

