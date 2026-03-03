public class Student extends User {

    public Student(String name, String userID, String email) {
        super(name, userID, email);
    }

    @Override
    public int getLimit(){
        return 3;
    }

    @Override
    public String getType(){
        return "STUDENT";
    }
}

