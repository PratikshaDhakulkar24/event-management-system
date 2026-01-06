package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

import dao.EventRegistrationDAOImpl;
import model.EventRegistration;

@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        System.out.println("REGISTER SERVLET HIT");

        HttpSession session = request.getSession(false);
        String username = (String) session.getAttribute("username");

        // ❌ User login नसेल तर
        if (username == null) {
            response.sendRedirect("userlogin.jsp");
            return;
        }

        // ✅ Form data
        String fullName = request.getParameter("fullName");
        String contact = request.getParameter("contact");
        String department = request.getParameter("department");
        String eventName = request.getParameter("eventName");
        String gender = request.getParameter("gender");

        // ✅ Model object
        EventRegistration reg = new EventRegistration();
        reg.setUsername(username);          // ⭐ MOST IMPORTANT LINE
        reg.setFullName(fullName);
        reg.setContact(contact);
        reg.setDepartment(department);
        reg.setEventName(eventName);
        reg.setGender(gender);

        // ✅ DB insert
        EventRegistrationDAOImpl dao = new EventRegistrationDAOImpl();
        dao.registerEvent(reg);

        // ✅ Success popup + redirect
        response.setContentType("text/html");
        response.getWriter().println(
            "<html><body>" +
            "<script>" +
            "alert('Registration Successful!');" +
            "window.location='userdashboard.jsp?view=events';" +
            "</script>" +
            "</body></html>"
        );
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect("userdashboard.jsp?view=myregistrations");
    }
}
