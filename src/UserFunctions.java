import java.util.ArrayList;

public class UserFunctions {
    userStorage storage = new userStorage();

    public User createUser(String userId, String name, String email, String type){
        if(storage.exists(userId)){
            System.out.println("Error: User with ID " + userId + " already exists.");
            return null;
        }
        User user;
        switch (type.toUpperCase()) {
            case "STUDENT": user = new Student(name, userId, email); break;
            case "STAFF": user = new Staff(name, userId, email); break;
            case "GUEST": user = new Guest(name, userId, email); break;
            default: {
                System.out.println("Error: Invalid User Type");
                return null;
            }
        }
        storage.add(user);
        System.out.println("Success: User '" + user.getname() + "' added.");
        return user;
    }

    public void addUser(User user) {
        if(storage.exists(user.getUserID())){
            System.out.println("Error: User with ID " + user.getUserID() + " already exists.");
            return;
        }
        storage.add(user);
        System.out.println("Success: User '" + user.getname() + "' added.");
    }

    public User getUser(String userId){
        if(!storage.exists(userId)){
            return null;
        }
        return storage.get(userId);
    }

    public void viewUserDetails(String userId){
        if(!storage.exists(userId)){
            System.out.println("Error: User with ID " + userId + " not found.");
            return;
        }
        User u = storage.get(userId);
        System.out.println("--- User Details ---");
        System.out.println("Name: " + u.getname());
        System.out.println("ID: " + u.getUserID());
        System.out.println("Email: " + u.getemail());
        System.out.println("Booking Limit: " + u.getLimit());
        System.out.println("Type: " + u.getClass().getSimpleName());
    }

    public void listAllUsers(){
        ArrayList<User> users = storage.getUsers();
        if(users.isEmpty()){
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

    public ArrayList<User> getUsers(){
        return storage.getUsers();
    }
}
