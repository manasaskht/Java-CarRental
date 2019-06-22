<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link rel="stylesheet" href="/css/bootstrap.min.css">
    <title>Login page</title>
</head>
<body>
<div class="container">
    <h3>Login page</h3>
    <form method="post"
          action="/login">
        <table border="0" cellpadding="2" cellspacing="2">
            <tr>
                <td>Username</td>
                <td><input type="text" name="email"></td>

            </tr>
            <tr>
                <td>Password</td>
                <td><input type="password" name="password"></td>

            </tr>
            <tr>
                <td></td>
                <td><input type="submit" value="Login"></td>
            </tr>
        </table>
    </form>
    <c:if test="${Invalid_Email!= null}">
        <p style="color: red">${Invalid_Email}</p>
    </c:if>
    <c:if test="${Invalid_Password!= null}">
        <p style="color: red">${Invalid_Password}</p>
    </c:if>
</div>
<script src="/js/jquery-3.4.1.min.js"></script>
<script src="/js/index.js"></script>
</body>
</html>