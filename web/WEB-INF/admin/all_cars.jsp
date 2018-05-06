<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: HOME
  Date: 03.05.2018
  Time: 21:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head>
    <title>All Cars</title>
</head>
<body>
<c:if test="${requestScope.message != null}">
<p>${requestScope.message}</p>
</c:if>
<table border=1>
    <thead>
    <tr>
        <th>Car Id</th>
        <th>Model</th>
        <th>Number</th>
        <th>State</th>
        <th>Driver</th>
        <th>Type</th>
    </tr>
    </thead>
    <tbody>

    <c:forEach items="${requestScope.cars}" var="car">
        <tr>
            <td><c:out value="${car.id}" /></td>
            <td><c:out value="${car.model}" /></td>
            <td><c:out value="${car.number}" /></td>
            <td><c:out value="${car.state}" /></td>
            <td><c:out value="${car.driver}" /></td>
            <td><c:out value="${car.type}" /></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<br>
<a href="${pageContext.request.contextPath}/taxi/index">Homepage</a>
</body>
</html>
