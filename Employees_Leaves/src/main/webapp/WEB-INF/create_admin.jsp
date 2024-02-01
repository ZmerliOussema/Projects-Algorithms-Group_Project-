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
<title>إدارة عطل الموظفين</title>
<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css">
<!-- change to match your file/naming structure -->
<script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
</head>

<body style="background: #161615;">
<div class="wrapper">

<form:form action="/create/admin/add" method="post" modelAttribute="newEmployee">
<h1>Register</h1>
	<table>
	    <thead>
	    	<tr>
	            <td class="form-label">First Name:</td>
	            <td >
	            	<form:errors path="firstName" class="text-danger"/>
					<form:input  class="form-control"  path="firstName" value="admin"/>
	            </td>
	        </tr>
	        <tr>
	            <td class="form-label">Last Name:</td>
	            <td >
	            	<form:errors path="lastName" class="text-danger"/>
					<form:input  class="form-control"  path="lastName" value="admin"/>
	            </td>
	        </tr>
	        <tr>
	            <td class="form-label">Email:</td>
	            <td >
	            	<form:errors path="email" class="text-danger"/>
					<form:input  class="form-control"  path="email"/>
	            </td>
	        </tr>
	        	        <tr>
	            <td >
					<form:input  type="hidden"  class="form-control"  path="role" value="admin"/>
					<form:input  type="hidden"  class="form-control"  path="address" value="address"/>
					<form:input  type="hidden"  class="form-control"  path="title" value="title"/>
					<form:input  type="hidden"  class="form-control"  path="rangeEmployee" value="rangeEmployee"/>
					<form:input  type="hidden"  class="form-control"  path="category" value="category"/>
					<form:input  type="hidden"  class="form-control"  path="phoneNumber" value="216"/>
	           <form:input  type="hidden"  class="form-control"  path="firstNameAr" value="أدمين"/>
	           <form:input  type="hidden"  class="form-control"  path="lastNameAr" value="أدمين"/>
	           <form:input  type="hidden"  class="form-control"  path="addressAr" value="addressAr"/>
	            <form:input  type="hidden"  class="form-control"  path="titleAr" value="titleAr"/>
	           <form:input  type="hidden"  class="form-control"  path="rangeAr" value="rangeAr"/>
	           <form:input  type="hidden"  class="form-control"  path="categoryAr" value="categoryAr"/>
	            </td>
	        </tr>
	        <tr>
	            <td class="form-label">Password:</td>
	            <td >
	            	<form:errors path="password" class="text-danger"/>
					<form:input type="password"  class="form-control"  path="password"/>
	            </td>
	        </tr>
	        <tr>
	            <td class="form-label">Confirm PW:</td>
	            <td >
	            	<form:errors path="confirm" class="text-danger"/>
					<form:input type="password"  class="form-control"  path="confirm"/>
	            </td>
	        </tr>
	        <tr>
	        	<td colspan=2><input   class="btn btn-success"  type="submit" value="Submit"/></td>
	        </tr>
	    </thead>
	</table>
</form:form>
</div>
</body>
</html>