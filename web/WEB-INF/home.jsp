<%@ page import="model.Item" %>
<%@ page import="java.util.List" %>
<%@ page import="model.Category" %>
<%@ page import="model.User" %>
<%--
  Created by IntelliJ IDEA.
  User: NZP
  Date: 24.02.2022
  Time: 11:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Home</title>
</head>
<body>
<%
    User user = (User) session.getAttribute("user");
    List<Category> categories = (List<Category>) request.getAttribute("categories");
    List<Item> items = (List<Item>) request.getAttribute("items");
%>
<div style="width: 1000px; margin: 0 auto; padding: 5px">

    <% if (user == null) { %>
    <div style="height: 25px; text-align: right; padding: 5px">
        <a href="/login">Լոգին</a> | <a href="/userRegister">Գրանցում</a></div>
    <% } else { %>

    <div style="height: 25px; text-align: right; padding: 5px">
        Բարև <%=user.getName()%>  | <a href="/myItems">Իմ Հայտարարությունները</a> | <a href="/addItem">Ավելացնել</a>
        | <a href="/logout">Logout</a></div>
    <%}%>

    <div style="height: 25px; text-align: center; padding: 5px">
        <ul style="overflow:hidden">
            <li style="display: inline; margin-left:40px; padding: 5px"><a href="/home">Գլխավոր</a></li>
            <% for (Category category : categories) { %>
            <li style="display: inline; margin-left:40px;">
                <a href="/home?cat_id=<%=category.getId()%>"><%=category.getName()%>
                </a>
            </li>
            <% } %>
        </ul>
    </div>

    <div style="margin: 0 auto; padding: 5px; text-align: center">
        <% for (Item item : items) { %>
        <a href="/singleItem?id=<%=item.getId()%>">
            <div style="float: left; padding: 5px">
                <div>
                    <%if (item.getPicURL() != null && !item.getPicURL().equals("")) {%>
                    <img src="/image?path=<%=item.getPicURL()%>" width="180" height="120"/>
                    <%} else {%>
                    <img src="img/poto.jpg" width="180" height="120"/>
                    <%}%>
                </div>
                <div>
                    <span><%=item.getTitle()%> <br>
                        <%=item.getPrice()%> </span>
                </div>
            </div>
        </a>
        <% }%>
    </div>
</div>

</body>
</html>
