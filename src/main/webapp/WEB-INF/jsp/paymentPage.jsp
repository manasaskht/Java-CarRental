<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<html>
<head>
    <link rel="stylesheet" href="/css/bootstrap.min.css">
    <style>
        a.custom-card,
        a.custom-card:hover {
            color: inherit;
        }

        .topnav {
            background-color: #333;
            overflow: hidden;
            width:100%;
            margin-bottom: 50px;
        }

        .topnav a {
            float: left;
            color: #f2f2f2;
            text-align: center;
            padding: 14px 16px;
            text-decoration: none;
            font-size: 17px;
        }


        .topnav a:hover {
            background-color: #ddd;
            color: black;
        }


        .topnav a.active {
            background-color: #4CAF50;
            color: white;
        }
    </style>
</head>
<body >

    <div class="container-fluid">
        <div class="topnav">
            <a class="active" href="#home">Home</a>
            <a href="#news">News</a>
            <a href="#contact">Contact</a>
            <a href="#about">About</a>
        </div>
        <form name="paymentForm" action="paymentPage" method="post">
            <h1 style="text-align: center">Payment Page</h1><hr>
            <br/>
            <div class="row ">
                <div class="col-lg-12">
                    <div class="row ">
                        <div class="col-lg-4">
                            <div class="card bg-light" style="width:350px">
                                    <h4 class="card-title">User Details</h4>
                                    <p class="card-text"><b>Name:</b>${userData.name}</p>
                                    <p class="card-text"><b>Email:</b>${userData.email} </p>
                                <hr>
                                    <h4>Car Details</h4>
                                    <p class="card-text"><b>Car model:</b>${carData.model} </p>
                                    <p class="card-text"><b>Car description:</b>${carData.description}</p>
                                    <p class="card-text"><b>Booking Date:</b>${bookingData.fromDate}</p> to ${bookingData.toDate}
                                </div>

                        </div>
                        <div class="col-lg-8">
                            <div class="form-group">
                                <label for="customerName">Customer Name</label>
                                <input type="text" class="form-control" id="customerName" name="customerName"  required>
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
                                <input type="text" class="form-control" id="cardNumber" name="cardNumber" maxlength="16" required>
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
    </div>

<script src="/js/jquery-3.4.1.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script type="text/javascript">

</script>
</body>
</html>
