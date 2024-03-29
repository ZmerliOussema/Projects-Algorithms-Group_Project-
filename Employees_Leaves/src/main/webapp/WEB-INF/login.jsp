<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN"
	crossorigin="anonymous">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css' rel='stylesheet'>
<link rel="stylesheet" type="text/css" href="/css/style.css">
<title>إدارة عطل الموظفين</title>
<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css">
<!-- change to match your file/naming structure -->
<script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
</head>

<body style="background: #161615;">
	<div class="wrapper">
		<form:form action="/login" method="post" modelAttribute="newLogin">
			<h1>Login</h1>
			<div class="input-box">
				<form:input path="email" type="email" placeholder="Email" />
				<i class="bx bxs-user"></i>
			</div>
			<div class="errors">
				<form:errors path="email" class="text-danger" />
			</div>
			<div class="input-box">
				<form:password path="password" placeholder="Password" />
				<i class="bx bxs-lock-alt"></i>
			</div>
			<div class="errors">
				<form:errors path="password" class="text-danger" />
			</div>
			<button type="submit" class="btn">Login</button>
		</form:form>
	</div>
</body>
</html>