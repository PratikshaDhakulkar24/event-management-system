package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

import dao.EventRegistrationDAO;
import dao.EventRegistrationDAOImpl;
import model.EventRegistration;

@WebServlet("/MyRegistrationServlet")
public class MyRegistrationServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        String username = (String) session.getAttribute("username");

        // user login check
        if (username == null) {
            response.sendRedirect("userlogin.jsp");
            return;
        }

        // DAO object (NO casting needed)
        EventRegistrationDAO dao = new EventRegistrationDAOImpl();

        // get registered events
        List<EventRegistration> regList =
                dao.getRegistrationsByUsername(username);

        // send data to JSP
        request.setAttribute("registrations", regList);
        request.getRequestDispatcher("myregistrations.jsp")
               .forward(request, response);
    }
}
