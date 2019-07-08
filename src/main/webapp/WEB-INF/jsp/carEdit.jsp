<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link rel="stylesheet" href="/css/bootstrap.min.css">
</head>
<body>
<div class="container">
    <div class="row">
        <div class="col-sm-12">
            <form method="POST" action="/edit-car-details" enctype="multipart/form-data">
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
<script src="/js/index.js"></script>
</body>
</html>