<%
response.setHeader("Cache-Control","no-cache");
response.setHeader("Pragma","no-cache");
response.setDateHeader("Expires",0);
%>


<%@ taglib prefix="html" uri="http://struts.apache.org/tags-html" %>


<%@page import = "java.util.Random"  %>

<%@ page contentType="text/html; charset=GBK"%>

<%
	session.setAttribute("css","Style.css");
	Random random = new Random();
	int n = random.nextInt(1000);
	String num = Integer.toString(n);
	session.setAttribute("checknum",num);	
%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<title>登录</title>

<link href="<%=request.getContextPath()%>/CSS/Style.css" rel="stylesheet" type="text/css"> 
 
<link href="CSS/MGstyle.css" rel="stylesheet" type="text/css" />

<SCRIPT src="<%=request.getContextPath()%>/js/validate.js" type="text/javascript"></SCRIPT> 
<SCRIPT src="<%=request.getContextPath()%>/js/event.js" type="text/javascript"></SCRIPT> 
<SCRIPT src="<%=request.getContextPath()%>/js/func.js" type="text/javascript"></SCRIPT> 

<script language="javascript">
function checkLogin() {
	if(document.forms[0].login_id.value == ""||document.forms[0].password.value == "") {
		alert(" 请输入用户名和密码！ ");
		return false;
	}
	return true;	
		
}
function Login() {
	if(document.forms[0].login_id.value == ""||document.forms[0].password.value == "") {
		alert(" 请输入用户名和密码！ ");
		return;
	}
	document.forms[0].submit();	
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

</head>

<body onload="MM_preloadImages('images/login/loginbutton_m.gif')">

<html:form method="post" action="Login.do" onsubmit="return checkLogin()">
<input name="random" type="hidden" value="<%=num%>">
<input name="PIXEL" type="hidden">

<script>
document.forms[0].PIXEL.value = window.screen.width;


function Keydown(e){
	if(navigator.appName == "Netscape" ){
		if(e.which== 13){//检测键盘，输入回车，提交表单
		   	if(document.forms[0].login_id.value == ""||document.forms[0].password.value == "") {
				alert(" 请输入用户名和密码！ ");
				return;
			}
		document.forms[0].submit();	
		}
	}else{
		if(event.keyCode== 13){//检测键盘，输入回车，提交表单
		   	if(document.forms[0].login_id.value == ""||document.forms[0].password.value == "") {
				alert(" 请输入用户名和密码！ ");
				return;
			}
		document.forms[0].submit();	
		}
	}
}
document.onkeydown= Keydown; 
</script>

<table width="544" height="447" border="0" cellpadding="0" cellspacing="0" align="center">
  <tr>
    <td height="75" background="images/login/login_header.gif">&nbsp;</td>
  </tr>
  <tr>
    <td height="77" background="images/login/login_pic01.gif">&nbsp;</td>
  </tr>
  <tr>
    <td height="108"><table width="100%" height="108" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td width="214" background="images/login/login_picleft.gif">&nbsp;</td>
        <td width="269" bgcolor="#e7e7e7" valign="top"><table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
		     <td width="26"><img src="images/login/IDIcon.gif" width="26" height="26" align="absmiddle" /></td>
            <td width="50" height="36" align="right"><span class="PWDText">用户名:</span></td>
            <td width="193">&nbsp;<input name="login_id" type="text" class="PWDInputBoxSmall" />
            
            </td>
          </tr>
          <tr>
		  <td height="36"><img src="images/login/PWDIcon.gif" width="26" height="26" /></td>
            <td align="right"><span class="PWDText">密码:</span></td>
            <td>&nbsp;<input name="password" class="PWDInputBoxSmall"  type="password"/>
            
            </td>
          </tr>
          <tr>
		  <td height="36">&nbsp;</td>
            <td>&nbsp;</td>
            <td align="right"><a href="javascript:onClick=Login()" onmouseout="MM_swapImgRestore()" onmouseover="MM_swapImage('Image3','','images/login/loginbutton_m.gif',1)"><img src="images/login/loginbutton.gif" name="Image3" width="86" height="23" border="0" id="Image3" /></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
          </tr>
        </table></td>
        <td width="61" background="images/login/login_picright.gif">&nbsp;</td>
      </tr>
    </table></td>
  </tr>
  <tr>
    <td height="66" background="images/login/login_pic02.gif">&nbsp;</td>
  </tr>
  <tr>
    <td height="54" background="images/login/login_piccopyright.gif" align="center"><span class="CopyrightText">copyright&copy;2011</span></td>
  </tr>
  <tr>
    <td height="67" background="images/login/login_bottom.gif">&nbsp;</td>
  </tr>
</table>
</html:form>
</body>
</html>
