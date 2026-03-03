import java.util.ArrayList;
import java.util.Scanner;

public class main {
    private static EventManagement eventManagement;
    private static ArrayList<User> users;

    public static void main(String[] args) {
        eventManagement = new EventManagement();
        users = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        System.out.println("=== Event Booking System ===\n");

        while (running) {
            System.out.println("\n1. Add User");
            System.out.println("2. Create Event");
            System.out.println("3. Update Event");
            System.out.println("4. Cancel Event");
            System.out.println("5. View Users");
            System.out.println("6. List Events");
            System.out.println("7. Search Events");
            System.out.println("8. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    addUser(scanner);
                    break;
                case 2:
                    createEvent(scanner);
                    break;
                case 3:
                    updateEvent(scanner);
                    break;
                case 4:
                    cancelEvent(scanner);
                    break;
                case 5:
                    viewUsers();
                    break;
                case 6:
                    eventManagement.listEvents();
                    break;
                case 7:
                    searchEvents(scanner);
                    break;
                case 8:
                    running = false;
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid option!");
            }
        }

        scanner.close();
    }

    private static void addUser(Scanner scanner) {
        System.out.print("Enter User ID: ");
        String id = scanner.nextLine();
        System.out.print("Enter Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Email: ");
        String email = scanner.nextLine();
        System.out.print("Enter Type (Student/Staff/Guest): ");
        String type = scanner.nextLine();

        User user = null;
        if (type.equalsIgnoreCase("Student")) {
            user = new Student(id, name, email);
        } else if (type.equalsIgnoreCase("Staff")) {
            user = new Staff(id, name, email);
        } else if (type.equalsIgnoreCase("Guest")) {
            user = new Guest(id, name, email);
        }

        if (user != null) {
            users.add(user);
            System.out.println("User added successfully!");
        }
    }

    private static void createEvent(Scanner scanner) {
        System.out.print("Enter Event ID: ");
        String id = scanner.nextLine();
        System.out.print("Enter Title: ");
        String title = scanner.nextLine();
        System.out.print("Enter Date/Time: ");
        String dateTime = scanner.nextLine();
        System.out.print("Enter Location: ");
        String location = scanner.nextLine();
        System.out.print("Enter Capacity: ");
        int capacity = scanner.nextInt();
        scanner.nextLine();

        Event event = new Event(id, title, dateTime, location, capacity);
        eventManagement.createEvent(event);
    }

    private static void updateEvent(Scanner scanner) {
        System.out.print("Enter Event ID to update: ");
        String eventId = scanner.nextLine();
        System.out.print("Enter new title: ");
        String newTitle = scanner.nextLine();
        System.out.print("Enter new date/time: ");
        String newDateTime = scanner.nextLine();
        System.out.print("Enter new location: ");
        String newLocation = scanner.nextLine();
        System.out.print("Enter new capacity: ");
        int newCapacity = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter specific field value (or leave blank): ");
        String specificField = scanner.nextLine();

        eventManagement.updateEvent(eventId, newTitle, newDateTime, newLocation, newCapacity, specificField);
    }

    private static void cancelEvent(Scanner scanner) {
        System.out.print("Enter Event ID to cancel: ");
        String eventId = scanner.nextLine();
        eventManagement.cancelEvent(eventId);
    }

    private static void viewUsers() {
        if (users.isEmpty()) {
            System.out.println("\n--- No Users ---");
            return;
        }
        System.out.println("\n--- Users ---");
        for (User user : users) {
            System.out.println(user);
        }
    }

    private static void searchEvents(Scanner scanner) {
        System.out.print("Enter title query (or leave blank): ");
        String titleQuery = scanner.nextLine();
        System.out.print("Enter event type (Workshop/Seminar/Concert/All): ");
        String eventType = scanner.nextLine();

        eventManagement.searchAndFilterEvents(titleQuery, eventType);
    }
}
