<%@ include file = "/includes/common.jsp" %>
<%@ page  contentType="text/html; charset=GBK" %>

<jsp:useBean id="pageResult" scope="request" class="com.eis.base.PageObject" />

<html>

<%int maxPage = 1;
if (pageResult != null)
	maxPage = pageResult.getTotalPage();
%>
<head>
<title>��������Ŀ�б�</title>
<script language="javascript">  
function turnPage( pagenm ) {   
	document.forms[0].act.value = "gdp";  
	document.forms[0].pageNO.value = pagenm;     
	document.forms[0].submit(); 
} 
function doTrace(){
	if(document.forms[0].projectId.value == null ||document.forms[0].projectId.value == "") { 
		alert('����ѡ���¼'); 
		return; 
	} 	
	document.forms[0].act.value="dl";
	document.forms[0].submit();
	
}
function setPKey(projectId_var) { 
	document.forms[0].projectId.value=projectId_var; 
} 
</script>
</head>
<body>

<html:form method="post" action="ProjectDistribute.do">
<input type=hidden name=act value="gdp">
<html:hidden property="projectId"/>

<%=ViewUtil.getTitle("��������Ŀ�б�")%>
	
	<table class=heightspace_top3>
		<tr>
			<td></td>
		</tr>
	</table>

	<table width="98%" class="dtPanel_Line1" border="0" cellspacing="1"
		align="center" cellpadding="0">
		<tr align="center" class="dtPanel_Top01" height="28">
			<td nowrap>��Ŀ���</td>			
			<td nowrap>��ʼʱ��</td>
			<td nowrap>����ʱ��</td>
			<td nowrap>��Ŀ�׶�</td>		
			<td>ѡ��</td>
		</tr>
		<%if (pageResult != null) {
	List list = pageResult.getList();
	if (list != null) {
		Iterator iter = list.iterator();
		while (iter.hasNext()) {
			com.projectmaintain.ProjectDistributeVO vo = (com.projectmaintain.ProjectDistributeVO) iter.next();%>
		<tr align="center"  bgcolor="Beige" >
			<td><a href="ProjectMaintain.do?act=qp&projectId=<%=vo.getProjectId()%>"><%=vo.getProjectId()%></a></td>
			<td><%=vo.getStartDate()%></td>
			<td><%=vo.getEndDate()%></td>
			<td><%=SingleDicMap.getDicItemVal(SingleDic.PROJECT_MANAGE_COLLECTION,vo.getStatus())%> </td>
			<td><label><input type="radio" name="param"	onClick="setPKey('<%=vo.getProjectId()%>')">
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
			<input type="button" value="��Ŀ����" class="Button" onClick="doTrace()"/>		
		</tr>
	</table>
</html:form>

<p>&nbsp;</p>
</body>
</html>


