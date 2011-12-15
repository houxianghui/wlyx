<%@ include file = "/includes/common.jsp" %> 
<%@ page contentType="text/html; charset=GBK"%> 
<jsp:useBean id="workListForm" scope="request" class="com.huateng.blue.work.WorkListForm" />
<html> 
 <head>
<title>任务维护</title> 
<script language="javascript">
function getPreProject(){
	window.location.href="ProjectMaintain.do?act=gpp&projectId="+document.forms[0].workId.value;
	
}
function getDistribute(){
	window.location.href="WorkDistribute.do?act=qd&workId="+document.forms[0].workId.value;
}	
function getProgramList(){
	window.location.href="ProgramMaintain.do?act=ql&projectId="+document.forms[0].workId.value;
}
function getChangeList(){
	window.location.href="ProjectMaintain.do?act=gc&projectId="+document.forms[0].workId.value;
}
function getRequireChangeList(){
	window.location.href="RequireChange.do?act=list&projectId="+document.forms[0].workId.value;
}
</script>
 </head>
<body> 
<p>&nbsp;</p> 

<html:form method="post" action="WorkList.do"> 
<html:hidden property="act"/>
<%=ViewUtil.getTitle("任务维护")%> 
 
    <table align="center" width="98%" class="dtPanel_Line3" border="0" cellspacing="1" cellpadding="0"> 
        
         
        <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left">&nbsp; 任务编号：</td> 
         <td align="left" class="dtPanel_Main2">&nbsp; 
         	<bean:write property="workId" name="workListForm"/>
         	<html:hidden property="workId"/>
         </td>
       </tr> 
     
		<tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left">&nbsp; 任务名称：</td> 
         <td align="left" class="dtPanel_Main2">&nbsp; 
         	<bean:write property="workName" name="workListForm"/>
         	<html:hidden property="workName"/>
         </td>
       </tr> 
        <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left">&nbsp; 开始日期：</td> 
         <td align="left" class="dtPanel_Main2">&nbsp;          
          <bean:write property="startDate" name="workListForm"/>
         </td> 
       </tr> 
       <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left">&nbsp; 结束日期：</td> 
         <td align="left" class="dtPanel_Main2">&nbsp;          
          <bean:write property="endDate" name="workListForm"/>
         </td> 
       </tr> 
       <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left">&nbsp; 任务内容：</td> 
         <td align="left" class="dtPanel_Main2">&nbsp;          
          <bean:write property="content" name="workListForm"/>
         </td> 
       </tr> 
       <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left">&nbsp; 任务状态：</td> 
         <td align="left" class="dtPanel_Main2">&nbsp;          
         <%=SingleDicMap.getDicItemVal(SingleDic.WORK_STATUS,workListForm.getWorkStatus())%>
         </td> 
       </tr> 
       <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left">&nbsp; 录入日期：</td> 
         <td align="left" class="dtPanel_Main2">&nbsp;          
          <bean:write property="inputDate" name="workListForm"/>
         </td> 
       </tr> 
       <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left">&nbsp; 录入人员：</td> 
         <td align="left" class="dtPanel_Main2">&nbsp;          
         <%=ReDefSDicMap.getDicItemVal("0003",workListForm.getInputUser().trim())%>
         </td> 
       </tr> 
      
  </table> 
 
    <table  class=heightspace_bottom1  border="0" cellspacing="0" cellpadding="0"><tr><td></td></tr></table> 
 
    <table align="center" width="98%" border="0" cellspacing="0" cellpadding="0"> 
        <tr> 
			<td height="25" align="center" class="dtPanel_Bottom"> 
				<html:button property="前驱任务" styleClass="Button" onclick="getPreProject()">前驱任务</html:button>
				<html:button property="任务分配" styleClass="Button" onclick="getDistribute()">任务分配</html:button>
				<html:button property="任务变更历史" styleClass="Button" onclick="getChangeList()">任务变更历史</html:button>
				<html:button property="需求变更历史" styleClass="Button" onclick="getRequireChangeList()">需求变更历史</html:button>
				<html:button property="返回" styleClass="Button" onclick="history.back()" >返回</html:button>
	 		</td> 
	    </tr> 
  </table> 
 
</html:form> 
 
</body> 
</html> 
 

