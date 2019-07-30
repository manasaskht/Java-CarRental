<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link rel="stylesheet" href="/css/bootstrap.min.css">
    <title>Forgot Password</title> 
</head>
<body>
<div class="container">
   

        <h3 style="color:blue;" class="card-title">Forgot Password</h3>
    <form method="post"
          action="/forgot-password" >
        <div >
            <h5>Email address</h5>
        <input type="text" name="email"  placeholder="Email address">

        </div>
<br>
    <div>

        <button class="btn btn-sm btn-primary  text-uppercase" type="submit" value="reset">Reset Password</button>
    </div>
    </form>
   
    <c:if test="${Invalid_Email!= null}">
        <p style="color: red">${Invalid_Email}</p>
    </c:if>
    <c:if test="${Unregistered_Email!= null}">
        <p style="color: red">${Unregistered_Email}</p>
    </c:if>
    <c:if test="${Success!= null}">
        <p style="color: red">${Success}</p>
    </c:if>
</div>

<script src="/js/jquery-3.4.1.min.js"></script>
<script src="/js/index.js"></script>
<script src="/js/updatepassword.js"></script>

</body>
</html>