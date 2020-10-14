<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:bundle basename="locale" prefix="createShowroom.">
    <html>
    <head>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css"/>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/materialize.min.css"/>
        <script src="${pageContext.request.contextPath}/js/materialize.min.js"></script>
        <title><fmt:message key="createShowroom"/></title>
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
            <c:out value="${sessionScope.showroomExists}"/><br>
            <% session.removeAttribute("showroomExists"); %>
            <c:out value="${sessionScope.enterAllFields}"/><br>
            <% session.removeAttribute("enterAllFields"); %>
            <form method="post" action="${pageContext.request.contextPath}/exhibitions/admin/create_showroom/create_showroom_button">
                <div class="row input-field">
                    <input class="col s2" id="name" type="text" name="showroom_name"/><br>
                    <label for="name"><fmt:message key="name"/>*</label>
                </div>
                <label><fmt:message key="selectExpositions"/></label><br>
                <div class="row">
                    <select class="browser-default col s2" name="exposition_name">
                        <option value="null">-</option>
                        <c:forEach var="exposition_name" items="${requestScope.expositionsNames}">
                            <option value="${exposition_name}">${exposition_name}</option>
                        </c:forEach>
                    </select><br>
                </div>
                <button class="btn" type="submit"><fmt:message key="create"/></button>
                <a class="btn" href="${pageContext.request.contextPath}/exhibitions/admin"><fmt:message key="cancel"/></a>
            </form>
        </body>
    </html>
</fmt:bundle>