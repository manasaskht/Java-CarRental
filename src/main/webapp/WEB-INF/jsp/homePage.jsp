<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link rel="stylesheet" href="/css/bootstrap.min.css">
    <link href="/css/datepicker.css" rel="stylesheet">
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
                <a class="nav-link linkClass active" href="#">Home</a>
            </li>
            <li class="nav-item">
                <a class="nav-link linkClass" href="carrent">Rent a Car</a>
            </li>
            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" id="navbarDropdown" role="button" data-toggle="dropdown"
                   aria-haspopup="true" aria-expanded="false">User Profile</a>
                <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                    <a class="dropdown-item" href="userListedCars">Listed Cars</a>
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
<div class="container">
    <div class="container card"
         style="margin-top:30px; padding-top:40px; padding-left: 40px; padding-right: 40px; padding-bottom: 20px">
        <form action="/homePage" id="form" method="post">
            <div class="row">

                <div class="form-group col-sm-6">
                    <label for="cityId">Select a city</label>
                    <select class="form-control" id="cityId" name="cityId">
                        <c:forEach items="${city}" var="city">
                            <option value="${city.cityId}">${city.cityName}</option>
                        </c:forEach>
                    </select>
                    <c:if test="${cityError != null}">
                        <p style="color: red">${cityError}</p>
                    </c:if>
                </div>
                <div class="form-group col-sm-6">
                    <label for="carType">Select a vehicle</label>
                    <select class="form-control" id="carType" name="carType">
                        <c:forEach items="${carType}" var="carType">
                            <option value="${carType.carTypeId}">${carType.carTypeName}</option>
                        </c:forEach>
                    </select>
                    <c:if test="${carError != null}">
                        <p style="color: red">${carError}</p>
                    </c:if>
                </div>
            </div>
            <div class="row justify-content-md-center">
                <div class="col-sm-4">
                    <label for="dateFrom">From Date</label>
                    <input id="dateFrom" name="dateFrom" value="${fromDate}" data-toggle="datepicker"/>
                </div>
                <div class="col-sm-4">
                    <label for="dateTo">To Date</label>
                    <input id="dateTo" name="dateTo" value="${toDate}" data-toggle="datepicker"/>
                </div>
            </div>

            <c:if test="${dateError != null}">
                <div class="row justify-content-md-center">
                    <p style="color: red">${dateError}</p>
                </div>
            </c:if>

            <div class="row justify-content-md-center">
                <div class="col-sm-3">
                    <div class="form-group">
                        <button type="submit" style="width: 100%; margin-top: 20px; margin-bottom: -20px"
                                class="btn btn-primary">Submit
                        </button>
                    </div>
                </div>
            </div>
        </form>
    </div>

    <c:if test="${cars.size() > 0}">
        <div>
            <c:forEach items="${cars}" var="car">
                <div class="card" style="margin-top:10px">
                    <div class="card-body">
                        <div class="row">
                            <div class="col-sm-3">
                                <img style="width:100%;height:200px"
                                     src="data:image/jpeg;base64,${car.imageURL}"
                                     border="0"/>
                            </div>
                            <div class="col-sm-7">
                                <h3>${car.model}</h3>
                                <p>Description: ${car.description}</p>
                                <p>Rate: ${car.carRate}</p>
                            </div>
                        </div>
                        <div class="row">
                            <div style="margin-top:25px" class="col-sm-3 offset-sm-3">
                                <form action="/paymentPage" method="post">
                                    <input type="hidden" name="carId" value="${car.carId}"/>
                                    <input type="hidden" name="fromDate" value="${fromDate}"/>
                                    <input type="hidden" name="toDate" value="${toDate}"/>
                                    <button type="submit" style="width:100%" class="btn btn-danger">Book Now</button>
                                </form>
                            </div>
                            <div style="margin-top:25px" class="col-sm-3">
                                <form action="/carDetails" method="post">
                                    <input type="hidden" name="carId" value="${car.carId}"/>
                                    <input type="hidden" name="fromDate" value="${fromDate}"/>
                                    <input type="hidden" name="toDate" value="${toDate}"/>
                                    <button type="submit" style="width:100%" class="btn btn-info">View More</button>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
    </c:if>
    <c:if test="${cars.size() == 0}">
        <div class="row justify-content-md-center" style="margin:20px; font-size: 40px">
            <p>No Cars are available</p>
        </div>
    </c:if>
</div>
<script src="/js/jquery-3.4.1.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="/js/datepicker.js"></script>
<script>
    Date.prototype.addDays = function (days) {
        var date = new Date(this.valueOf());
        date.setDate(date.getDate() + days);
        return date;
    };

    var currentDate = new Date();
    var selectableDate = currentDate.addDays(30);

    console.log(currentDate);

    $('#dateFrom').datepicker({
        format: 'yyyy-mm-dd',
        startDate: currentDate,
        endDate: selectableDate
    });

    $('#dateTo').datepicker({
        format: 'yyyy-mm-dd',
        startDate: currentDate,
        endDate: selectableDate
    });


    document.querySelector("#form").addEventListener("submit", function (e) {
        var fromD = $("#dateFrom").val();
        var to = $("#dateTo").val();
        console.log(fromD);
        console.log(to);
        if (to == "" || fromD == "") {
            alert("Please select a proper from and to date");
            e.preventDefault();
        }
        var fromDate = new Date(fromD);
        var toDate = new Date(to);
        console.log(fromDate);
        console.log(toDate);
        if (toDate - fromDate < 0) {
            alert("Please select a proper from and to date");
            e.preventDefault();
        }
    });
</script>
</body>
</html>
