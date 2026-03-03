import java.util.ArrayList;

public class WaitlistManager {
    private ArrayList<String> eventIDlist;
    private ArrayList<ArrayList<User>> allWaitlists;

    public WaitlistManager() {
        this.eventIDlist = new ArrayList<String>();
        this.allWaitlists = new ArrayList<ArrayList<User>>();
    }

    // Add a user to the waitlist for an event
    public void addToWaitlist(String eventId, User user) {
        int index = eventIDlist.indexOf(eventId);

        if (index == -1) {
            // Event not in list yet so create new waitlist
            eventIDlist.add(eventId);
            ArrayList<User> newWaitlist = new ArrayList<User>();
            newWaitlist.add(user);
            allWaitlists.add(newWaitlist);
        } else {
            // Event exists so add user to its waitlist
            ArrayList<User> existingWaitlist = allWaitlists.get(index);
            existingWaitlist.add(user);
        }
        System.out.println("User '" + user.getname() + "' added to waitlist for event " + eventId);
    }

    // Check if an event has people waiting
    public boolean hasWaitlist(String eventId) {
        int index = eventIDlist.indexOf(eventId);
        if (index == -1) {
            return false;
        }
        ArrayList<User> waitlist = allWaitlists.get(index);
        if (waitlist.size() > 0) {
            return true;
        } else {
            return false;
        }
    }
}


