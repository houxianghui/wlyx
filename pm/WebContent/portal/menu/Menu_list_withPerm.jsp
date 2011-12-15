<%
response.setHeader("Cache-Control","no-cache");
response.setHeader("Pragma","no-cache");
response.setDateHeader("Expires",0);
%>


<%@ include file = "/includes/common.jsp" %>

<%@ page import="com.eis.portal.menu.MenuVO" %>
<%@ page import="com.eis.portal.menu.*" %>
<%@ page import="com.eis.base.PageObject" %>
<%@ page  contentType="text/html; charset=GBK" %>

<html>

<title>菜单列表</title>



<link rel="StyleSheet" href="CSS/DTree.css" type="text/css" />

<script src="js/dtree1.js" type="text/javascript" ></script>


<body  class="body_bg_grey1"  BGPROPERTIES="fixed" >


<html:form method="post" action="Menu.do">
<input type=hidden name=menu_id>
<input type=hidden name=act value="listWithPerm">
<input type=hidden name=requery >

<div class="dtree">
<p ><a href="javascript: d.openAll();">全部展开</a> | <a href="javascript: d.closeAll();">全部关闭</a></p>
</div>

<table  width="98%" align="center"  border="0" cellpadding="0" cellspacing="0">
<tr>
<td valign="top"  align="left">		
<!--div class="dtree"  style="overflow:auto;width:400%"-->
<div class="dtree"  style="overflow-x:hidden;overflow-y:hidden;width:260">
<script type='text/javascript' >
	d = new dTree('d');	
	d.add(0,-1, '功能菜单', '', '', '', 'images/menuImg/page.gif');
	
	//输出动态菜单内容
	<%
		String parent_id=null;
		String menu_name=null;
		String menu_id=null;
		String url=null;	
		
			
		
		List list=(List)request.getAttribute("menuListPerm");	
		if(list != null ) {
			Iterator iter = list.iterator();
			while (iter.hasNext()) {
				MenuVO vo = (MenuVO) iter.next();				
				parent_id = vo.getParent_id();
				menu_name = vo.getMenu_name();
				menu_id = vo.getMenu_id();
				url=(vo.getMenu_url()).trim();
				 
				
				if(menu_id == null || (menu_id!= null && menu_id.trim().equals(""))){
					out.println("d.add("+Integer.parseInt(menu_id)+","+Integer.parseInt(parent_id)+",'"+menu_name+"','');");
				}
				else{
					out.println("d.add("+Integer.parseInt(menu_id)+","+Integer.parseInt(parent_id)+",'"+menu_name+"','"+url+"','','main');");
					
				} 
			 }	
			
			}    
				
		%>		
		
		
	d.add(90000001,0, '修改密码', 'Password.do?act=u', '', 'main');
	
	//增加转换角色菜单
	<%
		String[] roles = user.getRole();
		if(roles.length>1) {
	%>
		d.add(90000002,0, '转换角色', 'LoginChangeRole.do?act=list', '', 'main');
	<%	
		}
	
	%>
	//d.add(90000004,0, '转换样式', 'changcss/chg_css.jsp', '', 'main');
	//d.add(90000005,0, '重新装载参数', 'SysInit', '', 'main');	 
	
	//d.add(90000008,0, '滚动条IE', 'rollbarIE.jsp', '', 'main');	 	 
	
	d.add(90000003,0, '注销', 'UserLogout.do', '', 'main','images/ICO_Exit.gif');
			
	d.config.useIcons=true;
	d.config.useLines=true;
	d.config.closeSameLevel=true;
	document.write(d);
			
</script>				

</div>

</td></tr>
</table>
	
		
</html:form>


</body>
</html>
