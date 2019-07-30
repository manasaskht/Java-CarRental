<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <link rel="stylesheet" href="/css/bootstrap.min.css">
</head>
<body>


<br>




    <div class="container" style="margin-top:10px">

        <div class="row">
            <div class="col-sm-2">
            </div>
            <div class="col-sm-8">
                <c:forEach items="${bookedCars}" var="car">
                    <div class="card" style="margin-top:10px">
                        <div class="row">
                            <div class="col-sm-4">
                                <img style="width:100%;height:200px"
                                     src="data:image/jpeg;base64,${car.imageURL}"
                                     border="0"/>
                            </div>
                            <div class="col-sm-7">
                                <h4>${car.carModel}</h4>
                                <p>Vehicle Type : ${car.carTypeName}</p>
                                <p>Car Rate : ${car.carRate}</p>
                                <p>Car Description : ${car.description}</p>
                                <p>Booked From : ${car.fromDate}</p>
                                <p>Booked To : ${car.toDate}</p>


                                <form action="/userBookedCars" method="post">
                                    <div>
                                    <button class ="btn btn-dark" id="carId" name="carId" value="${car.carId}" type="submit" >Remove</button>
                                </div>
                                </form>
                            </div>
                        </div>
                    </div>

                </c:forEach>

            </div>
        </div>
    </div>










<br>


</body>

</html>
