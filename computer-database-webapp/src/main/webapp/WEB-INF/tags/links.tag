<%@ tag body-content="empty"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ attribute name="target" required="true"%>
<%@ attribute name="pageIndex" required="false"%>
<%@ attribute name="eltNumber" required="false"%>
<%@ attribute name="computerId" required="false"%>
<%@ attribute name="search" required="false"%>
<%@ attribute name="orderby" required="false"%>
<%@ attribute name="tmpOrderBy" required="false"%>
<%@ attribute name="ascdesc" required="false"%>

<c:set var="pathDash" value="/computer-database/dashboard" />

<c:set var="emptyText" value="" />
<c:set var="tmpPath" value="" />

<c:set var="tmpPageIndex"
	value="${emptyText.concat('?pageNumber=').concat(pageIndex)}" />
<c:set var="tmpEltNumber"
	value="${emptyText.concat('&eltNumber=').concat(eltNumber)}" />
<c:set var="tmpSearch"
	value="${emptyText.concat('&search=').concat(search)}" />
<c:set var="tmpPathOrderBy"
	value="${emptyText.concat('&orderby=').concat(orderby)}" />
<c:set var="tmpAscDesc"
	value="${emptyText.concat('&ascdesc=').concat(ascdesc)}" />

<c:choose>
	<c:when test="${ not empty target }">
		<c:choose>
			<c:when test="${target.equals('dashboard')}">
				<c:set var="tmpPath" value="${tmpPath.concat(pathDash)}" />
				<c:if
					test="${ not empty pageIndex and ''.concat(pageIndex).matches('[0-9]+')}">
					<c:set var="tmpPageIndex"
						value="${ emptyText.concat('?pageNumber=').concat(pageIndex) }" />
				</c:if>
				<c:if
					test="${ not empty eltNumber and ''.concat(eltNumber).matches('[0-9]+')}">
					<c:set var="tmpEltNumber"
						value="${ emptyText.concat('&eltNumber=').concat(eltNumber) }" />
				</c:if>
				<c:if
					test="${ not empty search and ''.concat(search).matches('^[a-zA-Z0-9]*$') }">
					<c:set var="tmpSearch"
						value="${emptyText.concat('&search=').concat(search)}" />
				</c:if>
				<c:if
					test="${ not empty orderby and ''.concat(orderby).matches('^[a-zA-Z0-9]*$') }">

					<c:set var="tmpPathOrderBy"
						value="${emptyText.concat('&orderby=').concat(orderby)}" />
					<c:if test="${orderby.equals(tmpOrderBy)}">
						<c:set var="tmpAscDesc"
							value="${emptyText.concat('&ascdesc=').concat(not ascdesc)}" />
					</c:if>
				</c:if>

				<c:set var="tmpPath" value="${ tmpPath.concat(tmpPageIndex) }" />
				<c:set var="tmpPath" value="${ tmpPath.concat(tmpEltNumber) }" />
				<c:set var="tmpPath" value="${ tmpPath.concat(tmpSearch) }" />
				<c:set var="tmpPath" value="${ tmpPath.concat(tmpPathOrderBy) }" />
				<c:set var="tmpPath" value="${ tmpPath.concat(tmpAscDesc) }" />
			</c:when>
			<c:when test="${target.equals('edit')}">
				<c:set var="tmpPath" value="${tmpPath.concat('/computer-database/editComputer')}" />
				<c:if
					test="${not empty computerId and ''.concat(computerId).matches('[0-9]+')}">
					<c:set var="tmpPath"
						value="${tmpPath.concat('?computerId=').concat(computerId)}" />
				</c:if>
			</c:when>
			<c:when test="${target.equals('add')}">
				<c:set var="tmpPath" value="${tmpPath.concat('/computer-database/addComputer')}" />
			</c:when>
		</c:choose>
	</c:when>
	<c:otherwise>
		<c:set var="tmpPath" value="${tmpPath.concat(pathDash)}" />
	</c:otherwise>
</c:choose>



<c:out value="${tmpPath}" escapeXml="false" />

