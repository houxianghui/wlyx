<%@ include file = "/includes/common.jsp" %> 
<%@ page contentType="text/html; charset=GBK"%> 
<html> 
 <head>
<title>�����б�ά��</title> 
 </head>
<body> 
<p>&nbsp;</p> 
 
<html:form method="post" enctype="multipart/form-data" action="ProgramMaintain.do"> 
<input type=hidden name=act value=upload> 
<%String projectId = request.getParameter("projectId");%>
<html:hidden property="projectId" value="<%=projectId%>"/>
<%=ViewUtil.getTitle("�����б�ά��(�ϴ��ļ������ֶ��༭ѡ��һ��)")%> 
    <table align="center" width="98%" class="dtPanel_Line3" border="0" cellspacing="1" cellpadding="0"> 

        <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="center" class="dtPanel_Left">&nbsp;�ļ��� 
			<html:file property="file"></html:file>
       		<html:submit value="�ύ" styleClass="Button"/>
         </td> 
       </tr>     
      
  </table>  
</html:form> 
</body> 

<html:form method="post" action="ProgramMaintain.do">
<input type=hidden name=act value=input>
<%String projectId = request.getParameter("projectId");%>
<html:hidden property="projectId" value="<%=projectId%>"/>
<%=ViewUtil.getTitle("�����б�")%> 
    <table align="center" width="98%" class="dtPanel_Line3" border="0" cellspacing="1" cellpadding="0"> 
         <tr bgcolor="#FFFFFF">
         <td width="35%" align="center" class="dtPanel_Left">
			<html:textarea property="program" cols="100" styleClass="Textarea" rows="20"></html:textarea>
       		
         </td> 
         </tr>
         <tr>
         <td width="35%" align="center" class="dtPanel_Left">
         	<html:submit value="�ύ" styleClass="Button"/>
         	<html:button property="back" value="����" styleClass="Button" onclick="history.back()"/>
         </td>
         </tr>   
  </table>  
</html:form>
</html> 
 

