<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 02-Aug-18
  Time: 14:27
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


<br>

<ctg:userTag>
    Hello my grggsgesegsg
    <br>
    ${tableMy}
    <br>
    ${myConf}
</ctg:userTag>

<ctg:adminTag>
    Hello my friend
    ${name}
    <br>
    ${tableAll}
    <br>
    ${allConf}
    <br>
    ${button}

</ctg:adminTag>


</body>
</html>
