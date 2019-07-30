<html>
<head>
    <link rel="stylesheet" href="/css/bootstrap.min.css">
    <link rel="stylesheet" href="/css/navBar.css">
</head>
<body>


<nav class="navbar navbar-expand-lg navClass w-100">
    <a class="navbar-brand">
        <img src="/img/websiteLogo.jpg" width="70" height="70" alt="">
    </a>
    <div class="collapse navbar-collapse">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item">
                <a class="nav-link linkClass active" href="/homePage">Home</a>
            </li>
            <li class="nav-item">
                <a class="nav-link linkClass" href="carrent">Rent a Car</a>
            </li>
            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" id="navbarDropdown" role="button" data-toggle="dropdown"
                   aria-haspopup="true" aria-expanded="false">User Profile</a>
                <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                    <a class="dropdown-item" href="userListedCars">Listed Cars</a>
                    <a class="dropdown-item" href="userBookedCars">Booked Cars </a>
                    <a class="dropdown-item" href="userUpdateProfile">Update User Profile</a>
                    <a class="dropdown-item" href="#">Update User Password</a>
                </div>
            </li>
        </ul>
        <br/>
        <form class="form-inline" action="/logout" method="post">
            <button class="btn btn-danger" type="submit">Logout</button>
        </form>
    </div>
</nav>
    <div class="container">
    <form action="/updatePassword" method="post" class="card p-3 bg-white">

        <label class="h2" >Update Password</label> <br>
        <div class="form-group">
            <label for="oldPassword">Old Password</label> <br>
            <input type="password" class="form-group" id="oldPassword" name="oldPassword" required>
            <div style="color:red;">
                <span>${oldPassworderror}</span>
            </div>

        </div>

        <div class="form-group">
            <label for="newPassword">New Password</label> <br>
            <input type="password" class="form-group" id="newPassword" name="newPassword" required>
            <div style="color:red;">
                <span>${newPasswordError}</span>
            </div>
        </div>

        <div class="form-group">
            <label for="confirmPassword">Confirm Password</label> <br>
            <input type="password" class="form-group" id="confirmPassword" name="confirmPassword" onkeyup="onTypeValidate();" required>
            <div style="color:red;">
            <span id="message"></span>
            </div>
        </div>

        <div>
        <button class ="btn btn-dark" onclick="return onTypeValidate();">Submit</button>
        </div>

        <br>
    </form>
    </div>








<script src="/js/jquery-3.4.1.min.js"></script>
<script src="js/bootstrap.min.js"></script>

    <script src="/js/updatepassword.js"></script>
</body>
</html>
