<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:bundle basename="locale" prefix="accountInfo.">
<html>
    <head>
        <title><fmt:message key="accountInfo"/></title>
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
                        <li><a href="${pageContext.request.contextPath}/exhibitions/user"><fmt:message key="yourAccount"/></a></li>
                        <li><a href="${pageContext.request.contextPath}/exhibitions/logout"><fmt:message key="logout"/></a></li>
                    </ul>
                </li>
            </ul>
        </header>
        <label><fmt:message key="username"/>: ${sessionScope.account.login}</label><br>
        <label><fmt:message key="firstName"/>: ${sessionScope.account.firstName}</label><br>
        <label><fmt:message key="lastName"/>: ${sessionScope.account.lastName}</label><br>
        <button type="button"><a href="${pageContext.request.contextPath}/exhibitions/user/account_info/edit_account"><fmt:message key="update"/></a></button>
        <button type="button"><a href="${pageContext.request.contextPath}/exhibitions/user/account_info/delete_account"><fmt:message key="delete"/></a></button>
    </body>
</html>
</fmt:bundle>