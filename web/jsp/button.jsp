<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 29-Jun-18
  Time: 20:51
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
    <h1>
        ${name}
    </h1>
</head>
<body>
<ctg:localeTag/>
<div align="center">
<ctg:adminTag>
    Hello, if you;re admin
</ctg:adminTag>

    <ctg:userTag>
        <%= RB.getString("myconf") %>
        ${myConf}
        <%= RB.getString("exconf") %>
        ${exConf}
    </ctg:userTag>

    <ctg:adminTag>
        <%= RB.getString("exconf") %>
        ${allConf}

        <button><%=RB.getString("addconf")%></button>

    </ctg:adminTag>


    <form name="test" action="/parseServlet" method="POST">
        <input type="submit" name="submbutton" value="DOM">
        <input type="submit" name="submbutton" value="SAX">
        <input type="submit" name="submbutton" value="STAX">
</form>



    ${listOfMyQuestion}



</div>
</body>
</html>
