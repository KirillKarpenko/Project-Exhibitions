<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:bundle basename="locale" prefix="register.">
<html>
    <head>
        <title><fmt:message key="register"/></title>
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
        </header>
        <c:out value="${requestScope.loginExists}"/><br>
        <form method="post" action="${pageContext.request.contextPath}/exhibitions/register/register_button">
            <c:out value="${requestScope.wrongUsername}"/><br>
            <label><fmt:message key="username"/><span>*</span></label>
            <input type="text" name="login"/><br>
            <c:out value="${requestScope.wrongPassword}"/><br>
            <label><fmt:message key="password"/><span>*</span></label>
            <input type="password" name="password"/><br>
            <c:out value="${requestScope.wrongFirstName}"/><br>
            <label><fmt:message key="firstName"/><span>*</span></label>
            <input type="text" name="first_name"><br>
            <c:out value="${requestScope.wrongLastName}"/><br>
            <label><fmt:message key="lastName"/><span>*</span></label>
            <input type="text" name="last_name"><br>
            <button type="submit"><fmt:message key="signUp"/></button>
            <button type="button"><a href="${pageContext.request.contextPath}/exhibitions/index"><fmt:message key="cancel"/></a></button>
        </form>
    </body>
</html>
</fmt:bundle>