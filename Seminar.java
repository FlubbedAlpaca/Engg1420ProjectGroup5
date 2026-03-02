public class Seminar extends Event {
    private String SpeakerName;

    public Seminar (String eventId, String title, String dateTime, String location, int capacity, String SpeakerName) {
        super(eventId, title, dateTime, location, capacity);
        this.SpeakerName = SpeakerName;
    }

    public String getSpeakerName() {
        return SpeakerName;
    }

    public void setSpeakerName(String SpeakerName) {
        this.SpeakerName = SpeakerName;
    }
}