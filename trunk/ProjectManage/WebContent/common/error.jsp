
<HTML>
<HEAD>
<%@ page import = "com.eis.util.SysLog"%>
<%@ page contentType="text/html; charset=GBK" %>
<%@ page isErrorPage="true"%>

<TITLE>�����쳣</TITLE>
<%
	SysLog.error("ִ��ҳ��������������쳣��");
	
	if(exception instanceof NullPointerException) {
		SysLog.error("ҳ�淢����ָ���쳣������Ա����ĸ�ֵ�Ƿ�������");
	}
	SysLog.error(exception.getMessage());
	
%>
</HEAD>
<BODY>
<script language="javascript">
alert('�����ʵ�ҳ�淢������');
history.back();

</script>
</BODY>
</HTML>
