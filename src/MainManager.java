import java.awt.print.Book;
import java.util.Scanner;

public class MainManager {
    private static UserFunctions userFunctions;
    private static EventManagement eventManagement;
    private static Scanner scanner;
    private static BookingManager bookingManager;
    private static WaitlistManager waitlistManager;
    public MainManager(UserFunctions userFunctions, EventManagement eventManagement, Scanner scanner, BookingManager bookingManager, WaitlistManager waitlistManager) {
        this.userFunctions = userFunctions;
        this.eventManagement = eventManagement;
        this.scanner = scanner;
        this.bookingManager = bookingManager;
        this.waitlistManager = waitlistManager;
    }

    void addUser(){
        System.out.print("Enter User ID: ");
        String userId = scanner.nextLine();

        System.out.print("Enter Name: ");
        String name = scanner.nextLine();

        System.out.print("Enter Email: ");
        String email = scanner.nextLine();

        System.out.print("Enter Type (Student/Staff/Guest): ");
        String type = scanner.nextLine();

        userFunctions.createUser(userId, name, email, type);
    }

    void viewUser(){
        System.out.print("Enter User ID: ");
        String userId = scanner.nextLine();
        userFunctions.viewUserDetails(userId);
    }

    void createEvent(){
        System.out.print("Enter Event ID: ");
        String eventId = scanner.nextLine();

        System.out.print("Enter Title: ");
        String title = scanner.nextLine();

        System.out.print("Enter Date/Time: ");
        String dateTime = scanner.nextLine();

        System.out.print("Enter Location: ");
        String location = scanner.nextLine();

        System.out.println("Enter Capacity: ");
        int capacity = Integer.parseInt(scanner.nextLine());

        System.out.print("Enter Event Type (Workshop/Seminar/Concert): ");
        String type = scanner.nextLine();

        Event event;

        switch(type){
            case "Workshop":
                System.out.print("Enter Topic: ");
                String topic = scanner.nextLine();
                event = new Workshop(eventId, title, dateTime, location, capacity, topic);
                eventManagement.createEvent(event);
                break;
            case "Seminar":
                System.out.print("Enter Speaker Name: ");
                String speakerName = scanner.nextLine();
                event = new Seminar(eventId, title, dateTime, location, capacity, speakerName);
                eventManagement.createEvent(event);
                break;
            case "Concert":
                System.out.print("Enter Age Restriction: ");
                String ageRestriction = scanner.nextLine();
                event = new Concert(eventId, title, dateTime, location, capacity, ageRestriction);
                eventManagement.createEvent(event);
                break;
            default:
                System.out.println("Invalid event type.");
        }
    }

    // Broken, need to add prompt to get event
    /*void updateEvent(){
        int newCapacity = Integer.parseInt(scanner.nextLine());

        Event event = eventManagement.getEvent(eventId);
        if (event == null) {
            System.out.println("Invalid event ID");
            return;
        }

        String specificField;
        if (event instanceof Workshop) {
            System.out.print("Enter new Topic: ");
            specificField = scanner.nextLine();
        } else if (event instanceof Seminar) {
            System.out.print("Enter new Speaker Name: ");
            specificField = scanner.nextLine();
        } else if (event instanceof Concert) {
            System.out.print("Enter new Age Restriction: ");
            specificField = scanner.nextLine();
        }else{
            System.out.println("Invalid event type.");
            return;
        }
        eventManagement.updateEvent(eventId, newTitle, newDateTime, newLocation, newCapacity, specificField);
    }*/

    void cancelEvent(){
        System.out.print("Enter Event ID to cancel: ");
        String eventId = scanner.nextLine();
        eventManagement.cancelEvent(eventId);
    }
    void listEvent(){
        eventManagement.listEvents();
    }
    void searchEvent(){
        System.out.print("Enter title to search for (or leave blank): ");
        String titleSearch = scanner.nextLine();
        System.out.print("Enter event type (Workshop/Seminar/Concert/All): ");
        String eventType = scanner.nextLine();
        eventManagement.searchAndFilterEvents(titleSearch, eventType);
    }
    void createBooking(){
        System.out.print("Enter User ID: ");
        String userId = scanner.nextLine();

        System.out.print("Enter Event ID: ");
        String eventId = scanner.nextLine();

        User user = userFunctions.getUser(userId);
        Event event = eventManagement.getEvent(eventId);

        if (user == null) {
            System.out.println("Error: User not found.");
            return;
        }

        if (event == null) {
            System.out.println("Error: Event not found.");
            return;
        }

        bookingManager.createBooking(user, event);

    }
    void cancelBooking(){
        System.out.println("Enter Booking ID to cancel: ");
        String bookingId = scanner.nextLine().trim();
        bookingManager.cancelBooking(bookingId);
    }
    void viewUserBookings(){
        System.out.print("Enter User ID: ");
        String userId = scanner.nextLine();

        User user = userFunctions.getUser(userId);
        if (user == null) {
            System.out.println("Error: User not found.");
            return;
        }

        bookingManager.listUserBookings(user);
    }
    void listAllBookings(){
        bookingManager.listAllBookings();
    }
    void viewWaitList(){
        System.out.print("Enter Event ID: ");
        String eventId = scanner.nextLine();
        waitlistManager.viewWaitlist(eventId);
    }
}
