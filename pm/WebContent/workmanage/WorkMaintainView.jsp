<%@ include file = "/includes/common.jsp" %> 
<%@ page contentType="text/html; charset=GBK"%> 
<jsp:useBean id="workListForm" scope="request" class="com.huateng.blue.work.WorkListForm" />
<html> 
 <head>
<title>����ά��</title> 
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
<%=ViewUtil.getTitle("����ά��")%> 
 
    <table align="center" width="98%" class="dtPanel_Line3" border="0" cellspacing="1" cellpadding="0"> 
        
         
        <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left">&nbsp; �����ţ�</td> 
         <td align="left" class="dtPanel_Main2">&nbsp; 
         	<bean:write property="workId" name="workListForm"/>
         	<html:hidden property="workId"/>
         </td>
       </tr> 
     
		<tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left">&nbsp; �������ƣ�</td> 
         <td align="left" class="dtPanel_Main2">&nbsp; 
         	<bean:write property="workName" name="workListForm"/>
         	<html:hidden property="workName"/>
         </td>
       </tr> 
        <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left">&nbsp; ��ʼ���ڣ�</td> 
         <td align="left" class="dtPanel_Main2">&nbsp;          
          <bean:write property="startDate" name="workListForm"/>
         </td> 
       </tr> 
       <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left">&nbsp; �������ڣ�</td> 
         <td align="left" class="dtPanel_Main2">&nbsp;          
          <bean:write property="endDate" name="workListForm"/>
         </td> 
       </tr> 
       <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left">&nbsp; �������ݣ�</td> 
         <td align="left" class="dtPanel_Main2">&nbsp;          
          <bean:write property="content" name="workListForm"/>
         </td> 
       </tr> 
       <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left">&nbsp; ����״̬��</td> 
         <td align="left" class="dtPanel_Main2">&nbsp;          
         <%=SingleDicMap.getDicItemVal(SingleDic.WORK_STATUS,workListForm.getWorkStatus())%>
         </td> 
       </tr> 
       <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left">&nbsp; ¼�����ڣ�</td> 
         <td align="left" class="dtPanel_Main2">&nbsp;          
          <bean:write property="inputDate" name="workListForm"/>
         </td> 
       </tr> 
       <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left">&nbsp; ¼����Ա��</td> 
         <td align="left" class="dtPanel_Main2">&nbsp;          
         <%=ReDefSDicMap.getDicItemVal("0003",workListForm.getInputUser().trim())%>
         </td> 
       </tr> 
      
  </table> 
 
    <table  class=heightspace_bottom1  border="0" cellspacing="0" cellpadding="0"><tr><td></td></tr></table> 
 
    <table align="center" width="98%" border="0" cellspacing="0" cellpadding="0"> 
        <tr> 
			<td height="25" align="center" class="dtPanel_Bottom"> 
				<html:button property="ǰ������" styleClass="Button" onclick="getPreProject()">ǰ������</html:button>
				<html:button property="�������" styleClass="Button" onclick="getDistribute()">�������</html:button>
				<html:button property="��������ʷ" styleClass="Button" onclick="getChangeList()">��������ʷ</html:button>
				<html:button property="��������ʷ" styleClass="Button" onclick="getRequireChangeList()">��������ʷ</html:button>
				<html:button property="����" styleClass="Button" onclick="history.back()" >����</html:button>
	 		</td> 
	    </tr> 
  </table> 
 
</html:form> 
 
</body> 
</html> 
 

