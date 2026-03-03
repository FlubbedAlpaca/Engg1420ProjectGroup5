import java.util.ArrayList;

public class userStorage {
    private ArrayList<User> users;

    public userStorage(){
        users = new ArrayList<>();
    }

    public boolean exists(String userId){
        for(User u : users){
            if(u.getUserID().equals(userId)) {
                return true;
            }
        }
        return false;
    }

    public void add(User user){
        users.add(user);
    }

    public User get(String userId){
        for (User u : users){
            if(u.getUserID().equals(userId)){
                return u;
            }
        }
        return null;
    }

    public ArrayList<User> getUsers(){
        return users;
    }

    public int getSize(){
        return users.size();
    }
}
