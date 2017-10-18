<%--
  Created by IntelliJ IDEA.
  User: paulvaldez
  Date: 10/18/17
  Time: 12:26 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Ads Index</title>
</head>
<body>
    <c:forEach var="ad" items="${ads}">
        <h1>${ad.title}</h1>
        <h2>${ad.description}</h2>
    </c:forEach>
</body>
</html>
