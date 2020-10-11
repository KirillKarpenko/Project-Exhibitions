<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:bundle basename="locale" prefix="updateAccount.">
<html>
    <head>
        <title><fmt:message key="updateAccount"/></title>
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
                        <li><a href="${pageContext.request.contextPath}/exhibitions/user"><fmt:message key="yourAccount"/></a></li>
                        <li><a href="${pageContext.request.contextPath}/exhibitions/logout"><fmt:message key="logout"/></a></li>
                    </ul>
                </li>
            </ul>
        </header>
        <c:out value="${sessionScope.loginExists}"/><br>
        <% session.removeAttribute("loginExists"); %>
        <form method="post" action="${pageContext.request.contextPath}/exhibitions/user/account_info/edit_account/edit_account_button">
            <c:out value="${sessionScope.wrongUsername}"/><br>
            <% session.removeAttribute("wrongUsername");%>
            <label><fmt:message key="username"/></label>
            <input type="text" name="login" placeholder="${sessionScope.account.login}"/><br>
            <c:out value="${sessionScope.wrongPassword}"/><br>
            <% session.removeAttribute("wrongPassword"); %>
            <label><fmt:message key="newPassword"/></label>
            <input type="password" name="password" placeholder="Old password"/><br>
            <c:out value="${sessionScope.wrongFirstName}"/><br>
            <% session.removeAttribute("wrongFirstName"); %>
            <label><fmt:message key="firstName"/></label>
            <input type="text" name="first_name" placeholder="${sessionScope.account.firstName}"/><br>
            <c:out value="${sessionScope.wrongLastName}"/><br>
            <% session.removeAttribute("wrongLastName"); %>
            <label><fmt:message key="lastName"/></label>
            <input type="text" name="last_name" placeholder="${sessionScope.account.lastName}"/><br>
            <button type="submit"><fmt:message key="update"/></button>
            <button type="button"><a href="${pageContext.request.contextPath}/exhibitions/user/account_info"><fmt:message key="cancel"/></a></button>
        </form>
    </body>
</html>
</fmt:bundle>