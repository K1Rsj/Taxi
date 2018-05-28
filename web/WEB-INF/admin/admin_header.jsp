<%@ include file="../components/addition.jsp" %>
<jsp:include page="../components/head.jsp"/>

<header id="header" class="header">
    <nav class="navbar navbar-expand-lg bg-black fixed-top">
        <div class="container">
            <div class="col-lg-3 ">
                <img src="${pageContext.request.contextPath}/resources/img/logo.png" alt="Taxi"
                     class="logo">
            </div>
            <button class="navbar-toggler" type="button" data-toggle="collapse"
                    data-target="#navbarResponsive"
                    aria-controls="navbarResponsive" aria-expanded="false"
                    aria-label="Toggle navigation">
            </button>
            <div class="collapse navbar-collapse" id="navbarResponsive">
                <ul class="navbar-nav ml-auto">
                    <li class="nav-item">
                        <a href="${pageContext.request.contextPath}/taxi/index"><fmt:message
                                key="home"/></a>
                    </li>
                    <li class="nav-item text-center">
                        <a href="${pageContext.request.contextPath}/taxi/add_discount_page"><fmt:message
                                key="add.discount"/></a>
                    </li>
                    <li class="nav-item text-center">
                        <a href="${pageContext.request.contextPath}/taxi/all_cars"><fmt:message
                                key="show.all.cars"/></a>
                    </li>
                    <li class="nav-item text-center">
                        <a href="${pageContext.request.contextPath}/taxi/add_car_page"><fmt:message
                                key="add.car"/></a>
                    </li>
                    <li class="nav-item text-center">
                        <a href="${pageContext.request.contextPath}/taxi/all_users"><fmt:message
                                key="show.all.users"/></a>
                    </li>
                    <li class="nav-item text-center">
                        <a href="${pageContext.request.contextPath}/taxi/all_car_types"><fmt:message
                                key="types.and.prices"/></a>
                    </li>
                    <li class="nav-item text-center">
                        <a href="${pageContext.request.contextPath}/taxi/logout"><fmt:message
                                key="log.out"/></a>
                    </li>
                    <li class="nav-item text-center">
                        <form>
                            <label for="language"></label><select class="nav-select"
                                                                  id="language" name="language"
                                                                  onchange="submit()">
                            <option value="en_US" ${language == 'en_US' ? 'selected' : ''}>
                                English
                            </option>
                            <option value="uk_UA" ${language == 'uk_UA' ? 'selected' : ''}>
                                Українська
                            </option>
                        </select>
                        </form>
                    </li>
                </ul>
            </div>
        </div>
    </nav>
</header>