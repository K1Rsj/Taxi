<%@ page contentType="text/html;charset=UTF-8"%>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Login Page</title>
</head>
<body>

        <h1>Вход в систему</h1><br/>
        <form method="get" action="${pageContext.request.contextPath}/taxi/login">

            <label>
                <input type="text" name="name">
            </label><br/>
            <label>
                <input type="password" name="pass">
            </label><br/><br/>
            <input class="button" type="submit" value="Войти">

        </form>
        <br/>
        <a href="${pageContext.request.contextPath}/taxi/logout">На головну</a>

</body>
</html>