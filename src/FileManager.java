import java.io.*;
import java.util.ArrayList;

public class FileManager {

    // Helper method to find CSV files in src/ folder only
    private static File findCsvFile(String fileName) {
        File srcDir = new File("src" + File.separator + fileName);
        return srcDir;
    }

    public static void saveUsers(ArrayList<User> users) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(findCsvFile("users.csv")))) {
            writer.println("userId,name,email,userType");

            for (User u : users) {
                writer.println(u.getUserID() + "," + u.getname() + "," + u.getemail() + "," + u.getType());
            }
        } catch (IOException e) {
            System.out.println("Error saving users.");
        }
    }

    public static void saveEvents(ArrayList<Event> events) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(findCsvFile("events.csv")))) {

            writer.println("eventId,title,dateTime,location,capacity,status,eventType,topic,speakerName,ageRestriction");

            for (Event e : events) {

                String type = e.getClass().getSimpleName();

                String topic = "";
                String speaker = "";
                String age = "";

                switch (e) {
                    case Workshop workshop -> topic = workshop.getTopic();
                    case Seminar seminar -> speaker = seminar.getSpeakerName();
                    case Concert concert -> age = concert.getAgeRestriction();
                    default -> {
                    }
                }

                writer.println(e.getEventId() + "," + e.getTitle() + "," + e.getDateTime() + "," + e.getLocation() + "," + e.getCapacity() + "," + e.getStatus() + "," + type + "," + topic + "," + speaker + "," + age);
            }

        } catch (IOException e) {
            System.out.println("Error saving events.");
        }
    }

    public static void saveBookings(ArrayList<Booking> bookings) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(findCsvFile("bookings.csv")))) {

            writer.println("bookingId,userId,eventId,createdAt,bookingStatus");

            for (Booking b : bookings) {
                writer.println(b.getBookingId() + "," + b.getUser().getUserID() + "," + b.getEvent().getEventId() + "," + b.getCreatedAt() + "," + b.getStatus());
            }

        } catch (IOException e) {
            System.out.println("Error saving bookings.");
        }
    }

    public static void loadUsers(UserFunctions userFunctions) {
        try (BufferedReader br = new BufferedReader(new FileReader(findCsvFile("users.csv")))) {
            br.readLine(); //skip header
            String line;

            while ((line = br.readLine()) != null) {
                String[] data = line.split(",", -1);
                if (data.length < 4) {
                    continue;
                }

                String id = data[0].trim();
                String name = data[1].trim();
                String email = data[2].trim();
                String type = data[3].trim();

                userFunctions.createUser(id, name, email, type);
            }
            System.out.println("Users loaded successfully.");
        } catch (IOException e) {
            System.out.println("Error loading users.csv: " + e.getMessage());
        }
    }

    public static void loadEvents(EventManagement eventManagement) {
        try (BufferedReader br = new BufferedReader(new FileReader(findCsvFile("events.csv")))) {
            br.readLine(); //skip header
            String line;

            while ((line = br.readLine()) != null) {
                String[] data = line.split(",", -1);
                if (data.length < 10) {
                    continue;
                }

                String id = data[0].trim();
                String title = data[1].trim();
                String dateTime = data[2].trim();
                String location = data[3].trim();
                String capacity = data[4].trim();
                int cap = Integer.parseInt(capacity);
                String status = data[5].trim();
                String type = data[6].trim();
                String workshop = data[7].trim();
                String seminar = data[8].trim();
                String concert = data[9].trim();

                Event event;
                switch (type) {
                    case "Workshop":
                        event = new Workshop(id, title, dateTime, location, cap, workshop);
                        event.setStatus(status);
                        eventManagement.createEvent(event);
                        break;
                    case "Seminar":
                        event = new Seminar(id, title, dateTime, location, cap, seminar);
                        event.setStatus(status);
                        eventManagement.createEvent(event);
                        break;
                    case "Concert":
                        event = new Concert(id, title, dateTime, location, cap, concert);
                        event.setStatus(status);
                        eventManagement.createEvent(event);
                        break;
                    default:
                        System.out.println("Skipping unknown event type: " + type);
                }
            }
            System.out.println("Events loaded successfully.");
        } catch (IOException e) {
            System.out.println("Error loading events.csv: " + e.getMessage());
        }
    }

    public static void loadBookings(BookingManager bookingManager, UserFunctions userFunctions, EventManagement eventManagement) {
        try (BufferedReader br = new BufferedReader(new FileReader(findCsvFile("bookings.csv")))) {
            br.readLine(); //skip header
            String line;

            while ((line = br.readLine()) != null) {
                String[] data = line.split(",", -1);
                if (data.length < 5) {
                    continue;
                }

                String bookingId = data[0].trim();
                String userId = data[1].trim();
                String eventId = data[2].trim();
                String createdAt = data[3].trim();
                String bookingStatus = data[4].trim();

                User user = userFunctions.getUser(userId);
                Event event = eventManagement.getEvent(eventId);

                if (user == null || event == null) {
                    System.out.println("Skipping booking " + bookingId + " - user or event not found.");
                    continue;
                }

                // Create booking with persisted data
                Booking booking = new Booking(bookingId, user, event, createdAt, bookingStatus);
                bookingManager.addLoadedBooking(booking);
            }
            System.out.println("Bookings loaded successfully.");
        } catch (IOException e) {
            System.out.println("Error loading bookings.csv: " + e.getMessage());
        }
    }
    }