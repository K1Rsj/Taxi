<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: HOME
  Date: 13.05.2018
  Time: 13:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head>
    <title>All users</title>
</head>
<body>
<c:if test="${requestScope.message != null}">
    <p>${requestScope.message}</p>
</c:if>
<table border=1>
    <thead>
    <tr>
        <th>User Id</th>
        <th>Login</th>
        <th>Email</th>
        <th>First name</th>
        <th>Second name</th>
        <th>Phone number</th>
        <th>Delete user</th>
    </tr>
    </thead>
    <tbody>

    <c:forEach items="${requestScope.users}" var="user">
        <tr>
            <td><c:out value="${user.id}" /></td>
            <td><c:out value="${user.login}" /></td>
            <td><c:out value="${user.email}" /></td>
            <td><c:out value="${user.firstName}" /></td>
            <td><c:out value="${user.secondName}" /></td>
            <td><c:out value="${user.phoneNumber}" /></td>
            <td><form method="post" action="${pageContext.request.contextPath}/taxi/delete_user">
                <label>
                    <input type="hidden" name="id" value="${user.id}">
                </label>
                <input type="submit" name="delete" value="Delete">
            </form>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
