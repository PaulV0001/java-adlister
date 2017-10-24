<%--
  Created by IntelliJ IDEA.
  User: paulvaldez
  Date: 10/24/17
  Time: 10:20 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Register</title>
    <%@include file="/WEB-INF/partials/head.jsp"%>
</head>
<body>
<%@include file="/WEB-INF/partials/navbar.jsp"%>
<form action="/register" method="post">
<div class="form-group">
    <label for="username">Username</label>
    <input type="text" id="username" name="username" class="form-control">
</div>
<div class="form-group">
    <label for="email">Email</label>
    <input type="text" id="email" name="email" class="form-control">
</div>
<div class="form-group">
    <label for="password">Password</label>
    <input type="password" id="password" name="password" class="form-control">
</div>
<div>
    <input type="submit" class="btn btn-primary btn-block" value="submit">
</div>
</form>

</body>
</html>
