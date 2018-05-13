<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: HOME
  Date: 28.04.2018
  Time: 20:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head>
    <title>Registration</title>
</head>
<body>
<c:if test="${requestScope.wrongInputMessage != null}">
    <p>${requestScope.wrongInputMessage}</p>
</c:if>
<form method="post" action="${pageContext.request.contextPath}/taxi/user_registration">
    <table border="1px" style = text-align:center>
        <tr>
            <td>Login</td>
            <td><label>
                <input type="text" name="login">
            </label></td>
        </tr>
        <tr>
            <td>Password</td>
            <td><label>
                <input type="password" name="password">
            </label></td>
        </tr>
        <tr>
            <td>Repeat password</td>
            <td><label>
                <input type="password" name="password2">
            </label></td>
        </tr>
        <tr>
            <td>Email</td>
            <td><label>
                <input type="text" name="email">
            </label></td>
        </tr>
        <tr>
            <td>First name</td>
            <td><label>
                <input type="text" name="first_name">
            </label></td>
        </tr>
        <tr>
            <td>Second name</td>
            <td><label>
                <input type="text" name="second_name">
            </label></td>
        </tr>
        <tr>
            <td>Phone number</td>
            <td><label>
                <input type="number" name="phone_number">
            </label></td>
        </tr>
        <tr>
            <td colspan="2"><input type="submit" value="Send"></td>
        </tr>
    </table>
</form>
</body>
</html>
