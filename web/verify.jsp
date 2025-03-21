<%@ page import="java.sql.*" %>

<%

    try {
        Class.forName("oracle.jdbc.driver.OracleDriver");

        Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "lbrce");
        String name2 = request.getParameter("name1");
        String password2 = request.getParameter("password1");
        String sql = "SELECT * FROM DB";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery();
        int c=1;
        while (rs.next()) {
            String name1 = rs.getString("name");
            String password1 = rs.getString("password");
            if(name2.equals(name1)&&password2.equals(password1))
               {
                    //HttpSession session = request.getSession();
                    // Set the username as an attribute in the session
                    session.setAttribute("username", name1);
                    c=0;
                    response.sendRedirect("selectsub.html");
                }
        }        
        if(c==1)
            response.sendRedirect("homepage.html");
        pstmt.close();
        rs.close();
        conn.close();
        
    } catch (Exception e) {
        e.printStackTrace();
    } 
%>
