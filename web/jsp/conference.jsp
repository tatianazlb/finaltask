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
<%@ page import="epam.zlobich.task6.entity.entitybd.UserBd" %>
<%@ page import="epam.zlobich.task6.entity.entitybd.Conference" %>
<%@ page import="java.util.ArrayList" %>
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

<br>
<div align="center">

    <%
        if (!((UserBd)request.getSession().getAttribute("user")).isRole()){%>
    <%=rb.getString("hello")%>
    <%=((UserBd)request.getSession().getAttribute("user")).getLogin()%>

    <br>


    <%=rb.getString("myconf")%>

    <form name="test" action="/theme" method="POST"> <input type="hidden" name="command" value="theme">
        <%
    for (Conference conference : (ArrayList<Conference>) request.getAttribute("userConference")
    ) {
        %>
        <input type="submit" class="w3-button w3-white w3-border w3-border-green w3-round-large" name="idConf" value="<%=conference.getId()+" " +conference.getName()+ " in " + conference.getPlace()%>"><br>
        <%
    }
     %>
    </form>

    <br>
    <br>
    <br>
    <%=rb.getString("exconf")%>

    <form name="test" action="/conference" method="POST">
        <input type="hidden" name="command" value="registerforconference">
        <%
            for (Conference conference : (ArrayList<Conference>) request.getAttribute("notUserConference")
                    ) {
        %>
        <input type="submit" class="w3-button w3-white w3-border w3-border-green w3-round-large" name="idConf"
               value="<%=conference.getId()+" " +conference.getName()+ " in " + conference.getPlace() + " " + rb.getString("clicktoregister")%>"
               onclick="return confirm('<%=rb.getString("sureregister")+" "+conference.getName()+ "?"%>')"><br>
        <%
            }
        %>
    </form>
    <%
        }
    %>


    <%
        if (((UserBd)request.getSession().getAttribute("user")).isRole()){%>
    <%=rb.getString("hello")%>
    <%=((UserBd)request.getSession().getAttribute("user")).getLogin()%>

    <br>

    <%=rb.getString("loggedasadmin")%>

    <br>

    <%rb.getString("exconf");%>
    <form name="test" action="/theme" method="POST"> <input type="hidden" name="command" value="theme">
        <%
        for (Conference conference : (ArrayList<Conference>) request.getAttribute("allConferences")
        ) {
        %>
        <input class="w3-button w3-white w3-border w3-border-green w3-round-large"
               style="width: 100%;" type="submit" name="idConf"
               value="<%=conference.getId()%> <%=conference.getName()%> in <%=conference.getPlace()%>"><br>
        <%
        }
        %>
    </form>

    <div align="center" class="w3-container">
            <button onclick="document.getElementById('id01').style.display='block'" class="w3-button w3-black"><%=rb.getString("addconf")%></button>
            <div id="id01" class="w3-modal">
                    <div class="w3-modal-content">
                            <div class="w3-container">
                                    <span onclick="document.getElementById('id01').style.display='none'" class="w3-button w3-display-topright">&times;</span>
                                    <form name="test" method="post" action="/conference">
                                            <input type="hidden" name="command" value="addconference">
                                            <p><b><%=rb.getString("name")%></b></p>
                                            <input type="text" name="name" size="45" maxlength="45">
                                            <p><b><%=rb.getString("place")%></b></p>
                                            <input type="text" name="place" size="45" maxlength="45">
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

</div>

</body>
</html>
