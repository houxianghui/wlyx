<%@ include file = "/includes/common.jsp" %> 
<%@ page contentType="text/html; charset=GBK"%> 
<html> 
 
<title>增加主键配置</title>  

 
<script language="javascript"> 
 
function doAdd(){ 
	//增加 
	//执行校验 
	var field = new Array("tb_name","key_field","step_len","max_val"); 
	var info = new Array("数据库表名","主键字段","步长","当前最大值"); 
 
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
 
<body   class="body_bg_grey1"> 
 
<html:form method="post" action="PmKey.do"> 
<input type=hidden name=act value=c> 
<input type=hidden name=step value="commit"> 
 
 <%=ViewUtil.getTitle("增加主键配置")%>
  
    <table align="center" width="98%"  class="dtPanel_Line3"  border="0" cellspacing="1" cellpadding="0"> 
                
        <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left">&nbsp;<%=ViewUtil.must()%>数据库表名：</td> 
         <td align="left" class="dtPanel_Main2">&nbsp; 
         <html:text property="tb_name" styleClass="Textfield"  size="20" maxlength="20" /> 
         </td> 
       </tr> 
 
        <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left">&nbsp;<%=ViewUtil.must()%>主键字段：</td> 
         <td align="left" class="dtPanel_Main2">&nbsp; 
         <html:text property="key_field" styleClass="Textfield"  size="20" maxlength="20" /> 
         </td> 
       </tr> 
 
        <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left">&nbsp;<%=ViewUtil.must()%>步长：</td> 
         <td align="left" class="dtPanel_Main2">&nbsp; 
         <html:text property="step_len" styleClass="Textfield"  size="20" maxlength="4" onkeypress="checknum()" onkeyup="onlyNum(this)"/> 
         </td> 
       </tr> 
 
        <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left">&nbsp;<%=ViewUtil.must()%>当前最大值：</td> 
         <td align="left" class="dtPanel_Main2">&nbsp; 
         <html:text property="max_val" styleClass="Textfield"  size="20" maxlength="20" onkeypress="checknum()"  onkeyup="onlyNum(this)"/> 
         </td> 
       </tr> 
  </table>
  
    	<table  class=heightspace_bottom1  border="0" cellspacing="0" cellpadding="0"><tr><td></td></tr></table>
   
    <table align="center" width="98%" border="0" cellspacing="0" cellpadding="0">      
        <tr> 
				<td height="25" align="center"  class="dtPanel_Bottom"> 
						<input	name="add" type="button" class="Button" value="保存" onClick="doAdd()"> &nbsp; 
						<input name="return" type="button" class="Button" value="返回" onClick="history.back()">   
		 		</td> 
	    </tr> 
  </table>  
 
</html:form> 
 
</body> 
</html> 
 

