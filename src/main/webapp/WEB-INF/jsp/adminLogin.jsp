<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link rel="stylesheet" href="/css/bootstrap.min.css">
    <link rel="stylesheet" href="/css/navBar.css">
</head>
<body>
<div class="container" style="margin-top:100px">
    <div class="row">
        <div class="col-sm-6 offset-sm-3">
            <h3>Login Page Admin</h3>
        </div>
    </div>
    <div class="row">
        <div class="col-sm-6 offset-sm-3">
            <div class="card">
                <div class="card-body">
                    <form action="/admin/login" method="post">
                        <div class="form-group">
                            <label for="username">Username</label>
                            <input type="text" class="form-control" id="username" name="username" placeholder="Enter Admin username"
                                   required minlength="3" maxlength="50">
                        </div>
                        <div class="form-group">
                            <label for="password">Password</label>
                            <input type="password" class="form-control" id="password" name="password"
                                   placeholder="Enter Admin Password" required minlength="5" maxlength="50">
                        </div>
                        <button type="submit" class="btn btn-primary">Login</button>
                        <c:if test="${loginError != null}">
                            <p style="color:red; margin-top:10px">Login Error</p>
                        </c:if>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>