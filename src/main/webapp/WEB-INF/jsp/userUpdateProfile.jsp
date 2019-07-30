
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList"
         import="com.group4.carrental.model.User"
%>
<%@ page import="com.group4.carrental.model.City" %>

<% User user = new User();
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
    <link rel="stylesheet" href="/css/signUpForm.css">
</head>
<body >
    <nav class="navbar navbar-expand-lg navClass w-100">
        <a class="navbar-brand">Car Rent</a>
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
                        <a class="dropdown-item" href="user-listed-cars">Listed Cars</a>
                        <a class="dropdown-item" href="user-booked-cars">Booked Cars </a>
                        <a class="dropdown-item" href="userUpdateProfile">Update User Profile</a>
                        <a class="dropdown-item" href="update-password">Update User Password</a>
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
            <div class="row align-items-center signUpform">
                <div class="col-lg-12">
                    <div class="col-lg-4">
                        <br/>
                        <div class="formTitle">
                            <h4>Update Profile</h4>
                        </div>
                        <hr>
                        <div class="form-group">
                            <label for="name">Name:</label>
                            <input type="text" class="form-control field" id="name" name="name"  value="<%=user.getName()%>">
                            <p style="color: red">${nameUpdateError}</p>
                        </div>

                        <div class="form-group">
                            <label for="city">City:</label>
                            <select class="form-control field" name="city_id" id="city">
                                <option value="0" selected>Select City</option>
                                <%if(cityArrayList!=null)
                                {
                                    for(int temp=0;temp<cityArrayList.size();temp++){%>
                                <option value="<%=cityArrayList.get(temp).getCityId()%>"><%=cityArrayList.get(temp).getCityName()%></option>
                                <%}}%>
                            </select>
                            <p style="color: red">${cityUpdateError}</p>

                        </div>
                        <br/>
                        <div>
                            <input type="submit" class="btn btn-primary" value="Update">
                        </div>
                        <br/>
                    </div>
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
