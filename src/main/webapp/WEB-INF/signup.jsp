<%--
  Created by IntelliJ IDEA.
  User: GabF
  Date: 09/09/2020
  Time: 17:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Register</title>

</head>

<body class="bg-gradient-primary">

<form class="user" id="register-form">
    <div class="form-group">
        <input type="email" class="form-control form-control-user" id="user-email"
               placeholder="Email Address" required>
    </div>
    <div class="form-group row">
        <div class="col-sm-6 mb-3 mb-sm-0">
            <input type="password" class="form-control form-control-user" id="user-password"
                   placeholder="Password" required>
        </div>
        <div class="col-sm-6">
            <input type="password" class="form-control form-control-user" id="user-repeat-password"
                   placeholder="Repeat Password" required>
        </div>
    </div>
    <button class="btn btn-primary btn-user btn-block">
        Register Account
    </button>

</form>

<script src="signup.js"></script>
</body>

</html>