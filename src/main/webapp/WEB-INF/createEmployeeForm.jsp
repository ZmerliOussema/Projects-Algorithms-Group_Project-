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
<link rel="stylesheet"	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
	crossorigin="anonymous"></script>
<title>Add Employee</title>
</head>
<body style="background-color: #161615;">
	<form:form action="/employees" method="post" modelAttribute="employee">
		<div
			class="container d-flex flex-column justify-content-between mt-3 border border-bottom-0 border-primary"
			style="width: 1000px; background-color: #DAA520;">
			<div class="text-center">
				<h1 style="color: #ffffffc5;">Add Employee - إضافة عون</h1>
				<a href="/dashboard"><i
					class='fa fa-home w3-xlarge btn btn-primary fw-bold'
					style="color: #4980aa; background-color: #5b96c7ef; color: #ffffffc5;"></i></a>
			</div>
			<div class="d-flex justify-content-between">
				<div>
					<div class="mb-3" style="width: 470px;">
						<label class="form-label fw-bold">First Name:</label>
						<form:input path="firstName" type="text" class="form-control" />
					</div>
					<div class="mb-3" style="width: 470px;">
						<label class="form-label fw-bold">Last Name:</label>
						<form:input path="lastName" type="text" class="form-control" />
					</div>
					<div class="mb-3" style="width: 470px;">
						<label class="form-label fw-bold">E-mail (البريد
							الالكتروني):</label>
						<form:input path="email" type="email" class="form-control" />
					</div>
					<div class="mb-3" style="width: 470px;">
						<label class="form-label fw-bold" style="color: #8a0f0f;">Password
							(كلمة العبور):</label>
						<form:password path="password" class="form-control" />
					</div>
					<div class="mb-3" style="width: 470px;">
						<label class="form-label fw-bold">Address:</label>
						<form:textarea path="address" class="form-control" rows="2" />
					</div>
					<div class="form-label d-flex justify-content-between"
						style="width: 470px;">
						<div class="mb-3" style="width: 470px;">
							<label class="form-label fw-bold">Title:</label>
							<form:select path="title" class="form-select">
								<form:option value="technicien">Technicien</form:option>
								<form:option value="bibliothecaire">Bibliothecaire</form:option>
								<form:option value="conseiller">Conseiller</form:option>
								<form:option value="gardien">Gardien</form:option>
								<form:option value="nettoyage">Nettoyage</form:option>
							</form:select>
						</div>
					</div>
					<div class="form-label d-flex justify-content-between"
						style="width: 470px;">
						<div class="mb-3" style="width: 350px;">
							<label class="form-label fw-bold">Range:</label>
							<form:select path="rangeEmployee" class="form-select">
								<form:option value="technicien">technicien</form:option>
								<form:option value="bibliothecaire">bibliothecaire</form:option>
								<form:option value="conseiller">conseiller</form:option>
								<form:option value="ouvrier">Ouvrier</form:option>
								<form:option value="conseiller">Conseiller</form:option>
							</form:select>
						</div>
						<div class="mb-3" style="width: 100px;">
							<label class="form-label fw-bold">Category</label>
							<form:select path="category" class="form-select">
								<form:option value="A1">A1</form:option>
								<form:option value="A2">A2</form:option>
								<form:option value="A3">A3</form:option>
								<form:option value="B">B</form:option>
								<form:option value="C">C</form:option>
							</form:select>
						</div>
					</div>
				</div>
				<div>
					<div class="mb-3" style="width: 470px;">
						<label class="form-label d-flex justify-content-end fw-bold">:الإسم</label>
						<form:input path="firstNameAr" type="text" class="form-control text-end" />
					</div>
					<div class="mb-3" style="width: 470px;">
						<label class="form-label d-flex justify-content-end fw-bold">:اللقب</label>
						<form:input path="lastNameAr" type="text" class="form-control text-end" />
					</div>
					<div class="mb-3" style="width: 470px;">
						<label class="form-label fw-bold">Phone Number (رقم
							الهاتف):</label>
						<form:input path="phoneNumber" type="text" class="form-control" />
					</div>
					<div class="mb-3" style="width: 470px;">
						<label class="form-label fw-bold" style="color: #8a0f0f;">Confirm
							Password (تأكيد كلمة العبور):</label>
						<form:input path="confirmPassword" type="password" class="form-control" />
					</div>
					<div class="mb-3" style="width: 470px;">
						<label
							class="form-label d-flex justify-content-end fw-bold fw-bold">:العنوان</label>
						<form:textarea path="addressAr" class="form-control text-end" rows="2" />
					</div>
					<div class="mb-3" style="width: 470px;">
						<label class="form-label d-flex justify-content-end fw-bold">:الخطة</label>
						<form:select path="titleAr" class="form-select text-end">
							<form:option value="تقني">تقني</form:option>
							<form:option value="مكتبي">مكتبي</form:option>
							<form:option value="متصرف">متصرف</form:option>
							<form:option value="حارس">حارس</form:option>
							<form:option value="عون تنظيف">عون تنظيف</form:option>
						</form:select>
					</div>
					<div class="form-label d-flex justify-content-between"
						style="width: 470px;">
						<div class="mb-3" style="width: 100px;">
							<label class="form-label d-flex justify-content-end fw-bold">:الصنف</label>
							<form:select path="categoryAr" class="form-select text-end">
								<form:option value="أ1">أ1</form:option>
								<form:option value="أ2">أ2</form:option>
								<form:option value="أ3">أ3</form:option>
								<form:option value="ب">ب</form:option>
								<form:option value="ج">ج</form:option>
							</form:select>
						</div>
						<div class="mb-3" style="width: 350px;">
							<label class="form-label d-flex justify-content-end fw-bold">:الرتبة</label>
							<form:select path="rangeAr" class="form-select text-end">
								<form:option value="تقني">تقني</form:option>
								<form:option value="مكتبي">مكتبي</form:option>
								<form:option value="متصرف">متصرف</form:option>
								<form:option value="مستكتب إدارة">مستكتب إدارة</form:option>
								<form:option value="عامل">عامل</form:option>
							</form:select>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div
			class="container d-flex justify-content-center border border-top-0 border-primary"
			style="width: 1000px; background-color: #DAA520;">
			<button type="submit" class="btn btn-primary my-2 fw-bold"
				style="width: 300px; background-color: #5b96c7ef; color: #161615;">Add
				- إضافة</button>
		</div>
	</form:form>
</body>
</html>