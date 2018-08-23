<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 21-Aug-18
  Time: 12:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Error</title>
</head>
<body>

<div align="center">

    <div><img src="SorryImage.png" alt="nof"/></div>


    <p>We are sorry, there is something wrong.</p>

    <details>
        <summary>Get Info 'Bout Problem</summary>
        <p>    <%=((Exception)request.getAttribute("exception")).getMessage()%></p>
        <p>    <%=((Exception)request.getAttribute("exception")).getLocalizedMessage()%></p>
        <p>    <%=((Exception)request.getAttribute("exception")).getStackTrace().toString()%></p>

    </details>



</div>
</body>
</html>
