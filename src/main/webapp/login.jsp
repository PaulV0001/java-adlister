<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login Page</title>
    <%@ include file="partials/styles.jsp" %>
</head>
<body>
    <%@include file="partials/navbar.jsp"%>

    <form method="POST" action="/login.jsp">

        <label for="username">Username</label>
        <input id="username" name="username" type="text">
        <br>

        <label for="password">Password</label>
        <input id="password" name="password" type="password">
        <br>

        <input type="submit" value="calculate">
    </form>

    <%= request.getParameter("username")%>
    <%= request.getParameter("password")%>

    <c:if test="${param.username == 'admin' && param.password == 'password'}">
    <% response.sendRedirect("/profile.jsp");%>
    </c:if>

</body>
</html>
