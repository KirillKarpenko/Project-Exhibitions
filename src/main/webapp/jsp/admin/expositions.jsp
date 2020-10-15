<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:bundle basename="locale" prefix="expositions.">
    <html>
        <head>
            <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css"/>
            <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/materialize.min.css"/>
            <title><fmt:message key="expositions"/></title>
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
                        <li><a href="${pageContext.request.contextPath}/exhibitions/admin"><fmt:message key="adminPage"/></a></li>
                        <li><a href="${pageContext.request.contextPath}/exhibitions/logout"><fmt:message key="logout"/></a></li>
                    </ul>
                </div>
            </nav>
            <table>
                <tr>
                    <th><fmt:message key="name"/></th>
                    <th><fmt:message key="category"/></th>
                    <th><fmt:message key="startDate"/></th>
                    <th><fmt:message key="endDate"/></th>
                    <th><fmt:message key="price"/></th>
                </tr>
                <c:forEach var="exp" items="${requestScope.expositions}" varStatus="i">
                    <tr>
                        <td align="center"><c:out value="${exp.name}"/></td>
                        <td align="center"><c:out value="${requestScope.categories[i.index]}"/></td>
                        <fmt:formatDate value="${exp.startDate}" pattern="yyyy-MM-dd" var="startDate"/>
                        <td align="center"><c:out value="${startDate}"/></td>
                        <fmt:formatDate value="${exp.endDate}" pattern="yyyy-MM-dd" var="endDate"/>
                        <td align="center"><c:out value="${endDate}"/></td>
                        <td align="center"><c:out value="${exp.price}"/></td>
                        <td align="center">
                            <form method="post" action="${pageContext.request.contextPath}/exhibitions/admin/expositions/update_exposition">
                                <input type="text" hidden name="exposition" value="${exp}">
                                <button class="btn" type="submit"><fmt:message key="update"/></button>
                            </form>
                        </td>
                        <td align="center">
                            <form method="post" action="${pageContext.request.contextPath}/exhibitions/admin/expositions/delete_exposition_button">
                                <input type="number" hidden name="exposition_id" value="${exp.id}">
                                <button class="btn red" type="submit"><fmt:message key="delete"/></button>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </table>
            <ul class="pagination">
                <c:forEach begin="1" end="${requestScope.totalExpositionPages}" var="i">
                    <c:choose>
                        <c:when test="${requestScope.expositionPage eq i}">
                            <li class="active"><a onclick="return false;">${i}</a></li>
                        </c:when>
                        <c:otherwise>
                            <li class="waves-effect"><a href="${pageContext.request.contextPath}/exhibitions/admin/expositions?expositionPage=${i}">${i}</a></li>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
            </ul>
        </body>
    </html>
</fmt:bundle>