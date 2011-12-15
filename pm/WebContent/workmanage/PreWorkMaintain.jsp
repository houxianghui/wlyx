
<%@ include file = "/includes/common.jsp" %>
<%@ page  contentType="text/html; charset=GBK" %>

<%@ page import="com.eis.base.PageObject" %>
<jsp:useBean id="workListForm" scope="request" class="com.huateng.blue.work.WorkListForm" />
<head>
<title>修改前驱任务</title>


<script language="javascript"> 
 
function doSave(){ 	
	var rolesRight=document.forms[0].rolesRight;
	for(var i=0;i<rolesRight.length;i++){
		rolesRight.options[i].selected=true;
	}
	document.forms[0].submit();
	
}
 
</script>
</head>
<body class="body_bg_grey1">
<html:form method="post" action="WorkList.do">
	<html:hidden property="workId" />
	<input type=hidden name=act value=upp>


	<%=ViewUtil.getTitle("当前任务" + workListForm.getWorkId())%>

	<table width="98%" align=center class="dtPanel_Left" border="0"
		cellspacing="1" cellpadding="0">
		<tr align="center">
			<td align="center">未结束任务</td>
			<td></td>
			<td>前驱任务</td>
		</tr>
		<tr align="center">
			<td><select name="rolesLeft" id="rolesLeftId" size="10" multiple
				class="Select-Multiple" style="width: 150">
				<%
//从action层获得用户可以选择但未选择的角色权限集合
List notSelected = (ArrayList) request.getAttribute("notSelectedProjects");

//如果rolesNotSelected不为空,遍历列表中对象，取对象各值,
//然后以列表框形式显示
if (notSelected != null) {
	Iterator iter = notSelected.iterator();

	while (iter.hasNext()) {
		String id = (String) iter.next();
		String str = "<option ";
		str = str + " value='" + id + "'>";
		str = str + id;
		str = str + "</option>";
		out.println(str);
	}

}%>
			</select></td>
			<td align="center"><input type='button' class='Button'
				onClick='moveSelected(document.forms[0].rolesLeft,document.forms[0].rolesRight)'
				value=" 增加 > "> <br>
			<br>
			<input type='button' class='Button'
				onClick='moveSelected(document.forms[0].rolesRight,document.forms[0].rolesLeft)'
				value=' < 删除 '> <br>
			<br>
			<input type='button' class='Button'
				onClick='moveAll(document.forms[0].rolesLeft,document.forms[0].rolesRight)'
				value='全增加>>'> <br>
			<br>
			<input type='button' class='Button'
				onClick='moveAll(document.forms[0].rolesRight,document.forms[0].rolesLeft)'
				value='<<全删除'></td>

			<td><select name="rolesRight" id="rolesRightId" size="10" multiple
				class="Select-Multiple" style="width: 150">
				<%
//从action层获得用户可以选择但未选择的角色权限集合
List selected = (ArrayList) request.getAttribute("selectedProjects");

//如果rolesNotSelected不为空,遍历列表中对象，取对象各值,
//然后以列表框形式显示
if (selected != null) {
	Iterator iter = selected.iterator();

	while (iter.hasNext()) {
		String id = (String) iter.next();
		String str = "<option ";
		str = str + " value='" + id + "'>";
		str = str + id;
		str = str + "</option>";
		out.println(str);
	}

}%>
			</select></td>
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
			<td height="25" align="center" class="dtPanel_Bottom"><input
				name="update" type="button" class="Button" value="保存"
				onClick="doSave()"> &nbsp; <input name="return" type="button"
				class="Button" value="返回" onClick="history.back()"></td>
		</tr>
	</table>

</html:form>

</body>
</html>
<SCRIPT LANGUAGE="JavaScript" src="js/move.js">
<!--
//-->
</SCRIPT>