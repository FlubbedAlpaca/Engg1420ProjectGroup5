import java.util.ArrayList;

public class BookingManager {
    private ArrayList<Booking> bookings;
    private WaitlistManager waitlistManager;
    private int bookingCounter;

    public BookingManager(WaitlistManager waitlistManager) {
        this.bookings = new ArrayList<>();
        this.waitlistManager = waitlistManager;
        this.bookingCounter = 1;
    }

    public void createBooking(User user, Event event) {

        if (event.getStatus().equalsIgnoreCase("Cancelled")) {
            System.out.println("Error: Cannot book a cancelled event.");
            return;
        }

        if (hasUserBookedEvent(user, event)) {
            System.out.println("Error: User has already booked this event.");
            return;
        }

        int activeBookings = getActiveBookingCount(user);
        if (activeBookings >= user.getLimit()) {
            System.out.println("Error: User has reached their booking limit of " + user.getLimit() + ".");
            return;
        }

        int confirmedCount = getConfirmedBookingCount(event);
        if (confirmedCount >= event.getCapacity()) {
            System.out.println("Event is full. Adding to waitlist...");
            waitlistManager.addToWaitlist(event.getEventId(), user);

            String bookingId = "B" + String.format("%03d", bookingCounter++);
            Booking booking = new Booking(user, event);
            bookings.add(booking);
            return;
        }

        String bookingId = "B" + String.format("%03d", bookingCounter++);
        Booking booking = new Booking(user, event);
        bookings.add(booking);
        System.out.println("Success: Booking " + bookingId + " confirmed for '" + user.getname() +
                         "' at event '" + event.getTitle() + "'.");
    }

    public void cancelBooking(int bookingId) {
        Booking bookingToCancel = null;

        for (Booking b : bookings) {
            if (b.getBookingId() == (bookingId)) {
                bookingToCancel = b;
                break;
            }
        }

        if (bookingToCancel == null) {
            System.out.println("Error: Booking not found.");
            return;
        }

        Event event = bookingToCancel.getEvent();
        String previousStatus = bookingToCancel.getStatus();

        bookings.remove(bookingToCancel);
        System.out.println("Booking " + bookingId + " cancelled.");

        if (previousStatus.equals("Confirmed") && waitlistManager.hasWaitlist(event.getEventId())) {
            User promotedUser = waitlistManager.promoteFirstFromWaitlist(event.getEventId());
            if (promotedUser != null) {
                for (Booking b : bookings) {
                    if (b.getUser().getUserID().equals(promotedUser.getUserID())
                        && b.getEvent().getEventId().equals(event.getEventId())
                        && b.getStatus().equals("Waitlisted")) {
                        b.setStatus("Confirmed");
                        System.out.println("User '" + promotedUser.getname() +
                                         "' promoted from waitlist to confirmed!");
                        break;
                    }
                }
            }
        }
    }

    private boolean hasUserBookedEvent(User user, Event event) {
        for (Booking b : bookings) {
            if (b.getUser().getUserID().equals(user.getUserID())
                && b.getEvent().getEventId().equals(event.getEventId())) {
                return true;
            }
        }
        return false;
    }

    private int getActiveBookingCount(User user) {
        int count = 0;
        for (Booking b : bookings) {
            if (b.getUser().getUserID().equals(user.getUserID())
                && b.getStatus().equals("Confirmed")) {
                count++;
            }
        }
        return count;
    }

    private int getConfirmedBookingCount(Event event) {
        int count = 0;
        for (Booking b : bookings) {
            if (b.getEvent().getEventId().equals(event.getEventId())
                && b.getStatus().equals("Confirmed")) {
                count++;
            }
        }
        return count;
    }

    public ArrayList<Booking> getBookings() {
        return bookings;
    }

    public void listAllBookings() {
        if (bookings.isEmpty()) {
            System.out.println("No bookings found.");
            return;
        }
        System.out.println("--- All Bookings ---");
        for (Booking b : bookings) {
            System.out.println("ID: " + b.getBookingId() +
                             "  User: " + b.getUser().getname() +
                             "  Event: " + b.getEvent().getTitle() +
                             "  Status: " + b.getStatus());
        }
    }

    public void listUserBookings(User user) {
        System.out.println("--- Bookings for " + user.getname() + " ---");
        boolean found = false;
        for (Booking b : bookings) {
            if (b.getUser().getUserID().equals(user.getUserID())) {
                System.out.println("ID: " + b.getBookingId() +
                                 "  Event: " + b.getEvent().getTitle() +
                                 "  Status: " + b.getStatus());
                found = true;
            }
        }
        if (!found) {
            System.out.println("No bookings found.");
        }
    }

    public void cancelAllBookingsForEvent(String eventId) {
        for (Booking b : bookings) {
            if (b.getEvent().getEventId().equals(eventId)) {
                if (!b.getStatus().equals("Cancelled")) {
                    b.setStatus("Cancelled");
                }
            }
        }
        System.out.println("All bookings for event " + eventId + " have been cancelled.");
    }
}
