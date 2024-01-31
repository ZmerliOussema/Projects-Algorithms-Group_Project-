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
<link rel="stylesheet" type="text/css" href="/css/style.css">
<title>ch_password</title>
<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css">
<script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
</head>

<body style="background: #161615;">
	<div class="wrapper">
		<form:form action="/employees/ch_password/${thisEmployee.id}" method="post" modelAttribute="newEmpPassword">
			<h3 class="text-center text-secondary-emphasis">تغيير كلمة عبور ${thisEmployee.firstNameAr} ${thisEmployee.lastNameAr}</h1>
			<div class="input-box">
				<form:password path="password" placeholder="Password" />
			</div>
			<div class="errors">
				<form:errors path="password" class="text-danger" />
			</div>
			<div class="input-box">
				<form:password path="confirm" placeholder="Confirm Password" />
			</div>
			<div class="errors">
				<form:errors path="confirm" class="text-danger" />
			</div>
			<button type="submit" class="btn fs-4">تغــــــيير</button>
		</form:form>
	</div>
</body>
</html>