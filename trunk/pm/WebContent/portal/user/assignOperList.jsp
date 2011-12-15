<%@ include file = "/includes/common.jsp" %>
<%@ page import="com.eis.portal.user.*" %>
<%@ page import="com.eis.base.PageObject" %>
<%@ page import="com.eis.cache.*" %>
<%@ page  contentType="text/html; charset=GBK" %>
<jsp:useBean id="userForm" scope="request"
	class="com.eis.portal.user.UserForm" />


<html>

<title>ָ���û��б�������</title>


<%PageObject pageResult = (PageObject) request.getAttribute("pageResult");

%>

<script language="javascript">

function doQuery() {
	if(getStrLength(document.forms[0].user_name_f.value)>18) { 		
		alert('�������������벻Ҫ����18���ַ�!'); 
	    document.forms[0].user_name_f.focus(); 
	    return; 
	}
	document.forms[0].requery.value='y';
	document.forms[0].submit();
}

function setKey(id,name) {
    opener.document.forms[0].<%=userForm.getId_field()%>.value=id;
	opener.document.forms[0].<%=userForm.getName_field()%>.value=name;
	window.close();
}

function turnPage( pagenm ) {  
    document.forms[0].pageNO.value = pagenm;    
    document.forms[0].submit();
}
</script>
 
<body class="body_bg_grey1">


<html:form method="post" action="User.do">
<input type=hidden name=act value="assignoperlist">
<input type=hidden name=requery>
<input type=hidden name=currAmsdStore value="<%=userForm.getCurrAmsdStore()%>">
<html:hidden property="id_field"/>
<html:hidden property="name_field"/>

	<table width="98%" class=dtPanel_Line align="center" border="0"
		cellspacing="1" cellpadding="0">
		<tr>
			<td class=dtPanel_Top02>

			<table class=heightspace_top1 border="0" cellspacing="0"
				cellpadding="0">
				<tr>
					<td></td>
				</tr>
			</table>

			<table width="100%" cellpadding="1">
				<tr>
					<td class=BlueFont>&nbsp; ����������<html:text property="user_name_f"
						styleClass="Textfield" size="20" maxlength="18" />&nbsp;</td>
					<td align=right><input name="query" type="button"
						class="Button_Search" onClick="doQuery()"> &nbsp;</td>
					<td align=left><input type="button" name="check3" id="" class="Button" value="�ر�" onClick="window.close()"/> &nbsp;</td>
				</tr>
			</table>
			<table class=heightspace_top2 border="0" cellspacing="0"
				cellpadding="0">
				<tr>
					<td></td>
				</tr>
			</table>

			</td>
		</tr>
	</table>


	<table class=heightspace_top3>
		<tr>
			<td></td>
		</tr>
	</table>

	<!--�б�-->
	<table width="98%" align="center" class="dtPanel_Line1" border="0"
		cellpadding="0" cellspacing="1">
		<tr align="center" class="dtPanel_Top01" height="28">
			<td width="9%">�û���</td>
			<td width="9%">��������</td>
			<td width="24%">��ϵ�绰</td>
			<td width="12%">�ֻ�</td>
			<td width="11%">��ʼ����</td>
			<td width="11%">ʧЧ����</td>
			<td width="11%">��������</td>
			<td width="6%">ѡ��</td>
		</tr>
		<%List list = pageResult.getList();
if (list != null) {
	Iterator iter = list.iterator();
	while (iter.hasNext()) {
		UserVO vo = (UserVO) iter.next();%>
		<tr align="center" class="dtPanel_Main" onclick="_clickTr( this )">
			<td><%=vo.getLogin_id()%></td>
			<td><%=vo.getUser_name()%></td>
			<td><%=vo.getPhone()%></td>
			<td><%=vo.getMobile()%></td>
			<td><%=vo.getBegin_dt()%></td>
			<td><%=vo.getInvalid_dt()%></td>
			<td><%=vo.getReg_dt()%></td>

			<td><label><input type="radio" name="user"
				onClick="setKey('<%=vo.getUser_id()%>','<%=vo.getUser_name()%>')"> </label></td>
		</tr>

		<%}
}%>
	</table>

	<!--ҳ���ҳ��-->
	<table width="98%" align="center" border="0" cellspacing="0"
		cellpadding="0">
		<tr>
			<td class="dtPanel_Pager"><%=pageResult.getFooter()%></td>
		</tr>
	</table>
</html:form>
</body>
</html>
