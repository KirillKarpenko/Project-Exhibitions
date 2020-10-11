<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:bundle basename="locale" prefix="updateExposition.">
<html>
    <head>
        <title><fmt:message key="updateExposition"/></title>
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
        <c:out value="${sessionScope.expositionExists}"/><br>
        <% session.removeAttribute("expositionExists"); %>
        <form method="post" action="${pageContext.request.contextPath}/exhibitions/admin/expositions/update_exposition/update_exposition_button">
            <label><fmt:message key="name"/>: </label>
            <input type="text" name="exposition_name" placeholder="${requestScope.exposition.name}"/><br>
            <label><fmt:message key="category"/>: </label>
            <select name="category">
                <option value="${requestScope.exposition.category}" selected disabled hidden>${requestScope.exposition.category}</option>
                <c:forEach var="category" items="${requestScope.categories}">
                    <option value="${category}">${category.name()}</option>
                </c:forEach>
            </select><br>
            <label><fmt:message key="startDate"/>: </label>
            <input type="date" name="start_date"/><br>
            <label><fmt:message key="endDate"/>: </label>
            <input type="date" name="end_date"/><br>
            <label><fmt:message key="price"/>: </label>
            <input type="number" step="0.01" min=0 name="price" placeholder="${requestScope.exposition.price}"><br>
            <button type="submit" name="exposition" value="${requestScope.exposition}"><fmt:message key="update"/></button>
            <button type="button"><a href="${pageContext.request.contextPath}/exhibitions/admin/expositions?expositionPage=1"><fmt:message key="cancel"/></a></button>
        </form>
    </body>
</html>
</fmt:bundle>