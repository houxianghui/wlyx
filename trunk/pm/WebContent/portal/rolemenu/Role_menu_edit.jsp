
<%@ page  contentType="text/html; charset=GBK" %>

<%@ include file = "/includes/common.jsp" %>

<%@ page import="com.eis.portal.rolemenu.RoleMenuVO" %>
<%@ page import="com.eis.portal.rolemenu.*" %>
<%@ page import="com.eis.base.PageObject" %>
<%@ page import="com.eis.portal.rolemenu.*" %>
<jsp:useBean id="rolemenuForm" scope="request" class="com.eis.portal.rolemenu.RoleMenuForm" /> 


<html>

<title>角色菜单权限管理</title>




<%
	//获取角色代码		
	String role_id=request.getParameter("role_id");
	//将角色代码存放到session中
	session.setAttribute("securityRoleId",role_id);	
	//从action层获得表示角色菜单权限的列表
	List selectmenulist=(ArrayList)request.getAttribute("selectmenuList");
	
%>
<script language="javascript">

function doSave(){
	//执行保存修改
	
	document.forms[0].submit();  //最好给form起个自己的名字
	}


</script>


<body  class="body_bg_grey1">

<html:form   method="post" action="RoleMenu.do">
<input type=hidden name=role_id value=<%=role_id%>>
<input type=hidden name=act value="u">
<input type=hidden name=step value="commit">

<table width="90%" border=0 align="center" cellpadding=0 align=center cellspacing=0 id='dtl_head' name='dtl_head'>
	  <tr>
      <td height=30>&nbsp;角色名称：<%=rolemenuForm.getRole_name()%></td>
    </tr>
  </table>
  
 
          <table width="92%" height="26" border="0" cellpadding="0" cellspacing="0">
  			<tr>
    			<td   align="center" class=button1 ><a class=tab_white href="RoleMenu.do?act=u&role_id=<%=role_id%>" >角色菜单权限</font></td>
    			<td  align="center"  class=button2 ><a href="RoleOP.do?act=u&role_id=<%=role_id%>">角色操作权限</a></td>
				<td   align="left" >&nbsp;</td>
  			</tr>
		</table> 


<TABLE width="98%" border=0 cellPadding=0 cellSpacing=1 bgcolor="#4169E1">
  <TBODY>
    <TR > 
      <TD  class=background_c1 >
      
      
      <table width="100%" border="0" cellspacing="1" cellpadding="0">
    	<tr class="dtPanel_Top02"> 
      		<td colspan="4" align="center">&nbsp;</td>      
    	</tr>     		
			<%
				//取到由action的list方法返回的list
				List list = selectmenulist;
				
				//如果list不为空,遍历列表中对象，取对象的各值,
				//将其中各对象以带复选框的层级菜单形式显示
				if(list != null ) {
					Iterator iter = list.iterator();
					int menu_level = 0;		//菜单级别
					String menu_id = null;		//菜单编码
					String menu_name = null;	//菜单名称				
					int check = 0;			//复选框是否勾选
					int check_display=0;	//复选框是否显示
					
					while (iter.hasNext()) {
						SelectMenuVO vo = (SelectMenuVO) iter.next();
						menu_level = vo.getMenu_level();
						menu_id=vo.getMenu_id();
						menu_name=vo.getMenu_name();
						check=vo.getCheck();
						check_display=vo.getCheck_display();
						
			%>
					<tr align="left"  class="dtPanel_Main" height=22><td>
					<%
					//根据菜单级别在左端显示空格
					for(int i=1;i<=menu_level;i++){out.print("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");}
					//如果显示复选框，则显示复选框+菜单名称
					 if(check_display!=0){
					 	String str="";	
					 	str=str+"<img src='images/DTreeIMG/page.gif'>";				 	
					 	str=str+"<input type='checkbox' ";
					 	str=str+"  name='selectmenu'" ;		//设置复选框name为selectmenu
					 	str=str+" value='"+menu_id+"' ";	//设置复选框value为菜单编号menu_id
					 	if(check==1){str=str+"  checked ";}	//若check=1，勾选复选框
					 	str=str+" > ";
					 	str=str+menu_name;				//菜单名称
					 	out.println(str) ;
					 }
					else{
					//如果不显示复选框，则只显示菜单名称	
						out.println("<img src='images/DTreeIMG/folderopen.gif'>");				
						out.println(menu_name);
					}					
					%>
						<td></tr>

				<%
						}
					}
				%>
				</table>
      
      
      
	</TD>
    </TR>
  </TBODY>
</TABLE>

			  	<table  class=heightspace_bottom1  border="0" cellspacing="0" cellpadding="0"><tr><td></td></tr></table>
			  					
				<table width="98%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td height="25" align="center"  class="dtPanel_Bottom">
						<input name="save" type="button" class="Button" value="保存" onClick="doSave()">&nbsp;
						<html:reset value="重置" styleClass="Button"/>&nbsp; 
						<input name="rt" type="button" class="Button" value="返回" onClick="location.href='Role.do?act=list';"> 
											
						</td>
					</tr>
				</table>				
	
		
</html:form>


</body>
</html>

