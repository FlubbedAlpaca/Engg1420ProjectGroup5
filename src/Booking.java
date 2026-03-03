import java.time.*;
import java.time.format.DateTimeFormatter;

public class Booking {
    public String bookingStatus;
    private int bookingID;
    private static int numBookings = 0;
    private String createdAt;
    private String userID;
    private String eventID;
    public Booking(User user, Event event){
        if (event.getCapacity() >= 1){
            bookingStatus = "Confirmed";

            bookingID = numBookings + 1;
            numBookings++;

            userID = user.getUserID();
            eventID = event.getEventId();
            LocalDateTime current_date = LocalDateTime.now();
            DateTimeFormatter date_format = DateTimeFormatter.ofPattern("dd-MM-yyy HH:mm");
            createdAt = current_date.format(date_format);
        }
    }
}