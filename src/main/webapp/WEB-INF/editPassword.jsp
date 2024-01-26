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
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet"	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN"
	crossorigin="anonymous">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
	crossorigin="anonymous"></script>
<title>Change Password</title>
</head>
<body style="background-color: #161615;">
        <form:form action="/users/${user.id }" method="post" modelAttribute="user">
        	<input type="hidden" name="_method" value="put">
            <div class="container d-flex flex-column justify-content-center align-items-center mt-3 border border-bottom-0 border-primary p-3" style="width: 550px; background-color: #DAA520;">
                <div class="text-center mb-5">
                    <h4>تحيين كلمة العبور الخاصة ب ${employee.firstNameAr } ${employee.lastNameAr }</h4>
                </div>
                <div>
                    <div class="mb-3 d-flex justify-content-center flex-column" style="width: 470px;">
                        <label class="form-label fw-bold text-center" style="color: #8a0f0f;">كلمة العبور الجديدة</label>
                        <form:errors path="password" class="text-danger" />
                        <form:password path="password" class="form-control" />
                    </div>
                    <div class="mb-3 d-flex justify-content-center flex-column mt-3" style="width: 470px;">
                        <label class="form-label fw-bold text-center" style="color: #8a0f0f;">تأكيد كلمة العبور الجديدة</label>
                        <form:errors path="confirmPW" class="text-danger" />
                        <form:password path="confirmPW" class="form-control" />
                    </div>
                </div>
            </div>
            <div class="container d-flex justify-content-center border border-top-0 border-primary" style="width: 550px; background-color: #DAA520;">
                <button type="submit" class="btn btn-primary my-2 fw-bold mb-3" style="width: 300px; background-color: #5b96c7ef; color: #161615;">تـحـــــيــيــن</button>
            </div>
        </form:form>


    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-geWF76RCwLtnZ8qwWowPQNguL3RmwHVBC9FhGdlKrxdiJJigb/j/68SIy3Te4Bkz" crossorigin="anonymous"></script>
</body>
</html>