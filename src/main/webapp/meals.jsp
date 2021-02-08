<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<html lang="ru">
<h3> <a href="index.html"> Home </a></h3>
<hr>
<h2> Meals </h2>

<table border=1>

<thead>
<tr>
    <td> Date </td>
    <td> Description </td>
    <td> Calories </td>
    <td> Update </td>
    <td> Delete </td>
</tr>
</thead>

<tbody>
<c:forEach var="meal" items="${meals}">
    <tr>
        <td>
            <fmt:parseDate value="${ meal.dateTime }" pattern="yyyy-MM-dd'T'HH:mm" var="parsedDateTime"/>
            <fmt:formatDate pattern="dd.MM.yyyy HH:mm" value="${ parsedDateTime }"/>
        </td>
        <td> <c:out value = "${meal.description}"/> </td>
        <td> <c:out value = "${meal.calories}"/> </td>
    </tr>
</c:forEach>
</tbody>

</table>

</html>
