<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link rel="stylesheet" href="/css/bootstrap.min.css">
    <title>Login page</title>
</head>
<body>
<div class="container" style="margin-right:30px">   
        <div class="col-sm-8">
        <h3 style="text-align: center;" class="card-title">Login Page</h3>
        <div class="card">
                <div class="card-body">
    <form method="post"
          action="/login" >
        <div >
            <label for="email">EmailAddress</label>
        <input type="text" class="form-control" name="email"  placeholder="Email address">

        </div>
<br>
     <div>
            <label for="password">Password</label>
            <input type="password" class="form-control" name="password"  placeholder="password">
     </div>
<br>
    <div>

        <button class="btn btn-md btn-primary  text-uppercase" type="submit" value="login">Login</button>
        <button class="btn btn-md btn-google  text-uppercase" onclick="location.href='/userSignUp'" type="button"> Sign Up </button>
      <br> <br>  <a href="/forgot-password">Forgot Password? Click Me</a>
    </div>
    <c:if test="${Invalid_Email!= null}">
        <p style="color: red">${Invalid_Email}</p>
    </c:if>
    <c:if test="${Invalid_Password!= null}">
        <p style="color: red">${Invalid_Password}</p>
    </c:if>
     <c:if test="${Invalid_Credentials!= null}">
        <p style="color: red">${Invalid_Credentials}</p>
    </c:if>
    </form> 
</div>
</div>
</div>
</div>

<script src="/js/jquery-3.4.1.min.js"></script>
<script src="/js/index.js"></script>
<script src="/js/updatepassword.js"></script>
</body>
</html>