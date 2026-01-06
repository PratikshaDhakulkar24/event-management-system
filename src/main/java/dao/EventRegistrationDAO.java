//package dao;
//
//import java.util.List;
//import model.EventRegistration;
//
//public interface EventRegistrationDAO {
//
//    void registerEvent(EventRegistration reg);
//
//    List<EventRegistration> getRegistrationsByUsername(String username);
//}
package dao;

import java.util.List;
import model.EventRegistration;

public interface EventRegistrationDAO {

    void registerEvent(EventRegistration reg);

    List<EventRegistration> getRegistrationsByUsername(String username);

    // 🔹 ADMIN METHODS
    List<EventRegistration> getAllRegistrations();

    List<EventRegistration> searchRegistrations(String fullName, String department);
}
