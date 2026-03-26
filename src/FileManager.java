import java.io.*;
import java.util.ArrayList;

public class FileManager {

    public static void saveUsers(ArrayList<User> users) {
        try (PrintWriter writer = new PrintWriter(new FileWriter("users.csv"))) {
            writer.println("userId,name,email,userType");

            for (User u : users) {
                writer.println(u.getUserID() + "," + u.getname() + "," + u.getemail() + "," + u.getType());
            }
        } catch (IOException e) {
            System.out.println("Error saving users.");
        }
    }

    public static void saveEvents(ArrayList<Event> events) {
        try (PrintWriter writer = new PrintWriter(new FileWriter("events.csv"))) {

            writer.println("eventId,title,dateTime,location,capacity,status,eventType,topic,speakerName,ageRestriction");

            for (Event e : events) {

                String type = e.getClass().getSimpleName();

                String topic = "";
                String speaker = "";
                String age = "";

                if (e instanceof Workshop) {
                    topic = ((Workshop) e).getTopic();
                } else if (e instanceof Seminar) {
                    speaker = ((Seminar) e).getSpeakerName();
                } else if (e instanceof Concert) {
                    age = ((Concert) e).getAgeRestriction();
                }

                writer.println(e.getEventId() + "," + e.getTitle() + "," + e.getDateTime() + "," + e.getLocation() + "," + e.getCapacity() + "," + e.getStatus() + "," + type + "," + topic + "," + speaker + "," + age);
            }

        } catch (IOException e) {
            System.out.println("Error saving events.");
        }
    }

    public static void saveBookings(ArrayList<Booking> bookings) {
        try (PrintWriter writer = new PrintWriter(new FileWriter("bookings.csv"))) {

            writer.println("bookingId,userId,eventId,createdAt,bookingStatus");

            for (Booking b : bookings) {
                writer.println(b.getBookingId() + "," + b.getUser().getUserID() + "," + b.getEvent().getEventId() + "," + b.getCreatedAt() + "," + b.getStatus());
            }

        } catch (IOException e) {
            System.out.println("Error saving bookings.");
        }
    }

    public static void loadUsers(UserFunctions userFunctions) {
        try (BufferedReader br = new BufferedReader(new FileReader("users.csv"))) {
            br.readLine(); //skip header
            String line;

            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");

                String id = data[0];
                String name = data[1];
                String email = data[2];
                String type = data[3];

                userFunctions.createUser(id, name, email, type);
            }
        } catch (IOException e) {
            System.out.println("No users file found.");
        }
    }

    public static void loadEvents(EventManagement eventManagement) {
        try (BufferedReader br = new BufferedReader(new FileReader("events.csv"))) {
            br.readLine(); //skip header
            String line;

            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");

                String id = data[0];
                String title = data[1];
                String dateTime = data[2];
                String location = data[3];
                String capacity = data[4];
                int cap = Integer.parseInt(capacity);
                String status = data[5];
                String type = data[6];
                String workshop = data[7];
                String seminar = data[8];
                String concert = data[9];

                Event event;
                switch (type) {
                    case ("Workshop"):
                        event = new Workshop(id, title, dateTime, location, cap, workshop);
                        event.setStatus(status);
                        eventManagement.createEvent(event);
                        break;
                    case ("Seminar"):
                        event = new Seminar(id, title, dateTime, location, cap, seminar);
                        event.setStatus(status);
                        eventManagement.createEvent(event);
                        break;
                    case ("Concert"):
                        event = new Concert(id, title, dateTime, location, cap, concert);
                        event.setStatus(status);
                        eventManagement.createEvent(event);
                        break;
                    default:
                        System.out.println("Invalid event type in data loading.");
                }


            }
        } catch (IOException e) {
            System.out.println("Error loading events from file");
        }
    }

    public static void loadBookings(BookingManager bookingManager, UserFunctions userFunctions, EventManagement eventManagement){
        try (BufferedReader br = new BufferedReader(new FileReader("bookings.csv"))) {
            br.readLine(); //skip header
            String line;

            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");

                String userId = data[1];
                String eventId = data[2];

                User user = userFunctions.getUser(userId);
                Event event = eventManagement.getEvent(eventId);

                bookingManager.createBooking(user, event);
            }
        }
        catch (IOException e){
            System.out.println("No booking file found.");
        }
        }
    }