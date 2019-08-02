<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList"
         import="com.group4.carrental.model.User"
%>
<%@ page import="com.group4.carrental.model.City" %>

<% User user = new User();
    ArrayList<City> cityArrayList= new ArrayList<City>();
    if (request!=null) {
        user = request.getAttribute("userData") != null ? (User) request.getAttribute("userData") : null;
    }
    cityArrayList= request.getAttribute("cityArrayList")!=null?(ArrayList<City>)request.getAttribute("cityArrayList"):null;
%>
<html>
<head>
    <link rel="stylesheet" href="/css/bootstrap.min.css">

</head>
<body >
<input type="hidden" name="selectedCity" id="selectedCity" value="<%=user!=null?user.getCity_id():0%>">
<header class="header">
    <div class="container">
        <form name="signUpForm" action="userSignUp" method="post">
            <div class="row">
                    <div class="col-sm-6 offset-sm-3 card">
                        <br/>
                        <div class="formTitle">
                            <h1 style="text-align: center"> SIGN UP</h1>
                        </div>
                       <hr>

                        <br/>
                        <div class="form-group">
                            <label for="name">Name:</label>
                            <input type="text" class="form-control" id="name" name="name"  value="<%=user!=null?user.getName():""%>" required>
                            <p style="color: red">${errorMsg.nameError}</p>
                        </div>

                        <div class="form-group">
                            <label for="email">E-mail Address</label>
                            <input type="email" name="email" id="email" class="form-control"  value="<%=user!=null?user.getEmail():""%>" required>
                            <p style="color: red">${errorMsg.emailError}</p>
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
                            <p style="color: red">${errorMsg.cityError}</p>

                        </div>
                        <div class="form-group">
                            <label for="password">Password</label>
                            <input class="form-control" id="password" type="password" name="password"  value="<%=user!=null?user.getPassword():""%>" required>
                            <p style="color: red">${errorMsg.pwdError}</p>
                        </div>
                        <div class="form-group">
                            <label for="confirmPassword">Confirm Password</label>
                            <input class="form-control" id="confirmPassword" type="password" name="confirmPassword" value="<%=user!=null?user.getConfirmPassword():""%>" required>
                            <p style="color: red">${errorMsg.confirmPwdError}</p>

                        </div>

                        <br/>
                        <div>
                            <input type="submit" class="btn btn-primary" value="Register">
                        </div>
                        <br/>
                    </div>
                </div>

        </form>
    </div>
</header>
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
