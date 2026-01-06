package controller;

import dao.EventDAOImpl;
import model.Event;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;

@WebServlet("/DownloadAllEventReportServlet")
public class DownloadAllEventReportServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("application/pdf");
        response.setHeader(
                "Content-Disposition",
                "attachment; filename=Upcoming_Events_Report.pdf"
        );

        try {
            // 1️⃣ PDF document
            Document document = new Document(PageSize.A4);
            PdfWriter.getInstance(document, response.getOutputStream());
            document.open();

            // 2️⃣ Title
            Font titleFont = new Font(Font.FontFamily.HELVETICA, 18, Font.BOLD);
            Paragraph title = new Paragraph("Upcoming Events Report", titleFont);
            title.setAlignment(Element.ALIGN_CENTER);
            title.setSpacingAfter(20);
            document.add(title);

            // 3️⃣ Table
            PdfPTable table = new PdfPTable(5);
            table.setWidthPercentage(100);
            table.setSpacingBefore(10);

            addHeader(table, "ID");
            addHeader(table, "Event Name");
            addHeader(table, "Date");
            addHeader(table, "Venue");
            addHeader(table, "Capacity");

            // 4️⃣ Fetch data
            EventDAOImpl dao = new EventDAOImpl();
            List<Event> list = dao.getUpcomingEvents();

            if (list != null && !list.isEmpty()) {
                for (Event e : list) {
                    table.addCell(String.valueOf(e.getId()));
                    table.addCell(e.getName());
                    table.addCell(e.getDate());
                    table.addCell(e.getVenue());
                    table.addCell(String.valueOf(e.getCapacity()));
                }
            } else {
                PdfPCell cell = new PdfPCell(
                        new Phrase("No upcoming events found")
                );
                cell.setColspan(5);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
            }

            // 5️⃣ Add table & close
            document.add(table);
            document.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 🔹 Table header style
    private void addHeader(PdfPTable table, String text) {
        PdfPCell cell = new PdfPCell(new Phrase(text));
        cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setPadding(6);
        table.addCell(cell);
    }
}
