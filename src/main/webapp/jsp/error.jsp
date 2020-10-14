<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" isErrorPage="true" language="java"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:bundle basename="locale" prefix="error.">
    <html>
    <head>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css"/>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/materialize.min.css"/>
        <title><fmt:message key="error"/></title>
    </head>
        <body>
            <%
                response.addHeader("Cache-Control", "no-cache,no-store,private,must-revalidate,max-stale=0,post-check=0,pre-check=0");
                response.addHeader("Pragma", "no-cache");
                response.addDateHeader ("Expires", 0);
            %>
            <h1 class="error">Error 404</h1>
            <a class="btn return_home" href="${pageContext.request.contextPath}/exhibitions/index?expositionPage=1">Return Home</a>
    </body>
    </html>
</fmt:bundle>