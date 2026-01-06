package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import dao.AdminDAO;
import dao.UserDAO;

@WebServlet("/adminlogin")
public class AdminLoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String id = request.getParameter("id");
        String pass = request.getParameter("pass");

        if (id == null || pass == null) {
            response.sendRedirect("adminlogin.jsp?error=true");
            return;
        }

        HttpSession session = request.getSession();

        /* ================= ADMIN LOGIN ================= */
        if (AdminDAO.validateAdmin(id, pass)) {

            session.setAttribute("admin", id);
            response.sendRedirect("adminDashboard.jsp");
            return;
        }

        /* ================= USER LOGIN ================= */
        if (UserDAO.validateUser(id, pass)) {

            session.setAttribute("username", id);
            response.sendRedirect("userdashboard.jsp");
            return;
        }

        /* ================= NEW USER AUTO REGISTER ================= */
        UserDAO.saveUser(id, pass);
        session.setAttribute("username", id);
        response.sendRedirect("userdashboard.jsp");
    }
}
