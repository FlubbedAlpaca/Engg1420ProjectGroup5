import java.time.*;
import java.time.format.DateTimeFormatter;

public class Booking {
    public String bookingStatus;
    private int bookingID;
    private static int numBookings = 0;
    private String createdAt;
    private User user;
    private Event event;
    public Booking(User user, Event event){
        if (event.getCapacity() >= 1){
            bookingStatus = "Confirmed";
        } else {
            bookingStatus = "Waitlisted";
        }

        bookingID = numBookings + 1;
        numBookings++;

        this.user = user;
        this.event = event;
        LocalDateTime current_date = LocalDateTime.now();
        DateTimeFormatter date_format = DateTimeFormatter.ofPattern("dd-MM-yyy HH:mm");
        createdAt = current_date.format(date_format);
    }

    public int getBookingId(){return bookingID;}

    public User getUser(){return user;}

    public Event getEvent(){return event;}

    public String getStatus(){return bookingStatus;}

    public String setStatus(String status){
        if (status.equals("Confirmed") || status.equals("Waitlisted") || status.equals("Cancelled")) {
            bookingStatus = status;
        }
    }
}