<%@ include file = "/includes/common.jsp" %> 
<%@ page contentType="text/html; charset=GBK"%> 
<jsp:useBean id="projectMaintainForm" scope="request" class="com.projectmaintain.ProjectMaintainForm" />
<html> 
 <head>
<title>任务维护</title> 
 
 
<script language="javascript"> 

</script> 
 </head>
<body> 
<script type="text/javascript" src="js/calendar.js"></script>
<p>&nbsp;</p> 

<html:form method="post" action="WorkList.do"> 
<input type=hidden name=act value="add"> 
<%=ViewUtil.getTitle("任务维护")%> 
 
    <table align="center" width="98%" class="dtPanel_Line3" border="0" cellspacing="1" cellpadding="0"> 
        
         
        <tr bgcolor="#FFFFFF"> 
        	<td width="35%" align="right" class="dtPanel_Left">&nbsp;任务编号：</td> 
         <td align="left" class="dtPanel_Main2">&nbsp; 
         <html:text property="workId" styleClass="Textfield" size="8" /> 
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
					<html:submit styleClass="Button" value="保存"/>
					<input type="button" class="Button" value="返回" onclick="history.back()">
		 		</td> 
	    </tr> 
  </table> 
 
</html:form> 
 
</body> 
</html> 
 

