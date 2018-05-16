<%@ include file="../components/addition.jsp"%>
<jsp:include page="../components/head.jsp"/>

<c:if test="${empty sessionScope.role}">
    <jsp:include page="../components/header.jsp"/>
</c:if>
<c:if test="${sessionScope.role eq 'USER'}">
    <jsp:include page="user_header.jsp"/>
</c:if>
<c:if test="${sessionScope.role eq 'ADMIN'}">
    <jsp:include page="../admin/admin_header.jsp"/>
</c:if>

<jsp:include page="../tables/all_car_types_table.jsp"/>
<jsp:include page="../components/footer.jsp"/>


