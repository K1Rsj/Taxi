<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: HOME
  Date: 12.05.2018
  Time: 23:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head>
    <title>Car types</title>
</head>
<body>
<c:if test="${requestScope.message != null}">
    <p>${requestScope.message}</p>
</c:if>
<table border=1>
    <thead>
    <tr>
        <th>Type</th>
        <th>Starting price</th>
        <th>Price per kilometer</th>
        <th>Discount</th>
    </tr>
    </thead>
    <tbody>

    <c:forEach items="${requestScope.carTypes}" var="type">
        <tr>
            <td><c:out value="${type.type}" /></td>
            <td><c:out value="${type.startingPrice/100} hrivnas" /></td>
            <td><c:out value="${type.pricePerKilometer/100} hrivnas" /></td>
            <td><c:out value="${type.discount}%" /></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
