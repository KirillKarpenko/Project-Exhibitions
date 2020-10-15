<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:bundle basename="locale" prefix="login.">
    <html>
        <head>
            <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css"/>
            <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/materialize.min.css"/>
            <script src="${pageContext.request.contextPath}/js/materialize.min.js"></script>
            <title><fmt:message key="login"/></title>
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
                    </ul>
                </div>
            </nav>
            <c:out value="${sessionScope.wrongUsernameOrPassword}"/><br>
            <% session.removeAttribute("wrongUsernameOrPassword"); %>
            <form method="post" action="${pageContext.request.contextPath}/exhibitions/login/login_button">
                <div class="row input-field">
                    <input class="col s2" id="username" type="text" name="login"/><br>
                    <label for="username"><fmt:message key="username"/></label>
                </div>
                <div class="row input-field">
                    <input class="col s2" id="password" type="password" name="password"/><br>
                    <label for="password"><fmt:message key="password"/></label>
                </div>
                <button class="btn" type="submit"><fmt:message key="login"/></button>
                <a class="btn" href="${pageContext.request.contextPath}/exhibitions/index?expositionPage=1"><fmt:message key="cancel"/></a>
            </form>
        </body>
    </html>
</fmt:bundle>