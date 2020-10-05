<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:bundle basename="locale" prefix="bookings.">
<html>
    <head>
        <title><fmt:message key="bookings"/></title>
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
                        <li><a href="${pageContext.request.contextPath}/exhibitions/user"><fmt:message key="yourAccount"/></a></li>
                        <li><a href="${pageContext.request.contextPath}/exhibitions/logout"><fmt:message key="logout"/></a></li>
                    </ul>
                </li>
            </ul>
        </header>
        <c:forEach var="booking" items="${requestScope.bookings}">
            <table cellspacing="10">
                <tr>
                    <th><fmt:message key="expositionName"/></th>
                    <th><fmt:message key="category"/></th>
                    <th><fmt:message key="startDate"/></th>
                    <th><fmt:message key="endDate"/></th>
                    <th><fmt:message key="quantity"/></th>
                </tr>
                <c:forEach var="ticket" items="${booking.tickets}">
                    <tr>
                        <td align="center"><c:out value="${ticket.exposition.name}"/></td>
                        <td align="center"><c:out value="${ticket.exposition.category}"/></td>
                        <fmt:formatDate value="${ticket.exposition.startDate}" pattern="yyyy-MM-dd" var="startDate"/>
                        <td align="center"><c:out value="${startDate}"/></td>
                        <fmt:formatDate value="${ticket.exposition.endDate}" pattern="yyyy-MM-dd" var="endDate"/>
                        <td align="center"><c:out value="${endDate}"/></td>
                        <td align="center"><c:out value="${ticket.quantity}"/></td>
                    </tr>
                </c:forEach>
            </table>
        </c:forEach>
        <table cellspacing="10">
            <tr>
                <c:forEach begin="1" end="${requestScope.totalAccountBookingPages}" var="i">
                    <c:choose>
                        <c:when test="${requestScope.bookingPage eq i}">
                            <td align="center">${i}</td>
                        </c:when>
                        <c:otherwise>
                            <td align="center"><a href="${pageContext.request.contextPath}/exhibitions/user/bookings?bookingPage=${i}">${i}</a></td>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
            </tr>
        </table>
    </body>
</html>
</fmt:bundle>