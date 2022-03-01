<%@ page import="model.Category" %>
<%@ page import="model.User" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: NZP
  Date: 24.02.2022
  Time: 13:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>AddItem</title>
</head>
<body>
<%
    List<Category> categories = (List<Category>) request.getAttribute("categories");
%>

<div style="margin: 0 auto;text-align: right; width: 1000px; height: 20px">
    <a href="/home">Back</a><br>
</div>

<div style="width: 1000px; margin: 0 auto; padding: 5px;text-align: center">

    <h3>Ավելացնել</h3><br>
    <form action="/addItem" method="post" enctype="multipart/form-data">
        Title <input type="text" name="title"><br>
        Price <input type="number" name="price"><br>
        Category <select name="category_id">
        <%
            for (Category category : categories) {%>
        <option value="<%=category.getId()%>"><%=category.getName()%>
        </option>
        <% }
        %>
    </select> <br>
        Picture <input type="file" name="image"><br>
        <input type="submit" value="ok">
    </form>

</div>
</body>
</html>
