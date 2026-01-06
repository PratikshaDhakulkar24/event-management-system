package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import model.User;

public class UserDAO {

    // ================== DB CONNECTION ==================
    private static Connection getConnection() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(
            "jdbc:mysql://localhost:3306/campus_event_db",
            "root",
            "Pratiksha@24"
        );
    }

    // ================== SAVE USER ==================
    public static void saveUser(String username, String password) {

        try (Connection con = getConnection()) {

            PreparedStatement ps = con.prepareStatement(
                "INSERT INTO users(username, password) VALUES (?, ?)"
            );

            ps.setString(1, username);
            ps.setString(2, password);

            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ================== VALIDATE USER LOGIN ==================
    public static boolean validateUser(String username, String password) {
        boolean status = false;

        try (Connection con = getConnection()) {

            PreparedStatement ps = con.prepareStatement(
                "SELECT * FROM users WHERE username=? AND password=?"
            );

            ps.setString(1, username);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();
            status = rs.next();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return status;
    }

    // ================== APPLY EVENT ==================
    public static boolean applyEvent(int userId, int eventId) {
        boolean status = false;

        try (Connection con = getConnection()) {

            PreparedStatement ps = con.prepareStatement(
                "INSERT INTO registrations(user_id, event_id) VALUES (?, ?)"
            );

            ps.setInt(1, userId);
            ps.setInt(2, eventId);

            int rows = ps.executeUpdate();
            if (rows > 0) {
                status = true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return status;
    }

    // ================== GET USER BY ID ==================
    public static User getUserById(int userId) {
        User user = null;

        try (Connection con = getConnection()) {

            PreparedStatement ps = con.prepareStatement(
                "SELECT * FROM users WHERE id = ?"
            );

            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                user = new User();
                user.setId(rs.getInt("id"));
                user.setUsername(rs.getString("username"));
                user.setEmail(rs.getString("email"));
                user.setMobile(rs.getString("mobile"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return user;
    }

    // ================== UPDATE USER ==================
    public static void updateUser(User user) {

        try (Connection con = getConnection()) {

            PreparedStatement ps = con.prepareStatement(
                "UPDATE users SET username=?, email=?, mobile=? WHERE id=?"
            );

            ps.setString(1, user.getUsername());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getMobile());
            ps.setInt(4, user.getId());

            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
