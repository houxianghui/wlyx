<%@ include file = "/includes/common.jsp" %> 
<%@ page contentType="text/html; charset=GBK"%> 
<jsp:useBean id="sDicForm" scope="request" class="com.eis.dic.sdic.SDicForm" /> 
<html> 
 
<title>增加单级字典信息</title> 
 
 
<script language="javascript"> 
 
function doAdd(){ 
	//增加 
	//执行校验 
	var field = new Array("item_code","item_val","list_order","logic_id","status"); 
	var info = new Array("选项编码","选项值","输出顺序","逻辑代码","状态"); 
 
	//检察输入信息是否为空 
	var tmp; 
	for(var i=0;i<field.length;i++)	{ 
	        tmp ="document.forms[0]."+field[i]+".value"; 
	        if(isEmpty(eval(tmp))) { 
	        	alert('请输入'+info[i]); 
	        	eval("document.forms[0]."+field[i]+".focus()"); 
	        	return; 
	        } 
	} 
	//检查字段长度
	if(getStrLength(document.forms[0].item_code.value)>12) { 		
	        	alert('选项编码长度请不要超过12位!'); 
	        	document.forms[0].item_code.focus(); 
	        	return; 
	}  
	if(getStrLength(document.forms[0].item_val.value)>40) { 		
	        	alert('选项值长度请不要超过40位!'); 
	        	document.forms[0].item_val.focus(); 
	        	return; 
	} 
	if(getStrLength(document.forms[0].logic_id.value)>12) { 		
	        	alert('逻辑代码长度请不要超过12位!'); 
	        	document.forms[0].logic_id.focus(); 
	        	return; 
	}
	//数字类型检查
	if(!isInteger(document.forms[0].list_order.value)){
			alert("输出顺序要求输入数字!");
			document.forms[0].list_order.focus();
			return;
	}  
		
	document.forms[0].submit(); 
} 
</script> 
 
<body   class="body_bg_grey1"> 

 
<html:form method="post" action="SDic.do"> 
<input type=hidden name=act value=c> 
<input type=hidden name=step value="commit"> 
  
  
  <%=ViewUtil.getTitle("增加单级字典")%>
   
    <table align="center" width="98%" class="dtPanel_Line3"  border="0" cellspacing="1" cellpadding="0">
        	  
        <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left">&nbsp;归类码：</td> 
         <td align="left" class="dtPanel_Main2">&nbsp; 
         <html:text property="type_id" readonly="true" styleClass="Textfield-READONLY"  size="20" maxlength="4" /> 
         </td> 
       </tr> 
 
        <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left">&nbsp;<%=ViewUtil.must()%>选项编码：</td> 
         <td align="left" class="dtPanel_Main2">&nbsp; 
         <html:text property="item_code" styleClass="Textfield"  size="20" maxlength="12" /> 
         </td> 
       </tr> 
 
        <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left">&nbsp;<%=ViewUtil.must()%>选项值：</td> 
         <td align="left" class="dtPanel_Main2">&nbsp; 
         <html:text property="item_val" styleClass="Textfield"  size="20" maxlength="40" /> 
         </td> 
       </tr> 
 
        <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left">&nbsp;<%=ViewUtil.must()%>输出顺序：</td> 
         <td align="left" class="dtPanel_Main2">&nbsp; 
         <html:text property="list_order" styleClass="Textfield"  onkeypress="checknum()"  onkeyup="onlyNum(this)" size="20" maxlength="10" /> 
         </td> 
       </tr> 
 
        <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left">&nbsp;<%=ViewUtil.must()%>逻辑代码：</td> 
         <td align="left" class="dtPanel_Main2">&nbsp; 
         <html:text property="logic_id" styleClass="Textfield"  size="20" maxlength="12" /> 
         </td> 
       </tr> 
 
        <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left">&nbsp;状态：</td> 
         <td align="left" class="dtPanel_Main2">&nbsp; 
         <html:select property="status">
              
              <html:option value="1">正常</html:option>
              <html:option value="0">作废</html:option>
           </html:select>
          
         </td> 
       </tr> 
  </table>
  
  	<table  class=heightspace_bottom1  border="0" cellspacing="0" cellpadding="0"><tr><td></td></tr></table>
   
    <table align="center" width="98%" border="0" cellspacing="0" cellpadding="0">      
        <tr> 
				<td height="25" align="center"  class="dtPanel_Bottom"> 
						<input	name="add" type="button" class="Button" value="保存" onClick="doAdd()"> &nbsp; 
						<input name="return" type="button" class="Button" value="返回" onClick="location.href='SDic.do?act=list&type_id=<%=sDicForm.getType_id()%>';">   
		 		</td> 
	    </tr> 
  </table>  
 
</html:form> 
 
</body> 
</html> 
 

