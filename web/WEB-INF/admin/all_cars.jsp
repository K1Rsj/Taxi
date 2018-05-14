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
<jsp:include page="all_cars_table.jsp"/>
</body>
</html>
