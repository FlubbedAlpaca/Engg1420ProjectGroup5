public class Staff extends User {
    public Staff(String name, String userID, String email) {
        super(name, userID, email);
    }

    @Override
    public int getLimit() {
        return 5;
    }

    @Override
    public String getType(){
        return "STAFF";
    }
}


