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
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
</head>
<body>

<ctg:localeTag/>

<div align="center" style="max-width: 600px; margin: 0 auto;">

<div align="center">

    ${registercheck}

    <form name="test" method="post" action="/main">
        <input type="hidden" name="command" value="login">
        <p><b><%= RB.getString("log") %></b></p>
        <input required pattern="[A-Za-z]+" type="text" name="login" size="45" maxlength="45" oninvalid="this.setCustomValidity('<%=RB.getString("wronglogin")%>')"
               oninput="this.setCustomValidity('')">
        <p><b><%= RB.getString("password") %></b></p>
        <input required type="password" name="password" size="45"maxlength="45" oninvalid="this.setCustomValidity('<%=RB.getString("passwordnotnull")%>')"
               oninput="this.setCustomValidity('')">
        <div><button type="submit" class="w3-button w3-black w3-hover-light-green"><%= RB.getString("subm") %></button>
            <button type="reset" class="w3-button w3-black w3-border w3-hover-light-green"><%= RB.getString("cl") %></button></div>
    </form>
</div>

<br>

<div align="center" class="w3-container">
    <button onclick="document.getElementById('id01').style.display='block'" class="w3-button w3-black"><%=RB.getString("register")%></button>
    <div id="id01" class="w3-modal">
        <div class="w3-modal-content w3-border w3-border-green w3-round-large">
            <div class="w3-container">
                <span onclick="document.getElementById('id01').style.display='none'" class="w3-button w3-display-topright">&times;</span>
                <form name="test" method="post" action="/register">
                    <input type="hidden" name="command" value="register">
                    <p><b><%= RB.getString("log") %></b></p>
                    <input required pattern="[A-Za-z]+" type="text" name="login" size="45" maxlength="45" oninvalid="this.setCustomValidity('<%=RB.getString("wronglogin")%>')"
                           oninput="this.setCustomValidity('')">
                    <p><b><%= RB.getString("password") %></b></p>
                    <input required type="password" name="password" size="45"maxlength="45" oninvalid="this.setCustomValidity('<%=RB.getString("passwordnotnull")%>')"
                           oninput="this.setCustomValidity('')">
                    <p><b><%= RB.getString("passwordA") %></b></p>
                    <input required type="password" name="passwordA" size="45"maxlength="45" oninvalid="this.setCustomValidity('<%=RB.getString("passwordnotnull")%>')"
                           oninput="this.setCustomValidity('')">
                    <p><b><%= RB.getString("homeland") %></b></p>
                    <input required pattern="[A-Za-z]+" type="text" name="homeland" size="45" maxlength="45" oninvalid="this.setCustomValidity('<%=RB.getString("wronghomeland")%>')"
                           oninput="this.setCustomValidity('')">
                    <div><button type="submit"><%= RB.getString("subm") %></button>
                        <button type="reset"><%= RB.getString("cl") %></button></div>
                </form>
            </div>
        </div>
    </div>
</div>

</div>


</body>
</html>
