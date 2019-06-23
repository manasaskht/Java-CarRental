<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link rel="stylesheet" href="/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="css/signUpForm.css">
</head>
<body>

<div class="container">
    <div class="row">
        <nav class="navbar navbar-expand-lg navbar-light bg-light w-100">
            <a class="navbar-brand">Car Rent</a>
            <div class="collapse navbar-collapse">
                <ul class="navbar-nav mr-auto">
                    <li class="nav-item">
                        <a class="nav-link" href="userUpdateProfile">Update User Profile</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="update_password">Update User Password</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="carrent">Car Rent</a>
                    </li>
                </ul>
                <form class="form-inline" action="/logout" method="post">
                    <button class="btn btn-danger" type="submit">Logout</button>
                </form>
            </div>
        </nav>
    </div>
</div>

</body>
</html>
