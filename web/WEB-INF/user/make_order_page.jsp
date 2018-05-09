<%--
  Created by IntelliJ IDEA.
  User: HOME
  Date: 06.05.2018
  Time: 16:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head>
    <title>Make order</title>
</head>
<body>
<form method="post" action="${pageContext.request.contextPath}/taxi/make_order">
    <table border="1px" style = text-align:center>
        <tr>
            <td>Departure Street</td>
            <td><label>
                <input type="text" name="departure">
            </label></td>
        </tr>
        <tr>
            <td>Destination Street</td>
            <td><label>
                <input type="text" name="destination">
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
