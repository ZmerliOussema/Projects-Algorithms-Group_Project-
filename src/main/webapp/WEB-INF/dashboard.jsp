<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page isErrorPage="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN"
        crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
        crossorigin="anonymous"></script>
    <title>Admin Dashboard</title>
</head>
<body style="background-color: #161615;">
    <div class="container my-3 border border-primary" style="width: 1000px; background-color: #DAA520;">
        <nav class="d-flex justify-content-between mt-3">
            <div class="d-flex justify-content-between align-items-center" style="width: 20%;">
                <a href="/logout" class="btn btn-primary fw-bold"
                    style="width: 100px; background-color: #5b96c7ef; color: #ffffffc5;">خروج</a>
                <a href="employees/new"
                    class="link-underline-light link-offset-2 link-underline-opacity-25 link-underline-opacity-100-hover"
                    style="color: #ffffffc5;">إضافة عون</a>
            </div>
            <div>
                <h5 class="text" style="color: #ffffffc5;">Admin :المستخدم</h5>
            </div>
        </nav>

        <div class="d-flex justify-content-center align-items-center grid gap-3 mt-5">
            <form action="#" class="form mt-5"></form>
            <select class="form-select" style="width: 100px; height: 35px;" name="year" id="year">
                <option value="2024">2024</option>
                <option value="2023" selected>2023</option>
            </select>
            <h3 class="text text-center text-dark fw-semibold" style="color: #ffffffc5;">العطل الخاصة بالموظفين لسنة</h3>
        </div>

        <div class="d-flex justify-content-end mt-3">
            <h6 class="text text-center text-dark fw-medium" style="color: #ffffffc5;">للتثبت من المعلومات الرجاء الإتصال
                بمصلحة الموارد البشرية *</h6>
        </div>

        <table class="table table-secondary table-striped table-hover text text-center">
            <thead>
                <tr>
                    <th colspan="2">العطل المرضية</th>
                    <th colspan="2" class="table-active border-bottom border-dark-subtle">العطل الإستثنائية</th>
                    <th colspan="2">العطل السنوية</th>
                    <th rowspan="2" class="table-active col col-4 align-middle">الإسم و اللقب</th>
                </tr>
                <tr>
                    <th>المتبقي</th>
                    <th>المأخوذ</th>
                    <th class="table-active">المتبقي</th>
                    <th class="table-active">المأخوذ</th>
                    <th>المتبقي</th>
                    <th>المأخوذ</th>
                </tr>
            </thead>
            <tbody class="table-group-divider">
                <c:forEach items="${employees}" var="employee">
                    <tr class="border-bottom border-dark-subtle">
                        <td><c:out value="${employee.leave.sickRest }"></c:out></td>
                        <td><c:out value="${employee.leave.sickTaken }"></c:out></td>
                        <td class="table-active"><c:out value="${employee.leave.specificRest }"></c:out></td>
                        <td class="table-active"><c:out value="${employee.leave.specificTaken }"></c:out></td>
                        <td><c:out value="${employee.leave.annualRest }"></c:out></td>
                        <td><c:out value="${employee.leave.sickTaken }"></c:out></td>
                        <td class="table-active text-end">
                            <a href="/employees/test"
                                class="link-underline-dark link-offset-2 link-underline-opacity-25 link-underline-opacity-100-hover text-dark fw-medium">
                                <c:out value="${employee.firstNameAr}"></c:out>
                            </a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</body>
</html>
