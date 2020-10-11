<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:bundle basename="locale" prefix="index.">
    <html>
    <head>
        <title><fmt:message key="exhibitions"/></title>
    </head>
    <body>
    <%
        response.addHeader("Cache-Control", "no-cache,no-store,private,must-revalidate,max-stale=0,post-check=0,pre-check=0");
        response.addHeader("Pragma", "no-cache");
        response.addDateHeader ("Expires", 0);
    %>
    <header>
        <a href="${pageContext.request.contextPath}/exhibitions/index?expositionPage=1"><fmt:message key="exhibitions"/></a>
        <a href="?locale=en"><fmt:message key="en"/></a>
        <a href="?locale=ua"><fmt:message key="ua"/></a>
        <c:choose>
            <c:when test="${sessionScope.account eq null}">
                <button><a href="${pageContext.request.contextPath}/exhibitions/login"><fmt:message key="login"/></a></button>
                <button><a href="${pageContext.request.contextPath}/exhibitions/register"><fmt:message key="register"/></a></button>
            </c:when>
            <c:otherwise>
                <ul>
                    <li>
                        <span><c:out value="${sessionScope.account.firstName}"/> <c:out value="${sessionScope.account.lastName}"/></span>
                        <ul>
                            <c:choose>
                                <c:when test="${sessionScope.account.role.toString() eq 'USER'}">
                                    <li><a href="${pageContext.request.contextPath}/exhibitions/user"><fmt:message key="yourAccount"/></a></li>
                                </c:when>
                                <c:otherwise>
                                    <li><a href="${pageContext.request.contextPath}/exhibitions/admin"><fmt:message key="adminPage"/></a></li>
                                </c:otherwise>
                            </c:choose>
                            <li><a href="${pageContext.request.contextPath}/exhibitions/logout"><fmt:message key="logout"/></a></li>
                        </ul>
                    </li>
                </ul>
            </c:otherwise>
        </c:choose>
    </header>
    <a href="${pageContext.request.contextPath}/exhibitions/sort_by_price_asc?page=1"><fmt:message key="sortByPriceAsc"/></a><br>
    <a href="${pageContext.request.contextPath}/exhibitions/sort_by_price_desc?page=1"><fmt:message key="sortByPriceDesc"/></a><br>
    <form method="post" action="${pageContext.request.contextPath}/exhibitions/filter_by_category?page=1">
        <select name="category">
            <c:forEach var="category" items="${requestScope.categories}">
                <option value="${category}">${category.name()}</option>
            </c:forEach>
        </select>
        <button type="submit"><fmt:message key="filterByCategory"/></button>
    </form>
    <c:out value="${sessionScope.enterDates}"/><br>
    <% session.removeAttribute("enterDates"); %>
    <form method="post" action="${pageContext.request.contextPath}/exhibitions/filter_by_date?page=1">
        <input type="date" name="start_date"/> - <input type="date" name="end_date"/>
        <button type="submit"><fmt:message key="filterByDate"/></button>
    </form>
    <table cellspacing="10">
        <tr>
            <th><fmt:message key="name"/></th>
            <th><fmt:message key="category"/></th>
            <th><fmt:message key="startDate"/></th>
            <th><fmt:message key="endDate"/></th>
            <th><fmt:message key="price"/></th>
        </tr>
        <c:forEach var="exp" items="${requestScope.expositions}">
            <tr>
                <td align="center"><c:out value="${exp.name}"/></td>
                <td align="center"><c:out value="${exp.category}"/></td>
                <fmt:formatDate value="${exp.startDate}" pattern="yyyy-MM-dd" var="startDate"/>
                <td align="center"><c:out value="${startDate}"/></td>
                <fmt:formatDate value="${exp.endDate}" pattern="yyyy-MM-dd" var="endDate"/>
                <td align="center"><c:out value="${endDate}"/></td>
                <td align="center"><c:out value="${exp.price}"/></td>
                <c:if test="${sessionScope.account.role.toString() eq 'USER'}">
                    <td align="center">
                        <form method="post" action="${pageContext.request.contextPath}/exhibitions/user/add_to_cart">
                            <input type="number" min="1" max="9" value="1" name="quantity"/>
                            <input type="text" hidden name="exposition" value="${exp}"/>
                            <button type="submit"><fmt:message key="addToCart"/></button>
                        </form>
                    </td>
                </c:if>
            </tr>
        </c:forEach>
    </table>
    <table cellspacing="10">
        <tr>
            <c:forEach begin="1" end="${requestScope.totalPages}" var="i">
                <c:choose>
                    <c:when test="${requestScope.page eq i}">
                        <td align="center">${i}</td>
                    </c:when>
                    <c:otherwise>
                        <td align="center"><a href="${pageContext.request.contextPath}/exhibitions/sort_by_price_asc?page=${i}">${i}</a></td>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </tr>
    </table>
    </body>
    </html>
</fmt:bundle>