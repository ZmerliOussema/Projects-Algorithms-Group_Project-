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
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
	crossorigin="anonymous"></script>
<title>Update Employee</title>
</head>
<body style="background-color: #161615;">
	<form:form action="/employees/edit/${employee.id}" method="post"
		modelAttribute="updateEmp">

		<div
			class="container d-flex flex-column justify-content-between mt-3 border border-bottom-0 border-primary"
			style="width: 1000px; background-color: #DAA520;">
			<div class="text-center">
				<h1 style="color: #ffffffc5;">المعطيات الخاصة بـ
					${employee.firstNameAr } ${employee.lastNameAr }</h1>
				<a href="/admin_dashboard"><i
					class='fa fa-home w3-xlarge btn btn-primary fw-bold'
					style="color: #4980aa; background-color: #5b96c7ef; color: #ffffffc5;"></i></a>
			</div>
			<div class="d-flex justify-content-between">
				<div>
					<div class="mb-3" style="width: 470px;">
						<label class="form-label fw-bold">First Name:</label>
						<form:errors path="firstName" class="text-danger" />
						<form:input path="firstName" value="${employee.firstName}"
							type="text" class="form-control" />
					</div>
					<div class="mb-3" style="width: 470px;">
						<label class="form-label fw-bold">Last Name:</label>
						<form:errors path="lastName" class="text-danger" />
						<form:input path="lastName" value="${employee.lastName}"
							type="text" class="form-control" />
					</div>
					<div class="mb-3" style="width: 470px;">
						<label class="form-label fw-bold">E-mail (البريد
							الالكتروني):</label>
						<form:errors path="email" class="text-danger" />
						<form:input path="email" value="${employee.email}" type="email"
							class="form-control" />
					</div>
					<div class="mb-3" style="width: 470px;">
						<form:errors path="password" class="text-danger" />
						<form:input path="password" value="${employee.password}"
							type="hidden" class=" form-control" />
					</div>
					<div class="mb-3" style="width: 470px;">
						<label class="form-label fw-bold">Address:</label>
						<form:errors path="address" class="text-danger" />
						<form:input path="address" value="${employee.address}"
							class="form-control" rows="2" />
					</div>
					<div class="form-label d-flex justify-content-between"
						style="width: 470px;">
						<div class="mb-2" style="width: 470px;">
							<label class="form-label fw-bold">Title:</label>
							<form:errors path="title" class="text-danger" />
							<c:set var="titles"
								value="Technicien, Bibliothecaire, Conseiller, Gardien, Nettoyage" />
							<form:select path="title" class="form-select">
								<c:forEach var="oneTitle" items="${titles}">
									<c:if test="${oneTitle == employee.title}">
										<form:option selected="selected" value="${oneTitle}">${oneTitle}</form:option>
									</c:if>
									<c:if test="${oneTitle != employee.title}">
										<form:option value="${oneTitle}">${oneTitle}</form:option>
									</c:if>
								</c:forEach>
							</form:select>
						</div>
					</div>
					<div class="form-label d-flex justify-content-between"
						style="width: 470px;">
						<div class="mb-3" style="width: 470px;">
							<label class="form-label fw-bold">Range:</label>
							<form:errors path="rangeEmployee" class="text-danger" />
							<c:set var="ranges"
								value="Technicien, Bibliothecaire, Conseiller, Ouvrier, Administration" />
							<form:select path="rangeEmployee" class="form-select">
								<c:forEach var="oneRange" items="${ranges}">
									<c:if test="${oneRange == employee.rangeEmployee}">
										<form:option selected="selected" value="${oneRange}">${oneRange}</form:option>
									</c:if>
									<c:if test="${oneRange != employee.rangeEmployee}">
										<form:option value="${oneRange}">${oneRange}</form:option>
									</c:if>
								</c:forEach>
							</form:select>
						</div>
						<div class="mb-3 ms-3" style="width: 100px;">
							<label class="form-label fw-bold">Category:</label>
							<form:errors path="category" class="text-danger" />
							<c:set var="categories" value="A1, A2, A3, B, C" />
							<form:select path="category" class="form-select">
								<c:forEach var="oneCategory" items="${categories}">
									<c:if test="${oneCategory == employee.category}">
										<form:option selected="selected" value="${oneCategory}">${oneCategory}</form:option>
									</c:if>
									<c:if test="${oneCategory != employee.category}">
										<form:option value="${oneCategory}">${oneCategory}</form:option>
									</c:if>
								</c:forEach>
							</form:select>
						</div>
					</div>
				</div>
				<div>
					<div class="mb-3" style="width: 470px;">
						<label class="form-label d-flex justify-content-end fw-bold">:الإسم</label>
						<form:errors path="firstNameAr" class="text-danger" />
						<form:input path="firstNameAr" value="${employee.firstNameAr}"
							type="text" class="form-control text-end" />
					</div>
					<div class="mb-3" style="width: 470px;">
						<label class="form-label d-flex justify-content-end fw-bold">:اللقب</label>
						<form:errors path="lastNameAr" class="text-danger" />
						<form:input path="lastNameAr" value="${employee.lastNameAr}"
							type="text" class="form-control text-end" />
					</div>
					<div class="mb-3" style="width: 470px;">
						<label class="form-label fw-bold">Phone Number (رقم
							الهاتف):</label>
						<form:errors path="phoneNumber" class="text-danger" />
						<form:input path="phoneNumber" value="${employee.phoneNumber}"
							type=" text" class="form-control" />
					</div>
					<div class="mb-3" style="width: 470px;">
						<form:errors path="confirm" class="text-danger" />
						<form:input path="confirm" value="${employee.password}"
							type="hidden" class="form-control" />
					</div>
					<div class="mb-3" style="width: 470px;">
						<label
							class="form-label d-flex justify-content-end fw-bold fw-bold">:العنوان</label>
						<form:errors path="addressAr" class="text-danger" />
						<form:input path="addressAr" type="text"
							value="${employee.addressAr}" class=" form-control text-end" />
					</div>
					<div class="mb-3" style="width: 470px;">
						<label class="form-label d-flex justify-content-end fw-bold">:الخطة</label>
						<form:errors path="titleAr" class="text-danger" />
						<c:set var="titlesAr" value="تقني, مكتبي, متصرف, حارس, تنظيف" />
						<form:select path="titleAr" class="form-select text-end">
							<c:forEach var="oneTitleAr" items="${titlesAr}">
								<c:if test="${oneTitleAr == employee.titleAr}">
									<form:option selected="selected" value="${oneTitleAr}">${oneTitleAr}</form:option>
								</c:if>
								<c:if test="${oneTitleAr != employee.titleAr}">
									<form:option value="${oneTitleAr}">${oneTitleAr}</form:option>
								</c:if>
							</c:forEach>
						</form:select>
					</div>
					<div class="form-label d-flex justify-content-between"
						style="width: 470px;">
						<div class="mb-3" style="width: 100px;">
							<label class="form-label d-flex justify-content-end fw-bold">:الصنف</label>
							<form:errors path="rangeAr" class="text-danger" />
							<c:set var="rangesAr" value="أ1, أ2, أ3, ب, ج" />
							<form:select path="rangeAr" class="form-select text-end">
								<c:forEach var="oneRangeAr" items="${rangesAr}">
									<c:if test="${oneRangeAr == employee.rangeAr}">
										<form:option selected="selected" value="${oneRangeAr}">${oneRangeAr}</form:option>
									</c:if>
									<c:if test="${oneRangeAr != employee.rangeAr}">
										<form:option value="${oneRangeAr}">${oneRangeAr}</form:option>
									</c:if>
								</c:forEach>
							</form:select>
						</div>
						<div class="mb-3" style="width: 350px;">
							<label class="form-label d-flex justify-content-end fw-bold">:الرتبة</label>
							<form:errors path="categoryAr" class="text-danger" />
							<c:set var="categoriesAr" value="تقني, مكتبي, متصرف, مستكتب إدارة, عامل" />
							<form:select path="categoryAr" class="form-select text-end">
								<c:forEach var="onecategoryAr" items="${categoriesAr}">
									<c:if test="${onecategoryAr == employee.categoryAr}">
										<form:option selected="selected" value="${onecategoryAr}">${onecategoryAr}</form:option>
									</c:if>
									<c:if test="${onecategoryAr != employee.categoryAr}">
										<form:option value="${onecategoryAr}">${onecategoryAr}</form:option>
									</c:if>
								</c:forEach>
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
</body>
</html>