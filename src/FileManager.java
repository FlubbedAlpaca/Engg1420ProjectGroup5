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
}