<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:bundle basename="locale" prefix="createExposition.">
<html>
    <head>
        <title><fmt:message key="createExposition"/></title>
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
        <c:out value="${sessionScope.expositionExists}"/><br>
        <% session.removeAttribute("expositionExists"); %>
        <c:out value="${sessionScope.enterAllFields}"/><br>
        <% session.removeAttribute("enterAllFields"); %>
        <form method="post" action="${pageContext.request.contextPath}/exhibitions/admin/create_exposition/create_exposition_button">
            <label><fmt:message key="name"/><span>*</span></label>
            <input type="text" name="exposition_name"/><br>
            <label><fmt:message key="category"/><span>*</span></label>
            <select name="category">
                <c:forEach var="category" items="${requestScope.categories}">
                    <option value="${category}">${category.name()}</option>
                </c:forEach>
            </select><br>
            <label><fmt:message key="startDate"/><span>*</span></label>
            <input type="date" name="start_date"/><br>
            <label><fmt:message key="endDate"/><span>*</span></label>
            <input type="date" name="end_date"/><br>
            <label><fmt:message key="price"/><span>*</span></label>
            <input type="number" step="0.01" min="0.01" name="price"><br>
            <button type="submit"><fmt:message key="create"/></button>
            <button type="button"><a href="${pageContext.request.contextPath}/exhibitions/admin"><fmt:message key="cancel"/></a></button>
        </form>
    </body>
</html>
</fmt:bundle>