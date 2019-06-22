
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList"
         import="com.group4.carrental.model.User"
%>

<% User user = new User();
   user=request.getAttribute("userData")!=null?(User) request.getAttribute("userData"):null;

%>
<html>
<head>
    <link rel="stylesheet" href="/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="css/signUpForm.css">

</head>
<body >

<header class="header">


    <div class="container">
        <form  action="userUpdateProfile" method="post">
            <div class="row">
                <div class="col-lg-12 reg">
                    <div class="col-lg-4">
                        <br/>
                        <div class="formTitle">
                            Sign Up
                        </div>
                        <br/>

                        <br/>
                        <div class="form-group">
                            <label for="name">Name:</label>
                            <input type="text" class="form-control" id="name" name="name"  value="<%=user.getName()%>" required>
                        </div>
                        <div class="form-group">
                            <label for="email">E-mail Address</label>
                            <input type="email" name="email" id="email" class="form-control" value="<%=user.getEmail()%>" required>

                        </div>
                        <div class="form-group">
                            <label for="city">City:</label>
                            <input type="text" class="form-control" id="city" name="city"  value="<%=user.getCity_id()%>" required>

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
</html>
