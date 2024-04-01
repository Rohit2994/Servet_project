<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>add student page</title>
</head>
<body>
<%
String count=(String)request.getAttribute("count");
%>
<h1><%= count %> Student data added to database </h1>

</body>
</html>