<%@ include file="../components/addition.jsp"%>
<jsp:include page="../components/head.jsp"/>

<div class="content">
<c:if test="${not empty requestScope.informationMessage }">
    <h3>${requestScope.informationMessage}</h3>
</c:if>
</div>