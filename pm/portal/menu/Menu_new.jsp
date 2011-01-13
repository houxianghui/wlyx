<%@ include file = "/includes/common.jsp" %>

<%@ page contentType="text/html; charset=GBK"%>

<html>

<title>增加菜单</title>


<script language="javascript">

function doAdd(){
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
	        	alert('菜单名称长度请不要超过40个字符!'); 
	        	document.forms[0].menu_name.focus(); 
	        	return; 
	}
	//检查字段长度
	if(getStrLength(document.forms[0].menu_url.value)>200) { 		
	        	alert('菜单URL长度请不要超过200个字符!'); 
	        	document.forms[0].menu_url.focus(); 
	        	return; 
	}	

	if(!isInteger(document.forms[0].list_order.value)){
		alert("显示顺序要求输入数字!");
		document.forms[0].list_order.focus();
		return;
	}
	//增加	
	document.forms[0].submit();
	
}


</script>
<body  class="body_bg_grey1">


<p>&nbsp;</p>
<form name="menuForm" method="post"  action="Menu.do">
<input type=hidden name=act value="c">
<input type=hidden name=step value="commit">
 
 <%=ViewUtil.getTitle("增加菜单")%>
 
    <table align="center"  class="dtPanel_Line3" width="98%" border="0" cellspacing="1" cellpadding="0">      
       
		<tr>
          <td width="40%" align="right"  class="dtPanel_Left">&nbsp;上级菜单：</td>
          <td align="left" class="dtPanel_Main2">&nbsp;                 
           <input type="text" name="parent_name" maxlength="40" size="20" value="<%= request.getParameter("menu_name")%>"  readonly="true"  class="Textfield-READONLY">
           <input type=hidden name=parent_id  value="<%= request.getParameter("menu_id")%>"  >   
          </td>
        </tr>
        <tr >
          <td  align="right"  class="dtPanel_Left">&nbsp;<%=ViewUtil.must()%>菜单名称：</td>
          <td align="left" class="dtPanel_Main2">&nbsp;
          <input type="text" name="menu_name" maxlength="40" size="20" value="" class="Textfield">
         </td>
        </tr>
        
        <%
        	 String level=request.getParameter("menu_level");
        	 level=Integer.toString(Integer.parseInt(level)+1);        	 
        %>
        <tr >
          <td " align="right"  class="dtPanel_Left">&nbsp;菜单级次：</td>
          <td align="left" class="dtPanel_Main2">&nbsp;
          <input type="text" name="menu_level" maxlength="16" size="20" readonly="true" value="<%=level%>" class="Textfield-READONLY">
          </td>
        </tr>
        <tr >
          <td  align="right"  class="dtPanel_Left">&nbsp;<%=ViewUtil.must()%>显示顺序：</td>
          <td align="left" class="dtPanel_Main2">&nbsp;
          <input type="text" name="list_order" maxlength="16" size="20" value="" onkeypress="checknum()"  onkeyup="onlyNum(this)"  class="Textfield">
        </td>
        </tr>         
        <tr >
          <td  align="right"  class="dtPanel_Left">&nbsp;菜单URL：</td>
          <td align="left" class="dtPanel_Main2">&nbsp;
          <input type="text" name="menu_url" maxlength="200" size="36" value="" class="Textfield">
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
			
		
</form>

<p>&nbsp;</p>

</body>
</html>
