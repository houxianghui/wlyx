
<%@ include file = "/includes/common.jsp" %>

<%@ page import="com.eis.cache.*" %> 
<%@ page import = "com.eis.portal.UserContext" %>
<%@ page import = "com.eis.util.*" %>

<%@ page contentType="text/html; charset=GBK"%> 
<html> 
 
<title>修改用户登录</title> 
<link href="<%=request.getContextPath()%>/CSS/MGstyle.css" rel="stylesheet" type="text/css" />

<SCRIPT src="<%=request.getContextPath()%>/js/validate.js" type="text/javascript"></SCRIPT>
<SCRIPT src="<%=request.getContextPath()%>/js/event.js" type="text/javascript"></SCRIPT>
<SCRIPT src="<%=request.getContextPath()%>/js/func.js" type="text/javascript"></SCRIPT>
<HEAD>

<%
	
	String msg = (String)request.getAttribute("message");
	if(msg!=null) {
	
%>


<script language="javascript">

	alert('<%=msg%>');
	
</script>

<%
	}
%>

<script language="javascript">
function checkPwd() {
	if(isEmpty(document.forms[0].oldPassword.value) || isEmpty(document.forms[0].password.value) || isEmpty(document.forms[0].newpwd2.value)){
		alert(" 请输入原密码、新密码和确认密码！ ");
		return;
	}
	else{
		if(document.forms[0].oldPassword.value == document.forms[0].password.value){
			alert(" 新密码应与原密码不同！ ");
			return;
		}
		else{
			if(document.forms[0].password.value == document.forms[0].newpwd2.value){
				//新密码必须是数字与字母的结合，六位以上
				var password = document.forms[0].password.value;
				if(password.length < 6){
					alert('新密码长度至少六位');
					document.forms[0].password.focus();
					return;
				}
				//新密码不能全是数字
				if(isNumber(password)){
					alert('新密码不能全是数字');
					document.forms[0].password.focus();
					return;
				}
				var k = 0;
				for(var i=0;i<password.length;i++){
					var va = password.charAt( i );
					if (isNumber(va))
					{
						k++;
						break;
					}
				}
				for(var i=0;i<password.length;i++){
					var va = password.charAt( i );
					if (isLetter(va))
					{
						k++;
						break;
					}
				}
				for(var i=0;i<password.length;i++){
					var va = password.charAt( i );
					if (!isNumber(va) && !isLetter(va))
					{
						k++;
						break;
					}
				}
				if(k<2){
					alert('新密码复杂度低，必须包含数字、字母和特殊字符中至少两种！');
					document.forms[0].password.focus();
					return;
				}
				
				document.forms[0].submit();
			}
			else{
				alert(" 新密码与确认密码不一致！ ");
				return;
			}			
		}
 	}
}

//<!--
function MM_swapImgRestore() { //v3.0
  var i,x,a=document.MM_sr; for(i=0;a&&i<a.length&&(x=a[i])&&x.oSrc;i++) x.src=x.oSrc;
}

function MM_preloadImages() { //v3.0
  var d=document; if(d.images){ if(!d.MM_p) d.MM_p=new Array();
    var i,j=d.MM_p.length,a=MM_preloadImages.arguments; for(i=0; i<a.length; i++)
    if (a[i].indexOf("#")!=0){ d.MM_p[j]=new Image; d.MM_p[j++].src=a[i];}}
}

function MM_findObj(n, d) { //v4.01
  var p,i,x;  if(!d) d=document; if((p=n.indexOf("?"))>0&&parent.frames.length) {
    d=parent.frames[n.substring(p+1)].document; n=n.substring(0,p);}
  if(!(x=d[n])&&d.all) x=d.all[n]; for (i=0;!x&&i<d.forms.length;i++) x=d.forms[i][n];
  for(i=0;!x&&d.layers&&i<d.layers.length;i++) x=MM_findObj(n,d.layers[i].document);
  if(!x && d.getElementById) x=d.getElementById(n); return x;
}

function MM_swapImage() { //v3.0
  var i,j=0,x,a=MM_swapImage.arguments; document.MM_sr=new Array; for(i=0;i<(a.length-2);i+=3)
   if ((x=MM_findObj(a[i]))!=null){document.MM_sr[j++]=x; if(!x.oSrc) x.oSrc=x.src; x.src=a[i+2];}
}
//-->
</script>
<style>

</style>

</HEAD>
<BODY onload="MM_preloadImages('<%=request.getContextPath()%>/images/PWD/button_m.gif')">

<html:form  method="post" action="Password.do"> 
<input type=hidden name=act value=u> 
<input type=hidden name=step value="commit">
<br><br><br>
<table width="508" height="217" border="0" align="center" cellspacing="0" cellpadding="0">
  <tr>
    <td width="22" background="<%=request.getContextPath()%>/images/PWD/PWD_left.gif">&nbsp;</td>
    <td width="295"><table width="100%" height="217" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td height="76" background="<%=request.getContextPath()%>/images/PWD/PWD_logo.gif">&nbsp;</td>
      </tr>
      <tr>
        <td height="79" background="<%=request.getContextPath()%>/images/PWD/PWD_ID_BGfill.gif" valign="top"><table width="100%" border="0" cellspacing="0" cellpadding="0">          
          <tr>
            <td width="24%" height="26" align="right"><span class="PWDText">原密码:</span></td>
			<td width="76%" >&nbsp;&nbsp;<input name="oldPassword" type="password" class="PWDInputBoxSmall" /></td>
          </tr>
          <tr>
            <td height="26" align="right"><span class="PWDText">新密码:</span></td>
			<td >&nbsp;&nbsp;<input name="password" type="password" class="PWDInputBoxSmall" /></td>
          </tr>
          <tr>
            <td height="27" align="right"><span class="PWDText">确认密码:</span></td>
			<td >&nbsp;&nbsp;<input name="newpwd2" type="password" class="PWDInputBoxSmall" /></td>
          </tr>
        </table></td>
      </tr>
      <tr>
        <td height="43" background="<%=request.getContextPath()%>/images/PWD/PWD_midfill.gif" align="right"><a href="javascript:checkPwd();" onmouseout="MM_swapImgRestore()" onmouseover="MM_swapImage('Image1','','<%=request.getContextPath()%>/images/PWD/button_m.gif',1)"><img src="<%=request.getContextPath()%>/images/PWD/button.gif" name="Image1" width="86" height="23" border="0" id="Image1" /></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
      </tr>
      <tr>
        <td height="19" background="<%=request.getContextPath()%>/images/PWD/PWD_bottomfill.gif">&nbsp;</td>
      </tr>
    </table></td>
    <td width="191"><table width="191" height="217" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td height="106" background="<%=request.getContextPath()%>/images/PWD/PWD_right01.gif">&nbsp;</td>
      </tr>
      <tr>
        <td height="110" background="<%=request.getContextPath()%>/images/PWD/PWD_right02.gif">&nbsp;</td>
      </tr>
    </table></td>
  </tr>
</table>
 
</html:form> 
</BODY>
</HTML>
