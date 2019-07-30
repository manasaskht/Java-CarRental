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
<div class="container">
    <div class="row">
        <div class="col-sm-12">
            <form method="POST" action="/editCarDetails" enctype="multipart/form-data">
                <div class="form-group">
                    <label for="model">Car Model</label>
                    <input type="text" class="form-control" id="model" name="model" value="${carDetails.carModel}"
                           required minlength="5" maxlength="50">
                    <c:if test="${modelError != null}">
                        <p style="color: red">${modelError}</p>
                    </c:if>
                </div>
                <div class="form-group">
                    <label for="description">Description</label>
                    <input type="text" class="form-control" id="description" name="description"
                           value="${carDetails.description}" required minlength="10" maxlength="50">
                    <c:if test="${descriptionError != null}">
                        <p style="color: red">${descriptionError}</p>
                    </c:if>
                </div>
                <div class="form-group">
                    <label for="carTypeId">Select City</label>
                    <select class="form-control" id="city" name="city" >
                        <option selected="true" value="${carDetails.cityId}">${carDetails.cityName}</option>
                        <c:forEach items="${city}" var="city">
                            <option value="${city.cityId}">${city.cityName}</option>
                        </c:forEach>
                    </select>
                    <c:if test="${cityError != null}">
                        <p style="color: red">${cityError}</p>
                    </c:if>
                </div>
                <div class="form-group">
                    <label for="carTypeId">Select Car Type</label>
                    <select class="form-control" id="carTypeId" name="carTypeId">
                        <option selected="true" value="${carDetails.carTypeId}">${carDetails.carTypeName}</option>
                        <c:forEach items="${carType}" var="carType">
                            <option value="${carType.carTypeId}">${carType.carTypeName}</option>
                        </c:forEach>
                    </select>
                    <c:if test="${carTypeError != null}">
                        <p style="color: red">${carTypeError}</p>
                    </c:if>
                </div>
                <div class="form-group">
                    <label for="carRate">Car Rate</label>
                    <input type="number" class="form-control" id="carRate" name="carRate" value="${carDetails.carRate}"
                           required>
                    <c:if test="${rateError != null}">
                        <p style="color: red">${rateError}</p>
                    </c:if>
                </div>

                <div class="car-image">
                    <img style="width:50%;height:300px"
                         src="data:image/jpeg;base64,${carDetails.imageURL}"
                         border="0"/>

                </div>

                <div class="form-group">
                    <label for="carImage">Change Car Image</label>
                    <input type="file" class="form-control-file" name="carImage" id="carImage" accept="image/*"

                           >

                </div>

                <button type="submit" class="btn btn-primary" id="carId" name="carId" value="${carDetails.carId}">Submit</button>
            </form>
        </div>
    </div>

</div>
<script src="/js/jquery-3.4.1.min.js"></script>
<script src="js/bootstrap.min.js"></script>
</body>
</html>
