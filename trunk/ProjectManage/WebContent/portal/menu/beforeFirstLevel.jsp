<%@ include file = "/includes/common.jsp" %>
<%@ page import="com.eis.portal.menu.MenuForm" %>
<%@ page contentType="text/html; charset=GBK" %>


<html>
<title>�鿴�˵���Ϣ</title>


<%
	MenuForm menuForm = (MenuForm)request.getAttribute("menuForm");
%>
<script language="javascript">

function doAdd(){
	//����
	document.forms[0].act.value='c';	
	document.forms[0].submit();
	
}



function turnPage( pagenm ) {  
	document.forms[0].act.value = "list"; 
    document.forms[0].pageNO.value = pagenm;    
    document.forms[0].submit();
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
			
			<html:form method="post" action="Menu.do">
	<input type=hidden name=menu_id value="00000000">
	<input type=hidden name=menu_name value="��">
	<input type=hidden name=menu_level value='0'>
	<input type=hidden name=act value="view">	

	
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
				class="Button" value="����һ���˵�" onClick="doAdd()"> &nbsp; 
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
