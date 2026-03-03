public class UserFunctions {
    public User createUser(String userId, String name, String email, String type){
        //if(user exists){
        //throw error: "UserId already exists: " + userId);
        //}
        User user;
        switch (type) {
            case "STUDENT": user = new Student(userId, name, email); break;
            case "STAFF": user = new Staff(userId, name, email); break;
            case "GUEST": user = new Guest(userId, name, email); break;
            default: {
                //throw error "Invalid user type"
            }
        }
        //add user to database
        return user;
    }

    public User viewUser(String userId){
        //get user based on id
        if(user == null){
            //throw error "No user with id: " + userId
        }
        return user;
    }

    public listAllUsers(){
        //return all users
    }
}
