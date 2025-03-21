import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class SelectSubjectServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        String subject = request.getParameter("subject");

        
        if (subject != null && !subject.isEmpty()) {
            if (subject.equals("Html")) {
                response.sendRedirect("HtmlServlet");
            } else if (subject.equals("CSS")) {
                response.sendRedirect("CssServlet");
            } else if (subject.equals("Javascript")) {
                response.sendRedirect("JavaScriptServlet");
            } else {
          
                String alertScript = "<script>alert('Please select a course.');</script>";
            response.getWriter().println(alertScript);
            response.sendRedirect("selectsub.html");
            }
        } else {
            
             String alertScript = "<script>alert('Please select a course.');</script>";
            response.getWriter().println(alertScript);
        }
    }
}
