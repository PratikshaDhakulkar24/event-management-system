package model;

public class Event {

    private int id;
    private String name;
    private String date;
    private String venue;
    private int capacity;

    
    public Event() {
    }

    
    public Event(String name, String date, String venue, int capacity) {
        this.name = name;
        this.date = date;
        this.venue = venue;
        this.capacity = capacity;
    }

    // getters & setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
}
