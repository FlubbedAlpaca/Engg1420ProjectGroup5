import java.util.ArrayList;
import java.util.Scanner;

public class main {
    private static Scanner scanner;
    public static void main(String[] args){
        scanner = new Scanner(System.in);
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
                                //addUser
                                break;
                            case 2:
                                //viewUser
                                break;
                            case 3:
                                //listallUsers
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
                                //createEvent
                                break;
                            case 2:
                                //updateEvent
                                break;
                            case 3:
                                //cancelEvent
                                break;
                            case 4:
                                //listEvent
                                break;
                            case 5:
                                //searchEvent
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
                                //createBooking
                                break;
                            case 2:
                                //cancelBooking
                                break;
                            case 3:
                                //viewUserBookings
                                break;
                            case 4:
                                //listAllBookings
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
                                //viewWailtList
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
                    //Save info
                    finished = true;
                    System.out.println("Data Saved. See you next time.");
                    break;
                default:
                    System.out.println("Invalid Choice Try Again.");
            }

        }
    }
}