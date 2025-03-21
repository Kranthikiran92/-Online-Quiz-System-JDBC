import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date; 
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession; 

public class JavaScriptServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html><head><title>JavaScript Quiz</title>");
        out.println("<link rel='stylesheet' href='https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css'>");
        out.println("<style>");
        out.println("body {");
        out.println("    background-color: #640D6B; /* Light gray background color */");
        out.println("}");
        out.println(".container {");
        out.println("    background-color: #fff; /* White background for the quiz container */");
        out.println("    border-radius: 10px;");
        out.println("    padding: 20px;");
        out.println("    margin-top: 50px;");
        out.println("    box-shadow: 0 0 10px rgba(0, 0, 0, 0.1); /* Add a subtle shadow */");
        out.println("}");
        out.println(".card {");
        out.println("    margin-bottom: 20px;");
        out.println("}");
        out.println("</style>");
        out.println("</head><body>");
        out.println("<div class='container'>");

        
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");

        
        out.println("<h1>Welcome, " + (username != null ? username : "Guest") + "!</h1>");
        out.println("<h3>let's start the  JavaScript Quizz</h3>");

       

        Connection conn=null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            
            Class.forName("oracle.jdbc.driver.OracleDriver");
            conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "lbrce");
            if(conn!=null){
                System.out.println("success");
            }

           
            String sql = "SELECT * FROM questions WHERE subject = 'JavaScript' ";
            stmt = conn.prepareStatement(sql);

        
            rs = stmt.executeQuery();
            
        
            int questionNumber = 1;
            out.println("<form id='quiz-form'  action='SubmitJavascriptQuiz' method='post'>");
            while (rs.next()) {
                String question = rs.getString("question");
                String optionA = rs.getString("option_a");
                String optionB = rs.getString("option_b");
                String optionC = rs.getString("option_c");
                out.println("<div class='card'>");
                out.println("<div class='card-body'>");
                out.println("<p><strong class='question'>Question " + questionNumber + ":</strong> " + question + "</p>");
                out.println("<ul class='list-group'>");
                out.println("<li class='list-group-item'><input type='radio' name='q" + questionNumber + "' value='a'> a) " + optionA + "</li>");
                out.println("<li class='list-group-item'><input type='radio' name='q" + questionNumber + "' value='b'> b) " + optionB + "</li>");
                out.println("<li class='list-group-item'><input type='radio' name='q" + questionNumber + "' value='c'> c) " + optionC + "</li>");
                out.println("</ul>");
                out.println("</div></div>");

                questionNumber++;
            }

            

            out.println("<input type='submit' class='btn btn-primary' value='Submit'>");
            out.println("</form>");
        } catch (Exception e) {
            e.printStackTrace();
            
        } finally {
            
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        out.println("</div></body></html>");
        out.close();
    }
}
