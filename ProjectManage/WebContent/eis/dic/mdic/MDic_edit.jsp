<%@ include file = "/includes/common.jsp" %> 
<%@ page contentType="text/html; charset=GBK"%> 
<html> 
 
<title>修改多级字典</title> 
 
<SCRIPT src="js/apply/cardApply.js" type="text/javascript"></SCRIPT> 
<script language="javascript"> 
 
function doSave(){ 
	//修改 
 
	//执行校验 
	var field = new Array("type_id","parent_id","item_id","item_val","list_order","item_level","status"); 
	var info = new Array("归类码","上级编码","选项编码","选项值","输出顺序","级次","status"); 
 
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
		alert("数据修改成功！"); 
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
 
<%=ViewUtil.getTitle("修改多级字典")%> 
 
    <table align="center" width="98%" class="dtPanel_Line3" border="0" cellspacing="1" cellpadding="0"> 
        <!--tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left">&nbsp;<%=ViewUtil.must()%>系统编码：</td> 
         <td align="left" class="dtPanel_Main2">&nbsp; 
         <html:text property="sys_id" styleClass="Textfield"  size="20" maxlength="12" /> 
         </td> 
       </tr--> 
 
        <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left">&nbsp;<%=ViewUtil.must()%>归类码：</td> 
         <td align="left" class="dtPanel_Main2">&nbsp; 
         <html:text property="type_id" styleClass="Textfield-READONLY" readonly="true" size="20" maxlength="4" /> 
         </td> 
       </tr> 
 
        <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left">&nbsp;<%=ViewUtil.must()%>上级编码：</td> 
         <td align="left" class="dtPanel_Main2">&nbsp; 
         <html:text property="parent_id" styleClass="Textfield-READONLY"  readonly="true"  size="20" maxlength="12" /> 
         </td> 
       </tr> 
 
        <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left">&nbsp;<%=ViewUtil.must()%>选项编码：</td> 
         <td align="left" class="dtPanel_Main2">&nbsp; 
         <html:text property="item_id" styleClass="Textfield"  size="20" maxlength="12" /> 
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
         <html:text property="list_order" styleClass="Textfield"  size="20" maxlength="2"  onblur="onlyNum(this)" onkeyup="onlyNum(this)"/> 
         </td> 
       </tr> 
 
        <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left">&nbsp;<%=ViewUtil.must()%>级次：</td> 
         <td align="left" class="dtPanel_Main2">&nbsp; 
         <html:text property="item_level" styleClass="Textfield-READONLY"  readonly="true"  size="20" maxlength="2" /> 
         </td> 
       </tr> 
 
        <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left">&nbsp;逻辑代码：</td> 
         <td align="left" class="dtPanel_Main2">&nbsp; 
         <html:text property="logic_id" styleClass="Textfield"  size="20" maxlength="12" /> 
         </td> 
       </tr> 
 
        <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left">&nbsp;<%=ViewUtil.must()%>状态：</td> 
         <td align="left" class="dtPanel_Main2">&nbsp; 
         <!--html:text property="status" styleClass="Textfield" size="20" maxlength="1" /--> 
         <html:select property="status"> 
         <html:option value= "1">正常</html:option> 
         <html:option value= "2">作废</html:option>
         </html:select>
         </td> 
       </tr> 
 
  </table> 
 
    <table  class=heightspace_bottom1  border="0" cellspacing="0" cellpadding="0"><tr><td></td></tr></table> 
 
    <table align="center" width="98%" border="0" cellspacing="0" cellpadding="0"> 
        <tr> 
				<td height="25" align="center" class="dtPanel_Bottom"> 
						<input	name="update" type="button" class="Button" value="保存" onClick="doSave()"> &nbsp; 
						<input name="return" type="button" class="Button" value="返回" onClick="doBack()">   
		 		</td> 
	    </tr> 
  </table> 
 
</html:form> 
 
</body> 
</html> 
 

