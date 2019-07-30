<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link rel="stylesheet" href="/css/bootstrap.min.css">
    <title>Forgot Password</title> 
</head>
<body>
<div class="container" style="margin-right:30px">  
   <div class="col-sm-8">
        <h3 style="text-align: center;" class="card-title">Forgot Password Page</h3>
    <div class="card">
                <div class="card-body">
    <form method="post"
          action="/forgot-password" >
        <div >
            <label for="email">EmailAddress</label>
        <input type="text" class="form-control" name="email"  placeholder="Email address">

        </div>
<br>
    <div>

        <button class="btn btn-primary  text-uppercase" type="submit" value="reset">Reset Password</button>
    </div>
    
   
    <c:if test="${Invalid_Email!= null}">
        <p style="color: red">${Invalid_Email}</p>
    </c:if>
    <c:if test="${Unregistered_Email!= null}">
        <p style="color: red">${Unregistered_Email}</p>
    </c:if>
    <c:if test="${Success!= null}">
        <p style="color: green">${Success}</p>
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