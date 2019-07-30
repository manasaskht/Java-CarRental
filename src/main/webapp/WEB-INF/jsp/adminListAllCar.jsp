<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link rel="stylesheet" href="/css/bootstrap.min.css">
    <link rel="stylesheet" href="/css/navBar.css">
</head>
<body>
<nav class="navbar navbar-expand-lg navClass w-100">
    <a class="navbar-brand">
        <img src="/img/websiteLogo.jpg" width="70" height="70" alt="">
    </a>
    <div class="collapse navbar-collapse">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item">
                <a class="nav-link linkClass" href="#">Cars List</a>
            </li>
            <li class="nav-item">
                <a class="nav-link linkClass" href="/admin/ListPendingRequests">Pending Request</a>
            </li>
            <li class="nav-item">
                <a class="nav-link linkClass" href="/adminBlackListCars">Blacklist cars</a>
            </li>

        </ul>
        <br/>
        <form class="form-inline" action="/admin/logout" method="POST">
            <button class="btn btn-danger" type="submit">Logout</button>
        </form>
    </div>
</nav>
<div class="container" style="margin-top:10px">
    <c:if test="${carBookedError != null}">
        <center><p style="color: red">${carBookedError}</p></center>
    </c:if>
    <div class="row">
        <div class="col-sm-8 offset-sm-2">
            <c:forEach items="${carList}" var="car">
                <div class="card" style="margin-top:10px">
                    <div class="card-body">
                        <div class="row">
                            <div class="col-sm-5">
                                <img style="width:100%;height:250px"
                                     src="data:image/jpeg;base64,${car.carImage}"
                                     border="0"/>
                            </div>
                            <div class="col-sm-7">
                                <h3>${car.carModel}</h3>
                                <p>Description: ${car.carDescription}</p>
                                <p>Rate: ${car.carRate}</p>
                                <p>Owner Name: ${car.carOwnerName}</p>
                            </div>
                        </div>
                        <div style="margin-top:25px" class="col-sm-3 offset-sm-5">
                            <form action="/admin/blacklist/${car.carId}" method="get">
                                <button type="submit" style="width:100%" class="btn btn-danger">Blacklist</button>
                            </form>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
</div>
</body>
</html>
