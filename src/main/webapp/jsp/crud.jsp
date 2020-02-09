<%--
  Created by IntelliJ IDEA.
  User: 00sna
  Date: 02.02.2020
  Time: 21:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<html>
<head>
    <title>crud</title>

</head>
<body>

<form method="post" action="/admin/deleteUser">
    <table>
        <tr>
            <th>id</th>
            <th>name</th>
            <th>password</th>
            <th>age</th>
            <th>role</th>
        </tr>
        <c:forEach items="${usersInJDBC}" var="User">
            <tr>
                <th>${User.id}</th>
                <th>${User.name}</th>
                <th>${User.password}</th>
                <th>${User.age}</th>
                <th>${User.role}</th>
                <td>
                    <a href="/admin/updateUser?id=<c:out value='${User.id}' />">Edit</a>
                </td>
                <!---------Check box Delete----------->
                <td id="deleteUser">
                    <input type="checkbox" name="Delete" value=${User.id}>
                </td>
            </tr>
        </c:forEach>
    </table>
    <!------------ button Clear Cart--------------->
    <div class="button-update">
        <input type="submit" name="Delete" value="Delete selected users">
    </div>
</form>
<h5>it`s clever man</h5>
</br>
</br>
<h4>add User in dataBase</h4>
<form method="post" action="/admin/admin">
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
    <input type="submit" value="inside in DB">
</form>
</br>
</br>

</body>
</html>

