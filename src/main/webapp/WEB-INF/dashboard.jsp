<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- New line below to use the JSP Standard Tag Library -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page isErrorPage="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN"
	crossorigin="anonymous">
	<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
	crossorigin="anonymous"></script>
<title>Admin Dashboard</title>

</head>
<body style="background-color: #161615;">
	<div class="container my-3 border border-primary"
		style="width: 1000px; background-color: #DAA520;">
		<nav class="d-flex justify-content-between mt-3">
			<div class="d-flex justify-content-between align-items-center"
				style="width: 20%;">
				<a href="/logout" class="btn btn-primary fw-bold"
					style="width: 100px; background-color: #5b96c7ef; color: #ffffffc5;">خروج</a>
				<a href="/employees/new"
					class="link-underline-light link-offset-2 link-underline-opacity-25 link-underline-opacity-100-hover"
					style="color: #ffffffc5;">إضافة عون</a>
			</div>
			<div>
				<h5 class="text" style="color: #ffffffc5;">${user.firstNameAr} ${user.lastNameAr}
					:المستخدم</h5>
			</div>
		</nav>
		<div
			class="d-flex justify-content-center align-items-center grid gap-3 mt-5">
			<h3 class="text text-center text-dark fw-semibold"
				style="color: #ffffffc5;">العطل الخاصة بالموظفين </h3>
		</div>
		<div class="d-flex justify-content-end mt-3">
			<h6 class="text text-center text-dark fw-medium"
				style="color: #ffffffc5;">للتثبت من المعلومات الرجاء الإتصال
				بمصلحة الموارد البشرية *</h6>
		</div>
		<table
			class="table table-secondary table-striped table-hover text text-center">
			<thead>
				<tr>
					<th colspan="2">العطل المرضية</th>
					<th colspan="2"
						class="table-active border-bottom border-dark-subtle">العطل
						الإستثنائية</th>
					<th colspan="2">العطل السنوية</th>
					<th rowspan="2" class="table-active col col-4 align-middle">الإسم
						و اللقب</th>
				</tr>
				<tr>
					<th>المتبقي</th>
					<th>المجموع</th>
					<th class="table-active">المتبقي</th>
					<th class="table-active">المجموع</th>
					<th>المتبقي</th>
					<th>المجموع</th>
				</tr>
			</thead>
			<tbody class="table-group-divider">
				<c:set var="totalAnnual" value="45" />
				<c:set var="totalSick" value="60" />
				<c:set var="totalSpecificLeave" value="6" />

				<c:forEach var="ownerId" items="${combinedLeaves.keySet()}">
					<tr>
						<td>${totalSick - combinedLeaves[ownerId].sick}</td>
						<td>${combinedLeaves[ownerId].sick}</td>
						<td class="table-active">${totalSpecificLeave - combinedLeaves[ownerId].specificLeave}</td>
						<td class="table-active">${combinedLeaves[ownerId].specificLeave}</td>
						<td>${totalAnnual - combinedLeaves[ownerId].annual}</td>
						<td>${combinedLeaves[ownerId].annual}</td>
						<td class="table-active"><a href="/leaves/${ownerId}">${combinedLeaves[ownerId].firstName}
								${combinedLeaves[ownerId].lastName}</a>
								
								<form action="/employees/${ownerId}" method="post">
								<input type="hidden" name="_method" value="DELETE"> 
								<input type="submit" value="delete" class="btn btn-danger">
							</form>
								
								</td>
					</tr>
				</c:forEach>

			</tbody>
		</table>
	</div>
</body>
</html>