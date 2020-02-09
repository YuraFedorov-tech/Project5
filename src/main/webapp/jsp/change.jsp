<%--
  Created by IntelliJ IDEA.
  User: 00sna
  Date: 08.02.2020
  Time: 17:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<html>
<head>
    <title>update</title>
</head>
<body>
<table>
    <tr>
        <th>id</th>
        <th>name</th>
        <th>color</th>
        <th>age</th>
    </tr>
    <c:forEach items="${usersInJDBC}" var="User">
        <tr>
            <th>${User.id}</th>
            <th>${User.name}</th>
            <th>${User.password}</th>
            <th>${User.age}</th>
            <th>${User.role}</th>
        </tr>
    </c:forEach>
</table>
</br>
</br>
<h5>Input new parametrs</h5>
<h4>update User in dataBase</h4>
<form method="post" action="/admin/updateUser">
    <label for="name">
        <input id="name" name="name" value="name">
    </label></br>
    <label for="password">
        <input id="password" name="password" value="password">
    </label></br>
    <label for="age">
        <input id="age" name="age" value="age">
    </label></br>
    <select name="role" size="1" >
        <option selected value="user">user</option>
        <option selected value="admin">admin</option>
    </select></br>
    <input type="submit" value="update in JDBC">
</form>

</body>
</html>

