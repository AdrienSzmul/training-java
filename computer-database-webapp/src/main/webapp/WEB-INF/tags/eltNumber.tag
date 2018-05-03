<%@ tag body-content="empty"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="cst" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>


<div class="btn-group btn-group-sm pull-right" role="group">
	<c:forEach items="${ eltNumberList }" var="eltNumber">
		<a
			href='<cst:links target="dashboard" eltNumber="${eltNumber}" pageIndex="${pageDTO.currentPageNumber}" search="${search}" orderby="${orderby}" />'>
			<button type="button" class="btn btn-default">${eltNumber}</button>
		</a>
	</c:forEach>
	
</div>