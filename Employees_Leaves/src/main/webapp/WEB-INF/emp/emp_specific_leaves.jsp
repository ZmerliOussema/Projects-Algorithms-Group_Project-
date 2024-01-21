<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- New line below to use the JSP Standard Tag Library -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page isErrorPage="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="/css/main.css">
<!-- change to match your file/naming structure -->
<script type="text/javascript" src="/js/app.js"></script>
<!-- change to match your file/naming structure -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN"
	crossorigin="anonymous">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
	crossorigin="anonymous"></script>
<title>Employee Specific leaves</title>
</head>
<body style="background-color: #161615;">
	<div class="container my-3 border border-primary"
		style="width: 1000px; background-color: #DAA520;">
		<nav class="d-flex justify-content-between mt-3">
			<%--{% if logged_user.id == 1 %}--%>
			<div class="d-flex justify-content-between align-items-center"
				style="width: 25%;">
				<a href="/logout" class="btn btn-primary fw-bold"
					style="width: 100px; background-color: #5b96c7ef; color: #ffffffc5;">خروج</a>
				<a href="/admin_dashboard"><i
					class='fa fa-home w3-xlarge btn btn-primary fw-bold'
					style="color: #4980aa; background-color: #5b96c7ef; color: #ffffffc5;"></i></a>
				<a href="/employees/test"><i
					class='fa fa-long-arrow-left w3-xlarge btn btn-primary fw-bold'
					style="color: #4980aa; background-color: #5b96c7ef; color: #ffffffc5;"></i></a>
			</div>
			<div>
				<h4 class="text" style="color: #ffffffc5;">Admin :المستخدم</h4>
			</div>
		</nav>
		<div
			class="d-flex flex-column justify-content-center align-items-center grid gap-1 mt-5">
			<div
				class="d-flex justify-content-center align-items-center grid gap-3">
				<form action="#" class="form">
					<select class="form-select" style="width: 100px; height: 35px;"
						name="year" id="year">
						<option value="2024">2024</option>
						<option value="2023" selected>2023</option>
					</select>
				</form>
				<h3 class="text text-center text-dark fw-semibold">
					العطل الإستثنائية الخاصة ب <a
						href="/employees/show/{{ annual_leaves_by_employee[0].employee.id }}"
						class="link-underline-dark link-offset-2 link-underline-opacity-25 link-underline-opacity-100-hover">
						نزار الجويني</a> لسنة
				</h3>
			</div>
			<div
				class="d-flex justify-content-center align-items-center grid gap-3">
				<h5 class="text text-center text-dark fw-semibold"
					style="color: #ffffffc5;">يوما في السنة</h5>
				<h5 class="text text-center text-dark fw-semibold"
					style="color: #ffffffc5;">06</h5>
			</div>
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
					<th colspan="2">العطل الإستثنائية</th>
					<th rowspan="2" class="table-active col col-3 align-middle">تاريخ
						النهاية</th>
					<th rowspan="2" class="col col-3 align-middle">تاريخ البداية</th>
				</tr>
				<tr>
					<th>المتبقي</th>
					<th>المجموع</th>
				</tr>
			</thead>
			<tbody class="table-group-divider">
				<tr class="border-bottom border-dark-subtle"></tr>
					<td>04</td>
					<td>02</td>
					<td class="table-active text-end">2023/03/15</td>
					<td class="text-end">2023/03/16</td>
				</tr>
				<form:form
					 action="/employees/specific/1/add" method="post" modelAttribute="newLeave">
					<tr>
						<div class="d-flex justify-content-center align-items-center">
							<td>
								<button type="submit" class="btn btn-primary fw-bold"
									style="width: 200px; background-color: #5b96c7ef; color: #161615;">Add
									- إضافة</button>
							</td>
							<td><form:input path="" type="number"
									class="form-control text-center" name="annual" id="annual"
									value="0" style="background-color: #ebca6eaf;" /></td>
							<td><form:input path="" type="date" class="form-control"
									name="end_date" id="end_date"
									style="background-color: #ebca6eaf;" /></td>
							<td><form:input path="" type="date" class="form-control"
								name="start_date" id="start_date"
								style="background-color: #ebca6eaf;" /></td>
						</div>
					</tr>
				</form:form>
			</tbody>
		</table>
	</div>
</body>
</html>