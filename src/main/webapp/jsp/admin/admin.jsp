<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:bundle basename="locale" prefix="admin.">
<html>
    <head>
        <title><fmt:message key="adminPage"/></title>
    </head>
    <body>
        <%
            response.addHeader("Cache-Control", "no-cache,no-store,private,must-revalidate,max-stale=0,post-check=0,pre-check=0");
            response.addHeader("Pragma", "no-cache");
            response.addDateHeader ("Expires", 0);
        %>
        <header>
            <a href="${pageContext.request.contextPath}/exhibitions/index?expositionPage=1"><fmt:message key="exhibitions"/></a>
            <a href="?locale=en_US"><fmt:message key="en"/></a>
            <a href="?locale=uk_UA"><fmt:message key="ua"/></a>
            <ul>
                <li>
                    <span><c:out value="${sessionScope.account.firstName}"/> <c:out value="${sessionScope.account.lastName}"/></span>
                    <ul>
                        <li><a href="${pageContext.request.contextPath}/exhibitions/admin"><fmt:message key="adminPage"/></a></li>
                        <li><a href="${pageContext.request.contextPath}/exhibitions/logout"><fmt:message key="logout"/></a></li>
                    </ul>
                </li>
            </ul>
        </header>
        <button type="button"><a href="${pageContext.request.contextPath}/exhibitions/admin/create_exposition"><fmt:message key="createExposition"/></a></button><br>
        <button type="button"><a href="${pageContext.request.contextPath}/exhibitions/admin/expositions?expositionPage=1"><fmt:message key="expositionsList"/></a></button><br>
        <button type="button"><a href="${pageContext.request.contextPath}/exhibitions/admin/create_showroom"><fmt:message key="createShowroom"/></a></button><br>
        <button type="button"><a href="${pageContext.request.contextPath}/exhibitions/admin/showrooms?showroomPage=1"><fmt:message key="showroomsList"/></a></button><br>
        <c:out value="${sessionScope.bookingDoNotExists}"/><br>
        <% session.removeAttribute("bookingDoNotExists"); %>
        <form method="post" action="${pageContext.request.contextPath}/exhibitions/admin/unpaid_booking">
            <input type="number" min="1" value="1" name="booking_id">
            <button type="submit"><fmt:message key="unpaidBooking"/></button>
        </form>
    </body>
</html>
</fmt:bundle>