<html>
<head>
    <link rel="stylesheet" href="/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="css/signUpForm.css">

</head>
<body >

<header class="header">
    <nav class="navbar navbar-style">
        <div class="container">
            <div class="navbar-header">
                <a href="" class="webSiteLogo"> <img class="logo" alt="logo" src="img/webLogo.jpg"></a>
            </div>
            <div class="webTitle"> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Car Rent&nbsp;&nbsp;&nbsp;</div>


            <nav class="navbar navbar-expand-sm navbar-right">
                <ul class="navbar-nav">
                    <li class="nav-item">
                        <a class="nav-link" href="">Login</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="">FAQ</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="">Contact Us</a>
                    </li>
                </ul>
            </nav>

        </div>
    </nav>
    <br/>
    <br/>

    <div class="container">
        <form name="signUpForm" action="saveUserDetails" method="post">
            <div class="row">
                <div class="col-lg-12 reg">
                    <div class="col-lg-4">
                        <br/>
                        <div class="formTitle">
                            Sign Up
                        </div>
                        <br/>

                        <br/>
                        <div class="form-group">
                            <label for="name">Name:</label>
                            <input type="text" class="form-control" id="name" name="name"  required>
                        </div>
                        <div class="form-group">
                            <label for="email">E-mail Address</label>
                            <input type="email" name="email" id="email" class="form-control"  required>

                        </div>
                        <div class="form-group">
                            <label for="city">City:</label>
                            <input type="text" class="form-control" id="city" name="city"  required>

                        </div>
                        <div class="form-group">
                            <label for="password">Password</label>
                            <input class="form-control" id="password" type="password" name="password"   required>

                        </div>
                        <div class="form-group">
                            <label for="confirmPwd">Confirm Password</label>
                            <input class="form-control" id="confirmPwd" type="password" name="confirmPwd"  required>

                        </div>

                        <br/>
                        <div>
                            <input type="submit" class="btn btn-primary" value="Register">
                        </div>
                        <br/>
                    </div>
                </div>
            </div>
        </form>
    </div>
</header>
<script src="/js/jquery-3.4.1.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/angular.min.js"></script>

<script type="text/javascript" src="js/userSignUp.js"></script>
</body>
</html>