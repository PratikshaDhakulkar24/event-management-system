package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

@WebServlet("/DownloadUserPDF")
public class DownloadUserReportServlet extends HttpServlet
{

    private static final long serialVersionUID = 1L; // ✅ add this

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("username") == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        String username = session.getAttribute("username").toString();

        response.setContentType("application/pdf");
        response.setHeader(
            "Content-Disposition",
            "attachment; filename=my_event_report.pdf"
        );

        try {
            Document document = new Document();
            PdfWriter.getInstance(document, response.getOutputStream());
            document.open();

            Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 16);
            document.add(new Paragraph("My Event Registration Report", titleFont));
            document.add(new Paragraph("Username: " + username));
            document.add(new Paragraph(" "));

            PdfPTable table = new PdfPTable(6);
            table.addCell("Username");
            table.addCell("Event Name");
            table.addCell("Full Name");
            table.addCell("Contact");
            table.addCell("Department");
            table.addCell("Gender");

            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/campus_event_db",
                "root",
                "Pratiksha@24"
            );

            String sql =
                "SELECT username, event_name, full_name, contact, department, gender " +
                "FROM eventregistration WHERE username = ?";

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, username);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                table.addCell(rs.getString("username"));
                table.addCell(rs.getString("event_name"));
                table.addCell(rs.getString("full_name"));
                table.addCell(rs.getString("contact"));
                table.addCell(rs.getString("department"));
                table.addCell(rs.getString("gender"));
            }

            document.add(table);
            document.close();
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
