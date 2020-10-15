<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:bundle basename="locale" prefix="updateExposition.">
    <html>
        <head>
            <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css"/>
            <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/materialize.min.css"/>
            <title><fmt:message key="updateExposition"/></title>
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
            <form method="post" action="${pageContext.request.contextPath}/exhibitions/admin/expositions/update_exposition/update_exposition_button">
                <h6><fmt:message key="name"/></h6>
                <div class="row">
                    <input class="col s2" type="text" name="exposition_name" placeholder="${requestScope.exposition.name}"/><br>
                </div>
                <h6><fmt:message key="category"/></h6>
                <div class="row">
                    <select class="col s2 browser-default" name="category">
                        <option value="${requestScope.exposition.category}" selected disabled hidden>${requestScope.category}</option>
                        <c:forEach var="category" items="${requestScope.categories}">
                            <option value="${category}">${category}</option>
                        </c:forEach>
                    </select><br>
                </div>
                <h6><fmt:message key="startDate"/></h6>
                <div class="row">
                    <input class="col s2" type="date" name="start_date"/><br>
                </div>
                <h6><fmt:message key="endDate"/></h6>
                <div class="row">
                    <input class="col s2" type="date" name="end_date"/><br>
                </div>
                <h6><fmt:message key="price"/></h6>
                <div class="row">
                    <input class="col s1" type="number" step="0.01" min=0 name="price" placeholder="${requestScope.exposition.price}"><br>
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
                <button class="btn" type="submit" name="exposition" value="${requestScope.exposition}"><fmt:message key="update"/></button>
                <a class="btn" href="${pageContext.request.contextPath}/exhibitions/admin/expositions?expositionPage=1"><fmt:message key="cancel"/></a>
            </form>
        </body>
    </html>
</fmt:bundle>