
import java.util.ArrayList;

public class WaitlistManager {
    private ArrayList<String> eventIDlist;
    private ArrayList<ArrayList<User>> allWaitlists;

    public WaitlistManager() {
        this.eventIDlist = new ArrayList<String>();
        this.allWaitlists = new ArrayList<ArrayList<User>>();
    }

    // Add user to waitlist
    public void addToWaitlist(String eventId, User user) {
        int index = eventIDlist.indexOf(eventId);

        if (index == -1) {
            eventIDlist.add(eventId);
            ArrayList<User> newWaitlist = new ArrayList<User>();
            newWaitlist.add(user);
            allWaitlists.add(newWaitlist);
        } else {
            ArrayList<User> existingWaitlist = allWaitlists.get(index);
            existingWaitlist.add(user);
        }
        System.out.println("User '" + user.getname() + "' added to waitlist for event " + eventId);
    }

    public boolean hasWaitlist(String eventId) {
        int index = eventIDlist.indexOf(eventId);
        if (index == -1) {
            return false;
        }
        ArrayList<User> waitlist = allWaitlists.get(index);
        return !waitlist.isEmpty();
    }

    public User promoteFirstFromWaitlist(String eventId) {
        int index = eventIDlist.indexOf(eventId);
        if (index == -1) {
            return null;
        }
        ArrayList<User> waitlist = allWaitlists.get(index);
        if (waitlist.isEmpty()) {
            return null;
        }
        return waitlist.remove(0);
    }

    // Remove specific person from waitlist
    public void removeFromWaitlist(String eventId, String userId) {
        int index = eventIDlist.indexOf(eventId);

        if (index != -1) {
            ArrayList<User> waitlist = allWaitlists.get(index);
            for (int i = 0; i < waitlist.size(); i++) {
                if (waitlist.get(i).getUserID().equals(userId)) {
                    waitlist.remove(i);
                    break;
                }
            }
        }
    }

    public void clearWaitlist(String eventId) {
        int index = eventIDlist.indexOf(eventId);
        if (index != -1) {
            allWaitlists.get(index).clear();
        }
    }

    public void viewWaitlist(String eventId) {
        int index = eventIDlist.indexOf(eventId);
        if (index == -1) {
            System.out.println("No waitlist for event " + eventId);
            return;
        }
        ArrayList<User> waitlist = allWaitlists.get(index);
        if (waitlist.isEmpty()) {
            System.out.println("Waitlist is empty for event " + eventId);
            return;
        }
        System.out.println("--- Waitlist for Event " + eventId + " ---");
        int position = 1;
        for (User u : waitlist) {
            System.out.println(position + ". " + u.getname() + " (" + u.getUserID() + ")");
            position++;
        }
    }
}


