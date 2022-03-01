<%@ page import="model.User" %>
<%@ page import="model.Category" %>
<%@ page import="model.Item" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: NZP
  Date: 28.02.2022
  Time: 13:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Singl</title>
</head>
<body>

<%
    User user = (User) session.getAttribute("user");
    Item item = (Item) request.getAttribute("item");
%>
<div style="margin: 0 auto;text-align: right; width: 1000px; height: 20px">
    <a href="/home">Back</a><br>
</div>
<div style="margin: 0 auto; width: 1000px; height: 1000px">
    <% if (user == null) { %>
    <div style="height: 25px; text-align: right; padding: 5px">
        <a href="/login">Լոգին</a> | <a href="/userRegister">Գրանցում</a></div>
    <% } else { %>

    <div style="height: 25px; text-align: right; padding: 5px">
        Բարև <%=user.getName()%>  | <a href="/myItems">Իմ Հայտարարությունները</a> | <a href="/addItem">Ավելացնել</a>
        | <a href="/logout">Logout</a></div>
    <%}%>

    <div style="margin: 0 auto;text-align: center; padding: 5px">
        <div style="width: 750px; float: left">
            <div style="margin: 0 auto;text-align: center; padding: 5px">
                <%if (item.getPicURL() != null && !item.getPicURL().equals("")) {%>
                <img src="/image?path=<%=item.getPicURL()%>" width="750"/>
                <%} else {%>
                <img src="/img/img.png" width="750"/>
                <%}%>
            </div>
            <div style="margin: 0 auto; text-align: center; padding: 5px">
                Ապրանքի տեսակը։ <%=item.getCategory().getName()%><br>
                Ապրանքի անունը։ <%=item.getTitle()%><br>
                Արժեքը։ <%=item.getPrice()%><br>
                Անուն։  <%=item.getUser().getName()%> <br>
                Ազգանուն։ <%=item.getUser().getSurname()%> <br>
                Հեռախոս։ <%=item.getUser().getPhone()%>
            </div>
        </div>
    </div>

</div>
</body>
</html>
