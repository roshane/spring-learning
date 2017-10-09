<%@page language="java" contentType="text/html; ISO-8859-1" trimDirectiveWhitespaces="true" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Home</title>
</head>
<body>
<hr>
<h3>customers</h3>
<ol>
    <c:forEach var="customer" items="${customers}">
        <li>${customer.name}&nbsp;${customer.age}</li>
    </c:forEach>
</ol>
</body>
</html>