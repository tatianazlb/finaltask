<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 30-Jul-18
  Time: 13:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="ctg" uri="customtags" %>
<%@ page import="java.util.ResourceBundle" %>
<%@ page import="java.util.Locale" %>
<%@ page import="epam.zlobich.task6.entity.entitybd.UserBD" %>
<%
    ResourceBundle RB;
    if (request.getSession().getAttribute("bundle")==null) {
        String lang = "en"; //Assign the correct language either by page or user-selected or browser language etc.
        RB = ResourceBundle.getBundle("app", new Locale(lang));

        request.getSession().setAttribute("bundle", RB);
    }
    else
        RB =(ResourceBundle)request.getSession().getAttribute("bundle");
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <h1>
        <%= RB.getString("hello") %>
        <%=((UserBD)request.getSession().getAttribute("user")).getLogin()%>
    </h1>
</head>
<body>

<ctg:userTag>
    <%= RB.getString("mylectures") %>

</ctg:userTag>




</body>
</html>
