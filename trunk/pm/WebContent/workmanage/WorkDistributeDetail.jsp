<%@ include file = "/includes/common.jsp" %> 
<%@ page contentType="text/html; charset=GBK"%> 
<jsp:useBean id="projectDistributeForm" scope="request" class="com.projectmaintain.ProjectDistributeForm" />
<jsp:useBean id="workDistributeForm" scope="request" class="com.huateng.blue.work.WorkDistributeForm" />
<html> 
 <head>
<title>任务分配</title> 
<script type="text/javascript">
function getChangeList(){
	window.location.href="ProjectMaintain.do?act=gc&projectId="+document.forms[0].workId.value+"&id="+document.forms[0].id.value;
}
</script>
 </head>
<body> 
<p>&nbsp;</p> 

<html:form method="post" action="WorkDistribute.do"> 
<html:hidden property="act"/>
<html:hidden property="id"/>
<html:hidden property="workId"/>
<%=ViewUtil.getTitle("任务分配")%> 
 
    <table align="center" width="98%" class="dtPanel_Line3" border="0" cellspacing="1" cellpadding="0"> 
        
         
        <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left">&nbsp; 任务编号：</td> 
         <td align="left" class="dtPanel_Main2">&nbsp; 
         	<bean:write property="workId" name="workDistributeForm"/>         	
         </td>
       </tr> 
		<tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left">&nbsp; 任务状态：</td> 
         <td align="left" class="dtPanel_Main2">&nbsp; 
         	<%=SingleDicMap.getDicItemVal(SingleDic.WORK_STATUS,workDistributeForm.getWorkStatus())%>       	
         </td>
       </tr> 
        <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left">&nbsp; <bean:message key="projectDistributeForm.userId.display" bundle="projectMaintain"/>：</td> 
         <td align="left" class="dtPanel_Main2">&nbsp;          
          <%=ReDefSDicMap.getDicItemVal("0003",workDistributeForm.getUserId().trim())%>
         </td> 
       </tr> 
        <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left">&nbsp; <bean:message key="projectDistributeForm.startDate.display" bundle="projectMaintain"/>：</td>
         <td align="left" class="dtPanel_Main2">&nbsp; 
		 <bean:write property="startDate" name="workDistributeForm"/>
         </td> 
       </tr> 
        <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left">&nbsp;<bean:message key="projectDistributeForm.endDate.display" bundle="projectMaintain"/>：</td> 
         <td align="left" class="dtPanel_Main2">&nbsp; 
         <bean:write property="endDate" name="workDistributeForm"/>     
         </td> 
       </tr> 
		<tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left">&nbsp;预提醒天数：</td> 
         <td align="left" class="dtPanel_Main2">&nbsp; 
         <bean:write property="notifyDay" name="workDistributeForm"/>     
         </td> 
       </tr> 
        <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left">&nbsp; <bean:message key="projectDistributeForm.memo.display" bundle="projectMaintain"/>：</td>
         <td align="left" class="dtPanel_Main2">&nbsp; 
		 <bean:write property="content" name="workDistributeForm"/>
         </td> 
       </tr>
  </table> 
 
    <table  class=heightspace_bottom1  border="0" cellspacing="0" cellpadding="0"><tr><td></td></tr></table> 
 
    <table align="center" width="98%" border="0" cellspacing="0" cellpadding="0"> 
        <tr> 
			<td height="25" align="center" class="dtPanel_Bottom"> 
				<html:button property="变更历史" styleClass="Button" onclick="getChangeList()" >任务变更历史</html:button>				
				<html:button property="返回" styleClass="Button" onclick="history.back()" >返回</html:button>
	 		</td> 
	    </tr> 
  </table> 
 
</html:form> 
 
</body> 
</html> 
 

