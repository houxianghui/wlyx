<%@ include file = "/includes/common.jsp" %> 
<%@ page contentType="text/html; charset=GBK"%> 
<html> 
 <META http-equiv="Content-Type" content="text/html; charset=GBK">
<title>增加通知</title> 
 
<script language="javascript"> 
 
function doAdd(){ 
	//增加 
 
	//执行校验 
	var field = new Array("notice_comment"); 
	var info = new Array("通知内容"); 
 
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
 
 	if(trim(document.forms[0].notice_comment.value).length>400){
 		alert("通知内容超过限制");
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
 
<%=ViewUtil.getTitle("增加通知")%> 
 
    <table align="center" width="98%" class="dtPanel_Line3" border="0" cellspacing="1" cellpadding="0"> 
       
        <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left">&nbsp;<%=ViewUtil.must()%>通知内容：</td> 
         <td align="left" class="dtPanel_Main2">&nbsp; 
         <html:textarea property="notice_comment" styleClass="Textarea" rows="10" cols="50"/> 
         </td> 
       </tr> 
   	  <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left">&nbsp;上传文件：</td> 
          <td align="left" class="dtPanel_Main2">&nbsp; 
		  <html:file property="file"></html:file>
         </td> 
       </tr> 
        <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left">&nbsp;置顶： 
          <td align="left" class="dtPanel_Main2">&nbsp; 
				<%=SingleDicMap.getRadio("notice_top","0000","0")%>
         </td> 
       </tr>      
       
       
 
  </table> 
 
    <table  class=heightspace_bottom1  border="0" cellspacing="0" cellpadding="0"><tr><td></td></tr></table> 
 
    <table align="center" width="98%" border="0" cellspacing="0" cellpadding="0"> 
        <tr> 
				<td height="25" align="center" class="dtPanel_Bottom"> 
						<input	name="add" type="button" class="Button" value="保存" onClick="doAdd()"> &nbsp; 
						<input name="return" type="button" class="Button" value="返回" onClick="history.back()">   
		 		</td> 
	    </tr> 
  </table> 
 
</html:form> 
 
</body> 
</html> 
 

