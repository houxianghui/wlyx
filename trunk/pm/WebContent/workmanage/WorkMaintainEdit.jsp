<%@ include file = "/includes/common.jsp" %> 
<%@ page contentType="text/html; charset=GBK"%> 
<jsp:useBean id="workListForm" scope="request" class="com.huateng.blue.work.WorkListForm" />
<html> 
 <head>
<title>项目维护</title> 
<script language="javascript"> 
function doCommit(){
	var s = prompt('请输入修改原因','');
	if(s == null || s==""){
		alert("请输入修改原因");
		return;
	}
	document.forms[0].reason.value=s;
	document.forms[0].submit();
}	

</script> 
 </head>
<body> 
<script type="text/javascript" src="js/calendar.js"></script>
<p>&nbsp;</p> 

<html:form method="post" action="WorkList.do"> 
<input type=hidden name=act value="update"> 
<html:hidden property="reason"/>
<%=ViewUtil.getTitle("任务维护")%> 
 
    <table align="center" width="98%" class="dtPanel_Line3" border="0" cellspacing="1" cellpadding="0"> 
        
         
        <tr bgcolor="#FFFFFF"> 
        	<td width="35%" align="right" class="dtPanel_Left">&nbsp;任务编号：</td> 
         <td align="left" class="dtPanel_Main2">&nbsp; 
         <html:text property="workId" styleClass="Textfield-READONLY" size="4" readonly="true"/> 
         </td>
       </tr> 
      
		<tr bgcolor="#FFFFFF"> 
         	<td width="35%" align="right" class="dtPanel_Left">&nbsp;任务名称：</td> 
         <td align="left" class="dtPanel_Main2">&nbsp; 
         <html:text property="workName" styleClass="Textfield" size="40"/> 
         </td>
       </tr>           
		<tr bgcolor="#FFFFFF"> 
         	<td width="35%" align="right" class="dtPanel_Left">&nbsp;任务开始日期：</td> 
         <td align="left" class="dtPanel_Main2">&nbsp; 
         <html:text property="startDate" styleClass="Textfield" size="8" readonly="true" onclick="new Calendar().show(this);"/> 
         </td>
       </tr>     
       <tr bgcolor="#FFFFFF"> 
         	<td width="35%" align="right" class="dtPanel_Left">&nbsp;任务结束日期：</td> 
         <td align="left" class="dtPanel_Main2">&nbsp; 
         <html:text property="endDate" styleClass="Textfield" size="8" readonly="true" onclick="new Calendar().show(this);"/> 
         </td>
       </tr>    
      	<tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left">&nbsp; 任务内容：</td>
         <td align="left" class="dtPanel_Main2">&nbsp;         
         <html:textarea property="content" styleClass="Textarea" cols="40" rows="2"></html:textarea>
         </td> 
       </tr>    
  </table> 
 
    <table  class=heightspace_bottom1  border="0" cellspacing="0" cellpadding="0"><tr><td></td></tr></table> 
 
    <table align="center" width="98%" border="0" cellspacing="0" cellpadding="0"> 
        <tr> 
				<td height="25" align="center" class="dtPanel_Bottom"> 					
					<html:button styleClass="Button" value="保存" property="baocun" onclick="doCommit()"></html:button>
					<input type="button" class="Button" value="返回" onclick="history.back()">
		 		</td> 
	    </tr> 
  </table> 
 
</html:form> 
 
</body> 
</html> 
 

