
<%@ page contentType="text/html; charset=GBK"%>

<HTML>
<HEAD>


<TITLE>信息提示</TITLE>
</HEAD>
<BODY>
<TABLE align="center">
<tr align="center" valign="middle"><td><img src="images/sorry01.gif"></img></td></tr>
<%String msg = (String)request.getAttribute("msg"); %>
<tr><td><%if(msg==null || msg.trim().length()==0){
	out.print("您录入的数据已经提交，请稍后检查，不要重复提交数据");
	}else{
		out.print(msg);
	}
 %>
</td></tr>
</TABLE>
</BODY>
</HTML>
