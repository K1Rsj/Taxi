<%@ include file="../components/addition.jsp" %>

<header id="header" class="header">
    <nav class="navbar navbar-expand-lg bg-black fixed-top">
                <img src="${pageContext.request.contextPath}/resources/img/logo.png" alt="Taxi"
                     class="logo">
            <button class="navbar-toggler" type="button" data-toggle="collapse"
                    data-target="#navbarResponsive"
                    aria-controls="navbarResponsive" aria-expanded="false"
                    aria-label="Toggle navigation">
            </button>
            <div class="collapse navbar-collapse" id="navbarResponsive">
                <ul class="navbar-nav ml-auto mr-auto">
                    <li class="nav-item">
                        <a href="${pageContext.request.contextPath}/taxi/index"><fmt:message
                                key="home"/></a>
                    </li>
                    <li class="nav-item text-center">
                        <a href="${pageContext.request.contextPath}/taxi/all_car_types"><fmt:message
                                key="types.and.prices"/></a>
                    </li>
                    <li class="nav-item text-center">
                        <a href="${pageContext.request.contextPath}/taxi/user_registration_page"><fmt:message
                                key="registration"/></a>
                    </li>
                    <li class="nav-item text-center">
                        <a href="${pageContext.request.contextPath}/taxi/logout"><fmt:message
                                key="log.out"/></a>
                    </li>
                    <li class="nav-item text-center">
                        <p class="text-white"><fmt:message key="language"/></p>
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
    </nav>
</header>