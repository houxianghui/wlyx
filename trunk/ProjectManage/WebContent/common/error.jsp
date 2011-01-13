
<HTML>
<HEAD>
<%@ page import = "com.eis.util.SysLog"%>
<%@ page contentType="text/html; charset=GBK" %>
<%@ page isErrorPage="true"%>

<TITLE>操作异常</TITLE>
<%
	SysLog.error("执行页面操作发生严重异常！");
	
	if(exception instanceof NullPointerException) {
		SysLog.error("页面发生空指针异常，请检查对变量的赋值是否完整！");
	}
	SysLog.error(exception.getMessage());
	
%>
</HEAD>
<BODY>
<script language="javascript">
alert('您访问的页面发生错误！');
history.back();

</script>
</BODY>
</HTML>
