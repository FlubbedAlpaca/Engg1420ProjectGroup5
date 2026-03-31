import java.util.ArrayList;
import java.util.Scanner;

public class main {
    private static Scanner scanner;
    private static EventManagement eventManagement;
    private static BookingManager bookingManager;
    private static UserFunctions userFunctions;
    private static WaitlistManager waitlistManager;

    public static void main(String[] args){
        waitlistManager = new WaitlistManager();
        bookingManager = new BookingManager(waitlistManager);
        eventManagement = new EventManagement();
        userFunctions = new UserFunctions();
        scanner = new Scanner(System.in);
        MainManager manager  = new MainManager();
        UserFunctions userFunctions = new UserFunctions();
        FileManager.loadUsers(userFunctions);
        FileManager.loadEvents(eventManagement);
        FileManager.loadBookings(bookingManager, userFunctions, eventManagement);
        boolean finished = false;

        System.out.println("Campus Event System");

        while(!finished){
            System.out.println("Main Menu");
            System.out.println("1. User Management");
            System.out.println("2. Event Management");
            System.out.println("3. Booking Management");
            System.out.println("4. Waitlist Management");
            System.out.println("5. Exit");
            System.out.println("Choose an option: ");
            int choice = Integer.parseInt(scanner.nextLine());
            switch(choice){
                case 1:
                    boolean back = false;
                    while(!back){
                        System.out.println("User Management");
                        System.out.println("1. Add User");
                        System.out.println("2. View User Details");
                        System.out.println("3. List All Users");
                        System.out.println("4. Back");
                        System.out.println("Choose an option: ");
                        choice = Integer.parseInt(scanner.nextLine());
                        switch(choice){
                            case 1:
                                manager.addUser();
                                break;
                            case 2:
                                manager.viewUser();
                                break;
                            case 3:
                                userFunctions.listAllUsers();
                                break;
                            case 4:
                                back = true;
                                break;
                            default:
                                System.out.println("Invalid choice, try again.");
                        }
                    }
                    break;
                case 2:
                    back = false;

                    while(!back){
                        System.out.println("Event Management");
                        System.out.println("1. Create Event");
                        System.out.println("2. Update Event");
                        System.out.println("3. Cancel Event");
                        System.out.println("4. List Events");
                        System.out.println("5. Search/Filter Events");
                        System.out.println("6. Back");
                        System.out.println("Choose an option: ");
                        choice = Integer.parseInt(scanner.nextLine());

                        switch(choice){
                            case 1:
                                manager.createEvent();
                                break;
                            case 2:
                                manager.updateEvent();
                                break;
                            case 3:
                                manager.cancelEvent();
                                break;
                            case 4:
                                manager.listEvent();
                                break;
                            case 5:
                                manager.searchEvent();
                                break;
                            case 6:
                                back = true;
                                break;
                            default:
                                System.out.println("Invalid choice, try again.");
                        }

                    }
                    break;
                case 3:
                    back = false;

                    while (!back) {
                        System.out.println("Booking Management");
                        System.out.println("1. Create Booking");
                        System.out.println("2. Cancel Booking");
                        System.out.println("3. View User Bookings");
                        System.out.println("4. List All Bookings");
                        System.out.println("5. Back");
                        System.out.println("Choose an option: ");
                        choice = Integer.parseInt(scanner.nextLine());
                        switch (choice) {
                            case 1:
                                manager.createBooking();
                                break;
                            case 2:
                                manager.cancelBooking();
                                break;
                            case 3:
                                manager.viewUserBookings();
                                break;
                            case 4:
                                manager.listAllBookings();
                                break;
                            case 5:
                                back = true;
                                break;
                            default:
                                System.out.println("Invalid choice, try again");
                        }
                    }
                    break;
                case 4:
                    back = false;
                    while(!back){
                        System.out.println("Waitlist Management");
                        System.out.println("1. View Waitlist for Event");
                        System.out.println("2. Back");
                        System.out.println("Choose an option: ");
                        choice = Integer.parseInt(scanner.nextLine());
                        switch(choice){
                            case 1:
                                manager.viewWaitList();
                                break;
                            case 2:
                                back = true;
                                break;
                            default:
                                System.out.println("Invalid choice, try again");
                        }

                    }
                    break;
                case 5:
                    finished = true;
                    FileManager.saveUsers(userFunctions.getUsers());
                    FileManager.saveEvents(eventManagement.getEvents());
                    FileManager.saveBookings(bookingManager.getBookings());
                    System.out.println("Data Saved. See you next time.");
                    break;
                default:
                    System.out.println("Invalid Choice Try Again.");
            }

        }
    }
}