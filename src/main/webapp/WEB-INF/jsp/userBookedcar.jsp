<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <link rel="stylesheet" href="/css/bootstrap.min.css">
</head>
<body>


<br><br>
<div class="container">








    <c:forEach items="${bookedCars}" var="car">

        <div class="card p-3 bg-white">
            <tr>
                <td>Car Model : ${car.carModel}</td>
                <br>

                <td> Car Type : ${car.carTypeName}</td>
                <br>
                <td>Car City : ${car.cityName}</td>

                <br>
                <td>Car Rate : ${car.carRate}</td>

                <br>
                <td>Car Description : ${car.description}</td>

            </tr>

            <br>
            <form action="/user-booked-cars" method="post">

                <div>
                    <button class ="btn btn-dark" id="carId" name="carId" value="${car.carId}" type="submit" >Remove</button>
                </div>
            </form>

        </div>




    </c:forEach>

</div>



</body>

</html>
