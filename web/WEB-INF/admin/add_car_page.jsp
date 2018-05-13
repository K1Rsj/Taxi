<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: HOME
  Date: 12.05.2018
  Time: 21:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head>
    <title>Add car</title>
</head>
<body>
<c:if test="${requestScope.informationMessage != null}">
    <p>${requestScope.informationMessage}</p>
</c:if>
<form method="post" action="${pageContext.request.contextPath}/taxi/add_car">
    <table border="1px" style = text-align:center>
        <tr>
            <td>Model</td>
            <td><label>
                <input type="text" name="model">
            </label></td>
        </tr>
        <tr>
            <td>Number</td>
            <td><label>
                <input type="text" name="number">
            </label></td>
        </tr>
        <tr>
            <td>Driver</td>
            <td><label>
                <input type="text" name="driver">
            </label></td>
        </tr>
        <tr>
            <td>Car type</td>
            <td><label>
                <select name="type">
                    <option selected value="standard">Standard</option>
                    <option value="comfort">Comfort</option>
                    <option value="minibus">Minibus</option>
                    <option value="premium">Premium</option>
                </select>
            </label></td>
        </tr>
        <tr>
            <td colspan="2"><input type="submit" value="Send"></td>
        </tr>
    </table>
</form>
</body>
</html>
