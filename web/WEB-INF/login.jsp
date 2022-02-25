<%--
  Created by IntelliJ IDEA.
  User: NZP
  Date: 24.02.2022
  Time: 12:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
<div style="width: 1000px; margin: 0 auto; padding: 5px;text-align: center">
    <h3>Մուտք</h3>
    <form action="/login" method="post">
        Email <input type="email" name="email"><br>
        Password <input type="password" name="password"><br>
        <input type="submit" value="ok">
    </form>

</div>
</body>
</html>
