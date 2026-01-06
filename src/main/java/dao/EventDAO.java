package dao;

import java.util.*;
import model.Event;

public interface EventDAO {
    boolean addEvent(Event e);
    boolean deleteEvent(int id);
    boolean updateEvent(Event e);
    List<Event> getAllEvents();


    List<Event> getUpcomingEvents();
    
}
