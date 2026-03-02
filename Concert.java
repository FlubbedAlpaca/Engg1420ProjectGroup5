public class Concert extends Event {
    private String features;

    public Concert (String eventId, String title, String dateTime, String location, int capacity, String features) {
        super(eventId, title, dateTime, location, capacity);
        this.features = features;
    }

    public String getFeatures() {
        return features;
    }

    public void setFeatures(String features) {
        this.features = features;
    }
}