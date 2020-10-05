<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" isErrorPage="true" language="java"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:bundle basename="locale" prefix="error.">
<html>
<head>
    <title><fmt:message key="error"/></title>
</head>
    <body>
        <%
            response.addHeader("Cache-Control", "no-cache,no-store,private,must-revalidate,max-stale=0,post-check=0,pre-check=0");
            response.addHeader("Pragma", "no-cache");
            response.addDateHeader ("Expires", 0);
        %>
        <p><fmt:message key="error"/></p>
        <h2><%= exception.getMessage() %>!</h2>
        <p><a href="${pageContext.request.contextPath}/exhibitions/index?expositionPage=1">Return Home</a></p>
</body>
</html>
</fmt:bundle>