<%
response.setHeader("Expires","0");
response.setHeader("Pragma","nono-cache");
response.setHeader("Catch-Control","no-cache");
%>
<%@ include file = "/includes/common.jsp" %>

<%@ page import="com.eis.portal.menu.MenuVO" %>
<%@ page import="com.eis.portal.op.*" %>
<%@ page import="com.eis.portal.menu.*" %>
<%@ page import="com.eis.base.PageObject" %>
<%@ page  contentType="text/html; charset=GBK" %>



<html>

<title>菜单列表</title>


<link rel="StyleSheet" href="CSS/DTree.css" type="text/css" />

<script src="js/dtree1.js" type="text/javascript" ></script>


<%
	//PageObject pageResult = (PageObject) request.getAttribute("pageResult");
	
	//int maxPage = 1;
	//if(pageResult != null)
	//	maxPage = pageResult.getTotalPage();
		
	List menuList=(List)request.getAttribute("opmenuList");
	
%>
<script language="javascript">

function doAdd(){
	//增加
	window.location="Menu.do?act=c";
	
	
}


function setKey(mid) {
	
	document.forms[0].menu_id.value=mid;	
	

}

function view(mid) {

	document.forms[0].act.value='r';	
	document.forms[0].menu_id.value=mid;
	document.forms[0].submit();	

}



function turnPage( pagenm ) {  
	document.forms[0].act.value = "list"; 
    document.forms[0].pageNO.value = pagenm;    
    document.forms[0].submit();
}






</script>

<body>


<html:form method="post" action="OP.do">
<input type=hidden name=op_code>
<input type=hidden name=act value="listmenu">
<input type=hidden name=requery >
<html:hidden property="menu_id"/>
<html:hidden property="menu_name"/>

<div class="dtree">
<p><a href="javascript: d.openAll();">全部展开</a> | <a href="javascript: d.closeAll();">全部关闭</a></p>
</div>
		
	
					
	<%
		String parent_id=null;
		String menu_name=null;
		String menu_id=null;
		String url=null;
		
		String str="<script type='text/javascript' >";
		str=str+"d = new dTree('d');";		
		str=str+"d.add(0,-1, '菜单', '', '', 'rightFrame', 'images/menuImg/page.gif');";
			
		List list=menuList;
		if(list != null ) {
			Iterator iter = list.iterator();
			while (iter.hasNext()) {
				MenuVO vo = (MenuVO) iter.next();				
				parent_id = vo.getParent_id();
				menu_name = vo.getMenu_name();
				menu_id = vo.getMenu_id();
				url="OP.do?act=list&menu_id="+menu_id+"&menu_name="+menu_name;
				 
				
				if(menu_id == null || (menu_id!= null && menu_id.trim().equals(""))){
					str=str+"d.add("+Integer.parseInt(menu_id)+","+Integer.parseInt(parent_id)+",'"+menu_name+"','');"+"\n";
				}
				else{
					str=str+"d.add("+Integer.parseInt(menu_id)+","+Integer.parseInt(parent_id)+",'"+menu_name+"','"+url+"','','rightFrame');"+"\n";
				} 
			 }
			 
			 str=str+"d.config.useIcons=true;";
			 str=str+"d.config.useLines=true;";
			 str=str+"d.config.closeSameLevel=true;";
			 str=str+"document.write(d);";			 
			 str=str+"</script>";
			 out.println(str);	
			}    
						
		%>				
				
		
		
</html:form>

<p>&nbsp;</p>
</body>
</html>
