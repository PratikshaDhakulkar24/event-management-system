package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import model.EventRegistration;

public class EventRegistrationDAOImpl implements EventRegistrationDAO {

    private Connection con;

    public EventRegistrationDAOImpl() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/campus_event_db",
                    "root",
                    "Pratiksha@24"
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ================= USER : REGISTER EVENT =================
    @Override
    public void registerEvent(EventRegistration reg) {
        try {
            String sql = "INSERT INTO eventregistration "
                       + "(username, full_name, contact, department, event_name, gender) "
                       + "VALUES (?,?,?,?,?,?)";

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, reg.getUsername());
            ps.setString(2, reg.getFullName());
            ps.setString(3, reg.getContact());
            ps.setString(4, reg.getDepartment());
            ps.setString(5, reg.getEventName());
            ps.setString(6, reg.getGender());

            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ================= USER : MY REGISTRATIONS =================
    @Override
    public List<EventRegistration> getRegistrationsByUsername(String username) {

        List<EventRegistration> list = new ArrayList<>();

        try {
            String sql = "SELECT * FROM eventregistration WHERE username=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, username);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                EventRegistration reg = new EventRegistration();

                reg.setId(rs.getInt("id"));
                reg.setUsername(rs.getString("username"));
                reg.setFullName(rs.getString("full_name"));
                reg.setContact(rs.getString("contact"));
                reg.setDepartment(rs.getString("department"));
                reg.setEventName(rs.getString("event_name"));
                reg.setGender(rs.getString("gender"));

                list.add(reg);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    // ================= ADMIN : VIEW ALL =================
    @Override
    public List<EventRegistration> getAllRegistrations() {

        List<EventRegistration> list = new ArrayList<>();

        try {
            String sql = "SELECT * FROM eventregistration";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                EventRegistration reg = new EventRegistration();

                reg.setId(rs.getInt("id"));
                reg.setUsername(rs.getString("username"));
                reg.setFullName(rs.getString("full_name"));
                reg.setContact(rs.getString("contact"));
                reg.setDepartment(rs.getString("department"));
                reg.setEventName(rs.getString("event_name"));
                reg.setGender(rs.getString("gender"));

                list.add(reg);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    // ================= ADMIN : SEARCH =================
    @Override
    public List<EventRegistration> searchRegistrations(String fullName, String department) {

        List<EventRegistration> list = new ArrayList<>();

        try {
            String sql = "SELECT * FROM eventregistration "
                       + "WHERE full_name LIKE ? AND department LIKE ?";

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, "%" + fullName + "%");
            ps.setString(2, "%" + department + "%");

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                EventRegistration reg = new EventRegistration();

                reg.setId(rs.getInt("id"));
                reg.setUsername(rs.getString("username"));
                reg.setFullName(rs.getString("full_name"));
                reg.setContact(rs.getString("contact"));
                reg.setDepartment(rs.getString("department"));
                reg.setEventName(rs.getString("event_name"));
                reg.setGender(rs.getString("gender"));

                list.add(reg);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    // ================= EXTRA METHODS (OPTIONAL) =================

    public void deleteStudent(int id) {
        try {
            String sql = "DELETE FROM eventregistration WHERE id=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateStudent(int id, String fullName, String contact, String department) {
        try {
            String sql = "UPDATE eventregistration "
                       + "SET full_name=?, contact=?, department=? WHERE id=?";

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, fullName);
            ps.setString(2, contact);
            ps.setString(3, department);
            ps.setInt(4, id);

            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
