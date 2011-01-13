<%@ include file = "/includes/common.jsp" %> 
<%@ page contentType="text/html; charset=GBK"%> 
<html> 
 
<title>增加首页模板</title> 

 
<script language="javascript">  

function doAdd(){ 
	//增加 
 
	//执行校验 
	var field = new Array("caption","url"); 
	var info = new Array("模板名称","URL"); 
 
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
	if(getStrLength(document.forms[0].caption.value)>40) { 		
	        	alert('模板名称长度中文字符不能超过20，英文字符不能超过40!'); 
	        	document.forms[0].caption.focus(); 
	        	return; 
	}
	//检查字段长度
	if(getStrLength(document.forms[0].url.value)>200) { 		
	        	alert('url长度不能超过200个字符!'); 
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


 <%=ViewUtil.getTitle("增加首页模板")%> 
 
    <table align="center" width="98%"  class="dtPanel_Line3" border="0" cellspacing="1" cellpadding="0"> 
 	 
          
        <tr bgcolor="#FFFFFF"> 
          <td width="25%" align="right" class="dtPanel_Left">&nbsp;<%=ViewUtil.must()%>模板名称：</td> 
         <td align="left" class="dtPanel_Main2">&nbsp; 
         <html:text property="caption" styleClass="Textfield"  size="40" maxlength="40" /> 
         </td> 
       </tr> 
 
        <tr bgcolor="#FFFFFF"> 
          <td width="25%" align="right" class="dtPanel_Left">&nbsp;<%=ViewUtil.must()%>URL：</td> 
         <td align="left" class="dtPanel_Main2">&nbsp; 
         <html:text property="url" styleClass="Textfield"  size="50" maxlength="200" /> 
         </td> 
       </tr> 
  </table>
  
  
   <table  class=heightspace_bottom1  border="0" cellspacing="0" cellpadding="0"><tr><td></td></tr></table>
    <table align="center" width="98%" border="0" cellspacing="0" cellpadding="0">  
        <tr> 
				<td  height="25" align="center" class="dtPanel_Bottom"> 
						<input	name="add" type="button" class="Button" value="保存" onClick="doAdd()"> &nbsp; 
						<input name="return" type="button" class="Button" value="返回" onClick="history.back()">   
		 		</td> 
	    </tr> 
  </table> 
 
</html:form> 
 
</body> 
</html> 
 

