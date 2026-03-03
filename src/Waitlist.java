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

    private final ArrayList<WaitlistEntry> waitlistEntries = new ArrayList<>();

    private PromotionListener promotionListener;

    public void setPromotionListener (PromotionListener listener) {
        this.promotionListener = Listener;
    }

    public List<String> viewEventWaitlist (String eventId) {
        List<WaitlistEntry> list = getWaitlistForEventOrdered(eventId);

        List<String> rows = new ArrayList<>();
        rows.add("=== WAITLIST FOR EVENT: " + eventId + "===");
        rows.add("Pos | WaitlistBookingId | Timestamp       | UserID     | Name     | Email");

        if (list.isEmpty()) {
            rows.add("(There are no waitlisted bookings for this event.)");
            return rows;
        }
        int pos = 1;
        for (WaitlistEntry e : list) {
            String line = String.format("%-3d | %-16s | %-19s | %-10s | %-14s| %s", pos, shortId(e.waitlistBookingId), e.timestamp, e.user.getUserID(), e.user.getname(), e.user.getemail());
            rows.add(line);
            pos++;
        }
        return rows;
    }

    public boolean removeWaitlistBooking(String waitlistBookingId) {
       for (int i = 0; i < waitlistEntries.size(); i++) {
           WaitlistEntry e = waitlistEntries.get(i);

           if (e.waitlistBookingId.equals(waitlistBookingId)) {
               e.booking.status = "CANCELLED";
               waitlistEntries.remove(i);
               return true;
           }
       }
       return false;
    }

    public PromotionNotification promoteNextFromWaitlist(Event event) {
        String eventId = event.getEventId();

        List<WaitlistEntry> list = getWaitlistForEventOrdered(eventId);
        if (list.isEmpty()) return null;

        WaitlistEntry promoted = list.get(0);

        promoted.booking.status = "CONFIRMED";
        waitlistEntries.remove(promoted);

        PromotionNotification note = new PromotionNotification (
                event.getEventId(), event.getTitle(), promoted.user.getUserID(), promoted.user.getname()
        );

        if (promotionListener != null) {
            promotionListener.onPromotion(note);
        }
        return note;
    }

    public String addToWaitlist(String eventId, User user, Booking booking) {
        booking.status = "WAITLISTED";

        WaitlistEntry entry = new waitlistEntry(eventId, user, booking);
        waitlistEntries.add(entry);

        waitlistEntries.sort(Comparator.comparing(e -> e.timestamp));

        return entry.waitlistBookingId;
    }



}

