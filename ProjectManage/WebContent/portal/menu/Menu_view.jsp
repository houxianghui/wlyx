<%@ include file = "/includes/common.jsp" %>
<%@ page import="com.eis.portal.menu.MenuForm" %>
<%@ page contentType="text/html; charset=GBK" %>


<html>
<title>查看菜单信息</title>



<%
	MenuForm menuForm = (MenuForm)request.getAttribute("menuForm");
%>
<script language="javascript">

//刷新左侧菜单列表
function reloadLeftMenu(){
		parent.leftFrame.location.reload();
}

<%
	String refresh = (String)session.getAttribute("refresh");
	if(null != refresh && refresh.equals("y"))	{		
		%>		
		reloadLeftMenu();		
		<%
	}
	session.removeAttribute("refresh");
	
%>

function doAdd(){
	//增加
	document.forms[0].act.value='c';	
	document.forms[0].submit();
	
}
function doEdit(){
	//修改	
	
	//提交表单
	document.forms[0].act.value='u';	
	document.forms[0].submit();
}
function doDelete() {
	//删除
	
	//进行确认提示
	if(!confirm('请慎重执行删除菜单操作，请问确实要删除菜单吗？')) {		
		return;
	}	
	
	//检查是否有选中的纪录
	document.forms[0].act.value='d';
	document.forms[0].submit();
}


</script>
<body  class="body_bg_grey1">

<p>&nbsp;</p>

<TABLE  width="99%" border="0" cellpadding="0" cellspacing="0">
	<TBODY>
		<tr>							
			<TD >
			
			
		<html:form method="post" action="Menu.do">
			<input type=hidden name=menu_id value=<%=menuForm.getMenu_id()%>>
			<input type=hidden name=menu_name value=<%=menuForm.getMenu_name()%>>
			<input type=hidden name=menu_level value=<%=menuForm.getMenu_level()%>>
			<input type=hidden name=act value="view">
			
<%=ViewUtil.getTitle("菜单信息")%>
 	
	<table align="center" width="98%"  class="dtPanel_Line1" border="0" cellspacing="1" cellpadding="0">
					
		<tr bgcolor="#FFFFFF">
			<td width="40%" align="right" class="dtPanel_Left">&nbsp;菜单编号：</td>
			<td align="left" class="dtPanel_Main">&nbsp; <%=menuForm.getMenu_id()%>
			</td>
		</tr>
		<tr bgcolor="#FFFFFF">
			<td  align="right" class="dtPanel_Left">&nbsp;上级菜单：</td>
			<td align="left" class="dtPanel_Main">&nbsp; <%=menuForm.getParent_name()%>
			</td>
		</tr>
		<tr bgcolor="#FFFFFF">
			<td  align="right" class="dtPanel_Left">&nbsp;菜单名称：</td>
			<td align="left" class="dtPanel_Main">&nbsp; <%=menuForm.getMenu_name()%>
			</td>
		</tr>
		
		<tr bgcolor="#FFFFFF">
			<td  align="right" class="dtPanel_Left">&nbsp;菜单级次：</td>
			<td align="left" class="dtPanel_Main">&nbsp; <%=menuForm.getMenu_level()%>
			</td>
		</tr>
		<tr bgcolor="#FFFFFF">
			<td  align="right" class="dtPanel_Left">&nbsp;显示顺序：</td>
			<td align="left" class="dtPanel_Main">&nbsp; <%=menuForm.getList_order()%>
			</td>
		</tr>		
		<tr bgcolor="#FFFFFF">
			<td  align="right" class="dtPanel_Left">&nbsp;菜单URL：</td>
			<td align="left" class="dtPanel_Main">&nbsp; <%=menuForm.getMenu_url()%>
			</td>
		</tr>
	</table>
	
 	<table  class=heightspace_bottom1  border="0" cellspacing="0" cellpadding="0"><tr><td></td></tr></table>
	<table align="center" width="98%" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td height="25" align="center"   class="dtPanel_Bottom">
			<auth:button  name="add"  id="menu_c" value="增加菜单" onClick="doAdd()"/> &nbsp; 
			<auth:button name="edit" id="menu_u"  value="修改菜单" onClick="doEdit()"/> &nbsp;			
			<auth:button name="delete" id="menu_d"  value="删除菜单" onClick="doDelete()"/> &nbsp;</td>
		</tr>
	</table>


</html:form>
			
			
			</TD>
		</TR>
	</TBODY>
</TABLE>


</body>
</html>
