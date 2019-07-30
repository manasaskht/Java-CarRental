<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList"
         import="com.group4.carrental.model.Car"
%>
<%@ page import="com.group4.carrental.model.AdminCar" %>
<%
    ArrayList<AdminCar> carArrayList= new ArrayList<AdminCar>();
    if (request!=null) {
        carArrayList= request.getAttribute("carArrayList")!=null?(ArrayList<AdminCar>)request.getAttribute("carArrayList"):null;
    }

%>
<html>
<head>
    <link rel="stylesheet" href="/css/bootstrap.min.css">
    <link rel="stylesheet" href="/css/navBar.css">
    <style>
        a.custom-card,
        a.custom-card:hover {
            color: inherit;
        }
    </style>
</head>
<body >
        <nav class="navbar navbar-expand-lg navClass w-100">
            <a class="navbar-brand">
                <img src="/img/webLogo.jpg" width="70" height="70" alt="">
            </a>
            <div class="collapse navbar-collapse">
                <ul class="navbar-nav mr-auto">
                    <li class="nav-item">
                        <a class="nav-link linkClass" href="homePage">Home</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link linkClass" href="/admin/listAllCar">Cars List</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link linkClass" href="/admin/ListPendingRequests">pending Request</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link linkClass" href="/adminBlackListCars">Blacklist cars</a>
                    </li>

                </ul>
                <br/>
                <form class="form-inline" action="/logout" method="post">
                    <button class="btn btn-danger" type="submit">Logout</button>
                </form>
            </div>
        </nav>
        <form name="signUpForm" action="removeFromBlacklist" method="post">
            <input type="hidden" name="carId" id="selectedCarId" value="">
            <input type="hidden" name="carOwnerMail" id="carOwnerMail" value="">
        <div class="row ">
            <div class="col-lg-12">
                <div class="row ">

                    <div class="col align-self-center">

                        <%if(carArrayList!=null){
                        for(int i=0;i<carArrayList.size();i++)
                        { %>
                        <a href="" class="custom-card" >
                        <div class="card" style="width: 500px;">
                            <div class="card-body">
                                <div class="row">
                                    <div class="col-lg-4">

                                <img style="width:70%;height:175px"
                                     src="data:image/jpeg;base64,<%=carArrayList.get(i).getCarImage()%>"
                                     border="0"/>
                                    </div>
                                    <div class="col-lg-8"><h4 class="card-title">Car <%=i+1%> </h4>
                                <p class="card-text">Model: <%=carArrayList.get(i).getCarModel()%></p>
                                        <p class="card-text">Owner Name: <%=carArrayList.get(i).getCarOwnerName()%></p>
                                <p class="card-text">Car Rate: <%=carArrayList.get(i).getCarRate()%></p>
                                        <p class="card-text">Description: <%=carArrayList.get(i).getCarDescription()%></p>

                                <p class="card-text"></p>
                                <input type="submit"  class="btn btn-primary   text-uppercase" onclick="removeFromBlackList(<%=carArrayList.get(i).getCarId()%>,'<%=carArrayList.get(i).getCarOwnerMail()%>')" value="Remove">
                                    </div>
                            </div>

                            </div>
                        </div>
                        </a>
                        <%}
                        }%>
                    </div>
                </div>

                    <br/>
                </div>
            </div>

        </form>
    </div>
    <div class="footer tempClass" style="margin-top:170px;">
        <h6 class="text-center">Car Rent</h6>
        <div class="footer-copyright text-center py-3">© 2019 Copyright:
            <a action="/homePage"> CarRent.com</a>
        </div>
    </div>

<script src="/js/jquery-3.4.1.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script type="text/javascript">

    function removeFromBlackList(carId,ownerMail)
    {
        document.getElementById('selectedCarId').value = carId;
        document.getElementById('carOwnerMail').value = ownerMail;
    }

</script>
</body>
</html>
