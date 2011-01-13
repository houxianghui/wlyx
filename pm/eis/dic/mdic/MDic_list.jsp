<%@ include file = "/includes/common.jsp" %> 
<%@ page import="com.eis.dic.mdic.MDicVO" %> 
<%@ page  contentType="text/html; charset=GBK" %> 
 
<link rel="StyleSheet" href="CSS/DTree.css" type="text/css" />

<script src="js/dtree1.js" type="text/javascript" ></script>
<html> 
 
<title>多级字典维护</title> 
 
 


<body> 

<!-----------------------------------------------------------------------> 
 
<html:form method="post" action="MDic.do"> 
<input type=hidden name=act value="list"> 
<input type=hidden name=requery > 
<input type=hidden name=sys_id> 
<input type=hidden name=type_id value=<%=request.getParameter("type_id")%>>  

<div class="dtree">
<p><a href="javascript: d.openAll();">全部展开</a> | <a href="javascript: d.closeAll();">全部关闭</a></p>
</div>
		
		
<div class="dtree" >

<script>
	d = new dTree('d');	
	d.add(0,-1, '多级字典', 'MDic.do?act=cfl&type_id='+document.forms[0].type_id.value+'&parent_id=0', '', 'rightFrame', 'images/menuImg/page.gif');	
					
	<%
		long parent_id= -2;
		String item_val=null;
		long sys_id=0;
		String url=null;
						
		List list = (List)request.getAttribute("ListR"); 

		if(list != null ) {

			Iterator iter = list.iterator();
			while (iter.hasNext()) {
				MDicVO vo = (MDicVO) iter.next();				
				parent_id = vo.getParent_id();
				item_val = vo.getItem_val();
				sys_id = vo.getSys_id();
				url="MDic.do?act=r&sys_id="+sys_id+"";
		 
				
				if(sys_id == 0 ){
		
					out.println("d.add("+sys_id+",-1,'"+item_val+"','');");
				}
				else{
				out.println("d.add("+sys_id+","+parent_id+",'"+item_val+"','"+url+"','','rightFrame');");
				} 
			 }
		}
	
		%>
	
	d.config.useIcons=true;
	
	d.config.useLines=true;

	d.config.closeSameLevel=true; 	 
	document.write(d);

</script>

</div>		
</html:form>
</body> 


</html> 
 

