<%@ include file = "/includes/common.jsp" %>
<%@ page  contentType="text/html; charset=GBK" %>
<jsp:useBean id="requireChangeForm" scope="request" class="com.projectmaintain.RequireChangeForm" />
<jsp:useBean id="pageResult" scope="request" class="com.eis.base.PageObject" />

<html>

<%int maxPage = 1;
if (pageResult != null)
	maxPage = pageResult.getTotalPage();
%>
<head>
<title>������ά��</title>
<%
	String projectId = (String)request.getAttribute("projectId");
	String version = ((Integer)request.getAttribute("version")).toString();
%>
<script language="javascript"> 


function doAdd(){ 
	//���� 
	window.location.href="RequireChange.do?act=new&projectId=<%=projectId%>&version=<%=version%>"; 
} 
 
function doEdit(){ 
	//�޸� 
	//����Ƿ���ѡ�еļ�¼ 
	if(document.forms[0].projectId.value == null ||document.forms[0].projectId.value == "") { 
		alert('����ѡ���¼'); 
		return; 
	} 
	//�ύ�� 
	document.forms[0].act.value='edit';	 
	document.forms[0].submit(); 
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
	document.forms[0].act.value='delete'; 
	document.forms[0].submit(); 
} 
 
function doQuery() {  
	document.forms[0].act.value = "list"; 
	document.forms[0].submit(); 
} 
 
function setPKey(projectId_var,version_var) { 
	document.forms[0].projectId.value=projectId_var;
	document.forms[0].version.value=version_var;
} 
 
function turnPage( pagenm ) {   
	document.forms[0].act.value = "list";  
	document.forms[0].pageNO.value = pagenm;     
	document.forms[0].submit(); 
} 

</script>
</head>
<body>

<html:form method="post" action="RequireChange.do">
<input type=hidden name=act value="list">
<html:hidden property="version" value="<%=version%>"/>
<html:hidden property="projectId" value="<%=projectId%>"/>

<%=ViewUtil.getTitle("������ά��")%>
	
	<table class=heightspace_top3>
		<tr>
			<td></td>
		</tr>
	</table>

	<table width="98%" class="dtPanel_Line1" border="0" cellspacing="1"
		align="center" cellpadding="0">
		<tr align="center" class="dtPanel_Top01">
			<td nowrap>��Ŀ���</td>
			<td>�汾</td>
			<td>�������</td>
			<td>���ԭ��</td>
			<td>�������</td>
			<td>ѡ��</td>
		</tr>
		<%if (pageResult != null) {
	List list = pageResult.getList();
	if (list != null) {
		Iterator iter = list.iterator();
		while (iter.hasNext()) {
			com.projectmaintain.RequireChangeVO vo = (com.projectmaintain.RequireChangeVO) iter.next();%>
		
		<tr align="center" class="dtPanel_Main" onclick="_clickTr( this )">
			<td><%=vo.getProjectId()%></td>
			<td><%=vo.getVersion()%></td>
			<td><%=vo.getContent()%> </td>	
			<td><%=vo.getReason()%></td>
			<td><%=vo.getChangeDate()%></td>		
			<td><label><input type="radio" name="param"	onClick="setPKey('<%=vo.getProjectId()%>','<%=vo.getVersion()%>')">
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
			<input type="button" value="����" class="Button" onClick="doAdd()"/>
			<input type="button" value="�޸�" class="Button" onClick="doEdit()"/>
			<input type="button" value="ɾ��" class="Button" onclick="doDelete()"/>				
			<input type="button" value="����" class="Button" onclick="history.back()"/>	
			</td>		
		</tr>
	</table>

</html:form>

<p>&nbsp;</p>
</body>
</html>


