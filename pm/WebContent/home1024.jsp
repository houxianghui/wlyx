<%
response.setHeader("Cache-Control","no-cache");
response.setHeader("Pragma","no-cache");
response.setDateHeader("Expires",0);
%>

<%@ include file = "/includes/common.jsp" %>

<%@ page contentType="text/html; charset=GBK"%>

<%
	String homepage = request.getContextPath()+(String)session.getAttribute("homepage");
%>
<html>

<head>
<style type="text/css">
.logo{
	display:block;
	width:document.body.clientWidth;
	height:10%;
	color:#fff;
	line-height:1.5em;
	text-decoration:none;
	background:url(images/Logo2.jpg) repeat-x ;
	font-size:xx-large;
	text-align: left;
	font-family:"微软雅黑","宋体",Geneva, sans-serif;	
	font-weight:bold;
	text-shadow:0 0 2px yellow, 0 -5px 4px #ace, 2px -10px 6px #658EAE, 2px -15px 11px #7699B7, 2px -25px 18px #EEF4F8;
}

</style>
<title>华腾项目管理系统</title>
<script>

function switchSysBar(){
	if(navigator.appName == "Netscape"){
		if (switchPoint.textContent=="<"){
			switchPoint.textContent=">"
			document.all("frmTitle").style.display="none"
		}else{
			switchPoint.textContent="<"
			document.all("frmTitle").style.display=""
		}
	}else{
		if (switchPoint.innerText=="<"){
			switchPoint.innerText=">"
			document.all("frmTitle").style.display="none"
		}else{
			switchPoint.innerText="<"
			document.all("frmTitle").style.display=""
		}
	}
}
window.open("Ep_notice.do?act=pop","note_pop","left = 50, top = 50, width = 450, height = 300,scrollbars = yes,toolbar = no, location = no,resizable = yes,status = yes" ); 

</script>

</head>

<body style="overflow: hidden"> 
<div align="center" class="logo">&nbsp;华腾项目管理系统</div>

<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">

  <tr>
    <td class="IdxFrame_Top_Frame">
    <table width="100%"  border="0" cellspacing="1" cellpadding="0" >
      <tr>
        <td height="20" class="IdxFrame_Top_Main">
        <table width="100%" border="0" align="right" cellpadding="0" cellspacing="0">
          <tr>
            <td  align="left">&nbsp;欢迎您！<%=user.getUserName()%>&nbsp;&nbsp;当前角色：<%=user.getRoleName()%>&nbsp;登录IP：<%=request.getRemoteAddr()%></td>
            <td width="125" align="right">
            </td>
            <td width="75" align="center"><a href="<%=homepage%>" target="main"><img src="images/ICO_HomePage.gif" alt="回到首页" width="16" height="16" border="0" align="absmiddle"></a>&nbsp;<a href=javascript:window.external.AddFavorite('<%=request.getRequestURL()%>','贷记卡外挂平台')><img src="images/ICO_Favorites.gif" alt="加入收藏夹" width="16" height="16" border="0" align="absmiddle"></a>&nbsp;<a href="UserLogout.do"><img src="images/ICO_Exit.gif" alt="注销"  border="0" width="16" height="16" align="absmiddle"></a>
            </td>
          </tr>
        </table></td>
      </tr>
    </table></td>
  </tr>
</table>

<table width="100%" height="95%" border="0" align="center" cellpadding="0" cellspacing="0"> 
  <tr> 
    <td valign="top" id="frmTitle" nowrap> <iframe frameborder="0" id="DTree" name="left" scrolling="auto" src="Menu.do?act=listWithPerm" style="overflow:auto;HEIGHT: 100%; WIDTH: 230px; Z-INDEX: 2"> </iframe> </td> 
    <td valign="top" style="WIDTH: 9pt"> <table border="0" cellpadding="0" cellspacing="0" height="100%"> 
        <tr> 
          <td valign="top" class="IdxFrame_Bar" style="HEIGHT: 100%" onclick="switchSysBar()"><span class="GrayFont" > 
    <br> <br> <br> <br> <br>  <br> <br> <br> <br> <br>  <br> <br> <br> <br> <br><span id="switchPoint">&lt;</span> <br>  
          </span></td> 
        </tr> 
    </table></td> 
    <td valign="top" style="WIDTH: 100%"> <iframe frameborder="0" id="main" name="main" scrolling="auto" src="<%=homepage%>" style="overflow:auto;HEIGHT: 90%; WIDTH: 100%; Z-INDEX: 1"> </iframe> </td> 
  </tr> 
  <tr><td colspan="3"><img name="TopPic_r1_c1" src="images/bottom.jpg" width="100%"  border="0" alt=""></td></tr>
</table> 
</body>
</html>
