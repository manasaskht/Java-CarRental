<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link rel="stylesheet" href="/css/bootstrap.min.css">
</head>
<body>
    <div class="container" style="margin-top:10px">
        <div class="row">
            <div class="col-sm-4">

            </div>
            <div class="col-sm-8">
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
                                    <p>${car.carDescription}</p>
                                    <p>${car.carRate}</p>
                                    <p>${car.carOwnerName}</p>
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