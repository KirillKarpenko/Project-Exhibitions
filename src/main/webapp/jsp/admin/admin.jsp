<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:bundle basename="locale" prefix="admin.">
    <html>
        <head>
            <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/materialize.min.css"/>
            <title><fmt:message key="adminPage"/></title>
        </head>
        <body>
            <%
                response.addHeader("Cache-Control", "no-cache,no-store,private,must-revalidate,max-stale=0,post-check=0,pre-check=0");
                response.addHeader("Pragma", "no-cache");
                response.addDateHeader ("Expires", 0);
            %>
            <nav>
                <div class="nav-wrapper">
                    <a class="brand-logo" href="${pageContext.request.contextPath}/exhibitions/index?page=1"><fmt:message key="exhibitions"/></a>
                    <ul class="right hide-on-med-and-down">
                        <li><a href="?locale=en"><fmt:message key="en"/></a></li>
                        <li><a href="?locale=ua"><fmt:message key="ua"/></a></li>
                        <li><a href="${pageContext.request.contextPath}/exhibitions/admin"><fmt:message key="adminPage"/></a></li>
                        <li><a href="${pageContext.request.contextPath}/exhibitions/logout"><fmt:message key="logout"/></a></li>
                    </ul>
                </div>
            </nav>
            <br>
            <a class="btn" href="${pageContext.request.contextPath}/exhibitions/admin/create_exposition"><fmt:message key="createExposition"/></a><br><br>
            <a class="btn" href="${pageContext.request.contextPath}/exhibitions/admin/expositions?expositionPage=1"><fmt:message key="expositionsList"/></a><br><br>
            <a class="btn" href="${pageContext.request.contextPath}/exhibitions/admin/create_showroom"><fmt:message key="createShowroom"/></a><br><br>
            <a class="btn" href="${pageContext.request.contextPath}/exhibitions/admin/showrooms?showroomPage=1"><fmt:message key="showroomsList"/></a><br>
            <c:out value="${sessionScope.bookingDoNotExists}"/><br>
            <% session.removeAttribute("bookingDoNotExists"); %>
            <form method="post" action="${pageContext.request.contextPath}/exhibitions/admin/unpaid_booking">
                <div class="row">
                    <input class="col s1" type="number" min="1" max="2147483647" value="1" name="booking_id">
                    <div class="col"></div>
                    <button class="btn col s3" type="submit"><fmt:message key="unpaidBooking"/></button>
                </div>
            </form>
        </body>
    </html>
</fmt:bundle>