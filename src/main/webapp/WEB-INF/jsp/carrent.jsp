<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link rel="stylesheet" href="/css/bootstrap.min.css">
</head>
<body>
<div class="container">
    <div class="row">
        <div class="col-sm-12">
            <form method="POST" action="/carrent" enctype="multipart/form-data">
                <div class="form-group">
                    <label for="model">Car Model</label>
                    <input type="text" class="form-control" id="model" name="model" placeholder="Enter Car Model Name"
                           required minlength="5">
                    <c:if test="${modelError != null}">
                        <p style="color: red">${modelError}</p>
                    </c:if>
                </div>
                <div class="form-group">
                    <label for="description">Description</label>
                    <input type="text" class="form-control" id="description" name="description"
                           placeholder="Enter Car Description" required minlength="10">
                    <c:if test="${descriptionError != null}">
                        <p style="color: red">${descriptionError}</p>
                    </c:if>
                </div>
                <div class="form-group">
                    <label for="carTypeId">Select City</label>
                    <select class="form-control" id="carCity" name="carTypeId">
                        <c:forEach items="${carType}" var="carType">
                            <option value="${carType.carTypeId}">${carType.carTypeName}</option>
                        </c:forEach>
                    </select>
                    <c:if test="${cityError != null}">
                        <p style="color: red">${cityError}</p>
                    </c:if>
                </div>
                <div class="form-group">
                    <label for="carTypeId">Select Car Type</label>
                    <select class="form-control" id="carTypeId" name="carTypeId">
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
                    <input type="number" class="form-control" id="carRate" name="carRate" placeholder="Enter Car Rate"
                           required>
                    <c:if test="${rateError != null}">
                        <p style="color: red">${rateError}</p>
                    </c:if>
                </div>
                <div class="form-group">
                    <label for="carImage">Example file input</label>
                    <input type="file" class="form-control-file" name="carImage" id="carImage" accept="image/*"
                           required>
                    <c:if test="${imageError != null}">
                        <p style="color: red">${imageError}</p>
                    </c:if>
                </div>
                <button type="submit" class="btn btn-primary">Submit</button>
            </form>
        </div>
    </div>

</div>
<script src="/js/jquery-3.4.1.min.js"></script>
<script src="/js/index.js"></script>
</body>
    </html>