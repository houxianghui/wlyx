
<%@ include file = "/includes/common.jsp" %>
<%@ page  contentType="text/html; charset=GBK" %>

<%@ page import="com.eis.base.PageObject" %>
<jsp:useBean id="projectMaintainForm" scope="request"class="com.projectmaintain.ProjectMaintainForm" />
<head>
<title>ǰ����Ŀ�б�</title>

</head>
<body class="body_bg_grey1">
<html:form method="post" action="ProjectMaintain.do">

	<%=ViewUtil.getTitle( projectMaintainForm.getProjectId()+"ǰ����Ŀ�б�")%>

	<table width="98%" align=center class="dtPanel_Left" border="0"
		cellspacing="1" cellpadding="0">
		<tr align="center"><td>			
				<%
//��action�����û�����ѡ��δѡ��Ľ�ɫȨ�޼���
List selected = (ArrayList) request.getAttribute("selectedProjects");

//���rolesNotSelected��Ϊ��,�����б��ж���ȡ�����ֵ,
//Ȼ�����б����ʽ��ʾ
if (selected != null) {
	Iterator iter = selected.iterator();

	while (iter.hasNext()) {
		String id = (String) iter.next();
		
		out.println(id+"<br>");
	}

}%>
		</td>
		</tr>
	</table>

	<table class=heightspace_bottom1 border="0" cellspacing="0"
		cellpadding="0">
		<tr>
			<td></td>
		</tr>
	</table>
	<table align=center width="98%" border="0" cellspacing="0"
		cellpadding="0">
		<tr>
			<td height="25" align="center" class="dtPanel_Bottom">
			 <input name="return" type="button"	class="Button" value="����" onClick="history.back()"></td>
		</tr>
	</table>

</html:form>

</body>
</html>
