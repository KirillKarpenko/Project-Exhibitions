<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:bundle basename="locale" prefix="bookings.">
    <html>
        <head>
            <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css"/>
            <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/materialize.min.css"/>
            <title><fmt:message key="bookings"/></title>
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
            <c:forEach var="booking" items="${requestScope.bookings}">
                <table>
                    <tr>
                        <th><fmt:message key="expositionName"/></th>
                        <th><fmt:message key="category"/></th>
                        <th><fmt:message key="startDate"/></th>
                        <th><fmt:message key="endDate"/></th>
                        <th><fmt:message key="quantity"/></th>
                    </tr>
                    <c:forEach var="ticket" items="${booking.tickets}" varStatus="i">
                        <tr>
                            <td align="center"><c:out value="${ticket.exposition.name}"/></td>
                            <td align="center"><c:out value="${requestScope.categories[i.index]}"/></td>
                            <fmt:formatDate value="${ticket.exposition.startDate}" pattern="yyyy-MM-dd" var="startDate"/>
                            <td align="center"><c:out value="${startDate}"/></td>
                            <fmt:formatDate value="${ticket.exposition.endDate}" pattern="yyyy-MM-dd" var="endDate"/>
                            <td align="center"><c:out value="${endDate}"/></td>
                            <td align="center"><c:out value="${ticket.quantity}"/></td>
                        </tr>
                    </c:forEach>
                </table>
            </c:forEach>
            <ul class="pagination">
                <c:forEach begin="1" end="${requestScope.totalAccountBookingPages}" var="i">
                    <c:choose>
                        <c:when test="${requestScope.bookingPage eq i}">
                            <li class="active"><a onclick="return false;">${i}</a></li>
                        </c:when>
                        <c:otherwise>
                            <li class="waves-effect"><a href="${pageContext.request.contextPath}/exhibitions/user/bookings?bookingPage=${i}">${i}</a></li>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
            </ul>
        </body>
    </html>
</fmt:bundle>