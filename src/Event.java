public class Event {

    private String eventId;
    private String title;
    private String datetime;
    private String location;
    private int capacity;
    private String status; // active / cancelled

    public Event(String eventId, String title, String dateTime, String location, int capacity) {
        this.eventId = eventId;
        this.title = title;
        this.datetime = dateTime;
        this.location = location;

        if (capacity > 0) {
            this.capacity = capacity;
        } else {
            System.out.println("Capacity must be greater than 0");
            this.capacity = 1;
        }
        this.status = "Active";
    }

    public String getEventId() {
        return eventId;
    }
    public String getTitle() {
        return title;
    }
    public String getDateTime() {
        return datetime;
    }
    public void setDateTime(String datetime) {
        this.datetime = datetime;
    }
    public String getLocation() {
        return location;
    }
    public void setLocation(String location) {
        this.location = location;
    }

    public int getCapacity() {
        return capacity;
    }
    public void setCapacity(int capacity) {
        if (capacity > 0) {
            this.capacity = capacity;
        } else {
            System.out.println("Capacity must be greater than 0");
        }
    }
    public String getStatus() {
        return status;
    }

    public void setStatus (String status) {
        this.status = status;
    }

    public void setTitle(String title) {
        this.title = title;
    }


}
