import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class SignupServlet extends HttpServlet {
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        res.setContentType("text/html");
        PrintWriter out = res.getWriter();
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");  // Updated driver class name for MySQL 8.0+
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/users?useSSL=false", "root", "3Veni@946");

            String q = "INSERT INTO user (name, email, password) VALUES (?, ?, ?)";
            PreparedStatement stm = con.prepareStatement(q);
            stm.setString(1, name);
            stm.setString(2, email);
            stm.setString(3, password);

            int x = stm.executeUpdate();
            System.out.println("Data Updated successfully... " + x);

            if (x > 0) {
                res.sendRedirect("login.html");
            } else {
                out.println("<html><body><p>Registration Not successful!</p></body></html>");
            }

            con.close();
        } catch (Exception e) {
            e.printStackTrace(); // Print stack trace to see the error
            out.println("<html><body><p>Error: " + e.getMessage() + "</p></body></html>");
        }
    }
}
