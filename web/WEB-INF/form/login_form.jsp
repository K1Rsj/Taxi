<%@ include file="../components/addition.jsp"%>
<jsp:include page="components/head.jsp"/>

<div class="container py-5 " style="padding-top: 12rem!important;">
    <c:if test="${not empty requestScope.informationMessage}">
        <div class="alert alert-info text-center" >${requestScope.informationMessage}</div>
    </c:if>
    <div class="row">
        <div class="col-md-12">
            <h1 class="text-center text-white mb-4">Welcome at online taxi service 'Premier Taxi'</h1>
            <div class="row text-center">
                <div class="col-md-6 text-white">
                </div>
                <div class="col-md-5 ml-auto">
                    <div class="card rounded-0">
                        <div class="card-header">
                        </div>
                        <div class="card-body">
                            <form class="form" id="formLogin" method="post" action="${pageContext.request.contextPath}/taxi/login">
                                <div class="form-group">
                                    <label for="login">Username</label>
                                    <input type="text" class="form-control form-control-lg rounded-0" name="login" id="login">

                                </div>
                                <div class="form-group">
                                    <label for="password">Password</label>
                                    <input type="password" class="form-control form-control-lg rounded-0" name="password" id="password">

                                </div>

                                <button type="submit" class="btn btn-success btn-lg float-right" id="btnLogin">Login</button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
            </div>
    </div>
</div>
