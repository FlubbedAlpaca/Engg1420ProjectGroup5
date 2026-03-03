public class UserFunctions {
    private userStorage storage;

    public UserFunctions() {
        this.storage = new userStorage();
    }
    public User createUser(String userId, String name, String email, String type){
        if(storage.exists(userId)){
            System.out.println("User Id already exists");
        }
        User user;
        switch (type) {
            case "STUDENT": user = new Student(userId, name, email); break;
            case "STAFF": user = new Staff(userId, name, email); break;
            case "GUEST": user = new Guest(userId, name, email); break;
            default: {
                user = null;
                System.out.println("Invalid User Type");
                break;
            }
        }
        storage.add(user);
        return user;
    }

    public User viewUser(String userId){
        User user;
        if(!storage.exists(userId)){
            System.out.println("No user with ID: " + userId);
            user = null;
        }else{
            user = storage.get(userId);
        }
        return user;
    }

    public User[] listAllUsers(){
        return storage.getAll();
    }
}
