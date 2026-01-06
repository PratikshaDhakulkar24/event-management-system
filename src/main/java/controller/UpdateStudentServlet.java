package controller;

import dao.EventRegistrationDAOImpl;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebServlet("/updateStudent")
public class UpdateStudentServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));
        String fullName = request.getParameter("fullName");
        String contact = request.getParameter("contact");
        String department = request.getParameter("department");

        EventRegistrationDAOImpl dao = new EventRegistrationDAOImpl();
        dao.updateStudent(id, fullName, contact, department);

        response.sendRedirect(request.getContextPath() + "/studentMaster");
    }
}
