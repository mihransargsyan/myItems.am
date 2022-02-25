<%@ page import="model.Item" %>
<%@ page import="model.Category" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: NZP
  Date: 25.02.2022
  Time: 10:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Category</title>
</head>
<body>
<%
    List<Item> itemListByCatId = (List<Item>) request.getAttribute("itemListByCatId");
    List<Category> allCategories = (List<Category>) request.getAttribute("allCategories");
%>
<div style="width: 1000px; margin: 0 auto; padding: 5px">

    <div style="height: 25px; text-align: right; padding: 5px">
        <a href="/login">Լոգին</a> | <a href="/userRegister">Գրանցում</a>
    </div>

    <div style="height: 25px; text-align: center; padding: 5px">
        <a href="/home" >Գլխավոր </a> | <%
        for (Category allCategory : allCategories) {%>
        <a href="/category?categoryId=<%=allCategory.getId()%>"><%=allCategory.getName()%> | </a>
        <% }%>
    </div>

    <div style="margin: 0 auto; padding: 5px; text-align: center">
        <%
            for (Item allItem : itemListByCatId) {%>
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
