<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:bundle basename="locale" prefix="updateShowroom.">
    <html>
        <head>
            <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css"/>
            <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/materialize.min.css"/>
            <title><fmt:message key="updateShowroom"/></title>
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
            <form method="post" action="${pageContext.request.contextPath}/exhibitions/admin/showrooms/update_showroom/update_showroom_button">
                <h6><fmt:message key="name"/></h6>
                <div class="row">
                    <input class="col s2" type="text" name="showroom_name" placeholder="${requestScope.showroom.name}"/><br>
                </div>
                <h6><fmt:message key="bookedBy"/></h6>
                <div class="row">
                    <select class="col s3 browser-default" name="bookedBy">
                        <option value="${requestScope.showroom.exposition.name}" selected disabled hidden>${requestScope.showroom.exposition.name}</option>
                        <c:forEach var="exposition_name" items="${requestScope.expositionsNames}">
                            <option value="${exposition_name}">${exposition_name}</option>
                        </c:forEach>
                    </select><br>
                </div>
                <button class="btn" type="submit" name="showroom" value="${requestScope.showroom}"><fmt:message key="update"/></button>
                <a class="btn" href="${pageContext.request.contextPath}/exhibitions/admin/showrooms?showroomPage=1"><fmt:message key="cancel"/></a>
            </form>
        </body>
    </html>
</fmt:bundle>