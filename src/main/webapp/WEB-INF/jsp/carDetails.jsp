<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
                <a class="nav-link linkClass active" href="/homePage">Home</a>
            </li>
            <li class="nav-item">
                <a class="nav-link linkClass" href="carrent">Rent a Car</a>
            </li>
            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" id="navbarDropdown" role="button" data-toggle="dropdown"
                   aria-haspopup="true" aria-expanded="false">User Profile</a>
                <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                    <a class="dropdown-item" href="#">Listed Cars</a>
                    <a class="dropdown-item" href="userBookedCars">Booked Cars </a>
                    <a class="dropdown-item" href="userUpdateProfile">Update User Profile</a>
                    <a class="dropdown-item" href="updatePassword">Update User Password</a>
                </div>
            </li>
        </ul>
        <br/>
        <form class="form-inline" action="/logout" method="post">
            <button class="btn btn-danger" type="submit">Logout</button>
        </form>
    </div>
</nav>
<div class="container" style="margin-top:10px">

    <div class="row">
        <div class="col-sm-1">
        </div>

        <div class="col-sm-9">
            <div class="card" style="margin-top:10px">
                <div class="row">
                    <div class="col-sm-6">
                        <img class="img-responsive align-content-center" style="width:100%;height:400px"
                             src="data:image/jpeg;base64,${carDetails.imageURL}"
                             border="0"/>
                    </div>

                    <div class="col-sm-6">
                        <h4>${carDetails.carModel}</h4>
                        <p >Vehicle Type : ${carDetails.carTypeName}</p>
                        <p>Car City : ${carDetails.cityName}</p>
                        <p>Car Rate : ${carDetails.carRate}</p>
                        <p>Car Description : ${carDetails.description}</p>
                        <p>Booked From : ${carBooking.fromDate}</p>
                        <p>Booked To : ${carBooking.toDate} </p>




                        <div style="margin-top:25px" class="col-sm-3 offset-sm-3">
                            <form action="/paymentPage" method="post">
                                <input type="hidden" id="carId" value="${carBooking.carId}" name="carId">
                                <input type="hidden" id="fromDate" name="fromDate" value="${carBooking.fromDate}">
                                <input type="hidden" id="toDate" name="toDate" value="${carBooking.toDate}">
                                <button type="submit"  class="btn btn-danger">Book Now</button>
                            </form>
                        </div>

                    </div>
                </div>

            </div>

        </div>
    </div>
</div>

<script src="/js/jquery-3.4.1.min.js"></script>
<script src="js/bootstrap.min.js"></script>
</body>

</html>
