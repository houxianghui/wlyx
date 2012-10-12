<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=GB18030"
    pageEncoding="GB18030"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<link rel="stylesheet" type="text/css" media="screen" href="style.css" />

<meta http-equiv="Content-Type" content="text/html; charset=GB18030">
<title>暗影刺客</title>
</head>
<body>
	<table cellspacing="1" border="0" cellpadding="0" class=".ext">
	<tr>
		<th>角色名称</th>
		<th>派系</th>
		<th>等级</th>
		<th>精力点数</th>
		<th>HP</th>
		<th>MP</th>
		<th>状态</th>
		<th>身份</th>
		<th>所在位置</th>
		<th>当日福利</th>
		<th>任务完成情况</th>
		<th>竞技完成情况</th>
	</tr>
	<c:forEach items="${users}" var="user">
		<tr>
		<td><a href="<c:out value="${user.url}"/>" target="_blank"><c:out value="${user.roleName}"></c:out></a></td>
		<td><c:out value="${user.profession}"></c:out></td>
		<td><c:out value="${user.level}"></c:out></td>
		<td><c:out value="${user.point}"></c:out></td>
		<td><c:out value="${user.currHP}"></c:out>/<c:out value="${user.maxHP}"></c:out></td>
		<td><c:out value="${user.currMP}"></c:out>/<c:out value="${user.maxMP}"></c:out></td>
		<td><c:out value="${user.status}"></c:out></td>
		<td><c:out value="${user.capacity}"></c:out></td>
		<td><c:out value="${user.position}"></c:out></td>
		<td><c:out value="${user.gotWeals}"></c:out></td>
		<td><c:out value="${user.taskInfo}"></c:out></td>
		<td><c:out value="${user.duelInfo}"></c:out></td>
		</tr>
	</c:forEach>
	</table>
</body>
</html>