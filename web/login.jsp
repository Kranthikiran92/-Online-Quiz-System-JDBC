<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" type="text/css" href="login.css">
    </head>
    <body>
        <form action="verify.jsp">
            <h3> Login Form</h3>
            UserName:<input type="text" name="name1"><br>
            Password:<input type="password" name="password1"><br>
            <input type="submit" value="sign-in"><br>
            <label>Don't have an account already</label><a href='signup.jsp'>sign-up</a>
            
        </form>
        
    </body>
</html>
