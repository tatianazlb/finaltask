<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 02-Aug-18
  Time: 15:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="ctg" uri="customtags" %>
<%@ page import="java.util.ResourceBundle" %>
<%@ page import="java.util.Locale" %>
<%@ page import="epam.zlobich.task6.entity.entitybd.Theme" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="epam.zlobich.task6.entity.entitybd.UserBd" %>
<%
    ResourceBundle rb;
    if (request.getSession().getAttribute("bundle")==null) {
        String lang = "en"; //Assign the correct language either by page or user-selected or browser language etc.
        rb = ResourceBundle.getBundle("app", new Locale(lang));

        request.getSession().setAttribute("bundle", rb);
    }
    else
        rb =(ResourceBundle)request.getSession().getAttribute("bundle");
%>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
</head>
<body>

<div align="right">
    <form name="test" action="/myroom" method="POST">
        <input type="hidden" name="command" value="mypage">
        <input type="submit"  class="w3-button w3-white w3-border w3-border-green w3-round-large" name="button" value="MYPAGE">
    </form>
</div>

<div align="center" style="max-width: 600px; margin: 0 auto;">

<h1><%=request.getParameter("idConf")%></h1><br>
<form name="test" action="/lecture" method="POST"><input type="hidden" name="command" value="lecture">

        <%   for (Theme cc : (ArrayList<Theme>) request.getAttribute("listOfThemes")
            ) {
            %>
            <input type="submit" class="w3-button w3-white w3-border w3-border-green w3-round-large" style="width: 100%;" name="theme" value="<%=cc.getName()%> at <%=cc.getDate()%>"><br>
<%
    }

%>
</form>

<%
    if (((UserBd)request.getSession().getAttribute("user")).isRole()){
%>
<div align="center" class="w3-container">
        <button onclick="document.getElementById('id011').style.display='block'" class="w3-button w3-black"><%=rb.getString("addtheme")%></button>
        <div id="id011" class="w3-modal">
                <div class="w3-modal-content">
                        <div class="w3-container">
                                <span onclick="document.getElementById('id011').style.display='none'" class="w3-button w3-display-topright">&times;</span>
                                <form name="test" method="post" action="/theme">
                                        <input type="hidden" name="command" value="addtheme">
                                        <input type="hidden" name="idConf" value="<%=request.getAttribute("idConf")%>">
                                        <p><b><%=rb.getString("name")%></b></p>
                                        <input type="text" name="name" size="45" maxlength="45">
                                        <p><b><%=rb.getString("date")%></b></p>
                                        <input type="date" name="date">
                                        <div>
                                            <button type="submit"><%= rb.getString("subm") %></button>
                                            <button type="reset"><%= rb.getString("cl") %></button>
                                        </div>
                                    </form>
                            </div>
                    </div>
            </div>
    </div>
<%
    }
%>

</div>

</body>
</html>
