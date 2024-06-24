import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*; 
import java.sql.*;

public class Add extends HttpServlet {

    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        res.setContentType("text/html");
        PrintWriter out = res.getWriter();
        res.setHeader("Refresh","4;cbse.html");
        out.println("<html><body>");
        String name = req.getParameter("name");
        String roll = req.getParameter("roll"); 
        String maths = req.getParameter("maths"); 
        String english = req.getParameter("english"); 
        String punjabi = req.getParameter("punjabi"); 
        String science = req.getParameter("science"); 

        Connection c = null;
        Statement s = null;
        ResultSet rs = null;

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            c = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "mca6");

            s = c.createStatement();
            int x = s.executeUpdate("insert into emp107 values('"+name+"','"+roll+"','"+maths+ "','"+english+"','"+punjabi+"','"+science+"')");
            rs = s.executeQuery("select * from emp107");
            ResultSetMetaData rsmd = rs.getMetaData();
			out.println("<body style='background-color: black; color: white;'>");
            
            out.println("<div style='text-align: center;'>");
            out.println("<table style='background-color: skyblue; border-collapse: collapse; width: 800px; margin: 0 auto;'>");
            out.println("<tr style='background-color: #007BFF; color: white;'>");
            
            for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                out.println("<th>" + rsmd.getColumnName(i) + "</th>");
            }
            out.println("</tr>");

           
            while (rs.next()) {
                out.println("<tr>");
                for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                    out.println("<td>" + rs.getString(i) + "</td>");
                }
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
		out.println("<button onclick='goBack()'>Go Back</button>");
        out.println("</body></html>");
    }

  
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        res.setContentType("text/html");
        PrintWriter out = res.getWriter();
        out.println("<html><body>");
        
        Connection c = null;
        Statement s = null;
        ResultSet rs = null;

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            c = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "gomu", "gomu");

            rs = c.createStatement().executeQuery("SELECT * FROM emp107");
            ResultSetMetaData rsmd = rs.getMetaData();

            out.println("<table bgcolor='yellow' border='1' width='500'>");
            out.println("<tr>");

            
            for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                out.println("<th>" + rsmd.getColumnName(i) + "</th>");
            }
            out.println("</tr>");

          
            while (rs.next()) {
                out.println("<tr>");
                for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                    out.println("<td>" + rs.getString(i) + "</td>");
                }
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
}