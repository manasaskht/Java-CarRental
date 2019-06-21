<html>
<head>
    <link rel="stylesheet" href="/css/bootstrap.min.css">
</head>
<body>

    <h2> Update password</h2>
    <br>

    <form action="/update_password" method="post">

        Old Password:<br>
        <input type="text" id="oldPassword" name="oldPassword" required>
        <br><br>
        New Password:<br>
        <input type="text" id="newPassword" name="newPassword" required>
        <br><br>
        Confirm Password:<br>
        <input type="text" id="confirmpassword" name="confirmpassword" required>

        <br><br>
        <input type="submit" value="Submit">

    </form>
    <br>
    ${error}
<script src="/js/jquery-3.4.1.min.js"></script>
<script src="/js/index.js"></script>
</body>
</html>