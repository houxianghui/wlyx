<%@ include file = "/includes/common.jsp" %> 
<%@ page import="com.eis.dic.mdic.MDicVO" %> 
<%@ page  contentType="text/html; charset=GBK" %> 
<html>  
<link rel="StyleSheet" href="CSS/DTree.css" type="text/css" />

<script src="js/MDictree.js" type="text/javascript" ></script>


<%
	java.util.ArrayList list = (java.util.ArrayList)request.getAttribute("ItemList");
	String typeName = (String)request.getAttribute("TypeName");
	
	String idField = (String)request.getAttribute("idField");
	
	String nameField = (String)request.getAttribute("nameField");
	
%>
 
<script language="javascript">
	
function setParentKey(id,name){	
	opener.document.forms[0].<%=idField%>.value=id;
	opener.document.forms[0].<%=nameField%>.value=name;	 	
	window.close();
}
</script>

<title><%=typeName%></title> 
 
 


<body> 
<br><br>
<!-----------------------------------------------------------------------> 
<table align="center">
<tr>
<td></td>
<td>
<html:form method="post" action="MDic.do"> 
<div class="dtree">
<p><a href="javascript: d.openAll();">全部展开</a> | <a href="javascript: d.closeAll();">全部关闭</a></p>
</div>
				
<div class="dtree" >
<script language="javascript">
	d = new dTree('d');	
	d.add(0,-1, '<%=typeName%>', '', '', '', 'images/menuImg/page.gif');						
	<%
		if(list != null ) {
			for(int i=0;i<list.size();i++)	{
				MDicVO vo = (MDicVO) list.get(i);						 
				out.println("d.add("+vo.getSys_id()+","+vo.getParent_id()+",'"+vo.getItem_val()+"','','','');");
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

</td>
<td></td>
</tr>
</table>
</body> 
</html> 
 

