import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;

public class Waitlist {
    private static class WaitlistEntry {
        private final String waitlistBookingId;
        private final String eventId;
        private final User user;
        private final Booking booking;
        private final LocalDateTime timestamp;

        private WaitlistEntry(String eventId, User user, Booking booking) {
            this.waitlistBookingId = UUID.randomUUID().toString();
            this.eventId = eventId;
            this.user = user;
            this.booking = booking;
            this.timestamp = LocalDateTime.now();
        }
    }

}

