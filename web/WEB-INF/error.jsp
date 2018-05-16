<%@ include file="components/addition.jsp"%>
<jsp:include page="components/head.jsp"/>

<div>
<c:if test="${not empty requestScope.errorMessage}">
    <div class="alert alert-info text-center text-white" >${requestScope.errorMessage}</div>
</c:if>
</div>
<div class="alert text-white text-center">
    <p>Oops something went wrong</p>
    <a class="btn btn-default" href="${pageContext.request.contextPath}/taxi/index" role="button" ></a>
</div>
<jsp:include page="components/footer.jsp"/>
