import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;
import java.sql.*;

public class PercentageCalculator extends HttpServlet {

    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        res.setContentType("text/html");
        PrintWriter out = res.getWriter();
        out.println("<html><head><style>");
        out.println("body { text-align: center; background-color: #ffdab9; }");
        out.println("table { margin: 20px auto; border-collapse: collapse; width: 80%; border: 2px solid #000; }");
        out.println("th, td { padding: 12px; text-align: left; border: 1px solid #000; }");
        out.println("th { background-color: #f2f2f2; }");
        out.println("</style></head><body>");

        Connection c = null;
        Statement s = null;
        ResultSet rs = null;

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            c = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "mca6");

            
            s = c.createStatement();
            rs = s.executeQuery("SELECT * FROM emp107");

            
            out.println("<h2>All Records with Percentages</h2>");

           
            out.println("<table border='1'>");

          
            ResultSetMetaData rsmd = rs.getMetaData();
            out.println("<tr>");
            for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                out.println("<th>" + rsmd.getColumnName(i) + "</th>");
            }
            out.println("<th>Percentage</th>");
            out.println("</tr>");

            
            while (rs.next()) {
                out.println("<tr>");
               
                int maths = rs.getInt("maths");
                int english = rs.getInt("english");
                int punjabi = rs.getInt("punjabi");
                int science = rs.getInt("science");

 
                int totalMarks = maths + english + punjabi + science;
                double percentage = (totalMarks / 400.0) * 100;

                
                for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                    out.println("<td>" + rs.getString(i) + "</td>");
                }
                out.println("<td>" + String.format("%.2f", percentage) + "%</td>");
                out.println("</tr>");
            }

            out.println("</table>");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (s != null) {
                    s.close();
                }
                if (c != null) {
                    c.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        out.println("</body></html>");
    }

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        
    }
}

