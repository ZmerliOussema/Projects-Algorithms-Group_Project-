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
<title>Employee Annual Leaves</title>
</head>
<body style="background-color: #161615;" onload="filterLeavesByYear()">
	<div class="container my-3 border border-primary"
		style="width: 1000px; background-color: #DAA520;">
		<nav class="d-flex justify-content-between mt-3">

			<div class="d-flex justify-content-between align-items-center"
				style="width: 25%;">
				<a href="/logout" class="btn btn-primary fw-bold"
					style="width: 100px; background-color: #5b96c7ef; color: #ffffffc5;">خروج</a>
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
			class="d-flex flex-column justify-content-center align-items-center grid gap-1 mt-5">


				<h3 class="text text-center text-dark fw-semibold">
					طلب عطلة
				</h3>

		</div>

		<table
			class="table table-secondary table-striped table-hover text text-center">
			<thead>
				<tr>
					<th></th>
					<th class="table-active col col-1 align-middle">المجموع</th>
					<th class="col col-2">نوع العطل</th>
					<th rowspan="2" class="table-active col col-3 align-middle">تاريخ
						النهاية</th>
					<th rowspan="2" class="col col-3 align-middle">تاريخ البداية</th>
				</tr>
			</thead>

			<form:form action="/requestLeave" method="post"
				modelAttribute="newLeave" id="leaveForm">
				<tbody>
					<tr>
						<td>
							<div class="d-flex justify-content-center align-items-center">
								<button type="submit" class="btn btn-primary fw-bold"
									style="width: 200px; background-color: #5b96c7ef; color: #161615;">Add
									- إضافة</button>
							</div>
						</td>
						<td><input class="form-control" type="number" id="typeOfLeave"
							 name="typeOfLeave" /></td>
						<td><select class="form-control" id="leaveType"
							name="leaveType" onchange="updateAnnualInput()">
								<option value="annual">سنوية</option>
								<option value="specificLeave">استثنائية</option>
								<option value="sick">مرضية</option>
						</select></td>
						<td><form:input class="form-control" type="date"
								path="end_date" id="end_date" onchange="calculateAnnual()" required="true" /></td>
						<td><form:input class="form-control text-center" type="date"
								path="start_date" id="start_date" onchange="calculateAnnual()" required="true" /></td>
					</tr>
				</tbody>
			</form:form>

		</table>
	</div>

	<script>
		function updateAnnualInput() {
			var leaveType = document.getElementById("leaveType").value;
			var typeOfLeave = document.getElementById("typeOfLeave");

			if (leaveType === "annual") {
				typeOfLeave.setAttribute("placeholder", "سنوية");
				typeOfLeave.setAttribute("path", "annual");
				typeOfLeave.setAttribute("name", "annual");
			} else if (leaveType === "specificLeave") {
				typeOfLeave.setAttribute("placeholder", "استثنائية");
				typeOfLeave.setAttribute("path", "specificLeave");
				typeOfLeave.setAttribute("name", "specificLeave");
			} else if (leaveType === "sick") {
				typeOfLeave.setAttribute("placeholder", "مرضية");
				typeOfLeave.setAttribute("path", "sick");
				typeOfLeave.setAttribute("name", "sick");
			}
		}
		function calculateAnnual() {
			var startDate = document.getElementById("start_date").value;
			var endDate = document.getElementById("end_date").value;

			// Parse the dates
			var startDateObj = new Date(startDate);
			var endDateObj = new Date(endDate);

			// Calculate the difference in days
			var differenceInDays = Math.floor((endDateObj - startDateObj)
					/ (1000 * 60 * 60 * 24));

			// Set the calculated value to the sick input
			document.getElementById("typeOfLeave").value = differenceInDays;
		}
		// Set default values when the page loads
	    window.onload = function () {
	        // Set placeholder for typeOfLeave input
	        document.getElementById("typeOfLeave").setAttribute("placeholder", "سنوية");
	        
	        // Set default value for leaveType select
	        document.getElementById("leaveType").value = "annual";
	        
	        // Call updateAnnualInput to ensure consistency
	        updateAnnualInput();
	    };
	</script>

</body>
</html>
