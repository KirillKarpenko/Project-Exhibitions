<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:bundle basename="locale" prefix="updateAccount.">
    <html>
        <head>
            <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css"/>
            <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/materialize.min.css"/>
            <title><fmt:message key="updateAccount"/></title>
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
            <c:out value="${sessionScope.loginExists}"/><br>
            <% session.removeAttribute("loginExists"); %>
            <form method="post" action="${pageContext.request.contextPath}/exhibitions/user/account_info/edit_account/edit_account_button">
                <c:out value="${sessionScope.wrongUsername}"/><br>
                <% session.removeAttribute("wrongUsername");%>
                <h6><fmt:message key="username"/></h6>
                <div class="row">
                    <input class="col s2" type="text" name="login" placeholder="${sessionScope.account.login}"/><br>
                </div>
                <c:out value="${sessionScope.wrongPassword}"/><br>
                <% session.removeAttribute("wrongPassword"); %>
                <h6><fmt:message key="newPassword"/></h6>
                <div class="row">
                    <input class="col s2" type="password" name="password" placeholder="********"/><br>
                </div>
                <c:out value="${sessionScope.wrongFirstName}"/><br>
                <% session.removeAttribute("wrongFirstName"); %>
                <h6><fmt:message key="firstName"/></h6>
                <div class="row">
                    <input class="col s2" type="text" name="first_name" placeholder="${sessionScope.account.firstName}"/><br>
                </div>
                <c:out value="${sessionScope.wrongLastName}"/><br>
                <% session.removeAttribute("wrongLastName"); %>
                <h6><fmt:message key="lastName"/></h6>
                <div class="row">
                    <input class="col s2" type="text" name="last_name" placeholder="${sessionScope.account.lastName}"/><br>
                </div>
                <button class="btn" type="submit"><fmt:message key="update"/></button>
                <a class="btn" href="${pageContext.request.contextPath}/exhibitions/user/account_info"><fmt:message key="cancel"/></a>
            </form>
        </body>
    </html>
</fmt:bundle>