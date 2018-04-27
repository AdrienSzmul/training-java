<%@ tag body-content="empty"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="cst" tagdir="/WEB-INF/tags"%>

<c:set var="end" value="${pageDTO.maxPageNumber}" />

<div class="container text-center">
	<ul class="pagination">
		<li><a
			href="<cst:links target="dashboard" pageIndex="0" eltNumber="${pageDTO.tailleMax}" search="${search}" orderby="${orderby}"/>"
			aria-label="First"> <span aria-hidden="true">&laquo;&laquo;</span>
		</a></li>
		<li><a
			href="<cst:links target="dashboard" pageIndex="${(pageDTO.currentPageNumber-1)<0?0:pageDTO.currentPageNumber-1}" eltNumber="${pageDTO.tailleMax}" search="${search}" orderby="${orderby}"/>"
			aria-label="Previous"> <span aria-hidden="true">&laquo;</span>
		</a></li>
		<li><a
			href="<cst:links target="dashboard" pageIndex="1" eltNumber="${pageDTO.tailleMax}" search="${search}" orderby="${orderby}"/>">1</a></li>
		<li><a
			href="<cst:links target="dashboard" pageIndex="2" eltNumber="${pageDTO.tailleMax}" search="${search}" orderby="${orderby}"/>">2</a></li>
		<li><a
			href="<cst:links target="dashboard" pageIndex="3" eltNumber="${pageDTO.tailleMax}" search="${search}" orderby="${orderby}"/>">3</a></li>
		<li><a
			href="<cst:links target="dashboard" pageIndex="4" eltNumber="${pageDTO.tailleMax}" search="${search}" orderby="${orderby}"/>">4</a></li>
		<li><a
			href="<cst:links target="dashboard" pageIndex="5" eltNumber="${pageDTO.tailleMax}" search="${search}" orderby="${orderby}"/>">5</a></li>
		<li><a
			href="<cst:links target="dashboard" pageIndex="${pageDTO.currentPageNumber+1>end?end:pageDTO.currentPageNumber+1}" eltNumber="${pageDTO.tailleMax}" search="${search}" orderby="${orderby}"/>"
			aria-label="Next"> <span aria-hidden="true">&raquo;</span>
		</a></li>
		<li><a
			href="<cst:links target="dashboard" pageIndex="${end-1}" eltNumber="${pageDTO.tailleMax}" search="${search}" orderby="${orderby}"/>"
			aria-label="Last"> <span aria-hidden="true">&raquo;&raquo;</span>
		</a></li>
	</ul>
</div>