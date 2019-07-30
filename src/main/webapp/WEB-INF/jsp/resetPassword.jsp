<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link rel="stylesheet" href="/css/bootstrap.min.css">
    <title>Reset Password page</title>
</head>
<body>
<div class="container">
    <div>

        <h3 style="color:blue;" class="card-title">Reset Password</h3>
    <form method="post"
          action="/reset-password" >
        <div >
            <h5>Email address</h5>
        <input type="text" name="email"  placeholder="Email address">

        </div>

     <div>
            <h5>Password</h5>
            <input type="password" name="password"  placeholder="password">
     </div>
        <div>
            <h5>Confirm Password</h5>
            <input type="password" name="confirmPassword"  placeholder="confirmPassword">
        </div>
      
<br>
    <div>

        <button class="btn btn-sm btn-primary  text-uppercase" type="submit" value="reset">Reset</button>
    </div>
    </form>
        <c:if test="${Invalid_Email!= null}">
            <p style="color: red">${Invalid_Email}</p>
        </c:if>
        <c:if test="${Unregistered_Email!= null}">
            <p style="color: red">${Unregistered_Email}</p>
        </c:if>
    <c:if test="${error_confirm_password!= null}">
        <p style="color: red">${error_confirm_password}</p>
    </c:if>
        <c:if test="${successMessage!= null}">
            <p style="color: red">${successMessage}</p>
        </c:if>
     <c:if test="${error_password!= null}">
        <p style="color: red">${error_password}</p>
    </c:if>
        <c:if test="${error!= null}">
            <p style="color: red">${error}</p>
        </c:if>
    
</div></div>

<script src="/js/jquery-3.4.1.min.js"></script>
<script src="/js/index.js"></script>
<script src="/js/updatepassword.js"></script>
</body>
</html>