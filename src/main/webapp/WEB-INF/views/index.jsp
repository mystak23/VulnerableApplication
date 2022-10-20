<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Spring NoSQL vulnerable application</title>
</head>
<body>
<div align="center">
	<h2>Welcome to the vulnerable application!</h2>
	<p>You can create your account and log in. This web application is designed for exploiting noSQL vulnerability.</p>
	<p>During registration, you are inserting your username and password (in unencrypted and unhashed form) into noSQL database.</p>
	<p>Your goal is to inject this database. Good luck!</p>
	<a href='register'><button>Register</button></a>
	<a href='login'><button>Login</button></a>
</div>
</body>
</html>