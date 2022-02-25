<%@ page import="model.User" %>
<%@ page import="model.Item" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: NZP
  Date: 24.02.2022
  Time: 11:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>myItems</title>
</head>
<body>
<%
    User user = (User) session.getAttribute("user");
    List<Item> allItems = (List<Item>) request.getAttribute("allItemsByUserId");
%>
<div style="width: 1000px; margin: 0 auto; padding: 5px">

    <div style="height: 25px; text-align: right; padding: 5px">
        Բարև  <%=user.getName()%> | <a href="/add">Ավելացնել</a> |
        <a href="/myItems">Իմ հայտարարությունները</a> | <a href="/logout">logout</a>
    </div>

    <div style="margin: 0 auto; padding: 5px; text-align: center">
        <%
            for (Item allItem : allItems) {
                if (allItem.getUserID() == user.getId()) {%>
        <div style="float: left; padding: 5px">
            <img src="/image?path=<%=allItem.getPicURL()%>  " width="180" height="120"/><br>
            <%=allItem.getTitle()%><br>
            <%=allItem.getPrice()%><br>
            <a href="/deleteItem?id=<%=allItem.getId()%>">Ջնջել</a><br>
            <% }%>
        </div>
        <% }%>
    </div>

</div>
</body>
</html>
