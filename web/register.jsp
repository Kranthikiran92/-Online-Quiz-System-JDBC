<%@ page import="java.sql.*" %>

<%

    try {
        Class.forName("oracle.jdbc.driver.OracleDriver");

        Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "lbrce");

        String name = request.getParameter("name");
        String password = request.getParameter("password");
        if(name.equals("")||password.equals(""))
        {
            response.sendRedirect("homepage.html");
        }
        
        else
        {
            String sql = "INSERT INTO DB (name, password) VALUES (?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, name);
            pstmt.setString(2, password);
        
            pstmt.executeUpdate();
            pstmt.close();
            response.sendRedirect("login.jsp");
    }
        conn.close();
    } catch (Exception e) {
        e.printStackTrace();
    } 
%>

