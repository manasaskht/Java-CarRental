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
        <div class="col-sm-2">
        </div>

        <div class="col-sm-8">
            <span style="color: red">${error}</span>
            <c:forEach items="${listedCars}" var="car">
                <div class="card" style="margin-top:10px">
                    <div class="row">
                        <div class="col-sm-4">
                            <img style="width:100%;height:200px"
                                 src="data:image/jpeg;base64,${car.imageURL}"
                                 border="0"/>
                        </div>
                        <div class="col-sm-7">
                            <h4>${car.carModel}</h4>
                            <p>Car Rate : ${car.carRate}</p>
                            <p>Car Description : ${car.description}</p>


                            <div class="row">
                                <div class="col-sm-2">
                                    <form action="/editCarDetails" method="get">

                                        <button class ="btn btn-dark" id="carIdEdit" name="carIdEdit" value="${car.carId}" type="submit" >Edit</button>
                                    </form>
                                </div>
                                <div class="col-sm-2">
                                    <form action="/userListedCars" method="post">
                                        <button class ="btn btn-dark" id="carId" name="carId" value="${car.carId}" type="submit" >Remove</button>

                                    </form>
                                </div>
                            </div>



                        </div>


                    </div>
                </div>



            </c:forEach>

</div>
</div>
</div>
<script src="/js/jquery-3.4.1.min.js"></script>
<script src="js/bootstrap.min.js"></script>

</body>

</html>
