<%@ page contentType="text/html; charset=GBK"%>

<HTML>
<HEAD>

<TITLE>重新登录</TITLE>
<script language="javascript">	
		alert('您的会话已经超时！请重新登录！');	
		parent.parent.window.location.href="<%=request.getContextPath()%>/";

</script>
</HEAD>
<BODY>
</BODY>
</HTML>
