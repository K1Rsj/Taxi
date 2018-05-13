<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8"%>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Login Page</title>
</head>
<body>
<c:if test="${requestScope.informationMessage != null}">
    <p>${requestScope.informationMessage}</p>
</c:if>
<br>
        <h1>Login form</h1><br/>
        <form method="post" action="${pageContext.request.contextPath}/taxi/login">

            <label>
                <input type="text" name="login">
            </label><br/>
            <label>
                <input type="password" name="password">
            </label><br/><br/>
            <input class="button" type="submit" value="Submit">
        </form>
        <br/>
</body>
</html>