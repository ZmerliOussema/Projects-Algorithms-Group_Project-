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
<title>Add an Employee</title>
<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="/css/main.css">
<!-- change to match your file/naming structure -->
<script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript" src="/js/app.js"></script>
<!-- change to match your file/naming structure -->
</head>
<body class="container">
	<h1>Add an Employee - إضافة عون</h1>
	<form:form action="/employees" method="post" modelAttribute="employee"
		class="w-25 py-3">
		<div class="row">
			<!-- left column -->
			<div class="col-md-6">
				<p class="form-group">
					<form:label path="firstName">First Name: </form:label>
					<form:errors path="firstName" class="text-danger" />
					<form:input path="firstName" class="form-control" />
				</p>
				<p class="form-group">
					<form:label path="lastName">Last name: </form:label>
					<form:errors path="lastName" class="text-danger" />
					<form:input path="lastName" class="form-control" />
				</p>
				<p class="form-group">
					<form:label path="title">Title: </form:label>
					<form:errors path="title" class="text-danger" />
					<form:select path="title" class="form-control">
						<form:option value="Engineer">Engineer</form:option>
						<form:option value="Technician">Technician</form:option>
						<form:option value="Advisor">Advisor</form:option>
						<form:option value="Guardian">Guardian</form:option>
						<form:option value="Driver">Driver</form:option>
					</form:select>
				</p>
				<p class="form-group">
					<form:label path="rangeEmployee">Range: </form:label>
					<form:errors path="rangeEmployee" class="text-danger" />
					<form:select path="rangeEmployee" class="form-control">
						<form:option value="Worker">Worker</form:option>
						<form:option value="Administrative">Administrative</form:option>
						<form:option value="Technical">Technical</form:option>
					</form:select>
				</p>
				<p class="form-group">
					<form:label path="category">Category: </form:label>
					<form:errors path="category" class="text-danger" />
					<form:select path="category" class="form-control">
						<form:option value="A1">A1</form:option>
						<form:option value="A2">A2</form:option>
						<form:option value="A3">A3</form:option>
						<form:option value="B">B</form:option>
						<form:option value="C">C</form:option>
					</form:select>
				</p>
				<p class="form-group">
					<form:label path="address">Address: </form:label>
					<form:errors path="address" class="text-danger" />
					<form:textarea path="address" class="form-control" />
				</p>
			</div>
			<!-- right column -->
			<div class="col-md-6">
				<p class="form-group">
					<form:label path="firstNameAr">الاسم</form:label>
					<form:errors path="firstNameAr" class="text-danger" />
					<form:input path="firstNameAr" class="form-control" />
				</p>
				<p class="form-group">
					<form:label path="lastNameAr">اللقب</form:label>
					<form:errors path="lastNameAr" class="text-danger" />
					<form:input path="lastNameAr" class="form-control" />
				</p>
				<p class="form-group">
					<form:label path="titleAr">الخطة</form:label>
					<form:errors path="titleAr" class="text-danger" />
					<form:select path="titleAr" class="form-control">
						<form:option value="مهندس">مهندس</form:option>
						<form:option value="فني">فني</form:option>
						<form:option value="مستشار">مستشار</form:option>
						<form:option value="حارس">حارس</form:option>
						<form:option value="سائق">سائق</form:option>
					</form:select>
				</p>
				<p class="form-group">
					<form:label path="rangeAr">الرتبة </form:label>
					<form:errors path="rangeAr" class="text-danger" />
					<form:select path="rangeAr" class="form-control">
						<form:option value="عامل">عامل</form:option>
						<form:option value="إداري">إداري</form:option>
						<form:option value="تقني">تقني</form:option>
					</form:select>
				</p>
				<p class="form-group">
					<form:label path="categoryAr">الصنف </form:label>
					<form:errors path="categoryAr" class="text-danger" />
					<form:select path="categoryAr" class="form-control">
						<form:option value="أ1">أ1</form:option>
						<form:option value="أ2">أ2</form:option>
						<form:option value="أ3">أ3</form:option>
						<form:option value="ب">ب</form:option>
						<form:option value="ج">ج</form:option>
					</form:select>
				</p>
				<p class="form-group">
					<form:label path="addressAr">العنوان </form:label>
					<form:errors path="addressAr" class="text-danger" />
					<form:textarea path="addressAr" class="form-control" />
				</p>
			</div>
		</div>
		<p class="form-group">
			<form:label path="email">Email: </form:label>
			<form:errors path="email" class="text-danger" />
			<form:input path="email" class="form-control" />
		</p>
		<p class="form-group">
			<form:label path="userName">Username: </form:label>
			<form:errors path="userName" class="text-danger" />
			<form:input path="userName" class="form-control" />
		</p>
		<p class="form-group">
			<form:label path="password">Password: </form:label>
			<form:errors path="password" class="text-danger" />
			<form:input path="password" class="form-control" />
		</p>
		<p class="form-group">
			<form:label path="phoneNumber">Phone Number: </form:label>
			<form:errors path="phoneNumber" class="text-danger" />
			<form:input path="phoneNumber" class="form-control" />
		</p>
		<input type="submit" value="Add" class="btn btn-primary" />
	</form:form>
</body>
</html>

