<%@ include file="../components/addition.jsp"%>
<jsp:include page="../components/head.jsp"/>

<div class="container py-5 " style="padding-top: 12rem!important;">
    <c:if test="${not empty requestScope.wrongInputMessage}">
        <div class="alert alert-info text-center" >${requestScope.wrongInputMessage}</div>
    </c:if>
    <div class="row">
        <div class="col-md-12">
            <h2 class="text-center text-white mb-4"><fmt:message key="registration"/></h2>
            <div class="row text-center">
                <div class="col-md-5 ml-auto mr-auto">
                    <div class="card rounded-0">
                        <div class="card-header">
                        </div>
                        <div class="card-body">
                            <form class="form" method="post" action="${pageContext.request.contextPath}/taxi/user_registration">
                                <div class="form-group">
                                    <label for="login"><fmt:message key="login"/></label>
                                    <input type="text" class="form-control form-control-lg rounded-0" name="login" id="login">
                                </div>

                                <div class="form-group">
                                    <label for="password"><fmt:message key="password"/></label>
                                    <input type="password" class="form-control form-control-lg rounded-0" name="password" id="password">
                                </div>

                                <div class="form-group">
                                    <label for="password2"><fmt:message key="repeat.password"/></label>
                                    <input type="password" class="form-control form-control-lg rounded-0" name="password2" id="password2">
                                </div>

                                <div class="form-group">
                                    <label for="email"><fmt:message key="email"/></label>
                                    <input type="email" class="form-control form-control-lg rounded-0" name="email" id="email">
                                </div>

                                <div class="form-group">
                                    <label for="first_name"><fmt:message key="first.name"/></label>
                                    <input type="text" class="form-control form-control-lg rounded-0" name="first_name" id="first_name">
                                </div>

                                <div class="form-group">
                                    <label for="second_name"><fmt:message key="second.name"/></label>
                                    <input type="text" class="form-control form-control-lg rounded-0" name="second_name" id="second_name">
                                </div>

                                <div class="form-group">
                                    <label for="phone_number"><fmt:message key="phone.number"/></label>
                                    <input type="number" class="form-control form-control-lg rounded-0" name="phone_number" id="phone_number">
                                </div>

                                <button type="submit" class="btn btn-success btn-lg float-right" id="btnLogin"><fmt:message key="create.account"/></button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>