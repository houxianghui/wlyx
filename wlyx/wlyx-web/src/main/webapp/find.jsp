<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=GB18030"
    pageEncoding="GB18030"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" media="screen" href="style.css" />

<meta http-equiv="Content-Type" content="text/html; charset=GB18030">
<title>��Ʒ����</title>
</head>
<body>
	<table cellspacing="1" border="0" cellpadding="0" class=".ext">
	<tr>
		<th>��ɫ����</th>
		<th>��Ʒ����</th>
		<th>��Ʒ����</th>
		<th>λ��</th>
	</tr>
	<c:forEach items="${result}" var="i">
		<tr>
		<td><c:out value="${i.roleName}"></c:out></td>
		<td><c:out value="${i.itemName}"></c:out></td>
		<td><c:out value="${i.count}"></c:out></td>
		<td><c:out value="${i.position}"></c:out></td>
		</tr>
	</c:forEach>
	</table>
</body>
</html>