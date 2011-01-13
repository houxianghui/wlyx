<%@ include file = "/includes/common.jsp" %> 
<%@ page contentType="text/html; charset=GBK"%> 
<jsp:useBean id="requireChangeForm" scope="request" class="com.projectmaintain.RequireChangeForm" />
<html> 
 <head>
<title>需求变更维护</title> 
 </head>
<body> 
<script language="javascript"> 
 
function doSave(){ 	
	var field = new Array("content","reason"); 
	var info = new Array("变更内容","变更原因"); 
 
	//检察输入信息是否为空 
	var tmp; 
	for(var i=0;i<field.length;i++)	{ 
	        tmp ="document.forms[0]."+field[i]+".value"; 
	        if(isEmpty(eval(tmp))) { 
	        	alert('请输入'+info[i]); 
	        	eval("document.forms[0]."+field[i]+".focus()"); 
	        	return; 
	        } 
	} 
	
	document.forms[0].submit();
	
}
</script>

<html:form method="post" action="RequireChange.do"> 
<input type=hidden name=act value="update"> 
<html:hidden property="projectId"/>
<html:hidden property="version"/>

    <table align="center" width="98%" class="dtPanel_Line3" border="0" cellspacing="1" cellpadding="0"> 
   		
      	<tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left">&nbsp;变更内容：</td>
         <td align="left" class="dtPanel_Main2">&nbsp;         
         <html:textarea property="content" styleClass="Textarea" cols="40" rows="6"></html:textarea>
         </td> 
       </tr>     
       <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left">&nbsp;变更原因：</td>
         <td align="left" class="dtPanel_Main2">&nbsp;         
         <html:textarea property="reason" styleClass="Textarea" cols="40" rows="6"></html:textarea>
         </td> 
       </tr>   
  </table> 
 
    <table  class=heightspace_bottom1  border="0" cellspacing="0" cellpadding="0"><tr><td></td></tr></table> 
 
    <table align="center" width="98%" border="0" cellspacing="0" cellpadding="0"> 
        <tr> 
				<td height="25" align="center" class="dtPanel_Bottom"> 
					<html:button property="保存" value="保存" styleClass="Button" onclick="doSave()"></html:button>
					<input type="button" class="Button" value="返回" onclick="history.back()">
		 		</td> 
	    </tr> 	   
	    
  </table> 
</html:form> 
 
</body> 
</html> 
 

