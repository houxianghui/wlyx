<%@ include file = "/includes/common.jsp" %>
<%@ page import="com.projectmaintain.ProjectMaintainVO" %>
<%@ page  contentType="text/html; charset=GBK" %>

<jsp:useBean id="pageResult" scope="request" class="com.eis.base.PageObject" />

<html>

<%int maxPage = 1;
if (pageResult != null)
	maxPage = pageResult.getTotalPage();
%>
<head>
<title>��Ŀά��</title>

<script language="javascript"> 
function findConflick(){
	if(document.forms[0].projectId.value == null ||document.forms[0].projectId.value == "") { 
		alert('����ѡ���¼'); 
		return; 
	} 
	document.forms[0].act.value='gcp';	 
	document.forms[0].submit(); 	
}
function editProgram(){
	if(document.forms[0].projectId.value == null ||document.forms[0].projectId.value == "") { 
		alert('����ѡ���¼'); 
		return; 
	} 
	window.location.href="ProgramMaintain.do?act=edit&&projectId="+document.forms[0].projectId.value;
}
function doAdd(){ 
	//���� 
	window.location="ProjectMaintainInit.do"; 
} 
 
function doEdit(){ 
	//�޸� 
	//����Ƿ���ѡ�еļ�¼ 
	if(document.forms[0].projectId.value == null ||document.forms[0].projectId.value == "") { 
		alert('����ѡ���¼'); 
		return; 
	} 
	//�ύ�� 
	document.forms[0].act.value='ep';	 
	document.forms[0].submit(); 
} 
function doEditPreProject(){ 
	//�޸� 
	//����Ƿ���ѡ�еļ�¼ 
	if(document.forms[0].projectId.value == null ||document.forms[0].projectId.value == "") { 
		alert('����ѡ���¼'); 
		return; 
	} 
	//�ύ�� 
	document.forms[0].act.value='epp';	 
	document.forms[0].submit(); 
} 
function doDistribute(){ 
	//�޸� 
	//����Ƿ���ѡ�еļ�¼ 
	if(document.forms[0].projectId.value == null ||document.forms[0].projectId.value == "") { 
		alert('����ѡ���¼'); 
		return; 
	} 
	//�ύ�� 
	window.location.href="ProjectDistribute.do?act=dl&&projectId="+document.forms[0].projectId.value+"&status="+document.forms[0].status.value;
} 
function requireChange(){ 
	//�޸� 
	//����Ƿ���ѡ�еļ�¼ 
	if(document.forms[0].projectId.value == null ||document.forms[0].projectId.value == "") { 
		alert('����ѡ���¼'); 
		return; 
	} 
	//�ύ�� 
	window.location.href="RequireChange.do?act=list&&projectId="+document.forms[0].projectId.value;
} 
function doDelete() { 
	//ɾ�� 
 
	//����Ƿ���ѡ�еļ�¼ 
	if(document.forms[0].projectId.value == null ||document.forms[0].projectId.value == "") { 
		alert('����ѡ���¼'); 
		return; 
	} 
 
	//����ȷ����ʾ 
	if(!confirm('��ȷ��ִ��ɾ��������')) { 
		return; 
	} 
	document.forms[0].act.value='dp'; 
	document.forms[0].submit(); 
} 
 
function doQuery() {  
	document.forms[0].act.value = "qpl";
	document.forms[0].submit(); 
} 
 
function setPKey(projectId_var,status_var) { 
	document.forms[0].projectId.value=projectId_var; 
	document.forms[0].status.value=status_var;
} 
 
function turnPage( pagenm ) {   
    	document.forms[0].act.value = "qpl";  
    	document.forms[0].pageNO.value = pagenm;     
    	document.forms[0].submit(); 
} 
 
</script>
</head>
<body>
<script type="text/javascript" src="js/calendar.js"></script>
<html:form method="post" action="ProjectMaintain.do">
<input type=hidden name=act value="qpl">
<html:hidden property="projectId"/>
<html:hidden property="status"/>
<%=ViewUtil.getTitle("��Ŀά��")%>
	
	<table class=heightspace_top3 width="98%" border="0" cellspacing="1"
		align="center" cellpadding="0">
		<tr>
			<td>
			<bean:message key="projectMaintainForm.projectId.display" bundle="projectMaintain"/>��
			<html:text property="projectId_f" styleClass="Textfield" size="4"></html:text>
			<bean:message key="projectMaintainForm.status.display" bundle="projectMaintain"/>��
			<html:select property="status_f" styleClass="Select">
				<html:optionsCollection name="projectMaintainForm" property="statusCollection"/>
			</html:select>
			������ڣ�<html:text property="endDate_f" styleClass="Textfield" size="8" readonly="true" onclick="new Calendar().show(this);"/>
			���������<html:text property="day" styleClass="Textfield" size='4'/>
			<input	name="query" type="button" class="Button_Search"  onclick="doQuery()">
			</td>
		</tr>
	</table>

	<table width="98%" class="dtPanel_Line1" border="0" cellspacing="1"
		align="center" cellpadding="0">
		<tr align="left" class="dtPanel_Top01" height="28">
			<td nowrap>&nbsp;��Ŀ���</td>
			<td>&nbsp;��Ŀ����</td>
			<td nowrap>&nbsp;��Ŀ�׶�</td>
			<td>��Ŀ��������</td>
			<td>&nbsp;��ע</td>
			<td align="center">ѡ��</td>

		</tr>
		<%if (pageResult != null) {
	List list = pageResult.getList();
	if (list != null) {
		Iterator iter = list.iterator();
		while (iter.hasNext()) {
			ProjectMaintainVO vo = (ProjectMaintainVO) iter.next();%>
		<tr align="left" class="dtPanel_Main" onclick="_clickTr( this )">
			<td><a href="ProjectMaintain.do?act=qp&projectId=<%=vo.getProjectId()%>">&nbsp;<%=vo.getProjectId()%></a></td>
			<td>&nbsp;<%=vo.getProjectName()%></td>			
			<td>&nbsp;<%=SingleDicMap.getDicItemVal(SingleDic.PROJECT_MANAGE_COLLECTION,vo.getStatus())%> </td>	
			<td><%=vo.getEndDate()%></td>
			<td>&nbsp;<%=vo.getMemo()%></td>	
			<td align="center"><label><input type="radio" name="param"	onClick="setPKey('<%=vo.getProjectId()%>','<%=vo.getStatus()%>')">
			</label></td>
		</tr>

		<%}
}
}%>

</table>
	<%
//������ҳ��ע 
if (pageResult != null) {%>
	<table width="98%" align="center" border="0" cellspacing="0"
		cellpadding="0">
		<tr>
			<td class="dtPanel_Pager"><%=pageResult.getFooter()%></td>
		</tr>
	</table>
	<%}%>
	<br>

	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td height="25" align="center">
			<input type="button" value=������ class="Button" onclick="requireChange()"/>
			<input type="button" value="������Ŀ" class="Button" onClick="doAdd()"/>
			<input type="button" value="�޸���Ŀ" class="Button" onClick="doEdit()"/>
			<input type="button" value="ɾ����Ŀ" class="Button" onclick="doDelete()"/>
			<input type="button" value="�޸�ǰ����Ŀ" class="Button" onclick="doEditPreProject()"/>
			<input type="button" value="ά�������б�" class="Button" onclick="editProgram()">
			<input type="button" value="��ͻ���" class="Button" onclick="findConflick()">
			<input type="button" value="��Ŀ����" class="Button" onclick="doDistribute()"></td>
		</tr>
	</table>

</html:form>

<p>&nbsp;</p>
</body>
</html>


