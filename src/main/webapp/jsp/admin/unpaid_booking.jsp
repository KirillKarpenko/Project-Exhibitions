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
                        <li><a href="${pageContext.request.contextPath}/exhibitions/admin"><fmt:message key="adminPage"/></a></li>
                        <li><a href="${pageContext.request.contextPath}/exhibitions/logout"><fmt:message key="logout"/></a></li>
                    </ul>
                </div>
            </nav>
            <table>
                <tr>
                    <th><fmt:message key="firstName"/></th>
                    <th><fmt:message key="lastName"/></th>
                    <th><fmt:message key="expositionName"/></th>
                    <th><fmt:message key="quantity"/></th>
                    <th><fmt:message key="price"/></th>
                </tr>
                <tr>
                    <td align="center"><c:out value="${requestScope.unpaidBooking.account.firstName}"/></td>
                    <td align="center"><c:out value="${requestScope.unpaidBooking.account.lastName}"/></td>
                    <c:forEach var="ticket" items="${requestScope.unpaidBooking.tickets}" begin="0" end="0">
                        <td align="center"><c:out value="${ticket.exposition.name}"/></td>
                        <td align="center"><c:out value="${ticket.quantity}"/></td>
                        <td align="center"><c:out value="${ticket.exposition.price}"/></td>
                    </c:forEach>
                    <c:forEach var="ticket" items="${requestScope.unpaidBooking.tickets}" begin="1">
                        <tr>
                            <td></td>
                            <td></td>
                            <td align="center"><c:out value="${ticket.exposition.name}"/></td>
                            <td align="center"><c:out value="${ticket.quantity}"/></td>
                            <td align="center"><c:out value="${ticket.exposition.price}"/></td>
                        </tr>
                    </c:forEach>
                </tr>
                <tr>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td align="center"><h6><fmt:message key="total"/>: <c:out value="${requestScope.unpaidBooking.total}"/></h6></td>
                    <td align="center">
                        <form method="post" action="${pageContext.request.contextPath}/exhibitions/admin/unpaid_booking/update_booking_button">
                            <input type="number" hidden name="booking_id" value="${requestScope.unpaidBooking.id}">
                            <button class="btn" type="submit"><fmt:message key="update"/></button>
                        </form>
                    </td>
                </tr>
            </table>
        </body>
    </html>
</fmt:bundle>
