package controller;

import dao.EventRegistrationDAOImpl;
import model.EventRegistration;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;

@WebServlet("/DownloadAllStudentReportServlet")
public class DownloadAllStudentReportServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public DownloadAllStudentReportServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {

        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=AllStudentsReport.pdf");

        try {
            Document document = new Document();
            PdfWriter.getInstance(document, response.getOutputStream());
            document.open();

            // TITLE
            Font titleFont = new Font(Font.FontFamily.HELVETICA, 18, Font.BOLD);
            Paragraph title = new Paragraph("All Students Registration Report", titleFont);
            title.setAlignment(Element.ALIGN_CENTER);
            title.setSpacingAfter(20);
            document.add(title);

            // TABLE
            PdfPTable table = new PdfPTable(7); // 7 columns
            table.setWidthPercentage(100);
            table.setWidths(new float[]{1f, 2f, 3f, 2f, 2f, 3f, 1.5f}); // column widths

            addHeader(table, "ID");
            addHeader(table, "Username");
            addHeader(table, "Full Name");
            addHeader(table, "Contact");
            addHeader(table, "Dept");
            addHeader(table, "Event");
            addHeader(table, "Gender");

            // Fetch data
            EventRegistrationDAOImpl dao = new EventRegistrationDAOImpl();
            List<EventRegistration> students = dao.getAllRegistrations();

            if (students.isEmpty()) {
                PdfPCell cell = new PdfPCell(new Phrase("No registrations found"));
                cell.setColspan(7);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
            } else {
                boolean alternate = false;
                for (EventRegistration s : students) {
                    // alternate row colors
                    BaseColor bgColor = alternate ? BaseColor.WHITE : new BaseColor(230, 230, 250);
                    addCell(table, String.valueOf(s.getId()), bgColor);
                    addCell(table, s.getUsername(), bgColor);
                    addCell(table, s.getFullName(), bgColor);
                    addCell(table, s.getContact(), bgColor);
                    addCell(table, s.getDepartment(), bgColor);
                    addCell(table, s.getEventName(), bgColor);
                    addCell(table, s.getGender(), bgColor);

                    alternate = !alternate;
                }
            }

            document.add(table);
            document.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void addHeader(PdfPTable table, String text) {
        PdfPCell cell = new PdfPCell(new Phrase(text));
        cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setPadding(5);
        table.addCell(cell);
    }

    private void addCell(PdfPTable table, String text, BaseColor bgColor) {
        PdfPCell cell = new PdfPCell(new Phrase(text));
        cell.setBackgroundColor(bgColor);
        cell.setPadding(5);
        table.addCell(cell);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        doGet(request, response);
    }
}
