package controller;

import dao.EventRegistrationDAOImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import model.EventRegistration;
import java.io.IOException;
import java.util.List;

@WebServlet("/studentMaster")
public class StudentMasterServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String fullName = request.getParameter("fullName");
        String department = request.getParameter("department");

        EventRegistrationDAOImpl dao = new EventRegistrationDAOImpl();
        List<EventRegistration> list;

        if ((fullName != null && !fullName.isEmpty()) ||
            (department != null && !department.isEmpty())) {

            list = dao.searchRegistrations(
                    fullName == null ? "" : fullName,
                    department == null ? "" : department
            );
        } else {
            list = dao.getAllRegistrations();
        }

        request.setAttribute("studentList", list);
        request.setAttribute("page", "viewStudents");

        request.getRequestDispatcher("adminDashboard.jsp")
               .forward(request, response);

    }
}
