import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class LoginServlet extends HttpServlet {
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        res.setContentType("text/html");
        PrintWriter out = res.getWriter();
        String name = req.getParameter("name");
        String password = req.getParameter("password");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // Updated driver class name
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/users?useSSL=false", "root", "3Veni@946");

            String query = "SELECT * FROM user WHERE name=? AND password=?";
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setString(1, name);
            stmt.setString(2, password);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                res.sendRedirect("Home.html");
            } else {
                out.println("<html><body><p>Wrong username or password</p></body></html>");
            }

            con.close();
        } catch (Exception e) {
            e.printStackTrace(); // Print stack trace to see the error
            out.println("<html><body><p>Error: " + e.getMessage() + "</p></body></html>");
        }
    }
}
