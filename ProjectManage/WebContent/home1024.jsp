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

<title>���ǿ����ƽ̨</title>
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
 
	//window.open( "LogIndex.do?act=note", "note_pop", 
		//	"left = 50, top = 50, width = 450, height = 300, scrollbars = no, toolbar = no, location = no, menubar = no, resizable = no, status = yes" );
<%//if(com.huateng.creditcard.sysadmin.noticemanage.NoteMap.getSumNotice()>1){%>
	//window.open("Ep_notice.do?act=pop","note_pop","left = 50, top = 50, width = 450, height = 300,scrollbars = yes,toolbar = no, location = no,resizable = yes,status = yes" ); 
<%//}%>
</script>

</head>

<body style="overflow: hidden"> 
<div align="center"><img name="TopPic_r1_c1" src="images/Logo.jpg" width="100%" height="10%" border="0" alt=""></div>

<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">

  <tr>
    <td class="IdxFrame_Top_Frame">
    <table width="100%"  border="0" cellspacing="1" cellpadding="0" >
      <tr>
        <td height="20" class="IdxFrame_Top_Main">
        <table width="100%" border="0" align="right" cellpadding="0" cellspacing="0">
          <tr>
            <td  align="left">&nbsp;��ӭ����<%=user.getUserName()%>&nbsp;&nbsp;��ǰ��ɫ��<%=user.getRoleName()%>&nbsp;��¼IP��<%=request.getRemoteAddr()%></td>
            <td width="125" align="right">
            </td>
            <td width="75" align="center"><a href="<%=homepage%>" target="main"><img src="images/ICO_HomePage.gif" alt="�ص���ҳ" width="16" height="16" border="0" align="absmiddle"></a>&nbsp;<a href=javascript:window.external.AddFavorite('<%=request.getRequestURL()%>','���ǿ����ƽ̨')><img src="images/ICO_Favorites.gif" alt="�����ղؼ�" width="16" height="16" border="0" align="absmiddle"></a>&nbsp;<a href="UserLogout.do"><img src="images/ICO_Exit.gif" alt="ǩ��"  border="0" width="16" height="16" align="absmiddle"></a>
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
