<%@ include file = "/includes/common.jsp" %>

<%@ page contentType="text/html; charset=GBK"%>

<jsp:useBean id="opForm" scope="request" class="com.eis.portal.op.OPForm" />


<html>
<title>修改操作权限</title>


<script language="javascript">

function doSave(){
	//执行校验
	var field = new Array("op_code","caption");
	var info = new Array("权限码","权限名称");
	var tmp;
	
	//检察输入信息是否为空
	for(var i=0;i<field.length;i++)	
	{
	
        tmp ="document.forms[0]."+field[i]+".value";
        if(isEmpty(eval(tmp))) {
        	alert('请输入'+info[i]);
        	eval("document.forms[0]."+field[i]+".focus()");
        	return;
        }		
	}
	//检查字段长度
	if(getStrLength(document.forms[0].caption.value)>40) { 		
	        	alert('操作名称长度请不要超过40个字符!'); 
	        	document.forms[0].caption.focus(); 
	        	return; 
	}
	//提交
	document.forms[0].submit();
	
}

<%
	String success = (String)request.getAttribute("success");
	if(null != success && success.equals("y"))	{		
		%>
		alert("数据修改成功！");
		<%
	}
%>

function doReturnList(){

  	document.forms[0].act.value='list';	
  	document.forms[0].caption.value='';
	document.forms[0].submit();	
	
}
</script>


<body  class="body_bg_grey1">

<p>&nbsp;</p>
<html:form method="post"   action="OP.do">
<input type=hidden name=act value="u">
<input type=hidden name=step value="commit">

 <%=ViewUtil.getTitle("修改操作权限定义")%>
 
    <table align="center" width="98%"  class="dtPanel_Line3" border="0" cellspacing="1" cellpadding="0"> 
    	     
        <tr bgcolor="#FFFFFF">
          <td width="30%" align="right" class="dtPanel_Left">&nbsp;功能菜单：</td>
          <td align="left" class="dtPanel_Main2">&nbsp;
         <html:text property="menu_name" styleClass="Textfield-READONLY"  readonly="true" size="30" maxlength="40" />
         <html:hidden property="menu_id" />
         </td>
        </tr>
        <tr bgcolor="#FFFFFF">
          <td width="30%" align="right" class="dtPanel_Left">&nbsp;权限码：</td>
          <td align="left" class="dtPanel_Main2">&nbsp; 
          <html:text property="op_code" styleClass="Textfield-READONLY"   readonly="true"  size="30" maxlength="20" />
          </td>
        </tr>
         <tr bgcolor="#FFFFFF">
          <td width="30%" align="right" class="dtPanel_Left">&nbsp;<%=ViewUtil.must()%>操作名称：</td>
          <td align="left" class="dtPanel_Main2">&nbsp; 
          <html:text property="caption" styleClass="Textfield" size="30" maxlength="40" />
          </td>
        </tr>        
  </table>        
    
    <table  class=heightspace_bottom1  border="0" cellspacing="0" cellpadding="0"><tr><td></td></tr></table>  
    <table align="center" width="98%" border="0" cellspacing="0" cellpadding="0">
        <tr>
				
				<td  height="25" align="center" class="dtPanel_Bottom">
						<input	name="update" type="button" class="Button" value="保存" onClick="doSave()"> &nbsp;
						<input name="return" type="button" class="Button" value="返回" onClick="doReturnList()">  
						
		  </td>
	  </tr>
  </table>
			
		
</html:form>
<p>&nbsp;</p>

</body>
</html>
