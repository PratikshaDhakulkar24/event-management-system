package controller;

import dao.EventRegistrationDAOImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/deleteStudent")
public class DeleteStudentServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String idStr = request.getParameter("id");

        if (idStr != null) {
            int id = Integer.parseInt(idStr);

            EventRegistrationDAOImpl dao = new EventRegistrationDAOImpl();
            dao.deleteStudent(id);
        }

        // redirect back to student list
        response.sendRedirect(request.getContextPath() + "/studentMaster");
    }
}
