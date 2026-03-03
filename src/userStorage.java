

public class userStorage {
    private User[] users;
    private int size;

    public userStorage(){
        users = new User[10];
        size = 0;
    }

    public boolean exists(String userId){
        for(int i = 0; i < size; i++){
            if(users[i].getUserId().equals(userId)) {
                return true;
            }
        }
        return false;
    }

    public void add(User user){
        if(size == users.length){
            User[] newArray = new User[users.length * 2];
            for(int i = 0; i < users.length; i++) {
                newArray[i] = users[i];
            }
            users = newArray;
        }
        users[size] = user;
        size++;
    }

    public User get(String userId){
        for (int i = 0; i < size; i++){
            if(users[i].getUserId().equals(userId)){
                return users[i];
            }
        }
        return null;
    }

    public User[] getAll(){
        return users;
    }


}
