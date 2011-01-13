<%@ include file = "/includes/common.jsp" %> 
<%@ page contentType="text/html; charset=GBK"%> 
<html> 
<head>
<title>增加工作列表</title> 
 
<script language="javascript"> 
 
function doAdd(){ 
	//增加 
 
	//执行校验 
	var field = new Array("project_no","project_name","curr_step","start_date","end_date"); 
	var info = new Array("项目编号","项目名称","当前阶段","开始日期","结束日期"); 
 
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
</script> 
</head>
<body> 
<p>&nbsp;</p> 
 
<html:form method="post" action="PROJECT_LIST.do"> 
<input type=hidden name=act value=c> 
<input type=hidden name=step value="commit"> 
 
<%=ViewUtil.getTitle("增加项目信息")%> 
 
    <table align="center" width="98%" class="dtPanel_Line3" border="0" cellspacing="1" cellpadding="0"> 
         
        <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left">&nbsp;<%=ViewUtil.must()%>项目编号：</td> 
         <td align="left" class="dtPanel_Main2">&nbsp; 
         <html:text property="project_no" styleClass="Textfield"  size="10" maxlength="4" /> 
         </td> 
       </tr> 
 
        <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left">&nbsp;<%=ViewUtil.must()%>项目名称：</td> 
         <td align="left" class="dtPanel_Main2">&nbsp; 
         <html:text property="project_name" styleClass="Textfield"  size="20" maxlength="20" /> 
         </td> 
       </tr> 
 
        <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left">&nbsp;<%=ViewUtil.must()%>当前阶段：</td> 
         <td align="left" class="dtPanel_Main2">&nbsp; 
        	<html:select property="curr_step">
        		<html:optionsCollection name="pROJECT_LISTForm" property="curr_step_c"/>
        	</html:select>
         </td> 
       </tr> 
 
        <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left">&nbsp;<%=ViewUtil.must()%>开始日期：</td> 
         <td align="left" class="dtPanel_Main2">&nbsp; 
         <html:text property="start_date" styleClass="Textfield"  size="20" maxlength="8" onblur="onlyNum(this)" onkeydown="onlyNum(this)" onkeyup="onlyNum(this)"/> 
         </td> 
       </tr> 
 
        <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left">&nbsp;<%=ViewUtil.must()%>结束日期：</td> 
         <td align="left" class="dtPanel_Main2">&nbsp; 
         <html:text property="end_date" styleClass="Textfield"  size="20" maxlength="8" onblur="onlyNum(this)" onkeydown="onlyNum(this)" onkeyup="onlyNum(this)"/> 
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
 

