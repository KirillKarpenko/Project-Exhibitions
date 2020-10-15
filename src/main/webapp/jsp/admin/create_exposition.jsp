<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:bundle basename="locale" prefix="createExposition.">
    <html>
        <head>
            <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css"/>
            <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/materialize.min.css"/>
            <script src="${pageContext.request.contextPath}/js/materialize.min.js"></script>
            <title><fmt:message key="createExposition"/></title>
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
            <c:out value="${sessionScope.expositionExists}"/><br>
            <% session.removeAttribute("expositionExists"); %>
            <c:out value="${sessionScope.enterAllFields}"/><br>
            <% session.removeAttribute("enterAllFields"); %>
            <form method="post" action="${pageContext.request.contextPath}/exhibitions/admin/create_exposition/create_exposition_button">
                <div class="row input-field">
                    <input class="col s2" id="name" type="text" name="exposition_name"/><br>
                    <label for="name"><fmt:message key="name"/>*</label>
                </div>
                <label><fmt:message key="category"/><span>*</span></label>
                <div class="row">
                    <select class="col s2 browser-default" name="category">
                        <c:forEach var="category" items="${requestScope.categories}">
                            <option value="${category}">${category}</option>
                        </c:forEach>
                    </select><br>
                </div>
                <label><fmt:message key="startDate"/>*</label>
                <div class="row">
                    <input class="col s2" type="date" name="start_date"/><br>
                </div>
                <label><fmt:message key="endDate"/>*</label>
                <div class="row">
                    <input class="col s2" type="date" name="end_date"/><br>
                </div>
                <div class="row input-field">
                    <input class="col s1" id="price" type="number" step="0.01" min="0.01" name="price"><br>
                    <label for="price"><fmt:message key="price"/>*</label>
                </div>
                <label><fmt:message key="locale"/></label>
                <div class="row">
                    <select class="col s2 browser-default" name="locale">
                        <option value="${sessionScope.lang}" selected disabled hidden>
                            <c:if test="${sessionScope.lang.equals('ua')}">
                                Українська
                            </c:if>
                            <c:if test="${sessionScope.lang.equals('en')}">
                                English
                            </c:if>
                        </option>
                        <option value="en">English</option>
                        <option value="ua">Українська</option>
                    </select>
                </div>
                <button class="btn" type="submit"><fmt:message key="create"/></button>
                <a class="btn" href="${pageContext.request.contextPath}/exhibitions/admin"><fmt:message key="cancel"/></a>
            </form>
        </body>
    </html>
</fmt:bundle>