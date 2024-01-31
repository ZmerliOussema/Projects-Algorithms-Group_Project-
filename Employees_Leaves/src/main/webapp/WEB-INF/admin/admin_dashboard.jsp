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
<title>Admin Dashboard</title>

</head>
<body style="background-color: #161615;">
	<div class="container my-3 border border-primary"
		style="width: 1000px; background-color: #DAA520;">
		<nav class="d-flex justify-content-between mt-3">
			<div class="d-flex justify-content-between align-items-center"
				style="width: 30%;">
				<a href="/logout" class="btn btn-primary fw-bold"
					style="width: 20%; background-color: #5b96c7ef; color: #ffffffc5;">خروج</a>
				<a href="/create/employee" class="btn btn-primary fw-bold ms-1 me-1"
					style="width: 30%; background-color: #5b96c7ef; color: #ffffffc5;">إضافة
					عون</a> <a href="/employeeLeaveRequests"
					class="btn btn-primary fw-bold"
					style="width: 50%; background-color: #5b96c7ef; color: #ffffffc5;">جميع
					طلبات العطل</a>
			</div>

			<div>
				<h5 class="text" style="color: #ffffffc5;">${user.firstName}
					:المستخدم</h5>
			</div>
		</nav>
		<div
			class="d-flex justify-content-center align-items-center grid gap-3 mt-5">
			<h3 class="text text-center text-dark fw-semibold"
				style="color: #ffffffc5;">العطل الخاصة بالموظفين</h3>
		</div>
		<div class="d-flex justify-content-end mt-3">
			<h6 class="text text-center text-dark fw-medium"
				style="color: #ffffffc5;">للتثبت من المعلومات الرجاء الإتصال
				بمصلحة الموارد البشرية *</h6>
		</div>
		<!-- Add search input -->
<div class="input-group mt-3 mb-3">
    <input type="text" class="form-control" id="searchInput">
    <div class="input-group-prepend">
        <span class="input-group-text">ابحث بإسم الموظف</span>
    </div>
</div>

		<table
			class="table table-secondary table-striped table-hover text text-center">
			<thead>
				<tr>
					<th colspan="2">العطل المرضية</th>
					<th colspan="2"
						class="table-active border-bottom border-dark-subtle">العطل
						الإستثنائية</th>
					<th colspan="2">العطل السنوية</th>
					<th rowspan="2" class="table-active col col-4 align-middle">الإسم
						و اللقب</th>
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
    <c:set var="totalAnnual" value="45" />
    <c:set var="totalSick" value="60" />
    <c:set var="totalSpecificLeave" value="6" />

    <c:forEach var="employeeId" items="${combinedLeaves.keySet()}">
            <tr class="employeeRow">
                <td>${totalSick - combinedLeaves[employeeId].sick}</td>
                <td>${combinedLeaves[employeeId].sick}</td>
                <td class="table-active">${totalSpecificLeave - combinedLeaves[employeeId].specificLeave}</td>
                <td class="table-active">${combinedLeaves[employeeId].specificLeave}</td>
                <td>${totalAnnual - combinedLeaves[employeeId].annual}</td>
                <td>${combinedLeaves[employeeId].annual}</td>
                <td class="table-active d-flex justify-content-end align-items-center">
                    <a href="/employees/${employeeId}">${combinedLeaves[employeeId].firstNameAr} ${combinedLeaves[employeeId].lastNameAr}</a>

                    <form action="/employees/${employeeId}/delete" method="post">
                        <input type="hidden" name="_method" value="DELETE">
                        <button type="submit" class="btn btn-danger ms-4">
                            <i class="fa fa-solid fa-trash"></i>
                        </button>
                    </form>
                </td>
            </tr>
    </c:forEach>
</tbody>

		</table>
	</div>
	<script>
		document.getElementById('searchInput').addEventListener('keyup',
				function() {
					var input, filter, table, tr, td, i, txtValue;
					input = this.value.toUpperCase();
					table = document.getElementsByClassName("table")[0];
					tr = table.getElementsByClassName("employeeRow");

					for (i = 0; i < tr.length; i++) {
						td = tr[i].getElementsByTagName("td")[6]; // Index 6 corresponds to the employee name cell
						if (td) {
							txtValue = td.textContent || td.innerText;
							if (txtValue.toUpperCase().indexOf(input) > -1) {
								tr[i].style.display = "";
							} else {
								tr[i].style.display = "none";
							}
						}
					}
				});
	</script>

</body>
</html>