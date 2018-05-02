<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tag"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<spring:url value="/static/css/bootstrap.min.css" var="cssBoot" />
<spring:url value="/static/css/font-awesome.css" var="fontAwe" />
<spring:url value="/static/css/main.css" var="mainCss" />
<spring:url value="/static/js/jquery.min.js" var="jQuery" />
<spring:url value="/static/js/bootstrap.min.js" var="jsBoot" />
<spring:url value="/static/js/dashboard.js" var="jsDash" />
<!-- Bootstrap -->
<link href="${cssBoot}" rel="stylesheet" media="screen">
<link href="${fontAwe}" rel="stylesheet" media="screen">
<link href="${mainCss}" rel="stylesheet" media="screen">
<title>Login Computer Database</title>
</head>
<body>
	<h1>Login</h1>
	<form name='f' action="<tag:links target="dashboard"/>" method='POST'>
		<table>
			<tr>
				<td>User:</td>
				<td><input type='text' name='username' value=''></td>
			</tr>
			<tr>
				<td>Password:</td>
				<td><input type='password' name='password' /></td>
			</tr>
			<tr>
				<td><input name="submit" type="submit" value="submit" /></td>
			</tr>
		</table>
	</form>
	<script src="${jQuery}"></script>
	<script src="${jsBoot}"></script>
	<script src="${jsDash}"></script>
</body>
</html>