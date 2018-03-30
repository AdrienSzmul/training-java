<%@ tag body-content="empty"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="cst" tagdir="/WEB-INF/tags"%>

<c:set var="end" value="${maxNumberPages}"/>

<div class="container text-center">
	<ul class="pagination">
		<li><a href="<cst:links target="dashboard" pageIndex="${pageIndex-1<0?0:pageIndex-1}" eltNumber="${eltNumber}" search="${search}"/>" aria-label="Previous"> <span
				aria-hidden="true">&laquo;</span>
		</a></li>
		<li><a href="<cst:links target="dashboard" pageIndex="1" eltNumber="${eltNumber}" search="${search}"/>">1</a></li>
		<li><a href="<cst:links target="dashboard" pageIndex="2" eltNumber="${eltNumber}" search="${search}"/>">2</a></li>
		<li><a href="<cst:links target="dashboard" pageIndex="3" eltNumber="${eltNumber}" search="${search}"/>">3</a></li>
		<li><a href="<cst:links target="dashboard" pageIndex="4" eltNumber="${eltNumber}" search="${search}"/>">4</a></li>
		<li><a href="<cst:links target="dashboard" pageIndex="5" eltNumber="${eltNumber}" search="${search}"/>">5</a></li>
		<li><a href="<cst:links target="dashboard" pageIndex="${pageIndex+1>end?end:pageIndex+1}" eltNumber="${eltNumber}" search="${search}"/>" aria-label="Next"> <span aria-hidden="true">&raquo;</span>
		</a></li>
	</ul>
</div>