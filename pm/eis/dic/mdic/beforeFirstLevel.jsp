<%@ include file = "/includes/common.jsp" %>
<%@ page import="com.eis.portal.menu.MenuForm" %>
<%@ page contentType="text/html; charset=GBK" %>


<html>
<title></title>

<script language="javascript">

function doAdd(){
	//增加
	//document.forms[0].act.value='c';	
	//document.forms[0].submit();
	window.location="MDic.do?act=c&type_id="+document.forms[0].type_id.value+"&parent_id="+document.forms[0].parent_id.value+"&item_level=1";
}
</script>
<body>

<br>
<TABLE  width="99%" border="0" cellpadding="0" cellspacing="0">
	<TBODY>
		<TR>
			<TD width="5">					
			</TD>	
			<TD width="1" bgcolor="#aacccc">					
			</TD>			
			<TD >
			
			<html:form method="post" action="MDic.do">
	<input type=hidden name=type_id value="<%=request.getParameter("type_id")%>">
	<input type=hidden name=parent_id value="<%=request.getParameter("parent_id")%>">
	<input type=hidden name=item_level value="1">


	
	<table align="center" width="98%" border="0" cellspacing="1"
		cellpadding="0">
		<tr height="20">
			<td>
			<center>
			<br><br>
			<P> </P>
			<br><br>
			</center>
			</td>
		</tr>
		<tr>
			<td height="25" align="center">
			<input name="add" type="button" 
				class="Button" value="增加一级字典" onClick="doAdd()"> &nbsp; 
				</td>
		</tr>
	</table>
</html:form>
			
			
			</TD>
		</TR>
	</TBODY>
</TABLE>


</body>
</html>
