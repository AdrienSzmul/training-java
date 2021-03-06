<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tag"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title><spring:message code="field.title" /></title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta charset="utf-8">
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
		<h1 id="homeTitle">
			<c:out value="${pageDTO.nombreElt}" />
			<spring:message code="field.found" />
			<form:form action="logout" method="POST">
				<input type="submit" name="logout" value="Logout">
			</form:form>
		</h1>
		<div id="actions" class="form-horizontal">
			<div class="pull-left">
				<form id="searchForm"
					action="<tag:links target="dashboard" search="${search}" pageIndex="${pageDTO.currentPageNumber}" eltNumber="${pageDTO.tailleMax}"/>"
					method="GET" class="form-inline">

					<input type="search" id="searchbox" name="search"
						class="form-control"
						placeholder="<spring:message code="field.search"/>" /> <input
						type="submit" id="searchsubmit"
						value="<spring:message code="field.btnSearch"/>"
						class="btn btn-primary" />
				</form>
			</div>
			<div class="pull-right">
				<a class="btn btn-success" id="addComputer"
					href="<tag:links target="add"/>"><spring:message
						code="field.btnAdd" /></a> <a class="btn btn-default"
					id="editComputer" href="#" onclick="$.fn.toggleEditMode();"><spring:message
						code="field.btnEdit" /></a>
			</div>
		</div>
	</div>

	<form:form id="deleteForm" action="dashboard" method="POST">
		<input type="hidden" name="selection">
	</form:form>

	<div class="container" style="margin-top: 10px;">
		<table class="table table-striped table-bordered">
			<thead>
				<tr>
					<!-- Variable declarations for passing labels as parameters -->
					<!-- Table header for Computer Name -->

					<th class="editMode" style="width: 60px; height: 22px;"><input
						type="checkbox" id="selectall" /> <span
						style="vertical-align: top;"> - <a href="#"
							id="deleteSelected" onclick="$.fn.deleteSelected();"> <i
								class="fa fa-trash-o fa-lg"></i>
						</a>
					</span></th>
					<th><a
						href="<tag:links target="dashboard" orderby="NAME" tmpOrderBy="${orderby}" ascdesc="${ascdesc}"/>"
						onclick="" class="sort-by"><spring:message
								code="field.tableHeadName" /></a></th>
					<th><a
						href="<tag:links target="dashboard" orderby="INTRODUCED" tmpOrderBy="${orderby}"  ascdesc="${ascdesc}"/>"
						onclick="" class="sort-by"><spring:message
								code="field.tableHeadIntroduced" /></a></th>
					<!-- Table header for Discontinued Date -->
					<th><a
						href="<tag:links target="dashboard" orderby="DISCONTINUED" tmpOrderBy="${orderby}"  ascdesc="${ascdesc}"/>"
						onclick="" class="sort-by"><spring:message
								code="field.tableHeadDiscontinued" /></a></th>
					<!-- Table header for Company -->
					<th><a
						href="<tag:links target="dashboard" orderby="COMPANY" tmpOrderBy="${orderby}"  ascdesc="${ascdesc}"/>"
						onclick="" class="sort-by"><spring:message
								code="field.tableHeadCompany" /></a></th>

				</tr>
			</thead>
			<!-- Browse attribute computers -->
			<tbody id="results">
				<c:forEach items="${pageDTO.listContent}" var="computer">
					<tr>
						<td class="editMode"><input type="checkbox" name="cb"
							class="cb" value="${computer.id}"></td>
						<td><a
							href="<tag:links target="edit" computerId="${computer.id}"/>"
							onclick=""><c:out value="${computer.name}" /></a></td>
						<td><c:out value="${computer.introduced}" /></td>
						<td><c:out value="${computer.discontinued}" /></td>
						<td><c:out value="${computer.company.name}" /></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	</section>

	<footer class="navbar-fixed-bottom"> <tag:eltNumber />
	<tag:pageNumber /> </footer>

	<script src="${jQuery}"></script>
	<script src="${jsBoot}"></script>
	<script src="${jsDash}"></script>

</body>
</html>