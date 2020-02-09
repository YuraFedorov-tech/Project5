<%--
  Created by IntelliJ IDEA.
  User: 00sna
  Date: 08.02.2020
  Time: 17:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>addUser</title>
</head>
<body>
<h1>Hi</h1>
<h1>add User or admin</h1>
<form method="post" action="/addUser">
    <label name="name" >
        <input id="name" name="name" value="name">
    </label></br>
    <label for password="password">
        <input id="password" name="password" value="password">
    </label></br>
    <label for age="age" >
        <input id="age" name="age" value="age">
    </label></br>

    <select name="role" size="1" >
        <option selected value="user">user</option>
        <option selected value="admin">admin</option>
    </select></br>
<input type="submit" value="inside in DB">
</form>
</body>
</html>
