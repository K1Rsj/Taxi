<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%-- Created by IntelliJ IDEA. --%>
<%@ page contentType="text/html;charset=UTF-8"%>
<html>
  <head>
    <title></title>
  </head>
  <body>
  <h2>
    Hello World!
  </h2>

  <br/>

  <c:if test="${requestScope.informationMessage != null}">
    <p>${requestScope.informationMessage}</p>
  </c:if>
  <br>
  <form method="post" action="${pageContext.request.contextPath}/taxi/login">
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
        <td colspan="2"><input type="submit" value="Send"></td>
      </tr>
    </table>
  </form>
  <br/>
  <a href="${pageContext.request.contextPath}/taxi/user_registration_page">Registration form</a>
  <br>
  <a href="${pageContext.request.contextPath}/taxi/exception">Exception</a>
  </body>
</html>