<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 29-Jun-18
  Time: 21:21
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
%>
<html>
<head>
    <title>Title</title>
</head>
<body>

<ctg:localeTag/>

<div align="center">
    ${res}
</div>
</body>
</html>
