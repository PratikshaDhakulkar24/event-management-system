package controller;

import dao.UserDAO;
import model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/UserProfileServlet")
public class UserProfileServlet extends HttpServlet {

    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        HttpSession session = req.getSession();
        int userId = (int) session.getAttribute("userId");

        User u = new User();
        u.setId(userId);
        u.setUsername(req.getParameter("username"));
        u.setEmail(req.getParameter("email"));
        u.setMobile(req.getParameter("mobile"));

        UserDAO.updateUser(u);

        res.sendRedirect("userProfile.jsp?msg=updated");
    }
}
