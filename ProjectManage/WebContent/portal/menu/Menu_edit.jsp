<%@ include file = "/includes/common.jsp" %>

<%@ page contentType="text/html; charset=GBK"%>


<html>
<title>修改菜单信息</title>


<script language="javascript">

function doSave(){
	//执行校验
	var field = new Array("menu_name","list_order");
	var info = new Array("菜单名称","显示顺序");
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
	if(getStrLength(document.forms[0].menu_name.value)>40) { 		
	        	alert('菜单名称长度超过40个字符!'); 
	        	document.forms[0].menu_name.focus(); 
	        	return; 
	}
	//检查字段长度
	if(getStrLength(document.forms[0].menu_url.value)>200) { 		
	        	alert('菜单URL长度超过200个字符!'); 
	        	document.forms[0].menu_url.focus(); 
	        	return; 
	}
	
	if(!isInteger(document.forms[0].list_order.value)){
		alert("显示顺序要求输入数字!");
		document.forms[0].list_order.focus();
		return;
	}
	document.forms[0].submit();
	
}

//刷新左侧菜单列表
function reloadLeftMenu(){
		parent.leftFrame.location.reload();
}

<%
	String refresh = (String)request.getSession().getAttribute("refresh");
	if(null != refresh && refresh.equals("y"))	{		
		%>		
		reloadLeftMenu();		
		<%
	}
	session.removeAttribute("refresh");
	
%>



<%
	String success = (String)request.getSession().getAttribute("success");
	if(null != success && success.equals("y"))	{		
		%>		
		reloadLeft();
		<%
	}
	session.removeAttribute("success");
%>

</script>


<body  class="body_bg_grey1">

<p>&nbsp;</p>

<html:form method="post" action="Menu.do">
<input type=hidden name=act value="u">
<input type=hidden name=step value="commit">
<html:hidden property="parent_id"/>
 
 <%=ViewUtil.getTitle("修改菜单信息")%>
 
    <table align="center"  class="dtPanel_Line3" width="98%" border="0" cellspacing="1" cellpadding="0">
        
       <tr bgcolor="#FFFFFF">
          <td width="35%" align="right"  class="dtPanel_Left">&nbsp;菜单编号：</td>
          <td align="left" class="dtPanel_Main2">&nbsp;
         <html:text property="menu_id" styleClass="Textfield-READONLY"  readonly="true" size="20" maxlength="30" />
         </td>
        </tr>
        <tr bgcolor="#FFFFFF">
          <td width="35%" align="right"  class="dtPanel_Left">&nbsp;上级菜单：</td>
          <td align="left" class="dtPanel_Main2">&nbsp; 
          <html:text property="parent_name" styleClass="Textfield-READONLY" readonly="true" size="20" maxlength="40" />       
          </td>
        </tr>
        <tr bgcolor="#FFFFFF">
          <td width="35%" align="right"  class="dtPanel_Left">&nbsp;<%=ViewUtil.must()%>菜单名称：</td>
          <td align="left" class="dtPanel_Main2">&nbsp;
          <html:text property="menu_name" styleClass="Textfield" size="20" maxlength="40" />
         </td>
        </tr>
        <html:hidden property="menu_mark"/>
        
        <tr bgcolor="#FFFFFF">
          <td width="35%" align="right"  class="dtPanel_Left">&nbsp;菜单级次：</td>
          <td align="left" class="dtPanel_Main2">&nbsp;
          <html:text property="menu_level" styleClass="Textfield-READONLY"  readonly="true"  size="20" maxlength="40" />
          </td>
        </tr>
        <tr bgcolor="#FFFFFF">
          <td width="35%" align="right"  class="dtPanel_Left">&nbsp;<%=ViewUtil.must()%>显示顺序：</td>
          <td align="left" class="dtPanel_Main2">&nbsp;
          <html:text property="list_order" styleClass="Textfield" size="20" maxlength="16" onkeypress="checknum()"  onkeyup="onlyNum(this)" />
          </td>
        </tr>        
         <tr bgcolor="#FFFFFF">
          <td width="35%" align="right"  class="dtPanel_Left">&nbsp;菜单URL：</td>
          <td align="left" class="dtPanel_Main2">&nbsp;
          <html:text property="menu_url" styleClass="Textfield" size="36" maxlength="200" />
          </td>
        </tr>
   </table>       
     
   <table  class=heightspace_bottom1  border="0" cellspacing="0" cellpadding="0"><tr><td></td></tr></table>
    <table align="center" width="98%" border="0" cellspacing="0" cellpadding="0">
            <tr>
				<td height="25" align="center"  class="dtPanel_Bottom" >
						<input	name="update" type="button" class="Button" value="保存" onClick="doSave()"> &nbsp;
						<input name="return" type="button" class="Button" value="返回" onClick="history.back();">  
						
		  </td>
	  </tr>
  </table>
			
		
</html:form>


</body>
</html>
