<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tag"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Computer Database</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta charset="utf-8">
<!-- Bootstrap -->
<link href="static/css/bootstrap.min.css" rel="stylesheet"
	media="screen">
<link href="static/css/font-awesome.css" rel="stylesheet" media="screen">
<link href="static/css/main.css" rel="stylesheet" media="screen">
</head>
<body>
	<header class="navbar navbar-inverse navbar-fixed-top">
	<div class="container">
		<a class="navbar-brand" href="<tag:links target="dashboard"/>">
			Application - Computer Database </a>
	</div>
	</header>

	<section id="main">
	<div class="container">
		<h1 id="homeTitle">
			<c:out value="${pageDTO.nombreElt}" />
			Computers found
		</h1>
		<div id="actions" class="form-horizontal">
			<div class="pull-left">
				<form id="searchForm"
					action="<tag:links target="dashboard" search="${search}" pageIndex="${pageDTO.currentPageNumber}" eltNumber="${pageDTO.tailleMax}"/>"
					method="GET" class="form-inline">

					<input type="search" id="searchbox" name="search"
						class="form-control" placeholder="Search name" /> <input
						type="submit" id="searchsubmit" value="Filter by name"
						class="btn btn-primary" />
				</form>
			</div>
			<div class="pull-right">
				<a class="btn btn-success" id="addComputer" href="AddComputer">Add
					Computer</a> <a class="btn btn-default" id="editComputer" href="#"
					onclick="$.fn.toggleEditMode();">Edit</a>
			</div>
		</div>
	</div>

	<form id="deleteForm" action="Dashboard" method="POST">
		<input type="hidden" name="selection">
	</form>

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
						onclick="" class="sort-by">Computer name</a></th>
					<th><a
						href="<tag:links target="dashboard" orderby="INTRODUCED" tmpOrderBy="${orderby}"  ascdesc="${ascdesc}"/>"
						onclick="" class="sort-by">Introduced date</a></th>
					<!-- Table header for Discontinued Date -->
					<th><a
						href="<tag:links target="dashboard" orderby="DISCONTINUED" tmpOrderBy="${orderby}"  ascdesc="${ascdesc}"/>"
						onclick="" class="sort-by">Discontinued date</a></th>
					<!-- Table header for Company -->
					<th><a
						href="<tag:links target="dashboard" orderby="COMPANY" tmpOrderBy="${orderby}"  ascdesc="${ascdesc}"/>"
						onclick="" class="sort-by">Company</a></th>

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

	<footer class="navbar-fixed-bottom"> <tag:eltNumber /> <tag:pageNumber />

	</footer>
	<script src="static/js/jquery.min.js"></script>
	<script src="static/js/bootstrap.min.js"></script>
	<script src="static/js/dashboard.js"></script>

</body>
</html>