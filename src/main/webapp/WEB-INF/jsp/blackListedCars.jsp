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
    <style>
        a.custom-card,
        a.custom-card:hover {
            color: inherit;
        }
    </style>
</head>
<body >
<header class="header">
    <div class="container">
        <form name="signUpForm" action="removeFromBlacklist" method="post">
            <input type="hidden" name="carId" id="selectedCarId" value="">
            <input type="hidden" name="carOwnerMail" id="carOwnerMail" value="">
        <div class="row ">
            <div class="col-lg-12">
                <div class="row ">
                    <div class="col-lg-4">
                        <br/>
                        <div class="formTitle">
                            <h1> Admin Page</h1>
                        </div>
                        <div>
                            <input type="button"  class="btn btn-primary   text-uppercase" onclick="location.href='/admin/list-all-car'" value="List of Cars">
                        </div><br/>
                        <div>
                            <input type="button"  class="btn btn-primary   text-uppercase" onclick="location.href='/admin/ListPendingRequests'" value="pending Request">
                        </div><br/>
                        <div>
                            <input type="button"  class="btn btn-primary   text-uppercase" onclick="location.href='/adminBlackListCars'" value="Blacklist cars">
                        </div><br/>
                        <br/>
                    </div>
                    <div class="col-lg-8">
                        <%if(carArrayList!=null){
                        for(int i=0;i<carArrayList.size();i++)
                        { %>
                        <a href="" class="custom-card" >
                        <div class="card" >
                            <div class="card-body">
                                <div class="row">
                                    <div class="col-lg-4">

                                <img style="width:100%;height:250px"
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
</header>
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
