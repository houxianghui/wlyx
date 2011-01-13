<% 
response.setHeader("Cache-Control","no-cache"); 
response.setHeader("Pragma","no-cache"); 
response.setDateHeader("Expires",0); 
%> 
 
<%@ include file = "/includes/common.jsp" %> 
<%@ page contentType="text/html; charset=GBK"%> 
<html> 
 
<title>修改自定义单级字典</title>  

 
<script language="javascript"> 
 
function doSave(){ 
	//修改 
 
	//执行校验 
	var field = new Array("type_id","caption","stmt"); 
	var info = new Array("规类码","字典内容","数据查询语句"); 
 
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
	if(getStrLength(document.forms[0].stmt.value)>400) { 
	        	alert('数据查询语句长度不能超过400!'); 
	        	document.forms[0].stmt.focus(); 
	        	return; 
	} 
	
	if(getStrLength(document.forms[0].memo.value)>80) { 
	        	alert('备注长度不能超过80!'); 
	        	document.forms[0].memo.focus(); 
	        	return; 
	} 
	
	document.forms[0].submit(); 
} 
<% 
	String success = (String)request.getAttribute("success"); 
	if(null != success && success.equals("y"))	{		 
		%> 
		alert("数据修改成功！"); 
		<% 
	} 
%> 
 
</script> 
 
<body   class="body_bg_grey1"> 
 
<html:form method="post" action="ReDefSDic.do"> 
<input type=hidden name=act value=u> 
<input type=hidden name=step value="commit"> 
 
 <%=ViewUtil.getTitle("修改自定义单级字典")%>
 
    <table align="center" width="98%"  class="dtPanel_Line3"  border="0" cellspacing="1" cellpadding="0">
             
        <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left">&nbsp;规类码：</td> 
         <td align="left" class="dtPanel_Main2">&nbsp; 
         <html:text property="type_id" styleClass="Textfield-READONLY"  size="4" maxlength="4" readonly="true" /> 
         </td> 
       </tr> 
 
        <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left">&nbsp;<%=ViewUtil.must()%>字典内容：</td> 
         <td align="left" class="dtPanel_Main2">&nbsp; 
         <html:text property="caption" styleClass="Textfield"  size="30" maxlength="15" /> 
         </td> 
       </tr> 
 
        <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left">&nbsp;<%=ViewUtil.must()%>数据查询语句：</td> 
         <td align="left" class="dtPanel_Main2">&nbsp; 
         <html:textarea property="stmt" styleClass="Textarea"  cols="50"  rows="4" /> 
         </td> 
       </tr> 
 
        <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left">&nbsp;备注：</td> 
         <td align="left" class="dtPanel_Main2">&nbsp; 
         <html:text property="memo" styleClass="Textfield"  size="52" maxlength="40" /> 
         </td> 
       </tr>
  </table> 
  
   	<table  class=heightspace_bottom1  border="0" cellspacing="0" cellpadding="0"><tr><td></td></tr></table>
   
    <table align="center" width="98%" border="0" cellspacing="0" cellpadding="0">      
        <tr> 
				<td height="25" align="center"  class="dtPanel_Bottom"> 
						<input	name="update" type="button" class="Button" value="保存" onClick="doSave()"> &nbsp; 
						<input name="return" type="button" class="Button" value="返回" onClick="location.href='ReDefSDic.do?act=list';">   
		 		</td> 
	    </tr> 
  </table> 
 
</html:form> 
 
</body> 
</html> 
 

