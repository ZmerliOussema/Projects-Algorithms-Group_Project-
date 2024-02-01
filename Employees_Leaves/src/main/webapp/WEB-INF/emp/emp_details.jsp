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
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
	crossorigin="anonymous"></script>
<title>Employee Details</title>
</head>
<body style="background-color: #161615;">
	<div class="container my-3 border border-primary"
		style="width: 1000px; background-color: #DAA520;">
		<nav class="d-flex justify-content-between mt-3">
			<div class="d-flex justify-content-between align-items-center"
				style="width: 23%;">
				<a href="/admin_dashboard"><i
					class='fa fa-home w3-xlarge btn btn-primary fw-bold'
					style="color: #4980aa; background-color: #5b96c7ef; color: #ffffffc5;"></i></a>
				<c:if test="${user.role == 'admin' }">
					<a href="/employees/ch_password/${employee.id}"
						class="btn btn-primary fw-bold ms-1 me-1"
						style="width: 70%; background-color: #5b96c7ef; color: #ffffffc5;">تغيير
						كلمة العبور</a>
					<a href="/employees/edit/${employee.id}"
						class="btn btn-primary fw-bold  me-1"
						style="width: 30%; background-color: #5b96c7ef; color: #ffffffc5;">تحيين</a>
				</c:if>
			</div>
			<div>
				<h4 class="text" style="color: #ffffffc5;">${user.firstName}
					:المستخدم</h4>
			</div>
		</nav>
		<div
			class="d-flex justify-content-center align-items-center grid gap-3 mt-5">
			<h3 class="text text-center text-dark fw-semibold">المعطيات
				الخاصة بـ ${employee.firstNameAr} ${employee.lastNameAr}</h3>
		</div>
		<div
			class="d-flex justify-content-end align-items-center grid gap-3 mt-3">
			<h6 class="text text-dark text-center fw-medium">للتثبت من
				المعلومات الرجاء الإتصال بمصلحة الموارد البشرية *</h6>
		</div>
		<fieldset class="border border-dark">
			<legend class="float-none w-auto text text-end"> Contact /
				الإتصال</legend>
			<div class="d-flex justify-content-between">
				<div class="text text-start">
					<h6 class="text fw-medium text-start">Email: ${employee.email}</h6>
					<h6 class="text fw-medium text-start">Phone Number:
						${employee.phoneNumber}</h6>
					<h6 class="text fw-medium text-start">Address:
						${employee.address}</h6>

				</div>
				<div class=" text text-end">
					<h6 class="text fw-medium text-end">${employee.email} : البريد
						الالكتروني</h6>
					<h6 class="text fw-medium text-end">رقم الهاتف :
						${employee.phoneNumber}</h6>
					<h6 class="text fw-medium text-end">العنوان :
						${employee.addressAr}</h6>

				</div>
			</div>
		</fieldset>

		<fieldset class="border border-dark mt-5 mb-3">
			<legend class="float-none w-auto text text-end">Personal
				Information / معطيات شخصية </legend>
			<div class="d-flex justify-content-between">
				<div class="text text-start">
					<h6 class="text fw-medium text-start">Name:
						${employee.firstName} ${employee.lastName}</h6>
					<h6 class="text fw-medium text-start">Title: ${employee.title}</h6>
					<h6 class="text fw-medium text-start">Category:
						${employee.category}</h6>
					<h6 class="text fw-medium text-start">Range:
						${employee.rangeEmployee}</h6>
				</div>
				<div class=" text text-end">
					<h6 class="text fw-medium text-end">الإسم و اللقب :
						${employee.firstNameAr} ${employee.lastNameAr}</h6>
					<h6 class="text fw-medium text-end">الخطة :
						${employee.titleAr}</h6>
					<h6 class="text fw-medium text-end">الرتبة :
						${employee.rangeAr}</h6>
					<h6 class="text fw-medium text-end">الصنف :
						${employee.categoryAr}</h6>
				</div>
			</div>
		</fieldset>

	</div>
</body>
</html>