
<%@ page contentType="text/html; charset=GBK"%>

<HTML>
<HEAD>


<TITLE>��Ϣ��ʾ</TITLE>
</HEAD>
<BODY>
<TABLE align="center">
<tr align="center" valign="middle"><td><img src="images/sorry01.gif"></img></td></tr>
<%String msg = (String)request.getAttribute("msg"); %>
<tr><td><%if(msg==null || msg.trim().length()==0){
	out.print("��¼��������Ѿ��ύ�����Ժ��飬��Ҫ�ظ��ύ����");
	}else{
		out.print(msg);
	}
 %>
</td></tr>
</TABLE>
</BODY>
</HTML>
