<html>
<head>
    <link rel="stylesheet" href="/css/bootstrap.min.css">
    <link rel="stylesheet" href="/css/navBar.css">
</head>
<body >

        <nav class="navbar navbar-expand-lg navClass">
            <a class="navbar-brand">
                <img src="/img/websiteLogo.jpg" width="70" height="70" alt="">
            </a>
            <div class="collapse navbar-collapse">
                <ul class="navbar-nav mr-auto">
                    <li class="nav-item">
                        <a class="nav-link linkClass" href="homePage">Home</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link linkClass" href="/admin/listAllCar">Cars List</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link linkClass" href="/adminListPendingRequests">pending Request</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link linkClass" href="/adminBlackListCars">Blacklist cars</a>
                    </li>

                </ul>
                <br/>
                <form class="form-inline" action="/logout" method="post">
                    <button class="btn btn-danger" type="submit">Logout</button>
                </form>
            </div>
        </nav>

    </form>
    <div class="footer tempClass" style="margin-top:500px;">
        <h6 class="text-center">Car Rent</h6>
        <div class="footer-copyright text-center py-3">© 2019 Copyright:
            <a action="/homePage"> CarRent.com</a>
        </div>
    </div>
</body>
<script src="/js/jquery-3.4.1.min.js"></script>
<script src="/js/bootstrap.min.js"></script>
</body>
</html>
