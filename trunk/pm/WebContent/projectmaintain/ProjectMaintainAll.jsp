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
<title>项目维护</title>
<script language="javascript"> 
 
 
function doQuery() {  
	document.forms[0].act.value = "qal"; 
	document.forms[0].submit(); 
} 
 
function setPKey(projectId_var,status_var) { 
	document.forms[0].projectId.value=projectId_var; 
	document.forms[0].status.value=status_var;
} 
function doDistribute(){ 
	//修改 
	//检查是否有选中的纪录 
	if(document.forms[0].projectId.value == null ||document.forms[0].projectId.value == "") { 
		alert('请先选择纪录'); 
		return; 
	} 
	//提交表单 
	window.location.href="ProjectDistribute.do?act=dl&&projectId="+document.forms[0].projectId.value+"&status="+document.forms[0].status.value;
}  
function turnPage( pagenm ) {   
    	document.forms[0].act.value = "qal";  
    	document.forms[0].pageNO.value = pagenm;     
    	document.forms[0].submit(); 
} 
 
</script>
</head>
<body>

<html:form method="post" action="ProjectMaintain.do">
<input type=hidden name=act value="qal">
<html:hidden property="projectId"/>
<html:hidden property="status"/>
<%=ViewUtil.getTitle("项目维护")%>
	
	<table class=heightspace_top3 width="98%" border="0" cellspacing="1"
		align="center" cellpadding="0">
		<tr>
			<td>
			<bean:message key="projectMaintainForm.projectId.display" bundle="projectMaintain"/>：
			<html:text property="projectId_f" styleClass="Textfield" size="4"></html:text>
			<bean:message key="projectMaintainForm.status.display" bundle="projectMaintain"/>：
			<html:select property="status_f" styleClass="Select">
				<html:optionsCollection name="projectMaintainForm" property="statusCollection"/>
			</html:select>
			<input	name="query" type="button" class="Button_Search"  onClick="doQuery()">
			</td>
		</tr>
	</table>

	<table width="98%" class="dtPanel_Line1" border="0" cellspacing="1"
		align="center" cellpadding="0">
		<tr align="center" class="dtPanel_Top01" height="28">
			<td nowrap>项目编号</td>
			<td>项目名称</td>
			<td nowrap>项目阶段</td>
			<td>备注</td>
			<td>选择</td>
		</tr>
		<%if (pageResult != null) {
	List list = pageResult.getList();
	if (list != null) {
		Iterator iter = list.iterator();
		while (iter.hasNext()) {
			ProjectMaintainVO vo = (ProjectMaintainVO) iter.next();%>
		<tr align="center" class="dtPanel_Main" onclick="_clickTr( this )">
			<td><a href="ProjectMaintain.do?act=qp&projectId=<%=vo.getProjectId()%>"><%=vo.getProjectId()%></a></td>
			<td><%=vo.getProjectName()%></td>		
			<td><%=SingleDicMap.getDicItemVal(SingleDic.PROJECT_MANAGE_COLLECTION,vo.getStatus())%> </td>	
			<td><%=vo.getMemo()%></td>		
			<td align="center"><label><input type="radio" name="param"	onClick="setPKey('<%=vo.getProjectId()%>','<%=vo.getStatus()%>')">
		</tr>

		<%}
}
}%>

</table>
	
	<%
//产生翻页脚注 
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
			
			<input type="button" value="项目分配" class="Button" onclick="doDistribute()"></td>
		</tr>
	</table>
</html:form>

<p>&nbsp;</p>
</body>
</html>


