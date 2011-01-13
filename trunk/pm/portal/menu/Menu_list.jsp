
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

<title>�˵��б�</title>


<link rel="StyleSheet" href="CSS/DTree.css" type="text/css" />

<script src="js/dtree1.js" type="text/javascript" ></script>


<%
		
	List menuList=(List)request.getAttribute("menuList");
	
%>
<script language="javascript">

function doAdd(){
	//����
	window.location="Menu.do?act=c";
	
	
}
function doEdit(){
	//�޸�
	
	//����Ƿ���ѡ�еļ�¼
	if(document.forms[0].menu_id.value == null ||document.forms[0].menu_id.value == "") {
		alert('����ѡ���¼');
		return;
	}
	
	//�ύ��
	document.forms[0].act.value='u';	
	document.forms[0].submit();
}
function doDelete() {
	//ɾ��
	
	//����Ƿ���ѡ�еļ�¼
	if(document.forms[0].menu_id.value == null ||document.forms[0].menu_id.value == "") {
		alert('����ѡ���¼');
		return;
	}
	
	//����ȷ����ʾ
	if(!confirm('��ȷ��ִ��ɾ��������')) {		
		return;
	}
	
	document.forms[0].act.value='d';
	document.forms[0].submit();
}

function doQuery() {
	//ɾ��
	
	//��������ѯ����
	

	document.forms[0].act.value = "list";
	document.forms[0].requery.value='y';
	document.forms[0].submit();
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

<body class=body_bg_white>


<html:form method="post" action="Menu.do">
<input type=hidden name=menu_id>
<input type=hidden name=act value="list">
<input type=hidden name=requery >

<div class="dtree">
<p><a href="javascript: d.openAll();">ȫ��չ��</a> | <a href="javascript: d.closeAll();">ȫ���ر�</a></p>
</div>
		
	
					
	<%
		String parent_id=null;
		String menu_name=null;
		String menu_id=null;
		String url=null;
		
		String str="<script type='text/javascript' >";
		str=str+"d = new dTree('d');";
		url="Menu.do?act=bfl";
		str=str+"d.add(0,-1, '�˵�', '"+url+"', '', 'rightFrame', 'images/menuImg/page.gif');";		
		
		List list=menuList;
		if(list != null ) {
			Iterator iter = list.iterator();
			while (iter.hasNext()) {
				MenuVO vo = (MenuVO) iter.next();				
				parent_id = vo.getParent_id();
				menu_name = vo.getMenu_name();
				menu_id = vo.getMenu_id();
				url="Menu.do?act=lr&menu_id="+menu_id;
				 
				
				if(menu_id == null || (menu_id!= null && menu_id.trim().equals(""))){
					str=str+"d.add("+Integer.parseInt(menu_id)+","+Integer.parseInt(parent_id)+",'"+menu_name+"','');\n";
				}
				else{
					str=str+"d.add("+Integer.parseInt(menu_id)+","+Integer.parseInt(parent_id)+",'"+menu_name+"','"+url+"','','rightFrame');\n";
				} 
			 }
			 
			 str=str+"d.config.useIcons=true;";
			 str=str+"d.config.useLines=true;";
			 str=str+"d.config.closeSameLevel=true;";
			 str=str+"document.write(d);";			
			 str=str+"</script>";
			 out.println(str);	
			}    
		else{
		%>
		
		<a href="Menu.do?act=bfl" target="rightFrame">���Ӳ˵�</a> 
		<%		
		}				
		%>		
				

		
</html:form>

</body>
</html>
