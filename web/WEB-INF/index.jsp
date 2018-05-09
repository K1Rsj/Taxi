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
  <jsp:include page="form/login.jsp"/>
  <br/>
  <a href="${pageContext.request.contextPath}/taxi/user_registration_page">Registration form</a>
  <br>
  <a href="${pageContext.request.contextPath}/taxi/exception">Exception</a>
  <br>
  <a href="${pageContext.request.contextPath}/taxi/logout">Log Out</a>
  </body>
</html>