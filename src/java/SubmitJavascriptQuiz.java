import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class SubmitJavascriptQuiz extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html><head><title>javascript Quiz Result</title>");
        // Add Bootstrap CSS link
        out.println("<link rel='stylesheet' href='https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css'>");
        // Add custom CSS for highlighting correct and wrong answers and background image
        out.println("<style>");
        out.println("body {");
        out.println("    background-image: url('background.jpg'); /* Replace 'background.jpg' with your background image path */");
        out.println("    background-size: cover;");
        out.println("}");
        out.println(".container {");
        out.println("    background-color: rgba(255, 255, 255, 0.8); /* Add a semi-transparent white background */");
        out.println("    border-radius: 10px;");
        out.println("    padding: 20px;");
        out.println("    margin-top: 50px;");
        out.println("}");
        out.println(".question {");
        out.println("    color: #333; /* Set question text color to black */");
        out.println("    font-weight: bold;");
        out.println("}");
        out.println(".list-group-item-success {");
        out.println("    background-color: #d4edda; /* Set background color for correct answers */");
        out.println("}");
        out.println(".list-group-item-danger {");
        out.println("    background-color: #f8d7da; /* Set background color for wrong answers */");
        out.println("}");
        out.println("</style>");
        out.println("</head><body>");
        out.println("<div class='container'>"); // Add container class for Bootstrap grid system
        out.println("<h1 class='mt-5 mb-4'>Java Script Quiz Result</h1>");

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            // Establish database connection (update URL, username, and password as needed)
            conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "lbrce");

            // Prepare SQL query to retrieve questions, options, and correct answers
            String sql = "SELECT question, option_a, option_b, option_c, correct_answer FROM questions WHERE subject = 'JavaScript' ORDER BY question_id";
            stmt = conn.prepareStatement(sql);

            // Execute query
            rs = stmt.executeQuery();

            // Retrieve user's selected answers from request parameters
            String[] userAnswers = new String[5];
            for (int i = 1; i <= 5; i++) {
                userAnswers[i-1] = request.getParameter("q" + i);
            }

            // Display questions, options, correct answers, and user's score
            int score = 0;
            int questionNumber = 1;
            while (rs.next()) {
                String question = rs.getString("question");
                String optionA = rs.getString("option_a");
                String optionB = rs.getString("option_b");
                String optionC = rs.getString("option_c");
                String correctAnswer = rs.getString("correct_answer");
                
                // Display question and options using Bootstrap classes
                out.println("<div class='card mb-3'>");
                out.println("<div class='card-body'>");
                out.println("<h5 class='card-title question'>Question " + questionNumber + "</h5>");
                out.println("<p class='card-text question'>" + question + "</p>");
                out.println("<ul class='list-group'>");
                
                
                out.println("<li class='list-group-item " + (userAnswers[questionNumber - 1] != null && userAnswers[questionNumber - 1].equalsIgnoreCase(correctAnswer) ? "list-group-item-success" : "list-group-item-danger") + "'>" + optionA + "</li>");
                out.println("<li class='list-group-item " + (userAnswers[questionNumber - 1] != null && userAnswers[questionNumber - 1].equalsIgnoreCase(correctAnswer) ? "list-group-item-success" : "list-group-item-danger") + "'>" + optionB + "</li>");
                out.println("<li class='list-group-item " + (userAnswers[questionNumber - 1] != null && userAnswers[questionNumber - 1].equalsIgnoreCase(correctAnswer) ? "list-group-item-success" : "list-group-item-danger") + "'>" + optionC + "</li>");
                
                out.println("</ul>");
                out.println("<p class='mt-2'><strong class='question'>Correct Answer:</strong> " + correctAnswer + "</p>");
                out.println("<p><strong class='question'>Your Answer:</strong> " + (userAnswers[questionNumber - 1] != null ? userAnswers[questionNumber - 1] : "Not answered") + "</p>");
                out.println("</div></div>");
                
                // Update score
                if (userAnswers[questionNumber - 1] != null && userAnswers[questionNumber - 1].equalsIgnoreCase(correctAnswer)) {
                    score++;
                }
                
                questionNumber++;
            }

          HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");
            out.println("<h2 class='mt-4'>"+(username != null ? username : "Guest")+":</h2>"+"<p class='mt-4'>Your score: " + score + "/5</p>");
            out.println("<form method='post' action='selectsub.html' class='mt-4'>");
            out.println((username != null ? username : "Guest")+" , you want to take another quizz <br>");
            out.println(" click here  for <br>");
            out.println("<button type='submit' class='btn btn-Success'>more-Quizzes</button>");
            out.println("</form>");
            out.println("<span style='float:right' >");
            out.println("<form method='post' action='login.jsp' class='mt-4'>");
            out.println("<button type='submit' class='btn btn-primary'>Logout</button>");
            out.println("</form>");
            out.println("</span>");
        } catch (SQLException e) {
            e.printStackTrace();
        } 
    }
}
