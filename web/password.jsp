<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 29-Jun-18
  Time: 13:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="ctg" uri="customtags" %>
<%@ page import="java.util.ResourceBundle" %>
<%@ page import="java.util.Locale" %>
<%
    ResourceBundle RB;
    if (request.getSession().getAttribute("bundle")==null) {
        String lang = "en"; //Assign the correct language either by page or user-selected or browser language etc.
        RB = ResourceBundle.getBundle("app", new Locale(lang));

        request.getSession().setAttribute("bundle", RB);
    }
    else
        RB =(ResourceBundle)request.getSession().getAttribute("bundle");

    if (request.getAttribute("sorry")==null) request.setAttribute("sorry", "");
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
</head>
<body>

<ctg:localeTag/>

<div align="center">

    ${sorry}

    <form name="test" method="post" action="/password">
        <p><b><%= RB.getString("log") %></b></p>
        <input type="text" name="login" size="45" maxlength="45">
        <p><b><%= RB.getString("password") %></b></p>
        <input type="text" name="pass" size="45"maxlength="45">
        <div><button type="submit"><%= RB.getString("subm") %></button>
            <button type="reset"><%= RB.getString("cl") %></button></div>
    </form>
</div>

</body>
</html>
