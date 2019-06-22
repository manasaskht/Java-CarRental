
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
    <link rel="stylesheet" type="text/css" href="css/signUpForm.css">

</head>
<body >

<header class="header">

    <input type="hidden" name="selectedCity" id="selectedCity" value="<%=user!=null?user.getCity_id():0%>">
    <div class="container">
        <form  action="userUpdateProfile" method="post">
            <div class="row">
                <div class="col-lg-12 reg">
                    <div class="col-lg-4">
                        <br/>
                        <div class="formTitle">
                            Update Profile
                        </div>
                        <br/>

                        <br/>
                        <div class="form-group">
                            <label for="name">Name:</label>
                            <input type="text" class="form-control" id="name" name="name"  value="<%=user.getName()%>">
                            <p class="error-msg">${nameUpdateError}</p>
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
                            <p class="error-msg">${cityUpdateError}</p>

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
