<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="count" value="${requestScope.firstIndex}" scope="page"/>
<table id="allCarsTable" class="table table-condensed">
    <tr>
        <th>#</th>
        <th>Model</th>
        <th>Number</th>
        <th>State</th>
        <th>Driver</th>
        <th>Type</th>
    </tr>
    <c:forEach var="car" items="${requestScope.cars}">
        <c:set var="count" value="${count + 1}" scope="page"/>
        <tr>
            <td><c:out value="${count}"/></td>
            <td><c:out value="${car.model}"/></td>
            <td><c:out value="${car.number}"/></td>
            <td><c:out value="${car.state}"/></td>
            <td><c:out value="${car.driver}"/></td>
            <td><c:out value="${car.carType.type}"/></td>
        </tr>
    </c:forEach>
</table>

<nav aria-label="Page navigation">
    <ul class="pagination justify-content-center">
        <c:forEach begin="1" end="${requestScope.numberOfPages}">

            <c:set var="pageCounter" value="${pageScope.pageCounter + 1}" scope="page"/>
            <form action="${pageContext.request.contextPath}/taxi/all_cars" method="post">
                <input type="hidden" value="${pageCounter}" name="currentPage">
                <input style="margin: 2px" type="submit" class="btn btn-primary btn-lg" value="${pageCounter}">
            </form>

        </c:forEach>
    </ul>
</nav>
