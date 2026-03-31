
import java.time.*;
import java.time.format.DateTimeFormatter;

public class Booking {
    private String bookingStatus;
    private String bookingID;
    private static int numBookings = -1;
    private String createdAt;
    private User user;
    private Event event;

    private static final String PREFIX = "ID";
    private static final DateTimeFormatter ISO_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");

    //making new booking
    public Booking(User user, Event event) {
        this.user = user;
        this.event = event;

        if (event.getCapacity() >= 1) {
            this.bookingStatus = "Confirmed";
        } else {
            this.bookingStatus = "Waitlisted";
        }

        numBookings++;
        this.bookingID = PREFIX + numBookings;

        this.createdAt = LocalDateTime.now().format(ISO_FORMATTER);
    }

    //loading past booking info
    public Booking(String bookingID, User user, Event event, String createdAt, String status) {
        this.bookingID = bookingID;
        this.user = user;
        this.event = event;
        this.createdAt = createdAt;
        this.bookingStatus = status;

        updateCounterFromId(bookingID);
    }


    private void updateCounterFromId(String id) {
        try {
            int numericId = Integer.parseInt(id.substring(PREFIX.length()));
            if (numericId > numBookings) {
                numBookings = numericId;
            }
        } catch (Exception e) {
            // invalid id
        }
    }


    public String getBookingId() {
        return bookingID;
    }

    public User getUser() {
        return user;
    }

    public Event getEvent() {
        return event;
    }

    public String getStatus() {
        return bookingStatus;
    }

    public String getCreatedAt() {
        return createdAt;
    }


    public String getUserName() {
        return user.getname();
    }

    public String getEventTitle() {
        return event.getTitle();
    }

    public void setStatus(String status) {
        if (status.equals("Confirmed") || status.equals("Waitlisted") || status.equals("Cancelled")) {
            bookingStatus = status;
        }
    }

    public String getUserName() {
        return user.getname();
    }

    public String getEventTitle() {
        return event.getTitle();
    }

}
