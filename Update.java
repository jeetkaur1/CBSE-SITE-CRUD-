import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;
import java.sql.*;

public class Update extends HttpServlet {

    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        res.setContentType("text/html");
        PrintWriter out = res.getWriter();

        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Updated Employee</title>");
        out.println("<style>");
        out.println("body { background-color: #add8e6; font-family: Arial, sans-serif; text-align: center; }");
        out.println("h2 { color: #333; }");
        out.println("table { width: 50%; margin: auto; border-collapse: collapse; background-color: white; }");
        out.println("table, th, td { border: 1px solid black; }");
        out.println("</style>");
        out.println("</head>");
        out.println("<body>");

        String rollnumber = req.getParameter("rollnumber");
        String name = req.getParameter("name");
        int maths = Integer.parseInt(req.getParameter("maths"));
        int english = Integer.parseInt(req.getParameter("english"));
        int punjabi = Integer.parseInt(req.getParameter("punjabi"));
        int science = Integer.parseInt(req.getParameter("science"));

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "mca6");

            PreparedStatement updateStmt = con.prepareStatement("UPDATE emp107 SET name=?, maths=?, english=?, punjabi=?, science=? WHERE rollnumber=?");
            updateStmt.setString(1, name);
            updateStmt.setInt(2, maths);
            updateStmt.setInt(3, english);
            updateStmt.setInt(4, punjabi);
            updateStmt.setInt(5, science);
            updateStmt.setString(6, rollnumber);

            int rowsUpdated = updateStmt.executeUpdate();

            if (rowsUpdated != 0) {
                out.println("<h2>Marks updated successfully for Roll Number " + rollnumber + ".</h2>");

                PreparedStatement selectStmt = con.prepareStatement("SELECT * FROM emp107");
                ResultSet rs = selectStmt.executeQuery();

                out.println("<h2>Updated Records</h2>");
                out.println("<table>");
                out.println("<tr>");
                out.println("<th>RollNumber</th>");
                out.println("<th>Name</th>");
                out.println("<th>Maths</th>");
                out.println("<th>English</th>");
                out.println("<th>Punjabi</th>");
                out.println("<th>Science</th>");
                out.println("</tr>");

                while (rs.next()) {
                    out.println("<tr>");
                    out.println("<td>" + rs.getString("rollnumber") + "</td>");
                    out.println("<td>" + rs.getString("name") + "</td>");
                    out.println("<td>" + rs.getInt("maths") + "</td>");
                    out.println("<td>" + rs.getInt("english") + "</td>");
                    out.println("<td>" + rs.getInt("punjabi") + "</td>");
                    out.println("<td>" + rs.getInt("science") + "</td>");
                    out.println("</tr>");
                }

                out.println("</table>");
                rs.close();
            } else {
                out.println("<p style='color:red;'>Record for RollNumber " + rollnumber + " not found or not updated.</p>");
            }

            con.close();
        } catch (Exception e) {
            out.println("<p style='color:red;'>Error updating marks: " + e.getMessage() + "</p>");
        }

        out.println("</body>");
        out.println("</html>");
    }

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        doPost(req, res);
    }
}
