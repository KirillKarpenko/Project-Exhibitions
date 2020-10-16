<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:bundle basename="locale" prefix="index.">
    <html>
        <head>
            <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css"/>
            <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/materialize.min.css"/>
            <title><fmt:message key="exhibitions"/></title>
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
                    <li><a href="${pageContext.request.contextPath}exhibitions/index?page=${requestScope.page}&locale=en"><fmt:message key="en"/></a></li>
                    <li><a href="${pageContext.request.contextPath}exhibitions/index?page=${requestScope.page}&locale=ua"><fmt:message key="ua"/></a></li>
                    <c:choose>
                        <c:when test="${sessionScope.account eq null}">
                            <li><a href="${pageContext.request.contextPath}/exhibitions/login"><fmt:message key="login"/></a></li>
                            <li><a href="${pageContext.request.contextPath}/exhibitions/register"><fmt:message key="register"/></a></li>
                        </c:when>
                        <c:otherwise>
                            <c:choose>
                                <c:when test="${sessionScope.account.role.toString() eq 'USER'}">
                                    <li><a href="${pageContext.request.contextPath}/exhibitions/user"><fmt:message key="yourAccount"/></a></li>
                                </c:when>
                                <c:otherwise>
                                    <li><a href="${pageContext.request.contextPath}/exhibitions/admin"><fmt:message key="adminPage"/></a></li>
                                </c:otherwise>
                            </c:choose>
                            <li><a href="${pageContext.request.contextPath}/exhibitions/logout"><fmt:message key="logout"/></a></li>
                        </c:otherwise>
                    </c:choose>
                </ul>
            </div>
        </nav><br>
        <div class="row">
            <a class="col s3 btn" href="${pageContext.request.contextPath}/exhibitions/index?category=${requestScope.category}&start_date=${requestScope.start_date}&end_date=${requestScope.end_date}&sort=sort_by_price_asc&page=1"><fmt:message key="sortByPriceAsc"/></a>
            <div class="col"></div>
            <a class="col s3 btn" href="${pageContext.request.contextPath}/exhibitions/index?category=${requestScope.category}&start_date=${requestScope.start_date}&end_date=${requestScope.end_date}&sort=sort_by_price_desc&page=1"><fmt:message key="sortByPriceDesc"/></a>
        </div>
        <div class="row">
            <form method="get" action="${pageContext.request.contextPath}/exhibitions/index?category=${requestScope.category}&start_date=${requestScope.start_date}&end_date=${requestScope.end_date}&sort=${requestScope.sort}&page=1">
                <select class="col s2 browser-default" name="category">
                    <c:forEach var="category" items="${requestScope.all_categories}">
                        <option value="${category}">${category}</option>
                    </c:forEach>
                </select>
                <input hidden name="start_date" value="${requestScope.start_date}">
                <input hidden name="end_date" value="${requestScope.end_date}">
                <input hidden name="sort" value="${requestScope.sort}">
                <div class="col"></div>
                <button class="col s3 btn" type="submit"><fmt:message key="filterByCategory"/></button>
            </form>
        </div>
        <div class="row">
            <form method="get" action="${pageContext.request.contextPath}/exhibitions/index?category=${requestScope.category}&start_date=${requestScope.start_date}&end_date=${requestScope.end_date}&sort=${requestScope.sort}&page=1">
                <input hidden name="category" value="${requestScope.category}">
                <input hidden name="sort" value="${requestScope.sort}">
                <input class="col s2" type="date" name="start_date"/> <h6 class="col">-</h6> <input class="col s2" type="date" name="end_date"/>
                <div class="col"></div>
                <button class="btn col s4" type="submit"><fmt:message key="filterByDate"/></button>
            </form>
        </div>
        <c:out value="${sessionScope.maxAmountOfTickets}"/><br>
        <% session.removeAttribute("maxAmountOfTickets"); %>
        <table>
            <tr>
                <th align="center"><fmt:message key="name"/></th>
                <th align="center"><fmt:message key="category"/></th>
                <th align="center"><fmt:message key="startDate"/></th>
                <th align="center"><fmt:message key="endDate"/></th>
                <th align="center"><fmt:message key="price"/></th>
            </tr>
            <c:forEach var="exp" items="${requestScope.expositions}" varStatus="i">
                <tr>
                    <td align="center"><c:out value="${exp.name}"/></td>
                    <td align="center"><c:out value="${requestScope.categories[i.index]}"/></td>
                    <fmt:formatDate value="${exp.startDate}" pattern="yyyy-MM-dd" var="startDate"/>
                    <td align="center"><c:out value="${startDate}"/></td>
                    <fmt:formatDate value="${exp.endDate}" pattern="yyyy-MM-dd" var="endDate"/>
                    <td align="center"><c:out value="${endDate}"/></td>
                    <td align="center"><c:out value="${exp.price}"/></td>
                    <c:if test="${sessionScope.account.role.toString() eq 'USER'}">
                        <td align="center">
                            <form method="post" action="${pageContext.request.contextPath}/exhibitions/user/add_to_cart">
                                <div class="row">
                                    <input class="col s2" type="number" min="1" max="10" value="1" name="quantity"/>
                                </div>
                                <input type="text" hidden name="exposition" value="${exp}"/>
                                <button class="btn" type="submit"><fmt:message key="addToCart"/></button>
                            </form>
                        </td>
                    </c:if>
                </tr>
            </c:forEach>
        </table>
        <ul class="pagination">
            <c:forEach begin="1" end="${requestScope.totalPages}" var="i">
                <c:choose>
                    <c:when test="${requestScope.page eq i}">
                        <li class="active"><a onclick="return false;">${i}</a></li>
                    </c:when>
                    <c:otherwise>
                        <li class="waves-effect"><a href="${pageContext.request.contextPath}/exhibitions/index?category=${requestScope.category}&start_date=${requestScope.start_date}&end_date=${requestScope.end_date}&sort=${requestScope.sort}&page=${i}">${i}</a></li>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </ul>
        </body>
    </html>
</fmt:bundle>