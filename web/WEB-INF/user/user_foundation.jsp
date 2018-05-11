<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: HOME
  Date: 02.05.2018
  Time: 21:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head>
    <title>User</title>
</head>
<body>
    <p>Hello user</p>

    <c:if test="${requestScope.orderInformationMessage != null}">
        <p>${requestScope.orderInformationMessage}</p>
    </c:if>

    <c:if test="${sessionScope.order != null}">

    <table border="1">
        <tbody>
        <tr><td><c:out value="${sessionScope.order.user.firstName} ${sessionScope.order.user.secondName}"/></td></tr>
        <tr><td>Your car is <c:out value="${sessionScope.order.car.model} ${sessionScope.order.car.number}"/></td></tr>
        <tr><td>Your trip costs <c:out value="${sessionScope.order.price/100} hrivnas" /></td></tr>
        <tr><td>From <c:out value="${sessionScope.order.departureStreet} to ${sessionScope.order.destinationStreet}"/></td></tr>
        </tbody>
    </table>
        <br>
        <a href="${pageContext.request.contextPath}/taxi/cancel_order">Cancel order</a>
        <br>
        <a href="${pageContext.request.contextPath}/taxi/confirm_order">Confirm order</a>
    </c:if>
    <br>
    <a href="${pageContext.request.contextPath}/taxi/logout">Log Out</a>
    <br>
    <a href="${pageContext.request.contextPath}/taxi/make_order_page">Make order</a>
</body>
</html>
