<%@ include file = "/includes/common.jsp" %>
<%@ page import="com.eis.base.PageObject" %>
<%@ page  contentType="text/html; charset=GBK" %>

<html>

<title>�����˵�Ȩ�޹���</title>



<%
	//��ȡ��������	
	String amsd_store=request.getParameter("amsd_store");
	//�����������ŵ�session��
	session.setAttribute("securityOrgId",amsd_store);	
	//��action���ñ�ʾ��ɫ�˵�Ȩ�޵��б�
	List selectmenulist=(ArrayList)request.getAttribute("selectmenuList");
	
%>
<script language="javascript">

function doSave(){
	//��ѡ���Ƿ���ʾ
	
	document.forms[0].submit(); 
	}


</script>


<body  class="body_bg_grey1">

<html:form   method="post" action="OrgMenu.do">
<input type=hidden name=amsd_store value=<%=amsd_store%>>
<input type=hidden name=act value="u">
<input type=hidden name=step value="commit">


 <table align="center"  class="dtPanel_Line3" width="100%" border="0" cellspacing="1" cellpadding="0">      
        <tr  class="dtPanel_Top01"  height="28">
			<td colspan="2"  class="dtPanel_Top01" align=center>����Ȩ�޹��� -- <%=request.getParameter("amsd_storename")%>
			</td>
		</tr>
</table>



<TABLE width="100%" border=0 cellPadding=0 cellSpacing=1 bgcolor="#4169E1">
  <TBODY>
    <TR > 
      <TD  class=background_c1 >

<table  align="left"  width="100%" border="0" cellspacing="1" cellpadding="0">
		
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
						menu_level = vo.getMenu_level();
						menu_id=vo.getMenu_id();
						menu_name=vo.getMenu_name();
						check=vo.getCheck();
						check_display=vo.getCheck_display();
						
			%>
					<tr align="left"   class="dtPanel_Main" height=22 >
					<td>&nbsp;&nbsp;&nbsp;&nbsp;
					<%
					//���ݲ˵������������ʾ�ո�
					for(int i=1;i<=menu_level;i++){out.print("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");}
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
		
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td height="25" align="center"  class="dtPanel_Bottom">
						<input name="save" type="button" class="Button" value="����" onClick="doSave()"> &nbsp;
						<html:reset value="����" styleClass="Button"/>  &nbsp; 
						 <input name="return" type="button" class="Button" value="����" onClick="location.href='Org.do?act=list';"> &nbsp;
												
						</td>
					</tr>
				</table>
		
</html:form>
<p>&nbsp;</p>

</body>
</html>

