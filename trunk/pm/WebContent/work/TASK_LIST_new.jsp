<%@ include file = "/includes/common.jsp" %> 
<%@ page contentType="text/html; charset=GBK"%> 
<html> 
<head>
<title>增加工作列表</title> 
 
<script language="javascript"> 
 
function doAdd(){ 
	//增加 
 
	//执行校验 
	var field = new Array("task_date","task_type","task_goal","task_cost"); 
	var info = new Array("日期","工作类别","任务完成度","工时"); 
 
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
	if(!isFloat(document.forms[0].task_cost.value)){
		alert("工时必须输入整数或小数");
		return;
	}

	document.forms[0].submit(); 
} 
</script> 
</head>
<body> 
<p>&nbsp;</p> 
<script type="text/javascript" src="js/calendar.js"></script>

<html:form method="post" action="TASK_LIST.do"> 
<input type=hidden name=act value=c> 
<input type=hidden name=step value="commit"> 
<html:hidden property="id"/>
<%=ViewUtil.getTitle("增加工作列表")%> 
 
    <table align="center" width="98%" class="dtPanel_Line3" border="0" cellspacing="1" cellpadding="0"> 
        <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left">&nbsp;<%=ViewUtil.must()%>项目编号：</td> 
         <td align="left" class="dtPanel_Main2">&nbsp; 
         <html:text property="project_no" styleClass="Textfield-READONLY"  size="6" maxlength="6" /> 
         </td> 
       </tr> 
 
        <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left">&nbsp;<%=ViewUtil.must()%>日期：</td> 
         <td align="left" class="dtPanel_Main2">&nbsp; 
         <html:text property="task_date" styleClass="Textfield"  size="20" maxlength="8" readonly="true" onclick="new Calendar().show(this);" /> 
         </td> 
       </tr> 
 
        <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left">&nbsp;<%=ViewUtil.must()%>项目阶段：</td> 
         <td align="left" class="dtPanel_Main2">&nbsp; 
         <html:select property="task_step" >
         	<html:optionsCollection name="tASK_LISTForm" property="task_step_c"/>
         </html:select> 
         <html:hidden property="task_step"/>
         </td> 
       </tr> 
 
        <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left">&nbsp;<%=ViewUtil.must()%>工作类别：</td> 
         <td align="left" class="dtPanel_Main2">&nbsp; 
         <html:select property="task_type">
         	<html:optionsCollection name="tASK_LISTForm" property="task_type_c"/>
         </html:select> 
         </td> 
       </tr> 
 		<html:hidden property="task_goal" value="0"/>        
 
        <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left">&nbsp;<%=ViewUtil.must()%>工时：</td> 
         <td align="left" class="dtPanel_Main2">&nbsp; 
         <html:text property="task_cost" styleClass="Textfield"  size="20" maxlength="5" /> 
         </td> 
       </tr>  
        <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left">&nbsp;工作内容：</td> 
         <td align="left" class="dtPanel_Main2">&nbsp; 
         <html:textarea property="task_memo" styleClass="Textarea" rows="5" cols="40"/> 
         </td> 
       </tr>
 
  </table> 
 
    <table  class=heightspace_bottom1  border="0" cellspacing="0" cellpadding="0"><tr><td></td></tr></table> 
 
    <table align="center" width="98%" border="0" cellspacing="0" cellpadding="0"> 
        <tr> 
				<td height="25" align="center" class="dtPanel_Bottom"> 
						<input	name="add" type="button" class="Button" value="保存" onClick="doAdd()"> &nbsp; 
						<input name="return" type="button" class="Button" value="返回" onClick="history.back()">   
		 		</td> 
	    </tr> 
  </table> 
 
</html:form> 
 
</body> 
</html> 
 

