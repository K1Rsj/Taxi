<%@ include file="../components/addition.jsp"%>
<jsp:include page="../components/head.jsp"/>
<jsp:include page="user_header.jsp"/>

<div class="content container">
    <c:out value="Your discount is ${requestScope.discount}%" />
</div>
<jsp:include page="../components/footer.jsp"/>

