<%@ include file="../components/addition.jsp"%>
<jsp:include page="../components/head.jsp"/>

<div class="container py-5 " style="padding-top: 12rem!important;">
    <c:if test="${not empty requestScope.orderInformationMessage}">
        <div class="alert alert-info text-center" >${requestScope.orderInformationMessage}</div>
    </c:if>
    <div class="row">
        <div class="col-md-12">
            <h2 class="text-center text-white mb-4">Make your order</h2>
            <div class="row text-center">
                <div class="col-md-5 ml-auto mr-auto">
                    <div class="card rounded-0">
                        <div class="card-header">
                        </div>
                        <div class="card-body">
                            <form class="form" method="post" action="${pageContext.request.contextPath}/taxi/make_order">
                                <div class="form-group">
                                    <label for="departure">Departure street</label>
                                    <input type="text" class="form-control form-control-lg rounded-0" name="departure" id="departure">
                                </div>

                                <div class="form-group">
                                    <label for="destination">Destination street</label>
                                    <input type="text" class="form-control form-control-lg rounded-0" name="destination" id="destination">
                                </div>

                                <div class="form-group">
                                    <label for="type">Car type</label>
                                    <select class="form-control form-control-lg rounded-0" name="type" id="type">
                                        <option selected value="standard">Standard</option>
                                        <option value="comfort">Comfort</option>
                                        <option value="minibus">Minibus</option>
                                        <option value="premium">Premium</option>
                                    </select>
                                </div>
                                <button type="submit" class="btn btn-success btn-lg float-right" id="btnLogin">Confirm</button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>