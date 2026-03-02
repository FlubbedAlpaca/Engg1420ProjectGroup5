public class Concert extends Event {
    private String AgeRestriction;

    public Concert (String eventId, String title, String dateTime, String location, int capacity, String AgeRestriction) {
        super(eventId, title, dateTime, location, capacity);
        this.AgeRestriction = AgeRestriction;
    }

    public String getAgeRestriction() {
        return AgeRestriction;
    }

    public void setAgeRestriction(String AgeRestriction) {
        this.AgeRestriction = AgeRestriction;
    }
}