<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tag"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title><spring:message code="field.title"/></title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- Bootstrap -->
<link href="static/css/bootstrap.min.css" rel="stylesheet"
	media="screen">
<link href="static/css/font-awesome.css" rel="stylesheet" media="screen">
<link href="static/css/main.css" rel="stylesheet" media="screen">
</head>
<body>
	<header class="navbar navbar-inverse navbar-fixed-top">
	<div class="container">
		<a class="navbar-brand" href="Dashboard"><spring:message code="field.header"/></a>
	</div>
	</header>

	<section id="main">
	<div class="container">
		<div class="row">
			<div class="col-xs-8 col-xs-offset-2 box">
				<h1><spring:message code="field.btnEditComp"/></h1>
				<c:set var="formPathEdit">
					<tag:links target="edit" />
				</c:set>
				<form:form action="${formPathEdit}" method="POST"
					modelAttribute="computerDTO">
					<fieldset>
						<form:input type="hidden" id="id" path="id" value="${computerDTO.id}"/>
						<div class="form-group">
							<form:label path="name"><spring:message code="field.tableHeadName"/></form:label>
							<form:input data-validation="length alphanumeric"
								data-validation-allowing="-_ " data-validation-length="4-20"
								data-validation-error-msg="Only alpahanu and between 4 to 20 char"
								type="text" class="form-control" id="computerName" path="name"
								placeholder="Computer name" value="${computerDTO.name}" />
						</div>
						<div class="form-group">
							<form:label path="introduced"><spring:message code="field.tableHeadIntroduced"/></form:label>
							<form:input data-validation="date"
								data-validation-optional="true"
								data-validation-error-msg="Date format is dd/mm/yyyy"
								type="date" class="form-control" id="introduced"
								path="introduced" placeholder="Introduced date"
								value="${computerDTO.introduced}" />
						</div>
						<div class="form-group">
							<form:label path="discontinued"><spring:message code="field.tableHeadDiscontinued"/></form:label>
							<form:input data-validation="date"
								data-validation-optional="true"
								data-validation-error-msg="Date format is dd/mm/yyyy"
								type="date" class="form-control" id="discontinued"
								path="discontinued" placeholder="Discontinued date"
								value="${computerDTO.discontinued}" />
						</div>
						<div class="form-group">
							<form:label path="company.id"><spring:message code="field.tableHeadCompany"/></form:label>
							<form:select class="form-control" id="companyId"
								path="company.id">
								<form:option value="0">--
									
								</form:option>
								<form:options items="${listCompanies}" itemValue="id"></form:options>
							</form:select>
						</div>
					</fieldset>
					<div class="actions pull-right">
						<input type="submit" value="<spring:message code="field.btnEdit"/>" class="btn btn-primary" /> or <a
							href="dashboard" class="btn btn-default"><spring:message code="field.cancel"/></a>
					</div>
				</form:form>
			</div>
		</div>
	</div>
	</section>
	<script
		src="//ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
	<script
		src="//cdnjs.cloudflare.com/ajax/libs/jquery-form-validator/2.3.26/jquery.form-validator.min.js"></script>
	<script>
		$.validate({
			lang : 'fr'
		});
	</script>
</body>
</html>