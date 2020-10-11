<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:bundle basename="locale" prefix="expositions.">
<html>
    <head>
        <title><fmt:message key="expositions"/></title>
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
                        <li><a href="${pageContext.request.contextPath}/exhibitions/admin"><fmt:message key="adminPage"/></a></li>
                        <li><a href="${pageContext.request.contextPath}/exhibitions/logout"><fmt:message key="logout"/></a></li>
                    </ul>
                </li>
            </ul>
        </header>
        <table cellspacing="10">
            <tr>
                <th><fmt:message key="name"/></th>
                <th><fmt:message key="category"/></th>
                <th><fmt:message key="startDate"/></th>
                <th><fmt:message key="endDate"/></th>
                <th><fmt:message key="price"/></th>
            </tr>
            <c:forEach var="exp" items="${requestScope.expositions}">
                <tr>
                    <td align="center"><c:out value="${exp.name}"/></td>
                    <td align="center"><c:out value="${exp.category}"/></td>
                    <fmt:formatDate value="${exp.startDate}" pattern="yyyy-MM-dd" var="startDate"/>
                    <td align="center"><c:out value="${startDate}"/></td>
                    <fmt:formatDate value="${exp.endDate}" pattern="yyyy-MM-dd" var="endDate"/>
                    <td align="center"><c:out value="${endDate}"/></td>
                    <td align="center"><c:out value="${exp.price}"/></td>
                    <td align="center">
                        <form method="post" action="${pageContext.request.contextPath}/exhibitions/admin/expositions/update_exposition">
                            <input type="text" hidden name="exposition" value="${exp}">
                            <button type="submit"><fmt:message key="update"/></button>
                        </form>
                    </td>
                    <td align="center">
                        <form method="post" action="${pageContext.request.contextPath}/exhibitions/admin/expositions/delete_exposition_button">
                            <input type="number" hidden name="exposition_id" value="${exp.id}">
                            <button type="submit"><fmt:message key="delete"/></button>
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </table>
        <table cellspacing="10">
            <tr>
                <c:forEach begin="1" end="${requestScope.totalExpositionPages}" var="i">
                    <c:choose>
                        <c:when test="${requestScope.expositionPage eq i}">
                            <td align="center">${i}</td>
                        </c:when>
                        <c:otherwise>
                            <td align="center"><a href="${pageContext.request.contextPath}/exhibitions/admin/expositions?expositionPage=${i}">${i}</a></td>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
            </tr>
        </table>
    </body>
</html>
</fmt:bundle>