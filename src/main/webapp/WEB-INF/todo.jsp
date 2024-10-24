<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>ToDo List</title>
</head>
<body>
    <h1>ToDo List</h1>
    
    <h2>Add New Item</h2>
    <form action="todo" method="post">
        <input type="hidden" name="action" value="add">
        <input type="text" name="description" required>
        <input type="submit" value="Add Item">
    </form>
    
    <h2>Current Items</h2>
    <c:if test="${empty items}">
        <p>The todo list is empty.</p>
    </c:if>
    <c:if test="${not empty items}">
        <ul>
            <c:forEach var="item" items="${items}">
                <li>
                    ${item.description}
                    <form action="todo" method="post" style="display:inline;">
                        <input type="hidden" name="action" value="remove">
                        <input type="hidden" name="id" value="${item.id}">
                        <input type="submit" value="Remove">
                    </form>
                </li>
            </c:forEach>
        </ul>
    </c:if>
    
    <h2>Clear All Items</h2>
    <form action="todo" method="post">
        <input type="hidden" name="action" value="clear">
        <input type="submit" value="Clear All Items">
    </form>
</body>
</html>
