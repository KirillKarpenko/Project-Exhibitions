<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:bundle basename="locale" prefix="showrooms.">
<html>
    <head>
        <title><fmt:message key="showrooms"/></title>
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
                <th><fmt:message key="bookedBy"/></th>
            </tr>
            <c:forEach var="sh" items="${requestScope.showrooms}">
                <tr>
                    <td align="center"><c:out value="${sh.name}"/></td>
                    <c:choose>
                        <c:when test="${sh.exposition.name eq null}">
                            <td align="center"><c:out value="-"/></td>
                        </c:when>
                        <c:otherwise>
                            <td align="center"><c:out value="${sh.exposition.name}"/></td>
                        </c:otherwise>
                    </c:choose>
                    <td align="center">
                        <form method="post" action="${pageContext.request.contextPath}/exhibitions/admin/showrooms/update_showroom">
                            <input type="text" hidden name="showroom" value="${sh}">
                            <button type="submit"><fmt:message key="update"/></button>
                        </form>
                    </td>
                    <td align="center">
                        <form method="post" action="${pageContext.request.contextPath}/exhibitions/admin/showrooms/delete_showroom_button">
                            <input type="number" hidden name="showroom_id" value="${sh.id}">
                            <button type="submit"><fmt:message key="delete"/></button>
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </table>
        <table cellspacing="10">
            <tr>
                <c:forEach begin="1" end="${requestScope.totalShowroomPages}" var="i">
                    <c:choose>
                        <c:when test="${requestScope.showroomPage eq i}">
                            <td align="center">${i}</td>
                        </c:when>
                        <c:otherwise>
                            <td align="center"><a href="${pageContext.request.contextPath}/exhibitions/admin/showrooms?showroomPage=${i}">${i}</a></td>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
            </tr>
        </table>
    </body>
</html>
</fmt:bundle>