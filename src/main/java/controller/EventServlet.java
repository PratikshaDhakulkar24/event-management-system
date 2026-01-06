package controller;

import dao.EventDAO;
import dao.EventDAOImpl;
import dao.UserDAO;
import model.Event;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/EventServlet")
public class EventServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        String action = req.getParameter("action");
        EventDAO dao = new EventDAOImpl();

        /* ================= ADD EVENT (ADMIN) ================= */
        if ("add".equals(action)) {
            try {
                String name = req.getParameter("name");
                String date = req.getParameter("date");
                String venue = req.getParameter("venue");
                int capacity = Integer.parseInt(req.getParameter("capacity"));

                Event e = new Event(name, date, venue, capacity);
                dao.addEvent(e);

            } catch (Exception ex) {
                ex.printStackTrace();
            }

            res.sendRedirect(req.getContextPath() + "/adminDashboard.jsp");
        }

        /* ================= DELETE EVENT (ADMIN) ================= */
        else if ("delete".equals(action)) {
            try {
                int id = Integer.parseInt(req.getParameter("id"));
                dao.deleteEvent(id);
            } catch (Exception ex) {
                ex.printStackTrace();
            }

            res.sendRedirect(req.getContextPath() + "/adminDashboard.jsp");
        }

        /* ================= UPDATE EVENT (ADMIN) ================= */
        else if ("update".equals(action)) {
            try {
                Event e = new Event();
                e.setId(Integer.parseInt(req.getParameter("id")));
                e.setName(req.getParameter("name"));
                e.setDate(req.getParameter("date"));
                e.setVenue(req.getParameter("venue"));
                e.setCapacity(Integer.parseInt(req.getParameter("capacity")));

                dao.updateEvent(e);

            } catch (Exception ex) {
                ex.printStackTrace();
            }

            res.sendRedirect(req.getContextPath() + "/adminDashboard.jsp");
        }

        /* ================= APPLY EVENT (USER) ================= */
        else if ("apply".equals(action)) {
            try {
                int eventId = Integer.parseInt(req.getParameter("eventId"));

                HttpSession session = req.getSession();
                int userId = (int) session.getAttribute("userId");

                UserDAO.applyEvent(userId, eventId);

            } catch (Exception ex) {
                ex.printStackTrace();
            }

            res.sendRedirect(req.getContextPath() + "/userAllEvents.jsp");
        }
    }
}
