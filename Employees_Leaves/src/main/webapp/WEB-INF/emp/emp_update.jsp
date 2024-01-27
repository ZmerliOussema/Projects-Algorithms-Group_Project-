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
				style="width: 20%;">
				<a href="/admin_dashboard"><i
					class='fa fa-home w3-xlarge btn btn-primary fw-bold'
					style="color: #4980aa; background-color: #5b96c7ef; color: #ffffffc5;"></i></a>
			</div>
			<div>
				<h4 class="text" style="color: #ffffffc5;">${user.firstName}
					:المستخدم</h4>
			</div>
		</nav>
		<div
			class="d-flex justify-content-center align-items-center grid gap-3 mt-5">
			<h3 class="text text-center text-dark fw-semibold">المعطيات
				الخاصة بـ ${employee.firstNameAr } ${employee.lastNameAr }</h3>
		</div>

		<form:form action="/employees/edit" method="post"
			modelAttribute="updateEmp">
			<form:input path="" type="hidden" name="id" value="{{employee.id}}" />
			<fieldset class="border border-dark">
				<legend class="float-none w-auto text text-end">الإتصال</legend>
				<div class="d-flex flex-column justify-content-center">
					<div class="d-flex justify-content-between align-items-center mx-5">
						<div class="row g-3 align-items-center">
							<div class="col-auto">
								<form:input path="email" type="email" value="${employee.email}"
									class="form-control" />
							</div>
							<div class="col-auto">
								<h6 class="text fw-medium">البريد الإلكتروني</h6>
							</div>
						</div>
						<div class="row g-3 align-items-center">
							<div class="col-auto">
								<form:input path="phoneNumber" type="text"
									value="${employee.phoneNumber}" class="form-control" />
							</div>
							<div class="col-auto">
								<h6 class="text fw-medium">رقم الهاتف</h6>
							</div>
						</div>
					</div>
					<div
						class="d-flex justify-content-end align-items-center mx-5 my-3">
						<div class="row g-3 align-items-center">
							<div class="col-auto">
								<form:input path="addressAr" type="text"
									value="${employee.addressAr}" class="form-control " />
							</div>
							<div class="col-auto">
								<h6 class="text fw-medium ms-4">العنوان</h6>
							</div>
						</div>
					</div>
				</div>
			</fieldset>
			<fieldset class="border border-dark mt-3 mb-3">
				<legend class="float-none w-auto text text-end">معطيات
					شخصية</legend>
				<div class="d-flex justify-content-end">
					<div>
						<div
							class="d-flex justify-content-end align-items-center grid gap-3 me-5 mb-3">
							<div>
								<form:select path="titleAr" class="form-select text-end mb-2"
									value="titleAr">
									<form:option value="تقني">تقني</form:option>
									<form:option value="مكتبي">مكتبي</form:option>
									<form:option value="متصرف">متصرف</form:option>
									<form:option value="حارس">حارس</form:option>
									<form:option value="عون تنظيف">عون تنظيف</form:option>
								</form:select>
							</div>
							<h6 class="text text-dark text-center fw-medium ms-3">الخطة</h6>
						</div>
						<div class="d-flex">
							<div
								class="d-flex justify-content-end align-items-center grid gap-3 me-5 mb-3">
								<div>
									<form:select path="categoryAr"
										class="form-select text-end mb-2" value="categoryAr">
										<form:option value="تقني">تقني</form:option>
										<form:option value="مكتبي">مكتبي</form:option>
										<form:option value="متصرف">متصرف</form:option>
										<form:option value="مستكتب إدارة">مستكتب إدارة</form:option>
										<form:option value="عامل">عامل</form:option>
									</form:select>
								</div>
								<h6 class="text text-dark text-center fw-medium ms-3">
									الصنف</h6>
							</div>
							<div
								class="d-flex justify-content-end align-items-center grid gap-3 me-5 mb-3">
								<div>
									<form:select path="rangeAr" class="form-select text-end mb-2"
										value="rangeAr">
										<form:option value="أ1">أ1</form:option>
										<form:option value="أ2">أ2</form:option>
										<form:option value="أ3">أ3</form:option>
										<form:option value="ب">ب</form:option>
										<form:option value="ج">ج</form:option>
									</form:select>
								</div>
								<h6 class="text text-dark text-center fw-medium ms-4">
									الرتبة</h6>
							</div>
						</div>
					</div>
					<div>
						<div>
							<div
								class="d-flex justify-content-end align-items-center grid gap-3 me-5 mb-3">
								<div>
									<form:input type="text" path="firstNameAr" id="first_name_ar"
										value="${employee.firstNameAr}"
										class="form-control mx-3 text text-end" />
								</div>
								<h6 class="text text-dark text-center fw-medium ms-5">
									الإسـم</h6>
							</div>
							<div
								class="d-flex justify-content-end align-items-center grid gap-3 me-5 mb-3">
								<div>
									<form:input type="text" path="lastNameAr" name="last_name_ar"
										id="last_name_ar" value="${employee.lastNameAr}"
										class="form-control mx-3 text text-end" />
								</div>
								<h6 class="text text-dark text-center fw-medium ms-5">
									اللقب</h6>
							</div>
						</div>
					</div>
				</div>
			</fieldset>
			<div class="container d-flex justify-content-center border-primary"
				style="width: 550px; background-color: #daa520">
				<button type="submit" class="btn btn-primary fw-bold fs-5 my-2 mb-3"
					style="width: 300px; background-color: #5b96c7ef; color: #161615">
					تـحـــــيــيــن</button>
			</div>
		</form:form>
	</div>
</body>
</html>