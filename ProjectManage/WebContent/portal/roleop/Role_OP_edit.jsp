<%@ include file = "/includes/common.jsp" %>
<%@ page import="java.util.*" %>
<%@ page import="com.eis.portal.roleop.*" %>
<%@ page import="com.eis.portal.role.*" %>
<%@ page import="com.eis.base.PageObject" %>
<%@ page  contentType="text/html; charset=GBK" %>
<jsp:useBean id="roleOPForm" scope="request" class="com.eis.portal.roleop.RoleOPForm" /> 

<TITLE>��ɫ����Ȩ�޸���</TITLE>


<%
	
	//��ȡ��ɫ����		
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

<BODY  class="body_bg_grey1" ��>

<form name=form1 method="post" action="RoleOP.do">
<input type=hidden name=role_id value=<%=role_id%>>
<input type=hidden name=act value="u">
<input type=hidden name=step value="commit">

  <table width="90%" border=0 align="center" cellpadding=0 align=center cellspacing=0 id='dtl_head' >
    <tr>
      <td  height=30>&nbsp;��ɫ���ƣ�<%=roleOPForm.getRole_name()%></td>
    </tr>
  </table>
  
 
        <table width="92%" height="25" border="0" cellpadding="0" cellspacing="0">
  			<tr>
    			<td  align="center" class=button2 ><a  href="RoleMenu.do?act=u&role_id=<%=role_id%>" >��ɫ�˵�Ȩ��</font></td>
    			
    			<td   align="center"  class=button1><a class=tab_white href="RoleOP.do?act=u&role_id=<%=role_id%>">��ɫ����Ȩ��</a></td>
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
      <td colspan="2" align="center">��ѡ����Ȩ��</td>
      <td colspan="2" align="left"><div align="center">��ѡ����Ȩ��</div></td>
    </tr>    
    <tr >
         
      <td width="40%" align="right">
	    <select name="left" id="leftId" size="25" multiple class="Select-Multiple" style="width:200">
	    <%
	    		//��action���ý�ɫ����ѡ��δѡ��ĵĲ���Ȩ�޼���
				List listNotSelected=(ArrayList)request.getAttribute("listNotSelected");	    		
				
				//���listNotSelected��Ϊ��,�����б��ж���ȡ�����ֵ,
				//Ȼ�����б����ʽ��ʾ
				if(listNotSelected != null ) {
					Iterator iter = listNotSelected.iterator();
					String op_code = null;		//Ȩ����
					String caption = null;		//Ȩ������					
					
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
				<input type='button' class='Button' style='width:80' onClick='moveSelected(document.forms[0].left,document.forms[0].right)'  value='���� >'> 
			    	<br><br>
			    	<input type='button' class='Button' style='width:80' onClick='moveSelected(document.forms[0].right,document.forms[0].left)'  value='ɾ��<'>
			    	<br><br>
			    	<input type='button' class='Button' style='width:80' onClick='moveAll(document.forms[0].left,document.forms[0].right)'  value='ȫ����>>'>
			    	<br><br>
			    	<input type='button' class='Button' style='width:80' onClick='moveAll(document.forms[0].right,document.forms[0].left)'  value='ȫɾ��<<'>	    	
		  </td>
      
      <td width="40%"  align="left">
        <select name="right" id="rightId"  size="25" multiple class="Select-Multiple" style="width:200">
 <%
	    		//��action���ý�ɫ��ѡ��Ĳ���Ȩ�޼���	
	    		List listSelected=(ArrayList)request.getAttribute("listSelected");					
				
				//���listSelected��Ϊ��,�����б��ж���ȡ�����ֵ,
				//Ȼ�����б����ʽ��ʾ
				if(listSelected!= null ) {
					Iterator iter = listSelected.iterator();
					String op_code = null;		//Ȩ����
					String caption = null;		//Ȩ������					
					
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
			<input name="save" type="button" class="Button" value="����" onClick="doSave()"> &nbsp;
			<input name="reset" type="button" class="Button" value="����" >&nbsp;
			<input name="rt" type="button" class="Button" value="����" onClick="location.href='Role.do?act=list';">
			</td>
		</tr>
	</table>
</form>
</BODY>
</HTML>


