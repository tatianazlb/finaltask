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
<%@ page import="epam.zlobich.task6.entity.entitybd.UserBd" %>
<%@ page import="java.io.ByteArrayOutputStream" %>
<%@ page import="javax.imageio.ImageIO" %>
<%@ page import="epam.zlobich.task6.entity.entitybd.Lecture" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="epam.zlobich.task6.entity.entitybd.Request" %>
<%@ page import="epam.zlobich.task6.entity.entitybd.Question" %>
<%
    ResourceBundle rb;
    if (request.getSession().getAttribute("bundle")==null) {
        String lang = "en"; //Assign the correct language either by page or user-selected or browser language etc.
        rb = ResourceBundle.getBundle("app", new Locale(lang));

        request.getSession().setAttribute("bundle", rb);
    }
    else
        rb =(ResourceBundle)request.getSession().getAttribute("bundle");


    //<fmt:setLocale value="${sessionScope.locale}"/>

    String b64;
    try {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(((UserBd) request.getSession().getAttribute("user")).getAvatar(), "jpg", baos);
        baos.flush();
        byte[] imageInByteArray = baos.toByteArray();
        baos.close();
        b64 = javax.xml.bind.DatatypeConverter.printBase64Binary(imageInByteArray);
    } catch (Exception e)
    {
        b64=null;
    }

%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">

    <h1>
        <%=rb.getString("hello") %>
        <%=((UserBd)request.getSession().getAttribute("user")).getLogin()%>
    </h1>
</head>
<body>

<div style ="max-width: 300px; max-height: 300px; position: fixed; top: 0; right: 0;">

<img src="data:image/jpg;base64, <%=b64%>" style ="max-width: 100%; max-height: 100%;" alt="Avatar not found" />

    <div align="center" class="w3-container">
        <button onclick="document.getElementById('id01').style.display='block'" class="w3-button w3-black"><%= rb.getString("update") %></button>
        <div id="id01" class="w3-modal">
            <div class="w3-modal-content">
                <div class="w3-container">
                    <span onclick="document.getElementById('id01').style.display='none'" class="w3-button w3-display-topright">&times;</span>
                    <form name="tedawst" method="post" action="/myroom" enctype="multipart/form-data">
                        <input type="hidden" name="command" value="updateuser">
                        <p><b><%= rb.getString("avatar") %></b></p>
                        <input type="file" name="avatar" accept="image/jpeg, image/jpg" formenctype="multipart/form-data" maxlength="3072">
                        <p><b><%= rb.getString("homeland") %></b></p>
                        <input type="text" value="<%=((UserBd)request.getSession().getAttribute("user")).getHomeland()%>" placeholder="<%=((UserBd)request.getSession().getAttribute("user")).getHomeland()%>" name="homeland" size="45"maxlength="45">
                        <div><button type="submit"><%= rb.getString("subm") %></button>
                            <button type="reset"><%= rb.getString("cl") %></button></div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

<div align="center" style="max-width: 600px; margin: 0 auto;">


<%
    if (((UserBd)request.getSession().getAttribute("user")).isRole()){
%>

<h1><%=rb.getString("allrequests")%></h1><br>
<form name="test1" action="/myroom" method="POST">
    <input type="hidden" name="command" value="approverequest">
    <table border= class="w3-table-all"><tr class="w3-pale-green"><th><%=rb.getString("title")%></th><th><%=rb.getString("themename")%></th></tr>
        <%
            for (Request requestBd : (ArrayList<Request>)request.getAttribute("listOfRequests")
                    ) {%>

        <tr><td><%=requestBd.getTitle()%></td><td><%=requestBd.getThemeName()%></td><td>
            <button  class="w3-button w3-white w3-border w3-border-green w3-round-large" style="width: 100%;" type="submit" name="title" value="<%=requestBd.getTitle()%>"><%=rb.getString("approverequest")%></button></td></tr>

        <%
            }
        %>
    </table></form>

<br>

    <br>

<h1><%=rb.getString("allquestions")%></h1><br>
<form name="t" action="/myroom" method="POST">
    <input type="hidden" name="command" value="answerquestion">

    <table border="1" class="w3-table-all"><tr class="w3-pale-green"><th><%=rb.getString("id")%></th><th><%=rb.getString("asked")%></th><th><%=rb.getString("answer")%></th></tr>
        <%
            for (Question question : (ArrayList<Question>)request.getAttribute("listOfQuestions")
                    ) {%>

        <tr><td><%=question.getId()%></td><td><%=question.getAskedQuestion()%></td><td><%
        if (question.getAnswer()==null)
        {
            %>
            <input type="text" name="answer" size="45" maxlength="45"><button  class="w3-button w3-white w3-border w3-border-green w3-round-large"type="submit" name="idQuestion" value="<%=question.getId()%>"><%= rb.getString("subm") %></button>
            <%
        }
        else{
            %><%=question.getAnswer()%><%
        }
        %></td></tr>

        <%
            }
        %>
    </table></form>

<br>


<%
    }
%>

<%
    if (!((UserBd)request.getSession().getAttribute("user")).isRole()){
%>
<h1><%=rb.getString("mylectures")%></h1><br>
<form name="test" action="/myroom" method="POST">
<input type="hidden" name="command" value="deletelecture">
<table border="1" class="w3-table-all"><tr class="w3-pale-green"><th><%=rb.getString("title")%></th><th><%=rb.getString("themename")%></th><th></th></tr>
    <%
        for (Lecture lecture : (ArrayList<Lecture>)request.getAttribute("listOfUserLectures")
                ) {%>
    <tr><td>
        <%=lecture.getTitle()%>
    </td><td>
        <%=lecture.getThemeName()%>
    </td><td>
        <button class="w3-button w3-white w3-border w3-border-green w3-round-large" style="width: 100%;" type="submit" name="title" value="<%=lecture.getTitle()%>"><%=rb.getString("deletelecture")%></button></td></tr>
            <%
        }
%>
</table></form>
    <br>

<h1><%=rb.getString("myrequests")%></h1><br>
<form name="tst1" action="/myroom" method="POST">
    <input type="hidden" name="command" value="deleterequest">
    <table border="1" class="w3-table-all"><tr class="w3-pale-green"><th><%=rb.getString("title")%></th><th><%=rb.getString("themename")%></th></tr>
<%
        for (Request requestBd : (ArrayList<Request>)request.getAttribute("listOfUserRequests")
        ) {%>

        <tr><td><%=requestBd.getTitle()%></td><td><%=requestBd.getThemeName()%></td><td>
            <button class="w3-button w3-white w3-border w3-border-green w3-round-large" style="width: 100%;" type="submit" name="title" value="<%=requestBd.getTitle()%>"><%=rb.getString("deleterequest")%></button></td></tr>

        <%
        }
%>
    </table></form>

    <br>

<h1><%=rb.getString("myquestions")%></h1><br>
<form name="st1" action="/myroom" method="POST">
    <input type="hidden" name="command" value="deletequestion">
    <table border="1" class="w3-table-all"><tr class="w3-pale-green"><th><%=rb.getString("id")%></th><th><%=rb.getString("asked")%></th><th><%=rb.getString("answer")%></th><th></th></tr>
        <%
            for (Question question : (ArrayList<Question>)request.getAttribute("listOfUserQuestions")
                    ) {%>
        <tr><td><%=question.getId()%></td><td><%=question.getAskedQuestion()%></td><td><%=question.getAnswer()==null? rb.getString("answernull"):question.getAnswer() %></td><td>
            <button  class="w3-button w3-white w3-border w3-border-green w3-round-large" style="width: 100%;" type="submit" name="idQuestion" value="<%=question.getId()%>"><%=rb.getString("deletequestion")%></button></td></tr>
        <%
            }
        %>
    </table></form>

    <br>

<div align="center" class="w3-container">
    <button onclick="document.getElementById('i01').style.display='block'" class="w3-button w3-black"><%= rb.getString("ask") %></button>
    <div id="i01" class="w3-modal">
        <div class="w3-modal-content">
            <div class="w3-container">
                <span onclick="document.getElementById('i01').style.display='none'" class="w3-button w3-display-topright">&times;</span>
                <form name="test" method="post" action="/myroom" enctype="multipart/form-data">
                    <input type="hidden" name="command" value="askquestion">
                    <p><b><%=rb.getString("askQ") %></b></p>
                    <input type="text" name="question" size="45" maxlength="45">
                    <div><button type="submit"><%= rb.getString("subm") %></button>
                        <button type="reset"><%= rb.getString("cl") %></button></div>
                </form>
            </div>
        </div>
    </div>
</div>


    <br>
<%
    if(((ArrayList<Question>) request.getAttribute("listOfUserQuestions")).isEmpty() && ((ArrayList<Request>) request.getAttribute("listOfUserRequests")).isEmpty()
            &&((ArrayList<Lecture>) request.getAttribute("listOfUserLectures")).isEmpty()){
%>
    <form name="st" action="/main" method="POST">
        <input type="hidden" name="command" value="deleteuser">
        <input class="w3-button w3-white w3-border w3-border-green w3-round-large" style="width: 100%;" type="submit" onclick="return confirm('<%=rb.getString("deleteusercheck")%>')" name="button" value="SUICIDE">
    </form>

<%
        }
    }
%>

<form name="terwst" action="/main" method="POST">
    <input type="hidden" name="command" value="logout">
    <input  class="w3-button w3-white w3-border w3-border-green w3-round-large" style="width: 100%;" type="submit" name="button" value="LOGOUT">
</form>

</div>

</body>
</html>

