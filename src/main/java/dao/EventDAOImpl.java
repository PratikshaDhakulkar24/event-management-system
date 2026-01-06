package dao;

import java.sql.*;
import java.util.*;
import model.Event;

public class EventDAOImpl implements EventDAO {

    private Connection con;

    // ================= DB CONNECTION =================
    public EventDAOImpl() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/campus_event_db?useSSL=false&serverTimezone=UTC",
                "root",
                "Pratiksha@24"
            );
            System.out.println("DB Connected Successfully");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ================= ADD EVENT =================
    @Override
    public boolean addEvent(Event e) {

        if (e.getDate() == null || e.getDate().trim().isEmpty()) {
            System.out.println("Date is null or empty");
            return false;
        }

        try {
            PreparedStatement ps = con.prepareStatement(
                "INSERT INTO event (event_name, event_date, venue, capacity) VALUES (?,?,?,?)"
            );

            ps.setString(1, e.getName());
            ps.setDate(2, java.sql.Date.valueOf(e.getDate())); // FIXED
            ps.setString(3, e.getVenue());
            ps.setInt(4, e.getCapacity());

            return ps.executeUpdate() > 0;

        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    // ================= DELETE EVENT =================
    @Override
    public boolean deleteEvent(int id) {
        try {
            PreparedStatement ps =
                con.prepareStatement("DELETE FROM event WHERE event_id=?");
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // ================= UPDATE EVENT =================
    @Override
    public boolean updateEvent(Event e) {

        if (e.getDate() == null || e.getDate().trim().isEmpty()) {
            System.out.println("Update date is null");
            return false;
        }

        try {
            PreparedStatement ps = con.prepareStatement(
                "UPDATE event SET event_name=?, event_date=?, venue=?, capacity=? WHERE event_id=?"
            );

            ps.setString(1, e.getName());
            ps.setDate(2, java.sql.Date.valueOf(e.getDate())); // FIXED
            ps.setString(3, e.getVenue());
            ps.setInt(4, e.getCapacity());
            ps.setInt(5, e.getId());

            return ps.executeUpdate() > 0;

        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    // ================= VIEW ALL EVENTS =================
    @Override
    public List<Event> getAllEvents() {

        List<Event> list = new ArrayList<>();

        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM event");

            while (rs.next()) {
                Event e = new Event();
                e.setId(rs.getInt("event_id"));
                e.setName(rs.getString("event_name"));
                e.setDate(rs.getString("event_date"));
                e.setVenue(rs.getString("venue"));
                e.setCapacity(rs.getInt("capacity"));
                list.add(e);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    // ================= UPCOMING EVENTS =================
    @Override
    public List<Event> getUpcomingEvents() {

        List<Event> list = new ArrayList<>();

        try {
            PreparedStatement ps = con.prepareStatement(
                "SELECT * FROM event WHERE event_date >= CURDATE()"
            );
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Event e = new Event();
                e.setId(rs.getInt("event_id"));
                e.setName(rs.getString("event_name"));
                e.setDate(rs.getString("event_date"));
                e.setVenue(rs.getString("venue"));
                e.setCapacity(rs.getInt("capacity"));
                list.add(e);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
}
