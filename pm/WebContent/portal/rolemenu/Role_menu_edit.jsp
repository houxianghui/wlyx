
<%@ page  contentType="text/html; charset=GBK" %>

<%@ include file = "/includes/common.jsp" %>

<%@ page import="com.eis.portal.rolemenu.RoleMenuVO" %>
<%@ page import="com.eis.portal.rolemenu.*" %>
<%@ page import="com.eis.base.PageObject" %>
<%@ page import="com.eis.portal.rolemenu.*" %>
<jsp:useBean id="rolemenuForm" scope="request" class="com.eis.portal.rolemenu.RoleMenuForm" /> 


<html>

<title>��ɫ�˵�Ȩ�޹���</title>




<%
	//��ȡ��ɫ����		
	String role_id=request.getParameter("role_id");
	//����ɫ�����ŵ�session��
	session.setAttribute("securityRoleId",role_id);	
	//��action���ñ�ʾ��ɫ�˵�Ȩ�޵��б�
	List selectmenulist=(ArrayList)request.getAttribute("selectmenuList");
	
%>
<script language="javascript">

function doSave(){
	//ִ�б����޸�
	
	document.forms[0].submit();  //��ø�form����Լ�������
	}


</script>


<body  class="body_bg_grey1">

<html:form   method="post" action="RoleMenu.do">
<input type=hidden name=role_id value=<%=role_id%>>
<input type=hidden name=act value="u">
<input type=hidden name=step value="commit">

<table width="90%" border=0 align="center" cellpadding=0 align=center cellspacing=0 id='dtl_head' name='dtl_head'>
	  <tr>
      <td height=30>&nbsp;��ɫ���ƣ�<%=rolemenuForm.getRole_name()%></td>
    </tr>
  </table>
  
 
          <table width="92%" height="26" border="0" cellpadding="0" cellspacing="0">
  			<tr>
    			<td   align="center" class=button1 ><a class=tab_white href="RoleMenu.do?act=u&role_id=<%=role_id%>" >��ɫ�˵�Ȩ��</font></td>
    			<td  align="center"  class=button2 ><a href="RoleOP.do?act=u&role_id=<%=role_id%>">��ɫ����Ȩ��</a></td>
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
				//ȡ����action��list�������ص�list
				List list = selectmenulist;
				
				//���list��Ϊ��,�����б��ж���ȡ����ĸ�ֵ,
				//�����и������Դ���ѡ��Ĳ㼶�˵���ʽ��ʾ
				if(list != null ) {
					Iterator iter = list.iterator();
					int menu_level = 0;		//�˵�����
					String menu_id = null;		//�˵�����
					String menu_name = null;	//�˵�����				
					int check = 0;			//��ѡ���Ƿ�ѡ
					int check_display=0;	//��ѡ���Ƿ���ʾ
					
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
					//���ݲ˵������������ʾ�ո�
					for(int i=1;i<=menu_level;i++){out.print("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");}
					//�����ʾ��ѡ������ʾ��ѡ��+�˵�����
					 if(check_display!=0){
					 	String str="";	
					 	str=str+"<img src='images/DTreeIMG/page.gif'>";				 	
					 	str=str+"<input type='checkbox' ";
					 	str=str+"  name='selectmenu'" ;		//���ø�ѡ��nameΪselectmenu
					 	str=str+" value='"+menu_id+"' ";	//���ø�ѡ��valueΪ�˵����menu_id
					 	if(check==1){str=str+"  checked ";}	//��check=1����ѡ��ѡ��
					 	str=str+" > ";
					 	str=str+menu_name;				//�˵�����
					 	out.println(str) ;
					 }
					else{
					//�������ʾ��ѡ����ֻ��ʾ�˵�����	
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
						<input name="save" type="button" class="Button" value="����" onClick="doSave()">&nbsp;
						<html:reset value="����" styleClass="Button"/>&nbsp; 
						<input name="rt" type="button" class="Button" value="����" onClick="location.href='Role.do?act=list';"> 
											
						</td>
					</tr>
				</table>				
	
		
</html:form>


</body>
</html>

