<%@ taglib prefix="mytags" uri="https://mytags.com.ua" %>
<%@ include file="../components/addition.jsp"%>
<jsp:include page="../components/head.jsp"/>


<div class="content container">

    <c:if test="${not empty requestScope.message}">
        <p>${requestScope.message}</p>
    </c:if>


    <table class="table table-striped bg-white text-black">
        <caption class="text-white"><fmt:message key="car.type"/></caption>
        <thead>
        <tr>
            <th scope="col">#</th>
            <th scope="col"><fmt:message key="type"/></th>
            <th scope="col"><fmt:message key="starting.price"/></th>
            <th scope="col"><fmt:message key="price.per.km"/></th>
            <th scope="col"><fmt:message key="discount.value"/></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${requestScope.carTypes}" var="type">
            <c:set var="count" value="${pageScope.count + 1}" scope="page"/>
            <tr>
                <td><c:out value="${count}" /></td>
                <td><c:out value="${type.type}" /></td>
                <td><mytags:formatMoney money="${type.startingPrice}"/> <fmt:message key="hryvnia"/></td>
                <td><mytags:formatMoney money="${type.pricePerKilometer}"/> <fmt:message key="hryvnia"/></td>
                <td><c:out value="${type.discount}%" /></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>


