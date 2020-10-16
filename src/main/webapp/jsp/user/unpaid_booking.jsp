<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:bundle basename="locale" prefix="unpaidBookings.">
    <html>
        <head>
            <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css"/>
            <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/materialize.min.css"/>
            <title><fmt:message key="unpaidBookings"/></title>
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
                        <li><a href="${pageContext.request.contextPath}/exhibitions/user"><fmt:message key="yourAccount"/></a></li>
                        <li><a href="${pageContext.request.contextPath}/exhibitions/logout"><fmt:message key="logout"/></a></li>
                    </ul>
                </div>
            </nav>
            <table>
                <tr>
                    <th><fmt:message key="expositionName"/></th>
                    <th><fmt:message key="category"/></th>
                    <th><fmt:message key="startDate"/></th>
                    <th><fmt:message key="endDate"/></th>
                    <th><fmt:message key="price"/></th>
                    <th><fmt:message key="quantity"/></th>
                </tr>
                <tr>
                    <c:forEach var="ticket" items="${requestScope.unpaidBooking.tickets}" varStatus="i">
                        <td align="center"><c:out value="${ticket.exposition.name}"/></td>
                        <td align="center"><c:out value="${requestScope.categories[i.index]}"/></td>
                        <fmt:formatDate value="${ticket.exposition.startDate}" pattern="yyyy-MM-dd" var="startDate"/>
                        <td align="center"><c:out value="${startDate}"/></td>
                        <fmt:formatDate value="${ticket.exposition.endDate}" pattern="yyyy-MM-dd" var="endDate"/>
                        <td align="center"><c:out value="${endDate}"/></td>
                        <td align="center"><c:out value="${ticket.exposition.price}"/></td>
                        <td align="center"><c:out value="${ticket.quantity}"/></td>
                    </c:forEach>
                </tr>
                <tr>
                    <td><h6><fmt:message key="bookingID"/>: <c:out value="${requestScope.unpaidBooking.id}"/></h6></td>
                    <td></td>
                    <td></td>
                    <td align="center"><h6><fmt:message key="total"/>: </h6></td>
                    <td align="center"><h6><c:out value="${requestScope.unpaidBooking.total}"/></h6></td>
                    <td align="center">
                        <form method="post" action="${pageContext.request.contextPath}/exhibitions/user/unpaid_booking/delete_unpaid_booking_button">
                            <input type="number" hidden name="booking_id" value="${requestScope.unpaidBooking.id}">
                            <button class="btn red" type="submit"><fmt:message key="delete"/></button>
                        </form>
                    </td>
                </tr>
            </table>
        </body>
    </html>
</fmt:bundle>