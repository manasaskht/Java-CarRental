<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link rel="stylesheet" href="/css/bootstrap.min.css">
</head>
<body>

<div class="container">
    <div class="row">
        <nav class="navbar navbar-expand-lg navbar-light bg-light w-100">
            <a class="navbar-brand">Car Rent</a>
            <div class="collapse navbar-collapse">
                <ul class="navbar-nav mr-auto">
                    <li class="nav-item">
                        <a class="nav-link" href="carrent">Rent a Car</a>
                    </li>
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" id="navbarDropdown" role="button" data-toggle="dropdown"
                           aria-haspopup="true" aria-expanded="false">User Profile</a>
                        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                            <a class="dropdown-item" href="user-listed-cars">Listed Cars</a>
                            <a class="dropdown-item" href="user-booked-cars">Booked Cars </a>
                            <a class="dropdown-item" href="userUpdateProfile">Update User Profile</a>
                            <a class="dropdown-item" href="update-password">Update User Password</a>
                        </div>
                    </li>
                </ul>
                <form class="form-inline" action="/logout" method="post">
                    <button class="btn btn-danger" type="submit">Logout</button>
                </form>
            </div>
        </nav>
    </div>
</div>
<script src="/js/jquery-3.4.1.min.js"></script>
<script src="js/bootstrap.min.js"></script>
</body>
</html>
