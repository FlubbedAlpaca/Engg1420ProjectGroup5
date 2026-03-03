public class Guest extends User {
    public Guest(String name, String userID, String email) {
        super(name, userID, email);
    }

    @Override
    public  int getLimit(){
        return 1;
    }

    @Override
    public String getType(){
        return "GUEST";
    }

}
