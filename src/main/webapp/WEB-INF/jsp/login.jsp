<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link rel="stylesheet" href="/css/bootstrap.min.css">
    <title>Login page</title>
</head>
<body>
<div class="container">
    <div>

        <h3 style="color:blue;" class="card-title">Login</h3>
    <form method="post"
          action="/login" >
        <div >
            <h5>Email address</h5>
        <input type="text" name="email"  placeholder="Email address">

        </div>

     <div>
            <h5>Password</h5>
            <input type="password" name="password"  placeholder="password">
     </div>
<br>
    <div>

        <button class="btn btn-sm btn-primary  text-uppercase" type="submit" value="login">Login</button>
        <button class="btn btn-sm btn-google  text-uppercase" onclick="location.href='/userSignUp'" type="button"> Sign Up </button>
        <a href="/forgot-password">Forgot Password? Click Me</a>
    </div>
    </form>
    <c:if test="${Invalid_Email!= null}">
        <p style="color: red">${Invalid_Email}</p>
    </c:if>
    <c:if test="${Invalid_Password!= null}">
        <p style="color: red">${Invalid_Password}</p>
    </c:if>
    
</div></div>

<script src="/js/jquery-3.4.1.min.js"></script>
<script src="/js/index.js"></script>
<script src="/js/updatepassword.js"></script>
</body>
</html>