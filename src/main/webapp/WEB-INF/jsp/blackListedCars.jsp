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
</head>
<body >
        <nav class="navbar navbar-expand-lg navClass w-100">
            <a class="navbar-brand">
                <img src="/img/websiteLogo.jpg" width="70" height="70" alt="">
            </a>
            <div class="collapse navbar-collapse">
                <ul class="navbar-nav mr-auto">

                    <li class="nav-item">
                        <a class="nav-link linkClass" href="/admin/listAllCar">Cars List</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link linkClass" href="/admin/ListPendingRequests">Pending Request</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link linkClass active" href="/adminBlackListCars">Blacklist cars</a>
                    </li>

                </ul>
                <br/>
                <form class="form-inline" action="/admin/logout" method="post">
                    <button class="btn btn-danger" type="submit">Logout</button>
                </form>
            </div>
        </nav>
        <form name="signUpForm" action="removeFromBlacklist" method="post">
            <input type="hidden" name="carId" id="selectedCarId" value="">
            <input type="hidden" name="carOwnerMail" id="carOwnerMail" value="">
        <div class="row ">
            <div class="col-sm-8 offset-sm-2">

                        <%if(carArrayList!=null){
                        for(int i=0;i<carArrayList.size();i++)
                        { %>

                        <div class="card" style="margin-top:10px">
                            <div class="card-body">
                                <div class="row">
                                    <div class="col-lg-4">

                                <img style="width:100%;height:250px"
                                     src="data:image/jpeg;base64,<%=carArrayList.get(i).getCarImage()%>"
                                     border="0"/>
                                    </div>
                                    <div class="col-lg-8"><h3 class="card-title"><%=carArrayList.get(i).getCarModel()%></h3>

                                        <p class="card-text">Owner Name: <%=carArrayList.get(i).getCarOwnerName()%></p>
                                <p class="card-text">Car Rate: <%=carArrayList.get(i).getCarRate()%></p>
                                        <p class="card-text">Description: <%=carArrayList.get(i).getCarDescription()%></p>
                                    </div>
                                    <div style="margin-top:25px" class="col-sm-3 offset-sm-5">
                                <input type="submit"  style="width:100%" class="btn btn-primary   text-uppercase" onclick="removeFromBlackList(<%=carArrayList.get(i).getCarId()%>,'<%=carArrayList.get(i).getCarOwnerMail()%>')" value="Remove">
                                        </div>

                            </div>

                            </div>
                        </div>

                        <%}
                        }%>
                    </div>
                </div>

                    <br/>


        </form>
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
