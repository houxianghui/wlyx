<%@ include file = "/includes/common.jsp" %>
<%@ page  contentType="text/html; charset=GBK" %>
<jsp:useBean id="issueRecordForm" scope="request" class="com.projectmaintain.IssueRecordForm" />
<jsp:useBean id="pageResult" scope="request" class="com.eis.base.PageObject" />

<html>

<%int maxPage = 1;
if (pageResult != null)
	maxPage = pageResult.getTotalPage();
%>
<head>
<title>项目维护</title>
<%
	String projectId = (String)request.getAttribute("projectId");
	String status = (String)request.getAttribute("status");
	String userId = (String)request.getAttribute("userId");
%>
<script language="javascript"> 


function doAdd(){ 
	//增加 
	window.location.href="IssueRecord.do?act=new&projectId=<%=projectId%>&status=<%=status%>&userId=<%=userId%>"; 
} 
 
function doEdit(){ 
	//修改 
	//检查是否有选中的纪录 
	if(document.forms[0].id.value == null ||document.forms[0].id.value == "") { 
		alert('请先选择纪录'); 
		return; 
	} 
	//提交表单 
	document.forms[0].act.value='edit';	 
	document.forms[0].submit(); 
} 

function doDelete() { 
	//删除 
 
	//检查是否有选中的纪录 
	if(document.forms[0].id.value == null ||document.forms[0].id.value == "") { 
		alert('请先选择纪录'); 
		return; 
	} 
 
	//进行确认提示 
	if(!confirm('您确认执行删除操作吗？')) { 
		return; 
	} 
	document.forms[0].act.value='delete'; 
	document.forms[0].submit(); 
} 
 
function doQuery() {  
	document.forms[0].act.value = "list"; 
	document.forms[0].submit(); 
} 
 
function setPKey(id_var) { 
	document.forms[0].id.value=id_var;
} 
 
function turnPage( pagenm ) {   
	document.forms[0].act.value = "list";  
	document.forms[0].pageNO.value = pagenm;     
	document.forms[0].submit(); 
} 

</script>
</head>
<body>

<html:form method="post" action="IssueRecord.do">
<input type=hidden name=act value="list">
<html:hidden property="userId" value="<%=userId%>"/>
<html:hidden property="projectId" value="<%=projectId%>"/>
<html:hidden property="status" value="<%=status%>"/>
<input type=hidden name=id>

<%=ViewUtil.getTitle("项目分配")%>
	
	<table class=heightspace_top3>
		<tr>
			<td></td>
		</tr>
	</table>

	<table width="98%" class="dtPanel_Line1" border="0" cellspacing="1"
		align="center" cellpadding="0">
		<tr align="center" class="dtPanel_Top01">
			<td nowrap>项目编号</td>
			<td>人员</td>
			<td>项目阶段</td>
			<td>问题</td>
			<td>选择</td>

		</tr>
		<%if (pageResult != null) {
	List list = pageResult.getList();
	if (list != null) {
		Iterator iter = list.iterator();
		while (iter.hasNext()) {
			com.projectmaintain.IssueRecordVO vo = (com.projectmaintain.IssueRecordVO) iter.next();%>
		
		<tr align="center" class="dtPanel_Main" onclick="_clickTr( this )">
			<td><%=vo.getProjectId()%></td>
			<td><%=ReDefSDicMap.getDicItemVal("0003",vo.getUserId())%></td>
			<td><%=SingleDicMap.getDicItemVal(SingleDic.PROJECT_MANAGE_COLLECTION,vo.getStatus())%> </td>	
			<td><%=vo.getMemo()%></td>	
			<td><label><input type="radio" name="param"	onClick="setPKey('<%=vo.getId()%>')">
			</label></td>
		</tr>

		<%}
}
}%>

</table>
	<%

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
			<input type="button" value="增加" class="Button" onClick="doAdd()"/>
			<input type="button" value="修改" class="Button" onClick="doEdit()"/>
			<input type="button" value="删除" class="Button" onclick="doDelete()"/>				
			<input type="button" value="返回" class="Button" onclick="history.back()"/>	
			</td>		
		</tr>
	</table>

</html:form>

<p>&nbsp;</p>
</body>
</html>


