<%@ include file = "/includes/common.jsp" %>

<%@ page import="com.eis.cache.*" %>

<%@ page contentType="text/html; charset=GBK"%>

<jsp:useBean id="roleForm" scope="request" class="com.eis.portal.role.RoleForm" /> 

<html>
<title>修改角色信息</title>

<script language="javascript">

function doSave(){
	//执行校验
	var field = new Array("role_name","templ_id","sesn_time","logic_id");
	var info = new Array("角色名称","登录首页","会话超时限制","控制代码");
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
	if(getStrLength(document.forms[0].role_name.value)>40) { 		
	        	alert('角色名称长度请不要超过40个字符!'); 
	        	document.forms[0].role_name.focus(); 
	        	return; 
	}
	//检查字段长度
	if(getStrLength(document.forms[0].logic_id.value)>8) { 		
	        	alert('控制代码长度请不要超过8个字符!'); 
	        	document.forms[0].logic_id.focus(); 
	        	return; 
	}
	
	//检察输入是否为正整数
	if(!isPositiveInteger(document.forms[0].sesn_time.value)){
        alert('会话超时限制请输入正整数！');
        document.forms[0].sesn_time.focus();
        return;
    } 
     
	
	//提交
	document.forms[0].act.value='u';
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


</script>


<body  class="body_bg_grey1">

<html:form method="post" action="Role.do">
<input type=hidden name=act value="u">
<input type=hidden name=step value="commit">
<html:hidden property="status"/>

<br>
<%=ViewUtil.getTitle("修改角色信息")%>

    <table align="center" width="98%"  class="dtPanel_Line3" border="0" cellspacing="1" cellpadding="0">
        	     
        <tr bgcolor="#FFFFFF">
          <td width="30%" align="right" class="dtPanel_Left">&nbsp;角色代码：</td>
          <td align="left" class="dtPanel_Main2">&nbsp;
         <html:text property="role_id" styleClass="Textfield-READONLY" readonly="true" size="20"  maxlength="8"/>
         </td>
        </tr>
        <tr bgcolor="#FFFFFF">
          <td  align="right" class="dtPanel_Left">&nbsp;<%=ViewUtil.must()%>角色名称：</td>
          <td align="left" class="dtPanel_Main2">&nbsp; 
          <html:text property="role_name" styleClass="Textfield" size="30" maxlength="40" />
          </td>
        </tr>
        
        
       <tr bgcolor="#FFFFFF">
          <td  align="right" class="dtPanel_Left">&nbsp;<%=ViewUtil.must()%>登录首页：</td>
          <td align="left" class="dtPanel_Main2">&nbsp;
          <html:select property="templ_id" >
            <html:optionsCollection name="roleForm" property="templ_id_options" />
          </html:select>         
        </td>  
        </tr>
        
         <tr bgcolor="#FFFFFF">
          <td  align="right" class="dtPanel_Left">&nbsp;<%=ViewUtil.must()%>会话超时限制：</td>
          <td align="left" class="dtPanel_Main2">&nbsp; 
          <html:text property="sesn_time" styleClass="Textfield" size="20" maxlength="20" onkeypress="checknum()"  onkeyup="onlyNum(this)" />&nbsp;秒
          </td>
        </tr>
        
                 <tr bgcolor="#FFFFFF">
          <td  align="right" class="dtPanel_Left">&nbsp;<%=ViewUtil.must()%>控制代码：</td>
          <td align="left" class="dtPanel_Main2">&nbsp; 
          <html:text property="logic_id" styleClass="Textfield" size="20" maxlength="8" />
          </td>
        </tr>
  </table>

  <table  class=heightspace_bottom1  border="0" cellspacing="0" cellpadding="0"><tr><td></td></tr></table>
   <table align="center" width="98%" border="0" cellspacing="0" cellpadding="0">        
        <tr>	
				<td height="25" align="center" class="dtPanel_Bottom">
						<input	name="update" type="button" class="Button" value="保存" onClick="doSave()"> &nbsp;
						<html:reset value="重置" styleClass="Button"/>  &nbsp; 
						<input name="return" type="button" class="Button" value="返回" onClick="location.href='Role.do?act=list';">  
						
		  </td>
	  </tr>
  </table>
			
		
</html:form>
<p>&nbsp;</p>

</body>
</html>
