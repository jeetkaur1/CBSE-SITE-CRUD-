import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;
import java.sql.*;

public class Delete extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Delete Employee</title>");
        out.println("<style>");
        out.println("body { background-color: #f0f0f0; font-family: Arial, sans-serif; }");
        out.println("h2 { color: #333; }");
        out.println("form { background-color: #fff; padding: 20px; border-radius: 5px; box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1); }");
        out.println("input[type='text'], input[type='submit'] { padding: 10px; margin: 5px; border: 1px solid #ccc; border-radius: 3px; }");
        out.println("input[type='submit'] { background-color: #4CAF50; color: white; cursor: pointer; }");
        out.println("table { width: 100%; border-collapse: collapse; }");
        out.println("th, td { border: 1px solid #ddd; padding: 8px; text-align: left; }");
        out.println("th { background-color: #4CAF50; color: white; }");
        out.println("tr:nth-child(even) { background-color: #f2f2f2; }");
        out.println("</style>");
        out.println("</head>");
        out.println("<body>");

      

        String roll = request.getParameter("txtRoll"); 
        if (roll != null && !roll.isEmpty()) {
            try {
                Class.forName("oracle.jdbc.driver.OracleDriver");
                Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "mca6");
                PreparedStatement deleteStmt = con.prepareStatement("delete from emp107 where rollnumber = ?");
                deleteStmt.setString(1, roll); 
                int deletedRows = deleteStmt.executeUpdate();
                if (deletedRows != 0) {
                    out.println("<p style='color:green;'>Record for Roll Number " + roll + " successfully deleted.</p>");
                    
                    PreparedStatement selectStmt = con.prepareStatement("select * from emp107");
                    ResultSet rs = selectStmt.executeQuery();
                    ResultSetMetaData rsmd = rs.getMetaData();
                    int columnCount = rsmd.getColumnCount();
                    
                    out.println("<h2>Updated Records</h2>");
                    out.println("<table>");
                    out.println("<tr>");
                    for (int i = 1; i <= columnCount; i++) {
                        out.println("<th>" + rsmd.getColumnName(i) + "</th>");
                    }
                    out.println("</tr>");
                    while (rs.next()) {
                        out.println("<tr>");
                        for (int i = 1; i <= columnCount; i++) {
                            out.println("<td>" + rs.getString(i) + "</td>");
                        }
                        out.println("</tr>");
                    }
                    out.println("</table>");
                    rs.close();
                } else {
                    out.println("<p style='color:red;'>Record for Roll Number " + roll + " not found.</p>");
                }
                con.close();
            } catch (Exception e) {
                out.println("<p style='color:red;'>Failed to delete record. Error: " + e.getMessage() + "</p>");
            }
        }

        out.println("</body>");
        out.println("</html>");
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}

