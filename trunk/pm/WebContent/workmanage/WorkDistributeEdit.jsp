<%@ include file = "/includes/common.jsp" %> 
<%@ page contentType="text/html; charset=GBK"%> 
<jsp:useBean id="projectDistributeForm" scope="request" class="com.projectmaintain.ProjectDistributeForm" />
<jsp:useBean id="workDistributeForm" scope="request" class="com.huateng.blue.work.WorkDistributeForm" />
<html> 
 <head>
<title>����ά��</title> 
<script language="javascript"> 
function doCommit(){
	var s = prompt('�������޸�ԭ��','');
	if(s == null || s==""){
		alert("�������޸�ԭ��");
		return;
	}
	document.forms[0].reason.value=s;
	document.forms[0].submit();
}	
</script>
 </head>
<body> 
<p>&nbsp;</p> 
<script type="text/javascript" src="js/calendar.js"></script>

<html:form method="post" action="WorkDistribute.do"> 
<input type=hidden name=act value="update"> 
<html:hidden property="workId"/>
<html:hidden property="id"/>
<html:hidden property="reason"/>
<%=ViewUtil.getTitle("�������")%> 

    <table align="center" width="98%" class="dtPanel_Line3" border="0" cellspacing="1" cellpadding="0"> 
      
       <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left">&nbsp; <bean:message key="projectDistributeForm.userId.display" bundle="projectMaintain"/>��</td> 
         <td align="left" class="dtPanel_Main2">&nbsp; 
         <html:select property="userId" styleClass="Select" disabled="true">
         	<html:optionsCollection name="projectDistributeForm" property="stuff"/>
         </html:select>
         <html:hidden property="userId"/>
         </td>
       </tr> 
        <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left">&nbsp; <bean:message key="projectDistributeForm.startDate.display" bundle="projectMaintain"/>��</td> 
         <td align="left" class="dtPanel_Main2">&nbsp; 
         <html:text property="startDate" styleClass="Textfield" size="8" readonly="true" onclick="new Calendar().show(this);"/> 
          <html:messages property="startDate" id="error"><font color="red"><bean:write name="error" /></font></html:messages>
         </td> 
       </tr> 
        <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left">&nbsp; <bean:message key="projectDistributeForm.endDate.display" bundle="projectMaintain"/>��</td>
         <td align="left" class="dtPanel_Main2">&nbsp; 
         <html:text property="endDate" styleClass="Textfield" size="8" readonly="true" onclick="new Calendar().show(this);"/>
         </td> 
       </tr>     
       <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left">&nbsp; Ԥ����������</td>
         <td align="left" class="dtPanel_Main2">&nbsp;         
         <html:text property="notifyDay" styleClass="Textfield" size="2"></html:text>
         </td> 
       </tr>   		
      	<tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left">&nbsp; <bean:message key="projectDistributeForm.memo.display" bundle="projectMaintain"/>��</td>
         <td align="left" class="dtPanel_Main2">&nbsp;         
         <html:textarea property="content" styleClass="Textarea" cols="40" rows="6"></html:textarea>
         </td> 
       </tr>     
  </table> 
 
    <table  class=heightspace_bottom1  border="0" cellspacing="0" cellpadding="0"><tr><td></td></tr></table> 
 
    <table align="center" width="98%" border="0" cellspacing="0" cellpadding="0"> 
        <tr> 
				<td height="25" align="center" class="dtPanel_Bottom"> 
					<html:button styleClass="Button" value="����" property="baocun" onclick="doCommit()"></html:button>
					<input type="button" class="Button" value="����" onclick="history.back()">
		 		</td> 
	    </tr> 
  </table> 
 
</html:form> 
 
</body> 
</html> 
 

