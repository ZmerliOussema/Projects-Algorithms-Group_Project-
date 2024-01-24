<%-- <%@ page language="java" contentType="text/html; charset=UTF-8"
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
<link rel="stylesheet"	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
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
				<a href="/employees/ch_password/1"
					class="link-underline-light link-offset-2 link-underline-opacity-25 link-underline-opacity-100-hover"
					style="color: #ffffffc5;">تغيير كلمة العبور</a> <a
					href="/employees/edit/1"
					class="link-underline-light link-offset-2 link-underline-opacity-25 link-underline-opacity-100-hover"
					style="color: #ffffffc5;">تحيين</a>
			</div>
			<div>
				<h4 class="text" style="color: #ffffffc5;">Admin :المستخدم</h4>
			</div>
		</nav>
		<div class="d-flex justify-content-center align-items-center grid gap-3 mt-5">
			<h3 class="text text-center text-dark fw-semibold">المعطيات
				الخاصة ب نزار الجويني</h3>
		</div>
		<div
			class="d-flex justify-content-end align-items-center grid gap-3 mt-3">
			<h6 class="text text-dark text-center fw-medium">للتثبت من
				المعلومات الرجاء الإتصال بمصلحة الموارد البشرية *</h6>
		</div>
		<fieldset class="border border-dark">
			<legend class="float-none w-auto text text-end">الإتصال</legend>
			<div class="d-flex flex-column justify-content-center">
				<div class="d-flex justify-content-between align-items-center mx-5">
					<h6 class="text fw-medium">nizar@gmail.com : البريد
						الالكتروني</h6>
					<h6 class="text fw-medium">98 111 111 : رقم الهاتف</h6>
				</div>
				<div class="d-flex justify-content-end align-items-center mx-5">
					<h6 class="text fw-medium">العنوان : تونس -- تونس</h6>
				</div>
			</div>
		</fieldset>
		<fieldset class="border border-dark mt-5 mb-3">
			<legend class="float-none w-auto text text-end">معطيات شخصية</legend>
			<div class="d-flex flex-column justify-content-center">
				<div class="d-flex justify-content-between align-items-center mx-5">
					<h6 class="text fw-medium">الخطة : مساعد تطبيق و بحث أول فوق الرتبة</h6>
					<h6 class="text fw-medium">الإسم و اللقب : نزار الجويني</h6>
				</div>
				<div class="d-flex flex-column align-items-end mx-5">
					<h6 class="text fw-medium">الصنف : فني</h6>
					<h6 class="text fw-medium">الرتبة : أ2</h6>
				</div>
			</div>
		</fieldset>
	</div>
</body>
</html> --%>