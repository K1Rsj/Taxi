<%@ include file="../components/addition.jsp"%>
<jsp:include page="../components/head.jsp"/>


<div class="content container">

    <c:if test="${not empty requestScope.message}">
        <p>${requestScope.message}</p>
    </c:if>


    <table class="table table-striped bg-white text-black">
        <caption class="text-white">Your orders</caption>
        <thead>
        <tr>
            <th scope="col">#</th>
            <th scope="col">Departure street</th>
            <th scope="col">Destination street</th>
            <th scope="col">Price</th>
            <th scope="col">Car model</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${requestScope.orders}" var="order">
            <c:set var="count" value="${pageScope.count + 1}" scope="page"/>
            <tr>
                <td><c:out value="${count}" /></td>
                <td><c:out value="${order.departureStreet}" /></td>
                <td><c:out value="${order.destinationStreet}" /></td>
                <td><c:out value="${order.price/100}"/></td>
                <td><c:out value="${order.car.model}" /></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

<nav aria-label="Page navigation">
    <ul class="pagination justify-content-center">
        <c:forEach begin="1" end="${requestScope.numberOfPages}">
            <c:set var="pageCounter" value="${pageScope.pageCounter + 1}" scope="page"/>
            <form action="${pageContext.request.contextPath}/taxi/my_orders" method="post">
                <input type="hidden" value="${pageCounter}" name="currentPage">
                <input style="margin: 2px" type="submit" class="btn btn btn-info btn-lg" value="${pageCounter}">
            </form>

        </c:forEach>
    </ul>
</nav>


