<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:bundle basename="locale" prefix="login.">
<html>
    <head>
        <title><fmt:message key="login"/></title>
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
        <c:out value="${requestScope.wrongUsernameOrPassword}"/><br>
        <form method="post" action="${pageContext.request.contextPath}/exhibitions/login/login_button">
            <input type="text" placeholder="<fmt:message key="username"/>" autofocus name="login"/><br>
            <input type="password" placeholder="<fmt:message key="password"/>" name="password"/><br>
            <button type="submit"><fmt:message key="login"/></button>
            <button type="button"><a href="${pageContext.request.contextPath}/exhibitions/index?expositionPage=1"><fmt:message key="cancel"/></a></button>
        </form>
    </body>
</html>
</fmt:bundle>