<%@ include file = "/includes/common.jsp" %> 
<%@ page contentType="text/html; charset=GBK"%> 
<jsp:useBean id="workListForm" scope="request" class="com.huateng.blue.work.WorkListForm" />
<html> 
 <head>
<title>��Ŀά��</title> 
<script language="javascript"> 
function doCommit(){
	var s = prompt('�������޸�ԭ��','');
	if(s == null || s==""){
		alert("�������޸�ԭ��");
		return;
	}
	document.forms[0].reason.value=s;
	document.forms[0].submit();
}	

</script> 
 </head>
<body> 
<script type="text/javascript" src="js/calendar.js"></script>
<p>&nbsp;</p> 

<html:form method="post" action="WorkList.do"> 
<input type=hidden name=act value="update"> 
<html:hidden property="reason"/>
<%=ViewUtil.getTitle("����ά��")%> 
 
    <table align="center" width="98%" class="dtPanel_Line3" border="0" cellspacing="1" cellpadding="0"> 
        
         
        <tr bgcolor="#FFFFFF"> 
        	<td width="35%" align="right" class="dtPanel_Left">&nbsp;�����ţ�</td> 
         <td align="left" class="dtPanel_Main2">&nbsp; 
         <html:text property="workId" styleClass="Textfield-READONLY" size="4" readonly="true"/> 
         </td>
       </tr> 
      
		<tr bgcolor="#FFFFFF"> 
         	<td width="35%" align="right" class="dtPanel_Left">&nbsp;�������ƣ�</td> 
         <td align="left" class="dtPanel_Main2">&nbsp; 
         <html:text property="workName" styleClass="Textfield" size="40"/> 
         </td>
       </tr>           
		<tr bgcolor="#FFFFFF"> 
         	<td width="35%" align="right" class="dtPanel_Left">&nbsp;����ʼ���ڣ�</td> 
         <td align="left" class="dtPanel_Main2">&nbsp; 
         <html:text property="startDate" styleClass="Textfield" size="8" readonly="true" onclick="new Calendar().show(this);"/> 
         </td>
       </tr>     
       <tr bgcolor="#FFFFFF"> 
         	<td width="35%" align="right" class="dtPanel_Left">&nbsp;����������ڣ�</td> 
         <td align="left" class="dtPanel_Main2">&nbsp; 
         <html:text property="endDate" styleClass="Textfield" size="8" readonly="true" onclick="new Calendar().show(this);"/> 
         </td>
       </tr>    
      	<tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left">&nbsp; �������ݣ�</td>
         <td align="left" class="dtPanel_Main2">&nbsp;         
         <html:textarea property="content" styleClass="Textarea" cols="40" rows="2"></html:textarea>
         </td> 
       </tr>    
  </table> 
 
    <table  class=heightspace_bottom1  border="0" cellspacing="0" cellpadding="0"><tr><td></td></tr></table> 
 
    <table align="center" width="98%" border="0" cellspacing="0" cellpadding="0"> 
        <tr> 
				<td height="25" align="center" class="dtPanel_Bottom"> 					
					<html:button styleClass="Button" value="����" property="baocun" onclick="doCommit()"></html:button>
					<input type="button" class="Button" value="����" onclick="history.back()">
		 		</td> 
	    </tr> 
  </table> 
 
</html:form> 
 
</body> 
</html> 
 

