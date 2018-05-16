<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tag"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
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
<title><spring:message code="field.loginTitle"/></title>
</head>

<body>
	<header class="navbar navbar-inverse navbar-fixed-top">
	<div class="container">
		<a class="navbar-brand" href="<tag:links target="dashboard"/>"><spring:message
				code="field.header" /></a>
	</div>
	</header>



	<section id="main">
	<div class="container">
		<div class="row">
			<div class="col-xs-8 col-xs-offset-2 box">
				<h1><spring:message code="field.loginH"/></h1>
				<form:form name='f' action="login" method='POST'>
					<fieldset>
						<div class="form-group">
							<label><spring:message code="field.user"/></label> <input type='text' name='username' value='' class="form-control" />
						</div>
						<div class="form-group">
							<label><spring:message code="field.pwd"/></label> <input type='password' name='password' class="form-control" />
						</div>
					</fieldset>
					<div class="actions pull-right">
						<input type="submit" value="Login" class="btn btn-primary" />
					</div>
				</form:form>
			</div>
		</div>
	</div>
	</section>
	<script src="${jQuery}"></script>
	<script src="${jsBoot}"></script>
	<script src="${jsDash}"></script>
</body>

</html>