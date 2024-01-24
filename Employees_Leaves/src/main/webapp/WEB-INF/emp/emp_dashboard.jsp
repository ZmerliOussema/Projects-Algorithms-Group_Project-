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
<title>employee Dashboard</title>
<script>
    function filterLeavesByYear() {
        var selectedYear = document.getElementById("year").value;
        var tableRows = document.querySelectorAll(".leave-row");

        tableRows.forEach(function(row) {
            var startDate = row.getAttribute("data-start-date");
            var endDate = row.getAttribute("data-end-date");

            // Extract the year from start_date and end_date
            var startYear = new Date(startDate).getFullYear().toString();
            var endYear = new Date(endDate).getFullYear().toString();

            // Check if the selected year falls within the start_date and end_date range
            if (selectedYear < startYear || selectedYear > endYear) {
                row.style.display = "none";
            } else {
                row.style.display = "";
            }
        });
    }
</script>
</head>
<body style="background-color: #161615;" onload="filterLeavesByYear()">
	<div class="container my-3 border border-primary"
		style="width: 1000px; background-color: #DAA520;">
		<nav class="d-flex justify-content-between mt-3">
			<div class="d-flex justify-content-between align-items-center"
				style="width: 17%;">
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
			class="d-flex justify-content-center align-items-center grid gap-3 mt-5">
			<form action="#" class="form">
				<select class="form-select" style="width: 100px; height: 35px;"
					name="year" id="year" onchange="filterLeavesByYear()">
					<option value="2024" selected>2024</option>
					<option value="2023">2023</option>
				</select>
			</form>
			<h3 class="text text-center text-dark fw-semibold">
				العطل الخاصة ب <a href="/employees/show/${employee.id}"
					class="link-underline-dark link-offset-2 link-underline-opacity-25 link-underline-opacity-100-hover text-dark">${employee.firstName}
					${employee.lastName}</a> لسنة
			</h3>
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
					<th colspan="2"><a href="/employees/sick/${employee.id}"
						class="link-underline-primary link-offset-2 link-underline-opacity-25 link-underline-opacity-100-hover text text-primary">العطل
							المرضية</a></th>
					<th colspan="2"
						class="table-active border-bottom border-dark-subtle"><a
						href="/employees/specific/${employee.id}"
						class="link-underline-primary link-offset-2 link-underline-opacity-25 link-underline-opacity-100-hover text text-primary">العطل
							الإستثنائية</a></th>
					<th colspan="2"><a href="/employees/annual/${employee.id}"
						class="link-underline-primary link-offset-2 link-underline-opacity-25 link-underline-opacity-100-hover text text-primary">العطل
							السنوية</a></th>
					<th rowspan="2" class="table-active col col-3 align-middle">تاريخ
						النهاية</th>
					<th rowspan="2" class="col col-3 align-middle">تاريخ البداية</th>
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
				<c:choose>
					<c:when test="${empty leaves}">
						<tr>
							<td colspan="8">هذا الموظف ليس لديه عطل </td>
						</tr>

					</c:when>
					<c:otherwise>
						<c:set var="remainingAnnual" value="45" />
						<c:set var="remainingSick" value="60" />
						<c:set var="remainingSpecificLeave" value="6" />

						<c:forEach var="leave" items="${leaves}">
							<c:set var="remainingAnnual"
								value="${remainingAnnual - leave.annual}" />
							<c:set var="remainingSick" value="${remainingSick - leave.sick}" />
							<c:set var="remainingSpecificLeave"
								value="${remainingSpecificLeave - leave.specificLeave}" />

							<tr class="border-bottom border-dark-subtle leave-row"
								data-start-date="${leave.start_date}"
								data-end-date="${leave.end_date}">
								<td>${leave.sick == 0 ? '-' : remainingSick}</td>
								<td>${leave.sick == 0 ? '-' : leave.sick}</td>
								<td class="table-active">${leave.specificLeave== 0 ? '-' : remainingSpecificLeave}</td>
								<td class="table-active">${leave.specificLeave == 0 ? '-' : leave.specificLeave}</td>
								<td>${leave.annual == 0 ? '-' : remainingAnnual}</td>
								<td>${leave.annual == 0 ? '-' : leave.annual}</td>
								<td class="table-active text-end"><fmt:formatDate
										value="${leave.end_date}" pattern="yyyy-MM-dd" /></td>
								<td class="text-end"><fmt:formatDate
										value="${leave.start_date}" pattern="yyyy-MM-dd" /> <c:if
										test="${user.role == 'admin'}">
										<form action="/leave/${leave.id}/delete" method="post">
											<input type="hidden" name="_method" value="delete"> <input
												type="submit" value="delete" class="btn btn-danger">
										</form>
									</c:if></td>
							</tr>
						</c:forEach>
					</c:otherwise>
				</c:choose>
			</tbody>
		</table>
	</div>
</body>
</html>