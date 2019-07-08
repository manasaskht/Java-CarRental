<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link rel="stylesheet" href="/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="css/signUpForm.css">

    <style>
        .dropbtn {
            font-size: 20px;
            background: none;
            border: none;
        }

        .dropdown {
            position: relative;
            display: inline-block;
        }

        .dropdown-content {
            display: none;
            position: absolute;
            min-width: 160px;
            box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);
            z-index: 1;
        }

        .dropdown-content a {
            color: black;
            padding: 12px 16px;
            text-decoration: none;
            display: block;
        }

        .dropdown-content a:hover {background-color: #ddd;}

        .dropdown:hover .dropdown-content {display: block;}
    </style>


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
                        <a class="nav-link" href="update-password">Update User Password</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="carrent">Car Rent</a>
                    </li>


                    <div class="dropdown">
                        <li class="navbar-item"> <button class="dropbtn" class="nav-link">User Profile</button> </li>
                        <div class="dropdown-content">
                            <a href="user-listed-cars">Listed Cars</a>
                            <a href="user-booked-cars">Booked Cars </a>
                            <a href="userUpdateProfile">Update User Profile</a>
                            <a href="update-password">Update User Password</a>
                        </div>
                    </div>
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
