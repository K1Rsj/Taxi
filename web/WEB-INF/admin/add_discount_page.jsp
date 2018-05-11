<%--
  Created by IntelliJ IDEA.
  User: HOME
  Date: 11.05.2018
  Time: 12:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head>
    <title>Add discount</title>
</head>
<body>
<form method="post" action="${pageContext.request.contextPath}/taxi/add_discount">
    <table border="1px" style = text-align:center>
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
            <td>Discount value</td>
            <td><label>
                <input type="number" name="discount">
            </label></td>
        </tr>
        <tr>
            <td colspan="2"><input type="submit" value="Send"></td>
        </tr>
    </table>
</form>
</body>
</html>
