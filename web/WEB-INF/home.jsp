<%@ page import="model.Item" %>
<%@ page import="java.util.List" %>
<%@ page import="model.Category" %><%--
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
    List<Item> allItems = (List<Item>) request.getAttribute("allItems");
    List<Category> allCategories = (List<Category>) request.getAttribute("allCategories");
%>
<div style="width: 1000px; margin: 0 auto; padding: 5px">

    <div style="height: 25px; text-align: right; padding: 5px">
        <a href="/login">Լոգին</a> | <a href="/userRegister">Գրանցում</a>
    </div>

    <div style="height: 25px; text-align: center; padding: 5px">
        <a href="/home">Գլխավոր </a> | <%
        for (Category allCategory : allCategories) {%>
        <a href="/category?categoryId=<%=allCategory.getId()%>"><%=allCategory.getName()%> | </a>
        <% }%>
    </div>

    <div style="margin: 0 auto; padding: 5px; text-align: center">
        <%
            for (Item allItem : allItems) {%>
        <div style="float: left; padding: 5px">
            <img src="/image?path=<%=allItem.getPicURL()%>  " width="180" height="120"/><br>
            <%=allItem.getTitle()%><br>
            <%=allItem.getPrice()%><br>
        </div>
        <% }%>
    </div>

</div>
</body>
</html>
