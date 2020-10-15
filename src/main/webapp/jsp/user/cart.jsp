<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:bundle basename="locale" prefix="cart.">
    <html>
        <head>
            <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css"/>
            <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/materialize.min.css"/>
            <title><fmt:message key="cart"/></title>
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
            <c:out value="${sessionScope.payPreviousBooking}"/><br>
            <%session.removeAttribute("payPreviousBooking");%>
            <table>
                <tr>
                    <th><fmt:message key="expositionName"/></th>
                    <th><fmt:message key="expositionCategory"/></th>
                    <th><fmt:message key="startDate"/></th>
                    <th><fmt:message key="endDate"/></th>
                    <th><fmt:message key="price"/></th>
                    <th><fmt:message key="quantity"/></th>
                </tr>
                <c:forEach var="item" items="${sessionScope.cart}" varStatus="loop">
                    <tr>
                        <td align="center"><c:out value="${item.exposition.name}"/></td>
                        <td align="center"><c:out value="${requestScope.categories[loop.index]}"/></td>
                        <fmt:formatDate value="${item.exposition.startDate}" pattern="yyyy-MM-dd" var="startDate"/>
                        <td align="center"><c:out value="${startDate}"/></td>
                        <fmt:formatDate value="${item.exposition.endDate}" pattern="yyyy-MM-dd" var="endDate"/>
                        <td align="center"><c:out value="${endDate}"/></td>
                        <td align="center"><c:out value="${item.exposition.price}"/></td>
                        <td align="center"><c:out value="${item.quantity}"/></td>
                        <td align="center">
                            <form method="post" action="${pageContext.request.contextPath}/exhibitions/user/cart/remove_from_cart">
                                <input type="number" hidden name="deletion_index" value="${loop.index}"/>
                                <button class="btn red" type="submit"><fmt:message key="delete"/></button>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
                <tr>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td align="center"><h6><fmt:message key="total"/>: <c:out value="${requestScope.total}"/></h6></td>
                    <td align="center">
                        <form method="post" action="${pageContext.request.contextPath}/exhibitions/user/cart/send_order">
                            <input type="number" step="0.01" hidden name="total" value="${requestScope.total}"/>
                            <button class="btn" type="submit"><fmt:message key="checkout"/></button>
                        </form>
                    </td>
                </tr>
            </table>
        </body>
    </html>
</fmt:bundle>