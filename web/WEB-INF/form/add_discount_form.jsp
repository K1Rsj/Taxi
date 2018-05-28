<%@ include file="../components/addition.jsp" %>
<jsp:include page="../components/head.jsp"/>

<div class="container py-5 " style="padding-top: 12rem!important;">
    <c:if test="${not empty requestScope.informationMessage}">
        <div class="alert alert-info text-center">${requestScope.informationMessage}</div>
    </c:if>
    <div class="row">
        <div class="col-md-12">
            <h2 class="text-center text-white mb-4"><fmt:message key="add.new.discount"/></h2>
            <div class="row text-center">
                <div class="col-md-5 ml-auto mr-auto">
                    <div class="card rounded-0">
                        <div class="card-header">
                        </div>
                        <div class="card-body">
                            <form class="form" method="post"
                                  action="${pageContext.request.contextPath}/taxi/add_discount">

                                <div class="form-group">
                                    <label for="type"><fmt:message key="car.type"/></label>
                                    <select class="form-control form-control-lg rounded-0"
                                            name="type" id="type">
                                        <option selected value="standard">Standard</option>
                                        <option value="comfort">Comfort</option>
                                        <option value="minibus">Minibus</option>
                                        <option value="premium">Premium</option>
                                    </select>
                                </div>

                                <div class="form-group">
                                    <label for="discount"><fmt:message
                                            key="discount.value"/></label>
                                    <input type="number"
                                           class="form-control form-control-lg rounded-0"
                                           name="discount" id="discount">
                                </div>

                                <button type="submit"
                                        class="btn btn-success btn-lg float-right"
                                        id="btnLogin"><fmt:message key="confirm"/></button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>