import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class RegisterServlet extends HttpServlet {
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        res.setContentType("text/html");
        PrintWriter out = res.getWriter();

        String studentName = req.getParameter("studentName");
        String phoneNumber = req.getParameter("phoneNumber");
        String branch = req.getParameter("branch");
        String eamcetRank = req.getParameter("eamcetRank");
        String gender = req.getParameter("gender");
        String hostelName = req.getParameter("hostelName");
        String roomNumber = req.getParameter("roomnumber");
        String fatherName = req.getParameter("fatherName");
        String fatherPhoneNumber = req.getParameter("fatherPhoneNumber");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hostel", "root", "3Veni@946");

            String q = "INSERT INTO booking (studentName, phoneNumber, branch, eamcetRank, gender, hostelName, roomNumber, fatherName, fatherPhoneNumber) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement stm = con.prepareStatement(q);
            stm.setString(1, studentName);
            stm.setString(2, phoneNumber);
            stm.setString(3, branch);
            stm.setInt(4, Integer.parseInt(eamcetRank));
            stm.setString(5, gender);
            stm.setString(6, hostelName);
            stm.setString(7, roomNumber);
            stm.setString(8, fatherName);
            stm.setString(9, fatherPhoneNumber);

            int x = stm.executeUpdate();

            if (x > 0) {
                out.println("<html><body><p>Registration successful!</p></body></html>");
            } else {
                out.println("<html><body><p>Registration not successful!</p></body></html>");
            }

            con.close();
        } catch (Exception e) {
            e.printStackTrace();
            out.println("<html><body><p>Error: " + e.getMessage() + "</p></body></html>");
        }
    }
}
