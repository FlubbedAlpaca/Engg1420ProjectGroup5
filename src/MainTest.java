import org.junit.jupiter.api.*;
import java.io.*;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    private ByteArrayInputStream testInput;
    private ByteArrayOutputStream testOutput;

    @BeforeEach
    void setUp() {
        // Capture console output
        testOutput = new ByteArrayOutputStream();
        System.setOut(new PrintStream(testOutput));
    }

    @AfterEach
    void tearDown() {
        System.setOut(System.out);
        System.setIn(System.in);
    }

    /**
     * Helper method to simulate user input
     */
    private void provideInput(String data) {
        testInput = new ByteArrayInputStream(data.getBytes());
        System.setIn(testInput);
    }

    /**
     * Test that the program starts and exits correctly (CLI mode)
     */
    @Test
    void testMainExitImmediately() {
        provideInput("5\n"); // choose "Exit"

        main.main(new String[]{"cli"});

        String output = testOutput.toString();
        assertTrue(output.contains("Campus Event System"));
        assertTrue(output.contains("Data Saved"));
    }

    /**
     * Test invalid input handling in main menu
     */
    @Test
    void testInvalidMenuChoice() {
        provideInput("99\n5\n");

        main.main(new String[]{"cli"});

        String output = testOutput.toString();
        assertTrue(output.contains("Invalid Choice"));
    }

    /**
     * Test navigation into User Management menu
     */
    @Test
    void testUserManagementMenuNavigation() {
        provideInput(
                "1\n" +  // enter User Management
                        "4\n" +  // back
                        "5\n"    // exit
        );

        main.main(new String[]{"cli"});

        String output = testOutput.toString();
        assertTrue(output.contains("User Management"));
    }

    /**
     * Test Event Management menu navigation
     */
    @Test
    void testEventManagementMenuNavigation() {
        provideInput(
                "2\n" +  // Event Management
                        "6\n" +  // back
                        "5\n"    // exit
        );

        main.main(new String[]{"cli"});

        String output = testOutput.toString();
        assertTrue(output.contains("Event Management"));
    }

    /**
     * Test Booking Management menu navigation
     */
    @Test
    void testBookingManagementMenuNavigation() {
        provideInput(
                "3\n" +  // Booking menu
                        "5\n" +  // back
                        "5\n"    // exit
        );

        main.main(new String[]{"cli"});

        String output = testOutput.toString();
        assertTrue(output.contains("Booking Management"));
    }

    /**
     * Test Waitlist menu navigation
     */
    @Test
    void testWaitlistMenuNavigation() {
        provideInput(
                "4\n" +  // Waitlist menu
                        "2\n" +  // back
                        "5\n"    // exit
        );

        main.main(new String[]{"cli"});

        String output = testOutput.toString();
        assertTrue(output.contains("Waitlist Management"));
    }

    @Test
    void testBookingUnderCapacity() {
        WaitlistManager waitlistManager = new WaitlistManager();
        BookingManager bookingManager = new BookingManager(waitlistManager);
        EventManagement eventManagement = new EventManagement();
        UserFunctions userFunctions = new UserFunctions();

        // Setup
        Event event = new Event("E1", "Test Event", "April 1 2026", "Test Land", 2);
        eventManagement.createEvent(event);

        User user = userFunctions.createUser("Alice", "U1", "alice@email.com", "STUDENT");

        // Act
        bookingManager.createBooking(user, event);

        // Assert
        assertEquals(1, bookingManager.getConfirmedBookingCount(event));
        assertFalse(waitlistManager.hasWaitlist("E1"));
    }

    @Test
    void testBookingWhenFullGoesToWaitlist() {
        WaitlistManager waitlistManager = new WaitlistManager();
        BookingManager bookingManager = new BookingManager(waitlistManager);
        EventManagement eventManagement = new EventManagement();
        UserFunctions userFunctions = new UserFunctions();

        Event event = new Event("E1", "Test Event", "April 1 2026", "Test Land", 1);
        eventManagement.createEvent(event);

        User user1 = userFunctions.createUser("Alice", "U1", "alice@email.com", "STUDENT");
        User user2 = userFunctions.createUser("Bob", "U2", "bob@email.com", "STAFF");

        // Fill capacity
        bookingManager.createBooking(user1, event);

        // This should go to waitlist
        bookingManager.createBooking(user2, event);

        assertEquals(1, bookingManager.getConfirmedBookingCount(event));
        assertTrue(waitlistManager.hasWaitlist("E1"));
    }

    @Test
    void testCancelBookingPromotesWaitlist() {
        WaitlistManager waitlistManager = new WaitlistManager();
        BookingManager bookingManager = new BookingManager(waitlistManager);
        EventManagement eventManagement = new EventManagement();
        UserFunctions userFunctions = new UserFunctions();

        Event event = new Event("E1", "Test Event", "April 1 2026", "Test Land", 1);
        eventManagement.createEvent(event);

        User user1 = userFunctions.createUser("Alice", "U1", "alice@email.com", "STUDENT");
        User user2 = userFunctions.createUser("Bob", "U2", "bob@email.com", "STAFF");

        // Fill booking
        bookingManager.createBooking(user1, event);

        // Add to waitlist
        bookingManager.createBooking(user2, event);

        // Cancel booking → should promote Bob
        bookingManager.cancelBooking(event.getEventId());

        // Assert
        assertEquals(1, bookingManager.getConfirmedBookingCount(event));
        assertTrue(bookingManager.hasUserBookedEvent(user1, event));
        assertFalse(waitlistManager.hasWaitlist("E1"));
    }

    @Test
    void testDuplicateBookingPrevention() {
        WaitlistManager waitlistManager = new WaitlistManager();
        BookingManager bookingManager = new BookingManager(waitlistManager);
        EventManagement eventManagement = new EventManagement();
        UserFunctions userFunctions = new UserFunctions();

        Event event = new Event("E1", "Test Event", "April 1 2026", "Test Land", 2);
        eventManagement.createEvent(event);
        User user = userFunctions.createUser("Alice", "U1", "alice@email.com", "STUDENT");

        bookingManager.createBooking(user, event);

        // Attempt duplicate
        bookingManager.createBooking(user, event);

        // Assert: still only 1 booking
        assertEquals(1, bookingManager.getConfirmedBookingCount(event));
    }
}