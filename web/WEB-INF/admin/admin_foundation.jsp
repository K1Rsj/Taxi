<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: HOME
  Date: 02.05.2018
  Time: 21:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head>
    <title>Admin</title>
</head>
<body>
    <p>Hello admin</p>
    <br>
    <c:if test="${requestScope.discountInformation != null}">
        <p>${requestScope.discountInformation}</p>
    </c:if>
    <br>
    <a href="${pageContext.request.contextPath}/taxi/add_discount_page">Add discount</a>
    <br>
    <a href="${pageContext.request.contextPath}/taxi/all_cars">Show all cars</a>
    <br>
    <a href="${pageContext.request.contextPath}/taxi/logout">Log Out</a>
</body>
</html>
