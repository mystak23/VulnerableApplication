<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>You successfully signed up to the system.</title>
<style type="text/css">
	span {
		display: inline-block;
		width: 200px;
		text-align: left;
	}
</style>
</head>
<body>
	<div align="center">
		<h2>Registration Succeeded!</h2>
		<form:form action="register" method="post" modelAttribute="user">
			<form:label path="name"><b>Full name: </b></form:label><span>${user.name}</span>
			<form:label path="password"><b>Password: </b></form:label><span>${user.password}</span>
		</form:form>
		<br><br><a href='index'><button>Return</button></a>
	</div>
</body>
</html>