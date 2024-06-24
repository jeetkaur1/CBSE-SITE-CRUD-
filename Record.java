import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*; 
import java.sql.*;

public class Record extends HttpServlet {

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        res.setContentType("text/html");
        PrintWriter out = res.getWriter();
        out.println("<html><body style='background-color: lightgreen;'>");

        Connection c = null;
        Statement s = null;
        ResultSet rs = null;

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            c = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "mca6");

            s = c.createStatement();
            rs = s.executeQuery("SELECT * FROM emp107");
            ResultSetMetaData rsmd = rs.getMetaData();
			
            out.println("<h2>Total Records of Students</h2>");
            out.println("<div style='text-align: center;'>");
            out.println("<table border='1' style='border-collapse: collapse; width: 80%; margin: 0 auto;'>");
            
            out.println("<tr>");
            for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                out.println("<th style='border: 1px solid black; padding: 8px;'>" + rsmd.getColumnName(i) + "</th>");
            }
            out.println("</tr>");

            while (rs.next()) {
                out.println("<tr>");
                for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                    out.println("<td style='border: 1px solid black; padding: 8px;'>" + rs.getString(i) + "</td>");
                }
                out.println("</tr>");
            }

            out.println("</table>");
            out.println("</div>");

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
}
