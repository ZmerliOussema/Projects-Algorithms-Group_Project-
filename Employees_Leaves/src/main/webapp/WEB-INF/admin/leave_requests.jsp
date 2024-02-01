<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!-- New line below to use the JSP Standard Tag Library -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isErrorPage="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
    <link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css' rel='stylesheet'>
    <script
            src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
            crossorigin="anonymous"></script>
    <title>Leave Requests</title>

</head>
<body style="background-color: #161615;">
<div class="container my-3 border border-primary"
     style="width: 1000px; background-color: #DAA520;">
    <nav class="d-flex justify-content-between mt-3">
        <div class="d-flex justify-content-between align-items-center"
             style="width: 20%;">
            <a href="/logout" class="btn btn-primary fw-bold"
               style="width: 100px; background-color: #5b96c7ef; color: #ffffffc5;">خروج</a>
            <a href="/create/employee" class="btn btn-primary fw-bold ms-1 me-1"
               style="width: 150px; background-color: #5b96c7ef; color: #ffffffc5;">إضافة
                عون</a> <a href="/admin_dashboard"><i
                class='fa fa-home w3-xlarge btn btn-primary fw-bold '
                style="color: #4980aa; background-color: #5b96c7ef; color: #ffffffc5;"></i></a>
        </div>
        <div>
            <h5 class="text" style="color: #ffffffc5;">${user.firstName}
                :المستخدم</h5>
        </div>
    </nav>
    <div
            class="d-flex justify-content-center align-items-center grid gap-3 mt-5">
        <h3 class="text text-center text-dark fw-semibold"
            style="color: #ffffffc5;">طلبات العطل الخاصة بالموظفين</h3>
    </div>

    <table
            class="table table-secondary table-striped table-hover text text-center">
        <thead>
        <tr>
            <th class="table-active ">الرفض أو القبول</th>
            <th>نوع العطلة</th>
            <th class="table-active ">المجموع</th>
            <th>تاريخ النهاية</th>
            <th class="table-active ">تاريخ البداية</th>
            <th>الإسم واللقب</th>
        </tr>
        </thead>
        <tbody class="table-group-divider">
        <c:set var="hasApprovedLeaveRequests" value="false"/>
        <c:forEach items="${leaveRequests}" var="leave">
            <c:choose>
                <c:when test="${empty leave.status or leave.status == 'approved'}">
                    <c:set var="hasApprovedLeaveRequests" value="true"/>
                    <tr>
                        <td class="d-flex justify-content-evenly table-active">
                            <form action="/approveLeaveRequest/${leave.id}" method="post">
                                <button type="submit" class="btn btn-success fw-bold"
                                        style="width: 70px; color: #161615;">قبول
                                </button>
                            </form>
                            <form action="/denyLeaveRequest/${leave.id}" method="post">
                                <button type="submit" class="btn btn-danger fw-bold"
                                        style="width: 70px; color: #161615;">رفض
                                </button>
                            </form>
                        </td>
                        <c:choose>
                            <c:when test="${leave.specificLeave != 0}">
                                <td>استثنائية</td>
                                <td class="table-active ">${leave.specificLeave}</td>
                            </c:when>
                            <c:when test="${leave.annual !=0}">
                                <td>سنوية</td>
                                <td class="table-active ">${leave.annual}</td>
                            </c:when>
                            <c:otherwise>
                                <td>مرضية</td>
                                <td class="table-active ">${leave.sick}</td>
                            </c:otherwise>
                        </c:choose>
                        <td><fmt:formatDate value="${leave.end_date}" pattern="yyyy-MM-dd"/></td>
                        <td class="table-active "><fmt:formatDate value="${leave.start_date}"
                                                                  pattern="yyyy-MM-dd"/></td>
                        <td>${leave.owner.firstNameAr}${leave.owner.lastNameAr}</td>
                    </tr>
                </c:when>
            </c:choose>
        </c:forEach>
        <c:if test="${!hasApprovedLeaveRequests}">
            <tr>
                <td colspan="6"  class="text-center">
                    ليس هناك أي طلبات عطل
                </td>
            </tr>
        </c:if>
        </tbody>

    </table>

</div>


</body>
</html>
