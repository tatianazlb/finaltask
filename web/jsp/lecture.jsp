<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 09-Aug-18
  Time: 22:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="ctg" uri="customtags" %>
<%@ page import="java.util.ResourceBundle" %>
<%@ page import="java.util.Locale" %>
<%@ page import="epam.zlobich.task6.entity.entitybd.UserBd" %>
<%@ page import="epam.zlobich.task6.entity.entitybd.Lecture" %>
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



<body onload="validForm()">
<div align="center" style="max-width: 600px; margin: 0 auto;">


<h1><%=request.getParameter("theme")%></h1><br>

<table border="1" class="w3-table-all"><tr class="w3-pale-green"><th><%=rb.getString("title")%></th><th><%=rb.getString("speaker")%></th></tr>
<%
       for (Lecture lecture : (ArrayList<Lecture>)request.getAttribute("listLectures")
                ) {%>
           <tr><td>
               <%=lecture.getTitle()%>
           </td><td>
               <%=lecture.getUser()%>
           </td></tr>
        <%
        }
%>
</table>


<%
    if (!((UserBd)request.getSession().getAttribute("user")).isRole()){
%>

<div align="center" id="apply" class="w3-container">
    <button onclick="document.getElementById('i01').style.display='block'" class="w3-button w3-black"><%=rb.getString("apply")%></button>
    <div id="i01" class="w3-modal">
        <div class="w3-modal-content">
            <div class="w3-container">
                <span onclick="document.getElementById('i01').style.display='none'" class="w3-button w3-display-topright">&times;</span>
                <form name="test" method="post" action="/lecture">
                    <input type="hidden" name="command" value="addrequest">
                    <input type="hidden" name="theme" value="<%=request.getParameter("theme")%>">
                    <p><b><%=rb.getString("title")%></b></p>
                    <input type="text" name="title" size="45" maxlength="45">
                    <div><button type="submit"><%=rb.getString("subm")%></button>
                        <button type="reset"><%=rb.getString("cl")%></button></div>
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

<script type="text/javascript">
    function validForm() {
        <%
        if(request.getAttribute("success")!=null) {
        %>
        alert("Request is succesfully added!");
        <%
        request.setAttribute("success", null);
        }
        %>
    }
</script>



