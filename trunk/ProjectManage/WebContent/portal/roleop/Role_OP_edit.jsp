<%@ include file = "/includes/common.jsp" %>
<%@ page import="java.util.*" %>
<%@ page import="com.eis.portal.roleop.*" %>
<%@ page import="com.eis.portal.role.*" %>
<%@ page import="com.eis.base.PageObject" %>
<%@ page  contentType="text/html; charset=GBK" %>
<jsp:useBean id="roleOPForm" scope="request" class="com.eis.portal.roleop.RoleOPForm" /> 

<TITLE>角色操作权限更改</TITLE>


<%
	
	//获取角色代码		
	String role_id=request.getParameter("role_id");
	
 
%>

<script>


function doSave(){ 
	var right=document.forms[0].right;
	for(var i=0;i<right.length;i++){
		right.options[i].selected=true;
	}
	document.forms[0].submit();
	
}

</script>
<SCRIPT LANGUAGE="JavaScript" src="js/move.js"></script>

</HEAD>

<BODY  class="body_bg_grey1" 　>

<form name=form1 method="post" action="RoleOP.do">
<input type=hidden name=role_id value=<%=role_id%>>
<input type=hidden name=act value="u">
<input type=hidden name=step value="commit">

  <table width="90%" border=0 align="center" cellpadding=0 align=center cellspacing=0 id='dtl_head' >
    <tr>
      <td  height=30>&nbsp;角色名称：<%=roleOPForm.getRole_name()%></td>
    </tr>
  </table>
  
 
        <table width="92%" height="25" border="0" cellpadding="0" cellspacing="0">
  			<tr>
    			<td  align="center" class=button2 ><a  href="RoleMenu.do?act=u&role_id=<%=role_id%>" >角色菜单权限</font></td>
    			
    			<td   align="center"  class=button1><a class=tab_white href="RoleOP.do?act=u&role_id=<%=role_id%>">角色操作权限</a></td>
				<td   align="left" >&nbsp;</td>
  			</tr>
		</table> 



<TABLE width="98%" border=0 cellPadding=0 cellSpacing=1 bgcolor="#4169E1">
  <TBODY>
    <TR bgColor=#FFFFFF> 
      <TD  class=background_c1>
      
      
      <table width=98%>
    </tr>   
    <tr class="dtPanel_Top02"> 
      <td colspan="2" align="center">可选操作权限</td>
      <td colspan="2" align="left"><div align="center">已选操作权限</div></td>
    </tr>    
    <tr >
         
      <td width="40%" align="right">
	    <select name="left" id="leftId" size="25" multiple class="Select-Multiple" style="width:200">
	    <%
	    		//从action层获得角色可以选择但未选择的的操作权限集合
				List listNotSelected=(ArrayList)request.getAttribute("listNotSelected");	    		
				
				//如果listNotSelected不为空,遍历列表中对象，取对象各值,
				//然后以列表框形式显示
				if(listNotSelected != null ) {
					Iterator iter = listNotSelected.iterator();
					String op_code = null;		//权限码
					String caption = null;		//权限名称					
					
					while (iter.hasNext()) {
						RoleOPVO vo = (RoleOPVO) iter.next();
						op_code = vo.getOp_code();
						caption=vo.getCaption();	
						
						String str="<option ";
						str=str+" value='"+op_code+"'>";
						str=str+caption;
						str=str+"</option>";
						out.println(str);
						}
				}					
			%>          
        </select> </td>
        
        
      <td valign="top" width="20%" colspan="2" align="center">
      
 				<br><br><br>
				<input type='button' class='Button' style='width:80' onClick='moveSelected(document.forms[0].left,document.forms[0].right)'  value='增加 >'> 
			    	<br><br>
			    	<input type='button' class='Button' style='width:80' onClick='moveSelected(document.forms[0].right,document.forms[0].left)'  value='删除<'>
			    	<br><br>
			    	<input type='button' class='Button' style='width:80' onClick='moveAll(document.forms[0].left,document.forms[0].right)'  value='全增加>>'>
			    	<br><br>
			    	<input type='button' class='Button' style='width:80' onClick='moveAll(document.forms[0].right,document.forms[0].left)'  value='全删除<<'>	    	
		  </td>
      
      <td width="40%"  align="left">
        <select name="right" id="rightId"  size="25" multiple class="Select-Multiple" style="width:200">
 <%
	    		//从action层获得角色已选择的操作权限集合	
	    		List listSelected=(ArrayList)request.getAttribute("listSelected");					
				
				//如果listSelected不为空,遍历列表中对象，取对象各值,
				//然后以列表框形式显示
				if(listSelected!= null ) {
					Iterator iter = listSelected.iterator();
					String op_code = null;		//权限码
					String caption = null;		//权限名称					
					
					while (iter.hasNext()) {
						RoleOPVO vo = (RoleOPVO)iter.next();
						op_code = vo.getOp_code();
						caption=vo.getCaption();	
						
						String str="<option ";
						str=str+" value='"+op_code+"'>";
						str=str+caption;
						str=str+"</option>";
						out.println(str);
						}
				}					
			%>  
        </select>        
         </td>
    </tr>
    <tr>
    	<td colspan="4">&nbsp;</td>
    </tr>    
  </table>
      
      
      </TD>
    </TR>
  </TBODY>
</TABLE>

      
<table  class=heightspace_bottom1  border="0" cellspacing="0" cellpadding="0"><tr><td></td></tr></table>
  
  	<table width="98%" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td height="25" align="center"   class="dtPanel_Bottom">
			<input name="save" type="button" class="Button" value="保存" onClick="doSave()"> &nbsp;
			<input name="reset" type="button" class="Button" value="重置" >&nbsp;
			<input name="rt" type="button" class="Button" value="返回" onClick="location.href='Role.do?act=list';">
			</td>
		</tr>
	</table>
</form>
</BODY>
</HTML>


