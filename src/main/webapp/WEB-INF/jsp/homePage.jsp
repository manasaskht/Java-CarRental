<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
      <link rel="stylesheet" href="/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="css/signUpForm.css">
</head>
<body>

   <div class="container">
            <div class="row">
           <h2>Car Rentals</h2>
            </div> 
            <br>
           <div class="row">
           <form method="post" action="/logout">
        <input type="submit" name="action" value="Logout">
        <input type="submit" name="action" value="UpdateProfile">
        <input type="submit" name="action" value="UpdatePassword">
        <input type="submit" name="action" value="CarRent">
        
  		</form>
           </div>
        </div>

</body>
</html>
