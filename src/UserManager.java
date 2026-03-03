import java.util.ArrayList;

public class UserManager {
    private ArrayList<User> users;

    public UserManager() {
        users = new ArrayList<>();
    }

    // Add a new user
    public void addUser(User user) {
        // Check if user already exists
        for (User u : users) {
            if (u.getUserID().equals(user.getUserID())) {
                System.out.println("Error: User with ID " + user.getUserID() + " already exists.");
                return;
            }
        }
        users.add(user);
        System.out.println("Success: User '" + user.getname() + "' added.");
    }

    // View user details by ID
    public void viewUserDetails(String userID) {
        for (User u : users) {
            if (u.getUserID().equals(userID)) {
                System.out.println("--- User Details ---");
                System.out.println("Name: " + u.getname());
                System.out.println("ID: " + u.getUserID());
                System.out.println("Email: " + u.getemail());
                System.out.println("Booking Limit: " + u.getLimit());
                System.out.println("Type: " + u.getClass().getSimpleName());
                return;
            }
        }
        System.out.println("Error: User with ID " + userID + " not found.");
    }

    // Get user by ID
    public User getUser(String userID) {
        for (User u : users) {
            if (u.getUserID().equals(userID)) {
                return u;
            }
        }
        return null;
    }

    // List all registered users
    public void listAllUsers() {
        if (users.isEmpty()) {
            System.out.println("No users registered.");
            return;
        }
        System.out.println("--- All Registered Users ---");
        for (User u : users) {
            System.out.println("ID: " + u.getUserID() + "  Name: " + u.getname() +
                             "  Type: " + u.getClass().getSimpleName() +
                             "  Limit: " + u.getLimit());
        }
    }

    public ArrayList<User> getUsers() {
        return users;
    }
}

