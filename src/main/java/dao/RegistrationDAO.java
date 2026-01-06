package dao;

import java.sql.*;

public class RegistrationDAO {

    private static Connection getConnection() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(
            "jdbc:mysql://localhost:3306/campus_event_db",
            "root",
            "Pratiksha@24"
        );
    }

    public static void saveRegistration(
        String event, String fn, String mn, String ln,
        String contact, String email,
        String dept, String year, String gender) {

        try (Connection con = getConnection()) {

            PreparedStatement ps = con.prepareStatement(
              "INSERT INTO registrations(event_name, fname, mname, lname, contact, email, department, year, gender) VALUES (?,?,?,?,?,?,?,?,?)"
            );

            ps.setString(1, event);
            ps.setString(2, fn);
            ps.setString(3, mn);
            ps.setString(4, ln);
            ps.setString(5, contact);
            ps.setString(6, email);
            ps.setString(7, dept);
            ps.setString(8, year);
            ps.setString(9, gender);

            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}