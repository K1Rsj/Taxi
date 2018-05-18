<%@ include file="../components/addition.jsp"%>
<jsp:include page="../components/head.jsp"/>

<div class="content container">

    <c:if test="${not empty requestScope.message}">
        <p>${requestScope.message}</p>
    </c:if>

    <c:set var="count" value="${requestScope.firstIndex}" scope="page"/>
    <table class="table table-striped bg-white text-black">
        <caption class="text-white"><fmt:message key="all.users"/></caption>
        <thead>
        <tr>
            <th scope="col">#</th>
            <th scope="col"><fmt:message key="login"/></th>
            <th scope="col"><fmt:message key="email"/></th>
            <th scope="col"><fmt:message key="first.name"/></th>
            <th scope="col"><fmt:message key="second.name"/></th>
            <th scope="col"><fmt:message key="phone.number"/></th>
            <th scope="col"><fmt:message key="role"/></th>
            <th scope="col"><fmt:message key="delete"/></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${requestScope.users}" var="user">
            <c:set var="count" value="${count + 1}" scope="page"/>
            <tr>
                <td><c:out value="${count}" /></td>
                <td><c:out value="${user.login}" /></td>
                <td><c:out value="${user.email}" /></td>
                <td><c:out value="${user.firstName}" /></td>
                <td><c:out value="${user.secondName}" /></td>
                <td><c:out value="${user.phoneNumber}" /></td>
                <td><c:out value="${user.role}" /></td>
                <td><form method="post" action="${pageContext.request.contextPath}/taxi/delete_user">
                    <label>
                        <input type="hidden" name="id" value="${user.id}">
                    </label>
                    <input class="btn btn-danger" type="submit" value="Delete" name="delete">
                </form>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

<nav aria-label="Page navigation">
    <ul class="pagination justify-content-center">
        <c:forEach begin="1" end="${requestScope.numberOfPages}">
            <c:set var="pageCounter" value="${pageScope.pageCounter + 1}" scope="page"/>
            <form action="${pageContext.request.contextPath}/taxi/all_users" method="post">
                <input type="hidden" value="${pageCounter}" name="currentPage">
                <input style="margin: 2px" type="submit" class="btn btn btn-info btn-lg" value="${pageCounter}">
            </form>

        </c:forEach>
    </ul>
</nav>