<%@ include file="../components/addition.jsp"%>
<jsp:include page="../components/head.jsp"/>


<div class="content container">
    <c:if test="${not empty requestScope.informationMessage}">
    <h3>${requestScope.informationMessage}</h3>
    </c:if>
    <c:if test="${not empty requestScope.orderInformationMessage}">
    <p>${requestScope.orderInformationMessage}</p>
    </c:if>

    <c:if test="${not empty sessionScope.order}">

    <table class="table table-striped bg-white text-black">
        <caption class="text-white">Current order</caption>
        <thead>
        <tr>
            <th scope="col">Full name</th>
            <th scope="col">Your car is</th>
            <th scope="col">Your trip costs</th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td><c:out value="${sessionScope.order.user.firstName} ${sessionScope.order.user.secondName}"/></td>
            <td><c:out value="${sessionScope.order.car.model} ${sessionScope.order.car.number}"/></td>
            <td><c:out value="${sessionScope.order.price/100} hrivnas" /></td>
        </tr>
        <tr>
        </tr>
        </tbody>
    </table>
        <div>
            <a href="${pageContext.request.contextPath}/taxi/confirm_order" class="btn btn-success" role="button">Confirm order</a>
            <a href="${pageContext.request.contextPath}/taxi/cancel_order" class="btn btn-danger" role="button">Cancel order</a>
        </div>
</c:if>
</div>
