import java.util.ArrayList;

public class EventManagement {

    private ArrayList<Event> events;
    private BookingManager bookingManager;
    private WaitlistManager waitlistManager;

    public EventManagement() {
        this.events = new ArrayList<>();
        this.bookingManager = null;
        this.waitlistManager = null;
    }

    public void setBookingManager(BookingManager bm) {
        this.bookingManager = bm;
    }

    public void setWaitlistManager(WaitlistManager wm) {
        this.waitlistManager = wm;
    }

    public Event getEvent(String eventId) {
        Event found = null;
        for (int i = 0; i < events.size(); i++) {
            Event e = events.get(i);
            if (e.getEventId().equals(eventId)) {
                found = e;
            }
        }
        return found;
    }

    public ArrayList<Event> getEvents() {
        return events;
    }

    public void createEvent(Event newEvent) {
        for (Event e : events) {
            if (e.getEventId().equals(newEvent.getEventId())) {
                System.out.println("Error: Event with ID " + newEvent.getEventId() + " already exists.");
                return;
            }
        }
        events.add(newEvent);
        System.out.println("Success: Event '" + newEvent.getTitle() + "' created.");
    }


    public void updateEvent(String eventId, String newTitle, String newDateTime, String newLocation, int newCapacity, String specificField) {
        for (Event e : events) {
            if (e.getEventId().equals(eventId)) {
                e.setTitle(newTitle);
                e.setDateTime(newDateTime);
                e.setLocation(newLocation);
                e.setCapacity(newCapacity);


                if (e instanceof Workshop) {
                    ((Workshop) e).setTopic(specificField);
                } else if (e instanceof Seminar) {
                    ((Seminar) e).setSpeakerName(specificField);
                } else if (e instanceof Concert) {
                    ((Concert) e).setAgeRestriction(specificField);
                }

                System.out.println("Success: Event " + eventId + " updated.");
                return;
            }
        }
        System.out.println("Error: Event not found.");
    }


    public void cancelEvent(String eventId) {
        for (Event e : events) {
            if (e.getEventId().equals(eventId)) {
                e.setStatus("Cancelled");

                if (bookingManager != null) {
                    bookingManager.cancelAllBookingsForEvent(eventId);
                }
                if (waitlistManager != null) {
                    waitlistManager.clearWaitlist(eventId);
                }

                System.out.println("Success: Event " + eventId + " has been cancelled.");

                return;
            }
        }
        System.out.println("Error: Event not found.");
    }


    public void listEvents() {
        System.out.println("--- All Events ---");
        for (Event e : events) {
            System.out.println("ID: " + e.getEventId() + " | Title: " + e.getTitle() +
                    " | Capacity: " + e.getCapacity() + " | Status: " + e.getStatus());
        }
    }


    public void searchAndFilterEvents(String titleQuery, String eventType) {
        System.out.println("--- Search Results ---");


        String lowerQuery = (titleQuery != null) ? titleQuery.toLowerCase() : "";

        for (Event e : events) {

            boolean titleMatches = e.getTitle().toLowerCase().contains(lowerQuery);


            boolean typeMatches = false;
            if (eventType == null || eventType.isEmpty() || eventType.equalsIgnoreCase("All")) {
                typeMatches = true; // No filter applied
            } else if (eventType.equalsIgnoreCase("Workshop") && e instanceof Workshop) {
                typeMatches = true;
            } else if (eventType.equalsIgnoreCase("Seminar") && e instanceof Seminar) {
                typeMatches = true;
            } else if (eventType.equalsIgnoreCase("Concert") && e instanceof Concert) {
                typeMatches = true;
            }


            if (titleMatches && typeMatches) {
                System.out.println("Found: [" + e.getEventId() + "] " + e.getTitle() + " (" + e.getClass().getSimpleName() + ")");
            }
        }
    }
}