<%
response.setHeader("Cache-Control","no-cache");
response.setHeader("Pragma","no-cache");
response.setDateHeader("Expires",0);
%>

<%@ page  contentType="text/html; charset=GBK" %>

<%@ include file = "/includes/common.jsp" %>

<%@ page import="com.eis.portal.UserContext" %>
<%@ page import="com.eis.portal.loginchangerole.*" %>
<%@ page import="com.eis.cache.*" %>

<HTML>
<HEAD>
<TITLE>��������ɫ��¼</TITLE>

<script language="javascript"> 
 
function doSubmit(){ 
	
	document.forms[0].submit();
	
}

</script>
</HEAD>
<BODY  class="body_bg_grey1">
<%
	//��ȡ�û�����ɫ����		
	String role_id=(String)request.getAttribute("roleId");
	
	//���û���ɫ����
	String[] roles=(String[])request.getAttribute("roles");		
	
%>

<html:form method="post" action="LoginChangeRole.do" target="_parent">
<input type=hidden name=act value="changerole" >
<br><br>



  
  <table   width="80%"  class="dtPanel_Line3" align="center" border="0"  cellspacing="1" cellpadding="0">
          <tr align="center" class="dtPanel_Top01"> 
            <td  class="dtPanel_Top01">��ѡ��һ����ɫ��Ϊ����ɫ��¼&nbsp;</td>            
          </tr>    
    
    <%
    	for(int i=0;i<roles.length;i++){
    %>
	<tr  align="center" class="dtPanel_Main" >
		<td>
	
	<%
		String str="<input type='radio' ";
		str=str+"  name='selectrole' " ;
		str=str+" value='"+roles[i]+"' ";
		if(roles[i].equals(role_id)){str=str+"  checked ";}	
		str=str+" > ";
		str=str+" "+ReDefSDicMap.getDicItemVal("0002",roles[i]);
		//roles[i].equals("0")?"":ReDefSDicMap.getDicItemVal("0002",roles[i])
		out.println(str);
		
	%>
		<td>
	</tr>
	<%
		}
	%>				
  </table> 
  
  <br>
  <center> 
  <input name="update" type="button" class="Button" value="��¼" onClick="doSubmit()"> &nbsp;
  </center> 
</html:form>
</BODY>
</HTML>
