<%@ include file = "/includes/common.jsp" %>
<%@ page import="com.eis.portal.menu.MenuForm" %>
<%@ page contentType="text/html; charset=GBK" %>


<html>
<title>�鿴�˵���Ϣ</title>



<%
	MenuForm menuForm = (MenuForm)request.getAttribute("menuForm");
%>
<script language="javascript">

//ˢ�����˵��б�
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
	//����
	document.forms[0].act.value='c';	
	document.forms[0].submit();
	
}
function doEdit(){
	//�޸�	
	
	//�ύ��
	document.forms[0].act.value='u';	
	document.forms[0].submit();
}
function doDelete() {
	//ɾ��
	
	//����ȷ����ʾ
	if(!confirm('������ִ��ɾ���˵�����������ȷʵҪɾ���˵���')) {		
		return;
	}	
	
	//����Ƿ���ѡ�еļ�¼
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
			
<%=ViewUtil.getTitle("�˵���Ϣ")%>
 	
	<table align="center" width="98%"  class="dtPanel_Line1" border="0" cellspacing="1" cellpadding="0">
					
		<tr bgcolor="#FFFFFF">
			<td width="40%" align="right" class="dtPanel_Left">&nbsp;�˵���ţ�</td>
			<td align="left" class="dtPanel_Main">&nbsp; <%=menuForm.getMenu_id()%>
			</td>
		</tr>
		<tr bgcolor="#FFFFFF">
			<td  align="right" class="dtPanel_Left">&nbsp;�ϼ��˵���</td>
			<td align="left" class="dtPanel_Main">&nbsp; <%=menuForm.getParent_name()%>
			</td>
		</tr>
		<tr bgcolor="#FFFFFF">
			<td  align="right" class="dtPanel_Left">&nbsp;�˵����ƣ�</td>
			<td align="left" class="dtPanel_Main">&nbsp; <%=menuForm.getMenu_name()%>
			</td>
		</tr>
		
		<tr bgcolor="#FFFFFF">
			<td  align="right" class="dtPanel_Left">&nbsp;�˵����Σ�</td>
			<td align="left" class="dtPanel_Main">&nbsp; <%=menuForm.getMenu_level()%>
			</td>
		</tr>
		<tr bgcolor="#FFFFFF">
			<td  align="right" class="dtPanel_Left">&nbsp;��ʾ˳��</td>
			<td align="left" class="dtPanel_Main">&nbsp; <%=menuForm.getList_order()%>
			</td>
		</tr>		
		<tr bgcolor="#FFFFFF">
			<td  align="right" class="dtPanel_Left">&nbsp;�˵�URL��</td>
			<td align="left" class="dtPanel_Main">&nbsp; <%=menuForm.getMenu_url()%>
			</td>
		</tr>
	</table>
	
 	<table  class=heightspace_bottom1  border="0" cellspacing="0" cellpadding="0"><tr><td></td></tr></table>
	<table align="center" width="98%" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td height="25" align="center"   class="dtPanel_Bottom">
			<auth:button  name="add"  id="menu_c" value="���Ӳ˵�" onClick="doAdd()"/> &nbsp; 
			<auth:button name="edit" id="menu_u"  value="�޸Ĳ˵�" onClick="doEdit()"/> &nbsp;			
			<auth:button name="delete" id="menu_d"  value="ɾ���˵�" onClick="doDelete()"/> &nbsp;</td>
		</tr>
	</table>


</html:form>
			
			
			</TD>
		</TR>
	</TBODY>
</TABLE>


</body>
</html>
