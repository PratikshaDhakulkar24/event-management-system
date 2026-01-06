package dao;

import java.sql.*;

public class AdminDAO {

    public static boolean validateAdmin(String id, String pass) {
        boolean status = false;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/campus_event_db",
                "root",
                "Pratiksha@24"
            );

            PreparedStatement ps = con.prepareStatement(
                "SELECT * FROM admin WHERE admin_id=? AND password=?"
            );

            ps.setString(1, id);
            ps.setString(2, pass);

            ResultSet rs = ps.executeQuery();
            status = rs.next();

            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return status;
    }
}
