<%@ include file = "/includes/common.jsp" %> 
<%@ page contentType="text/html; charset=GBK"%> 
<html> 
 <META http-equiv="Content-Type" content="text/html; charset=GBK">
<title>����֪ͨ</title> 
 
<script language="javascript"> 
 
function doAdd(){ 
	//���� 
 
	//ִ��У�� 
	var field = new Array("notice_comment"); 
	var info = new Array("֪ͨ����"); 
 
	//���������Ϣ�Ƿ�Ϊ�� 
	var tmp; 
	for(var i=0;i<field.length;i++)	{ 
	        tmp ="document.forms[0]."+field[i]+".value"; 
	        if(isEmpty(eval(tmp))) { 
	        	alert('������'+info[i]); 
	        	eval("document.forms[0]."+field[i]+".focus()"); 
	        	return; 
	        } 
	} 
 
 	if(trim(document.forms[0].notice_comment.value).length>400){
 		alert("֪ͨ���ݳ�������");
 		return;
 	}
 
	document.forms[0].submit(); 
} 
</script> 
 
<body> 
<p>&nbsp;</p> 
 
<html:form method="post" enctype="multipart/form-data" action="Ep_notice.do"> 
<input type=hidden name=act value=c> 
<input type=hidden name=step value="commit"> 
 
<%=ViewUtil.getTitle("����֪ͨ")%> 
 
    <table align="center" width="98%" class="dtPanel_Line3" border="0" cellspacing="1" cellpadding="0"> 
       
        <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left">&nbsp;<%=ViewUtil.must()%>֪ͨ���ݣ�</td> 
         <td align="left" class="dtPanel_Main2">&nbsp; 
         <html:textarea property="notice_comment" styleClass="Textarea" rows="10" cols="50"/> 
         </td> 
       </tr> 
   	  <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left">&nbsp;�ϴ��ļ���</td> 
          <td align="left" class="dtPanel_Main2">&nbsp; 
		  <html:file property="file"></html:file>
         </td> 
       </tr> 
        <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left">&nbsp;�ö��� 
          <td align="left" class="dtPanel_Main2">&nbsp; 
				<%=SingleDicMap.getRadio("notice_top","0000","0")%>
         </td> 
       </tr>      
       
       
 
  </table> 
 
    <table  class=heightspace_bottom1  border="0" cellspacing="0" cellpadding="0"><tr><td></td></tr></table> 
 
    <table align="center" width="98%" border="0" cellspacing="0" cellpadding="0"> 
        <tr> 
				<td height="25" align="center" class="dtPanel_Bottom"> 
						<input	name="add" type="button" class="Button" value="����" onClick="doAdd()"> &nbsp; 
						<input name="return" type="button" class="Button" value="����" onClick="history.back()">   
		 		</td> 
	    </tr> 
  </table> 
 
</html:form> 
 
</body> 
</html> 
 

