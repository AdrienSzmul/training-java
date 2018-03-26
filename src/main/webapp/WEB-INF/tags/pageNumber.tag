<%@ tag body-content="empty"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="cst" tagdir="/WEB-INF/tags"%>

<c:set var="end" value="${maxNumberPages}"/>

<div class="container text-center">
	<ul class="pagination">
		<li><a href="<cst:links target="self" pageIndex="${pageIndex-1<0?0:pageIndex-1}"/>" aria-label="Previous"> <span
				aria-hidden="true">&laquo;</span>
		</a></li>
		<li><a href="<cst:links target="self" pageIndex="1"/>">1</a></li>
		<li><a href="<cst:links target="self" pageIndex="2"/>">2</a></li>
		<li><a href="<cst:links target="self" pageIndex="3"/>">3</a></li>
		<li><a href="<cst:links target="self" pageIndex="4"/>">4</a></li>
		<li><a href="<cst:links target="self" pageIndex="5"/>">5</a></li>
		<li><a href="<cst:links target="self" pageIndex="${pageIndex+1>end?end:pageIndex+1}"/>" aria-label="Next"> <span aria-hidden="true">&raquo;</span>
		</a></li>
	</ul>
</div>