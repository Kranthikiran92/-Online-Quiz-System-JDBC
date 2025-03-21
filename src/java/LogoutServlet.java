import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.*;        
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.*;
public class LogoutServlet  extends HttpServlet
{
 public void doGet(HttpServletRequest req, HttpServletResponse res)
         throws ServletException, IOException
 {
     res.setContentType("text/html");
     PrintWriter out =res.getWriter();
     try{
                 
	 req.getRequestDispatcher("Login.html").include(req, res);
         HttpSession session=req.getSession();
         session.removeAttribute("username");
         session.invalidate(); 
          
         out.close(); 
     }
     catch(Exception e){
         out.println(""+e);
     }
       
 }
}