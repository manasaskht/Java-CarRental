<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<html>
<head>
    <link rel="stylesheet" href="/css/bootstrap.min.css">
    <link rel="stylesheet" href="/css/navBar.css">
</head>
<body >

        <nav class="navbar navbar-expand-lg navClass w-100">
            <a class="navbar-brand">
                <img src="/img/websiteLogo.jpg" width="70" height="70" alt="">
            </a>
            <div class="collapse navbar-collapse">
                <ul class="navbar-nav mr-auto">
                    <li class="nav-item">
                        <a class="nav-link linkClass active" href="homePage">Home</a>
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
        <div class="container-fluid">
        <form name="paymentForm" action="bookCar" method="post">
            <input type="hidden" name="fromDate" value=${bookingData.fromDate}>
            <input type="hidden" name="toDate" value=${bookingData.toDate}>
            <input type="hidden" name="userId" value=${bookingData.userId}>
            <input type="hidden" name="carId" value=${bookingData.carId}>
            <input type="hidden" name="totalRent" value=${totalRent}>

            <h1 style="text-align: center">Payment Page</h1><hr>
            <br/>
            <div class="row ">
                <div class="col-lg-12">
                    <div class="row ">
                        <div class="col-lg-4">
                            <div class="card bg-light" style="width:400px;">
                                    <h5 class="card-title" style="color: blue;">User Details</h5>
                                    <p class="card-text"><b>Name:</b>${userData.name}</p>
                                    <p class="card-text"><b>Email:</b>${userData.email} </p>
                                <hr>
                                    <h5 style="color: blue;">Car Details</h5>
                                    <p class="card-text"><b>Model:</b>${carData.model} </p>
                                    <p class="card-text"><b>Description:</b>${carData.description}</p>
                                    <p class="card-text"><b>Booking from:</b>${bookingData.fromDate}</p>
                                    <p class="card-text"><b>Booking To:</b>${bookingData.toDate}</p>
                                    <p clss="card-text"><b>Total Rent:</b>${totalRent}</p>
                                </div>

                        </div>
                        <div class="col-lg-8">
                            <div class="form-group">
                                <label for="customerName">Customer Name</label>
                                <input type="text" class="form-control" id="customerName" name="customerName" value="${paymentDetails.customerName}" required>
                            </div>
                            <div class="form-group">
                                <label for="cardType">Card Type:</label>
                                <select name="cardType" id="cardType" class="form-control">
                                    <option value="1">Visa</option>
                                    <option value="2">Master Card</option>
                                    <option value="3">American Express</option>
                                    <option value="4">Discover</option>
                                </select>
                            </div>
                            <div class="row">
                                <div class="col-lg-6">
                                    <div class="form-group">
                                        <label for="expiryMonth">Expiry Month:</label>
                                        <select name="cardType" id="expiryMonth" class="form-control">
                                            <option value="1">January</option>
                                            <option value="2">February</option>
                                            <option value="3">March</option>
                                            <option value="4">April</option>
                                            <option value="5">May</option>
                                            <option value="6">June</option>
                                            <option value="7">July</option>
                                            <option value="8">August</option>
                                            <option value="9">September</option>
                                            <option value="10">October</option>
                                            <option value="11">November</option>
                                            <option value="12">December</option>
                                        </select>

                                    </div>
                                </div>
                                <div class="col-lg-6">
                                    <div class="form-group">
                                        <label for="expiryYear">Expiry Year:</label>
                                        <select name="cardType" id="expiryYear" class="form-control">
                                            <option value="1">2019</option>
                                            <option value="2">2020</option>
                                            <option value="3">2021</option>
                                            <option value="4">2022</option>
                                            <option value="5">2023</option>
                                            <option value="6">2024</option>
                                            <option value="7">2025</option>
                                            <option value="8">2026</option>
                                            <option value="9">2027</option>
                                            <option value="10">2028</option>
                                            <option value="11">2029</option>
                                            <option value="12">2030</option>
                                        </select>
                                    </div>
                                </div>

                            </div>

                            <div class="form-group">
                                <label for="cardNumber">Card Number:</label>
                                <input type="number" class="form-control" id="cardNumber" name="cardNumber" value="${paymentDetails.cardNumber}" maxlength="16" required>
                            </div>
                            <p style="color: red">${cardNumberError}</p>
                            <div>
                                <input type="submit" class="btn btn-primary" value="Pay">
                            </div>
                        </div>
                    </div>

                    <br/>
                </div>
            </div>

        </form>


<script src="/js/jquery-3.4.1.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script type="text/javascript">

</script>
</body>
</html>
