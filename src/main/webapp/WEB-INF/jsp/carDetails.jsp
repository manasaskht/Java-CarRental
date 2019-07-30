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
        <div class="col-sm-1">
        </div>

        <div class="col-sm-9">
            <div class="card" style="margin-top:10px">
                <div class="row">
                    <div class="col-sm-6">
                        <img class="img-responsive align-content-center" style="width:100%;height:400px"
                             src="data:image/jpeg;base64,${carDetails.imageURL}"
                             border="0"/>
                    </div>

                    <div class="col-sm-6">
                        <h4>${carDetails.carModel}</h4>
                        <p >Vehicle Type : ${carDetails.carTypeName}</p>
                        <p>Car City : ${carDetails.cityName}</p>
                        <p>Car Rate : ${carDetails.carRate}</p>
                        <p>Car Description : ${carDetails.description}</p>
                        <p>Booked From : ${carBooking.fromDate}</p>
                        <p>Booked To : ${carBooking.toDate} </p>




                        <div style="margin-top:25px" class="col-sm-3 offset-sm-5">
                            <form action="/paymentPage" method="post">
                                <input type="hidden" id="carId" value="${carBooking.carId}" name="carId">
                                <input type="hidden" id="fromDate" name="fromDate" value="${carBooking.fromDate}">
                                <input type="hidden" id="toDate" name="toDate" value="${carBooking.toDate}">
                                <button type="submit" style="width:100%" class="btn btn-danger">Book Now</button>
                            </form>
                        </div>

                    </div>
                </div>

            </div>

        </div>
    </div>
</div>

<br>

</body>

</html>
