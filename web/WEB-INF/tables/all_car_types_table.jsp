<%@ include file="../components/addition.jsp"%>
<jsp:include page="../components/head.jsp"/>


<div class="content container">

    <c:if test="${not empty requestScope.message}">
        <p>${requestScope.message}</p>
    </c:if>


    <table class="table table-striped bg-white text-black">
        <caption class="text-white">Car types</caption>
        <thead>
        <tr>
            <th scope="col">#</th>
            <th scope="col">Type</th>
            <th scope="col">Starting price</th>
            <th scope="col">Price per kilometer</th>
            <th scope="col">Discount</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${requestScope.carTypes}" var="type">
            <c:set var="count" value="${pageScope.count + 1}" scope="page"/>
            <tr>
                <td><c:out value="${count}" /></td>
                <td><c:out value="${type.type}" /></td>
                <td><c:out value="${type.startingPrice/100} hrivnas" /></td>
                <td><c:out value="${type.pricePerKilometer/100} hrivnas" /></td>
                <td><c:out value="${type.discount}%" /></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>


