<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: HOME
  Date: 12.05.2018
  Time: 16:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head>
    <title>My orders</title>
</head>
<body>
<c:if test="${requestScope.message != null}">
    <p>${requestScope.message}</p>
</c:if>
<table border=1>
    <thead>
    <tr>
        <th>Departure street</th>
        <th>Destination street</th>
        <th>Price</th>
        <th>Car model</th>
    </tr>
    </thead>
    <tbody>

    <c:forEach items="${requestScope.orders}" var="order">
        <tr>
            <td><c:out value="${order.departureStreet}" /></td>
            <td><c:out value="${order.destinationStreet}" /></td>
            <td><c:out value="${order.price}" /></td>
            <td><c:out value="${order.car.model}" /></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<br>
<a href="${pageContext.request.contextPath}/taxi/index">Homepage</a>
</body>
</html>
