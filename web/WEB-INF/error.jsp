<%@ include file="components/addition.jsp"%>
<jsp:include page="components/head.jsp"/>
<div class="alert text-white text-center">
    <div class="alert alert-danger text-center error" ><fmt:message key="something.went.wrong"/></div>
    <a href="${pageContext.request.contextPath}/taxi/index" class="btn btn-danger" role="button"><fmt:message key="home"/></a>
</div>
<jsp:include page="components/footer.jsp"/>
