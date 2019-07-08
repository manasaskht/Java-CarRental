<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link rel="stylesheet" href="/css/bootstrap.min.css">
</head>
<body>
    <div class="container" style="margin-top:10px">
        <div class="row">
            <div class="col">
            <div class="card-body">
            <h5>PendingCarRequestsList</h5>
                <c:forEach items="${pendingCarRequests}" var="car">
                    <div class="card" style="margin-top:10px">
                        <div class="card-body">
                            <div class="row">                 
                                <div class="col-sm-4">
                                    <h3>${car.carModel}</h3>
                                    <p>${car.carDescription}</p>
                                    <p>${car.carRate}</p>
                                    <p>${car.carOwnerName}</p>
                                    <p>${car.carOwnerMail }
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