<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- c:out ; c:forEach etc. -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- Formatting (dates) -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!-- form:form -->
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!-- for rendering errors on PUT routes -->
<%@ page isErrorPage="true"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Dashboard</title>
<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="/css/main.css">
<!-- change to match your file/naming structure -->
<script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript" src="/js/app.js"></script>
<!-- change to match your file/naming structure -->
</head>
<body>
	<div>
		<p>User: ..........</p>
		<a href="/employees/new">Add an Employee</a> <a href="/logout">Logout</a>
	</div>
	<div>
		<p>Employee holidays for the year:
		<p>
			<select name="year">
				<option value="2023">2023</option>
				<option value="2024">2024</option>
			</select>
	</div>
	<div>
		<p>*To verify information, please contact the Human Resources
			Department</p>
	</div>
	<table class="table table-bordered">
		<thead>
			<tr>
				<th scope="col">Employee</th>
				<th scope="col">Annual Leaves (Total)</th>
				<th scope="col">Annual Leaves (Rest)</th>
				<th scope="col">Specific Leaves s(Total)</th>
				<th scope="col">Specific Leaves (Rest)</th>
				<th scope="col">Sick Leaves (Total)</th>
				<th scope="col">Sick Leaves (Rest)</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${employees}" var="employee">
				<tr>
					<td><c:out value="${employee.firstName }"></c:out> <c:out value="${employee.lastName }"></c:out></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>

