<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: HOME
  Date: 22.04.2018
  Time: 21:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head>
    <title>Error</title>
</head>
<body>
    <c:if test="${requestScope.errorMessage != null}">
    <p>${requestScope.errorMessage}</p>
    </c:if>

    <p>Oops something went wrong</p>
</body>
</html>
