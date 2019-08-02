
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList"
         import="com.group4.carrental.model.User"
%>
<%@ page import="com.group4.carrental.model.City" %>

<% User user;
   user=request.getAttribute("userData")!=null?(User) request.getAttribute("userData"):null;
    ArrayList<City> cityArrayList= new ArrayList<City>();
    if (request!=null) {
        user = request.getAttribute("userData") != null ? (User) request.getAttribute("userData") : null;
    }
    cityArrayList= request.getAttribute("cityArrayList")!=null?(ArrayList<City>)request.getAttribute("cityArrayList"):null;
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
                    <a class="nav-link linkClass" href="homePage">Home</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link linkClass" href="carrent">Rent a Car</a>
                </li>
                <li class="nav-item dropdown active">
                    <a class="nav-link dropdown-toggle" id="navbarDropdown" role="button" data-toggle="dropdown"
                       aria-haspopup="true" aria-expanded="false">User Profile</a>
                    <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                        <a class="dropdown-item" href="userListedCars">Listed Cars</a>
                        <a class="dropdown-item" href="userBookedCars">Booked Cars </a>
                        <a class="dropdown-item" href="userUpdateProfile">Update User Profile</a>
                        <a class="dropdown-item" href="updatePassword">Update User Password</a>
                    </div>
                </li>
            </ul>
            <br/>
            <form class="form-inline" action="/logout" method="post">
                <button class="btn btn-danger" type="submit">Logout</button>
            </form>
        </div>
    </nav>
    <input type="hidden" name="selectedCity" id="selectedCity" value="<%=user!=null?user.getCity_id():0%>">
    <div class="container">
        <form  action="userUpdateProfile" method="post">
            <div class="row">

                <div class="col-sm-6 offset-sm-3 card">
                        <br/>
                        <div class="formTitle">
                            <h4>Update Profile</h4>
                        </div>
                        <hr>
                        <div class="form-group">
                            <label for="name">Name:</label>
                            <input type="text" class="form-control" id="name" name="name"  value="<%=user.getName()%>">
                            <p style="color: red">${errorMsg.nameUpdateError}</p>
                        </div>

                        <div class="form-group">
                            <label for="city">City:</label>
                            <select class="form-control" name="city_id" id="city">
                                <option value="0" selected>Select City</option>
                                <%if(cityArrayList!=null)
                                {
                                    for(int temp=0;temp<cityArrayList.size();temp++){%>
                                <option value="<%=cityArrayList.get(temp).getCityId()%>"><%=cityArrayList.get(temp).getCityName()%></option>
                                <%}}%>
                            </select>
                            <p style="color: red">${errorMsg.cityUpdateError}</p>

                        </div>
                        <br/>
                        <div>
                            <input type="submit" class="btn btn-primary" value="Update">
                        </div>
                        <br/>
                    </div>
                </div>

        </form>
    </div>

<script src="/js/jquery-3.4.1.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>
<script src="js/bootstrap.min.js"></script>
</body>
<script type="text/javascript">
    setSelectedCity()
    function setSelectedCity()
    {
        var selectedCityId=document.getElementById("selectedCity").value
        document.getElementById('city').value = selectedCityId;
    }

</script>
</html>
