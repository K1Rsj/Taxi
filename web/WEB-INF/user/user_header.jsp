<%@ include file="../components/addition.jsp"%>

<header id="header" class="header">
    <nav class="navbar navbar-expand-lg bg-black fixed-top">
        <div class="container">
            <div class="col-lg-3 ">
                <img src="${pageContext.request.contextPath}/resources/img/logo.png" alt="Taxi" class="logo">
            </div>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive"
                    aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
            </button>
            <div class="collapse navbar-collapse" id="navbarResponsive">
                <ul class="navbar-nav ml-auto">
                    <li class="nav-item">
                        <a href="${pageContext.request.contextPath}/taxi/index">Home</a>
                    </li>
                    <li class="nav-item text-center" >
                        <a href="${pageContext.request.contextPath}/taxi/make_order_page">Make order</a>
                    </li>
                    <li class="nav-item text-center" >
                        <a href="${pageContext.request.contextPath}/taxi/my_orders">My orders</a>
                    </li>
                    <li class="nav-item text-center">
                        <a href="${pageContext.request.contextPath}/taxi/my_discount">My discount</a>
                    </li>
                    <li class="nav-item text-center">
                        <a href="${pageContext.request.contextPath}/taxi/all_car_types">View all car types and prices</a>
                    </li>
                    <li class="nav-item text-center">
                        <a href="${pageContext.request.contextPath}/taxi/logout">Log Out</a>
                    </li>
                    <li class="nav-item text-center">
                        <form>
                            <label for="language"></label><select class="nav-select" id="language" name="language" onchange="submit()">
                            <option value="en_US" ${language == 'en_US' ? 'selected' : ''}>lol</option>
                            <option value="ru_RU" ${language == 'ru_RU' ? 'selected' : ''}>kek</option>
                        </select>
                        </form>
                    </li>
                </ul>
            </div>
        </div>
    </nav>
</header>