<%
response.setHeader("Cache-Control","no-cache");
response.setHeader("Pragma","no-cache");
response.setDateHeader("Expires",0);
%>

<%@ include file = "/includes/common.jsp" %>
<%@ page  contentType="text/html; charset=GBK" %>

<%@ page import="java.util.*" %>
<%@ page import="com.eis.portal.user.*" %>
<%@ page import="com.eis.base.PageObject" %>
<%@ page import="com.eis.cache.*" %> 

 
<title>增加用户</title> 

 
<script language="javascript"> 
<% 
	String UserIdExist = (String)request.getAttribute("UserIdExist"); 
	if(null != UserIdExist && UserIdExist.equals("n"))	{		 
		%> 
		alert("用户名重复,请输入其他名称！"); 
		<% 
	}else if (null != UserIdExist && UserIdExist.equals("y")) {
		%> 
		alert("用户名可用！"); 
		<% 	
	}else{}
%> 
 
function doAdd(){ 
	//执行校验
	var field = new Array("login_id","user_name","role_name","begin_dt","invalid_dt");
	var info = new Array("用户名","中文姓名","主角色","起始日期","失效日期");
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
	if(getStrLength(document.forms[0].login_id.value)>10) { 		
	        	alert('用户名长度请不要超过10个字符!'); 
	        	document.forms[0].login_id.focus(); 
	        	return; 
	}
	if(getStrLength(document.forms[0].user_name.value)>18) { 		
	        	alert('中文姓名长度请不要超过18个字符!'); 
	        	document.forms[0].user_name.focus(); 
	        	return; 
	}
	//位数、格式检查
	if(!isEmpty(document.forms[0].phone.value)){		
		if(getStrLength(document.forms[0].phone.value) > 30){
			alert("联系电话位数应小于30位!");
			document.forms[0].phone.focus();
			return;
		}
	}

	if(!isEmpty(document.forms[0].mobile.value)){
		if(!isInteger(document.forms[0].mobile.value)){
			alert("手机号码要求输入数字!");
			document.forms[0].mobile.focus();
			return;
		}
		if(!((document.forms[0].mobile.value).length <= 11 )){
			alert("手机号码应不大于11位数字!");
			document.forms[0].mobile.focus();
			return;
		}
	}
	if(!isEmpty(document.forms[0].email.value)){
		if(!forEmail(document.forms[0].email)){
			alert("电子邮件格式输入有误!");
			document.forms[0].email.focus();
			return;
		}
	}
	if(!isEmpty(document.forms[0].email.value)){
		if((document.forms[0].email.value).length > 60){
			alert("电子邮件长度应不大于60个字符!");
			document.forms[0].email.focus();
			return;
		}
	}
	if(!isEmpty(document.forms[0].postcode.value)){
		if(!isInteger(document.forms[0].postcode.value)){
			alert("邮编要求输入数字!");
			document.forms[0].postcode.focus();
			return;
		}
		if((document.forms[0].postcode.value).length != 6){
			alert("邮编要求6位数字!");
			document.forms[0].email.focus();
			return;
		}
	}
	if(!isEmpty(document.forms[0].address.value)){
		if(getStrLength(document.forms[0].address.value) > 200){
			alert("联系地址不大于200个字符!");
			document.forms[0].address.focus();
			return;
		}
	}
	if(!isEmpty(document.forms[0].memo.value)){
		if(getStrLength(document.forms[0].memo.value) > 200){
			alert("备注长度不大于200个字符!");
			document.forms[0].memo.focus();
			return;
		}
	}
	//日期检查
	if(!isInteger(document.forms[0].begin_dt.value)){
		alert("起始日期输入数字，格式为yyyymmdd!");
		document.forms[0].begin_dt.focus();
		return;
	}
	if(document.forms[0].begin_dt.value.length != 8){
		alert("起始日期项输入8位数字，格式为yyyymmdd!");
		document.forms[0].begin_dt.focus();
		return;
	}
	if(!isInteger(document.forms[0].invalid_dt.value)){
		alert("失效日期输入数字，格式为yyyymmdd!");
		document.forms[0].invalid_dt.focus();
		return;
	}
	if(document.forms[0].invalid_dt.value.length != 8){
		alert("失效日期输入8位数字，格式为yyyymmdd!");
		document.forms[0].invalid_dt.focus();
		return;
	}
	
	var begin_dt = document.forms[0].begin_dt.value;
	var invalid_dt = document.forms[0].invalid_dt.value;
	
	if(!forDate(begin_dt)){
		alert("起始日期输入不正确！");
		document.forms[0].begin_dt.focus();
		return;
	}
	if(begin_dt < <%=DateUtil.getDTStr()%>){
	    alert("起始日期应大于等于系统当前日期！");
		document.forms[0].begin_dt.focus();
		return;
	}
	if(!forDate(invalid_dt)){
		alert("失效日期输入不正确！");
		document.forms[0].invalid_dt.focus();
		return;
	}
	if(document.forms[0].begin_dt.value > document.forms[0].invalid_dt.value){
	    alert("失效日期必须大于起始日期！");
		document.forms[0].invalid_dt.focus();
		return; 
	}
	
	//检查主角色在已选角色中
	if(!checkMainRole(document.forms[0].role_name,document.forms[0].rolesRight))
	{
		return;
	}
	function checkMainRole(text,list){	
		//主角色与列表中各项进行比较
		for(var i=0; i<list.options.length;i++)
		{
			if(list.options[i].text == text.value){				
				return true;
			}
		}
		//若列表中未包含主角色
		alert("主角色不在已选角色中！");
		list.focus();
		return false;
	}
	
	//增加  
	var rolesRight=document.forms[0].rolesRight;
	for(var i=0;i<rolesRight.length;i++){
		rolesRight.options[i].selected=true;
	}
	document.forms[0].submit();
}

function doCheckLoginId(){
    if(!isEmpty(document.forms[0].login_id.value)){
    	document.forms[0].act.value="check";
    	document.forms[0].submit();}
    else{
    	alert("请输入用户名！");    
    }
}

function moveItems(source,target,type){
	
	if(type == "selected") {
		moveSelected(source,target);
	}
	if (type == "all"){
		moveAll(source,target);
	}
	
	<%
	List collRoles = (ArrayList)request.getAttribute("collRoles");
	if(collRoles != null){
		Iterator iter = collRoles.iterator();
		while(iter.hasNext()){
			request.setAttribute("coll_id",iter.next());
	%>
	var coll_id = "<%=(String)request.getAttribute("coll_id")%>";
	var rolesRight=document.forms[0].rolesRight;
	for(var i = 0;i<rolesRight.options.length;i++){
		if(rolesRight.options[i].value == coll_id){
			document.getElementById("invalid_dt").readOnly=true;
			document.getElementById("invalid_dt").className="Textfield-READONLY";
			document.forms[0].invalid_dt.value="20991231";
			return;
		}
	}
	<%
	}
	}
	%>
	document.getElementById("invalid_dt").readOnly=false;
	document.getElementById("invalid_dt").className="Textfield";
	document.forms[0].invalid_dt.value="<%=DateUtil.getYearAfterSysdt(2)%>";
	return;
}


function doPopOrg() {
	openWin("OrgPop.do?act=list&id_field=amsd_store&name_field=org_name","user_org_pop");
}
<% 
	String org_id = (String)request.getSession().getAttribute("org_id"); 	
%> 

</script> 
 
<body class="body_bg_grey1">
 
<html:form  method="post" action="User.do"> 
<input type=hidden name=act value=c> 
<input type=hidden name=step value="commit"> 


 <%=ViewUtil.getTitle("增加用户")%>
    <table align="center" width="98%" class="dtPanel_Line3" border="0" cellspacing="1" cellpadding="0">
      
         

 
        <tr bgcolor="#FFFFFF"> 
          <td  align="right" class="dtPanel_Left">&nbsp;<%=ViewUtil.must()%>用户名：</td> 
         <td colspan="3" align="left" class="dtPanel_Main2">&nbsp; 
         <html:text property="login_id" styleClass="Textfield"  size="18" maxlength="10" />
         <input	name="checkLoginId" type="button" class="Button" value="检查用户名重复" onClick="doCheckLoginId()">
         </td> 
       </tr> 
 
        <tr bgcolor="#FFFFFF"> 
          <td  align="right" class="dtPanel_Left">&nbsp;<%=ViewUtil.must()%>中文姓名：</td>  
         <td colspan="3" align="left" class="dtPanel_Main2">&nbsp; 
         <html:text property="user_name" styleClass="Textfield"  size="26" maxlength="18" /> 
         </td> 
       </tr>        

        <tr bgcolor="#FFFFFF"> 
          <td  align="right" class="dtPanel_Left">&nbsp;联系电话：</td> 
         <td colspan="3" align="left" class="dtPanel_Main2">&nbsp; 
         <html:text property="phone" styleClass="Textfield"  size="30" maxlength="30" /> 
         </td> 
       </tr> 
 
        <tr bgcolor="#FFFFFF"> 
          <td  align="right" class="dtPanel_Left">&nbsp;手机：</td> 
         <td colspan="3" align="left" class="dtPanel_Main2">&nbsp; 
         <html:text property="mobile" styleClass="Textfield"   onkeypress="checknum()" onkeyup="onlyNum(this)"  size="19" maxlength="11" /> 
         </td> 
       </tr> 
 
        <tr bgcolor="#FFFFFF"> 
          <td  align="right" class="dtPanel_Left">&nbsp;电子邮件：</td> 
         <td colspan="3" align="left" class="dtPanel_Main2">&nbsp; 
         <html:text property="email" styleClass="Textfield"  size="60" maxlength="60" /> 
         </td> 
       </tr> 
 
        <tr bgcolor="#FFFFFF"> 
          <td  align="right" class="dtPanel_Left">&nbsp;邮编：</td> 
         <td colspan="3" align="left" class="dtPanel_Main2">&nbsp; 
         <html:text property="postcode" styleClass="Textfield"    onkeypress="checknum()" onkeyup="onlyNum(this)" size="18" maxlength="10" /> 
         </td> 
       </tr> 
 
        <tr bgcolor="#FFFFFF"> 
          <td  align="right" class="dtPanel_Left">&nbsp;联系地址：</td> 
         <td colspan="3" align="left" class="dtPanel_Main2">&nbsp; 
         <html:text property="address" styleClass="Textfield"  size="65" maxlength="200" /> 
         </td> 
       </tr> 

       
        <tr bgcolor="#FFFFFF"> 
          <td  align="right" class="dtPanel_Left">&nbsp;用户角色：</td> 
         <td colspan="3" align="left" class="dtPanel_Main2">
         <table>
         	<tr><td  align="center">可选角色</td><td width=70 colspan="2" align="center"></td><td  align="center"><%=ViewUtil.must()%>已选角色</td></tr>
         	<tr>
         		<td valign="top">
         		<table  border="0" cellpadding="0" cellspacing="2">
         		<tr><td>        			
         		
         		<select name="rolesLeft"  id="rolesLeftId" size="14" multiple   	style="width:150;BORDER:1px;">
	    <%
	    		//从action层获得用户可以选择但未选择的角色权限集合
				List rolesNotSelected=(ArrayList)request.getAttribute("rolesNotSelected");	    		
				
				//如果rolesNotSelected不为空,遍历列表中对象，取对象各值,
				//然后以列表框形式显示
				if(rolesNotSelected != null ) {
					Iterator iter = rolesNotSelected.iterator();
					String role_id = null;		//角色代码
					String role_name = null;		//角色名称					
					
					while (iter.hasNext()) {
						UserRoleVO vo = (UserRoleVO)iter.next();
						role_id = vo.getRole_id();
						role_name=vo.getRole_name();	
					
						String str="<option ";
						str=str+" value='"+role_id+"'>";
						str=str+role_name;
						str=str+"</option>";
						out.println(str);
					}
				}					
			%>
			    </select> 
  				</td></tr></table>  				
         		</td> 
         		        		
         		<td width=88 colspan="2" align="center">
		
		
		　　 		<input type='button' class='Button'  onClick='moveItems(document.forms[0].rolesLeft,document.forms[0].rolesRight,"selected")'  value=' 增加 > '> 
			    	<br><br>
			    	<input type='button' class='Button'  onClick='moveItems(document.forms[0].rolesRight,document.forms[0].rolesLeft,"selected")'  value=' < 删除 '>
			    	<br><br>
			    	<input type='button' class='Button'  onClick='moveItems(document.forms[0].rolesLeft,document.forms[0].rolesRight,"all")'  value='全增加>>'>
			    	<br><br>
			    	<input type='button' class='Button'  onClick='moveItems(document.forms[0].rolesRight,document.forms[0].rolesLeft,"all")'  value='<<全删除'>	    	
		
      			</td>
         		
         		<td valign="top">
         		
        		<table  border="0" cellpadding="0" cellspacing="2">
         		<tr><td>
         		
         		<select name="rolesRight"  id="rolesRightId"  size="11"  multiple  class="Select-Multiple"	style="width:150" >
 <%
	    		
	%>
	</select>  
	</td></tr>
	<tr><td>
	<input type="button"  style="width:149"  class=Button5    
		onClick="moveOneSelectedToText(document.forms[0].rolesRight,document.forms[0].role_name,document.forms[0].role_id)"  value="设为主角色">
	</td></tr>
	<tr><td>	
		<%=ViewUtil.must()%>主角色 <input type=text name="role_name"  readonly class="Textfield-READONLY"  size="15"/>		
		<html:hidden  property="role_id" />
		</td></tr>
		</table>
         		</td>
         	</tr>
         </table>
         </td> 
       </tr> 
 
        <tr bgcolor="#FFFFFF"> 
          <td  align="right" class="dtPanel_Left">&nbsp;<%=ViewUtil.must()%>起始日期：</td> 
         <td align="left" class="dtPanel_Main2">&nbsp; 
         <html:text property="begin_dt" onkeypress="checknum()" onkeyup="onlyNum(this)" styleClass="Textfield"  size="8" maxlength="8" value="<%=DateUtil.getDTStr()%>" /> 
         &nbsp;（格式：yyyymmdd）
         </td> 
         <td align="right" width="18%" class="dtPanel_Left">&nbsp;<%=ViewUtil.must()%>失效日期：</td>

         <td align="left" class="dtPanel_Main2">&nbsp; <html:text property="invalid_dt" styleId="invalid_dt" onkeypress="checknum()"  onkeyup="onlyNum(this)" styleClass="Textfield"  size="8" maxlength="8" value="<%=DateUtil.getYearAfterSysdt(2)%>" />
         &nbsp;（格式：yyyymmdd）
         </td>
       </tr> 
 
        <tr bgcolor="#FFFFFF"> 
          <td  align="right" class="dtPanel_Left">&nbsp;备注：</td> 
         <td colspan="3" align="left" class="dtPanel_Main2">&nbsp; 
         <html:text property="memo" styleClass="Textfield"  size="68" maxlength="200" /> 
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
<SCRIPT LANGUAGE="JavaScript" src="js/move.js">
<!--
//-->
</SCRIPT>
 

