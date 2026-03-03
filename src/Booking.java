public class Booking {
    private String bookingId;
    private User user;
    private Event event;
    public String status;

    public Booking(String bookingId, User user, Event event, String status) {
        this.bookingId = bookingId;
        this.user = user;
        this.event = event;
        this.status = status;
    }

    public String getBookingId() {
        return bookingId;
    }

    public User getUser() {
        return user;
    }

    public Event getEvent() {
        return event;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}