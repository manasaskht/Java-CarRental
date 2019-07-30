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
                <a class="nav-link linkClass" href="/admin/listAllCar">Cars List</a>
            </li>
            <li class="nav-item">
                <a class="nav-link linkClass" href="/admin/ListPendingRequests">Pending Request</a>
            </li>
            <li class="nav-item">
                <a class="nav-link linkClass active" href="/adminBlackListCars">Blacklist cars</a>
            </li>

        </ul>
        <br/>
        <form class="form-inline" action="/admin/logout" method="post">
            <button class="btn btn-danger" type="submit">Logout</button>
        </form>
    </div>
</nav>
<div class="container" style="margin-top:10px">
    <div class="row">
        <div class="col-sm-8">
            <div class="card-body">
                <h5>PendingCarRequestsList</h5>
                <c:forEach items="${pendingCarRequests}" var="car">
                    <div class="card" style="margin-top:10px">
                        <div class="card-body">
                            <div class="row">
                  
                                         <div class="col-sm-7">
                                    Model: <p> ${car.carModel}</p>
                                    Description: <p> ${car.carDescription}</p>
                                    Rate:  <p>${car.carRate}</p>
                                    Owner Name:<p>${car.carOwnerName}</p>
                                    Mail: <p>${car.carOwnerMail }
                                </div>
                            </div>
                            <div style="margin-top:25px" >
                                <form action="/admin/Approve/${car.carId}" method="get">
                                    <div>
                                        <button class="btn btn-sm btn-primary  text-uppercase" type="submit" >Approve</button>
                                        <button class="btn btn-sm btn-google  text-uppercase" onclick="location.href='/admin/Reject/${car.carId}'" type="button"> Reject </button>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>

        </div>
    </div>
</div>
</body>

</html>
