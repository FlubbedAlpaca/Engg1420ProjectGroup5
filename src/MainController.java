import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;

public class MainController {

    private UserFunctions userFunctions     = new UserFunctions();
    private WaitlistManager waitlistManager = new WaitlistManager();
    private BookingManager bookingManager   = new BookingManager(waitlistManager);
    private EventManagement eventManagement = new EventManagement();

    @FXML private VBox panelUsers;
    @FXML private VBox panelEvents;
    @FXML private VBox panelBookings;
    @FXML private VBox panelWaitlist;

    @FXML private TableView<User> tblUsers;
    @FXML private TableColumn<User, String> colUserId;
    @FXML private TableColumn<User, String> colUserName;
    @FXML private TableColumn<User, String> colUserEmail;
    @FXML private TableColumn<User, String> colUserType;
    @FXML private TextField txtUserId;
    @FXML private TextField txtUserName;
    @FXML private TextField txtUserEmail;
    @FXML private ComboBox<String> cmbUserType;

    @FXML private TableView<Event> tblEvents;
    @FXML private TableColumn<Event, String> colEventId;
    @FXML private TableColumn<Event, String> colEventTitle;
    @FXML private TableColumn<Event, String> colEventDate;
    @FXML private TableColumn<Event, String> colEventLocation;
    @FXML private TableColumn<Event, Integer> colEventCapacity;
    @FXML private TableColumn<Event, String> colEventStatus;
    @FXML private TableColumn<Event, String> colEventType;
    @FXML private TableColumn<Event, String> colEventSpecific;
    @FXML private TextField txtEventId;
    @FXML private TextField txtEventTitle;
    @FXML private TextField txtEventDate;
    @FXML private TextField txtEventLocation;
    @FXML private TextField txtEventCapacity;
    @FXML private TextField txtEventSpecific;
    @FXML private ComboBox<String> cmbEventType;
    @FXML private TextField txtSearchTitle;
    @FXML private ComboBox<String> cmbFilterType;

    @FXML private TableView<Booking> tblBookings;
    @FXML private TableColumn<Booking, String> colBookingId;
    @FXML private TableColumn<Booking, String> colBookingUser;
    @FXML private TableColumn<Booking, String> colBookingEvent;
    @FXML private TableColumn<Booking, String> colBookingStatus;
    @FXML private TextField txtBookingUserId;
    @FXML private TextField txtBookingEventId;
    @FXML private TextField txtCancelBookingId;

    @FXML private ListView<String> lstWaitlist;
    @FXML private TextField txtWaitlistEventId;

    @FXML private Label lblStatus;

    private ObservableList<User>    userObs    = FXCollections.observableArrayList();
    private ObservableList<Event>   eventObs   = FXCollections.observableArrayList();
    private ObservableList<Booking> bookingObs = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        cmbUserType.getItems().addAll("Student", "Staff", "Guest");
        cmbEventType.getItems().addAll("Workshop", "Seminar", "Concert");
        cmbFilterType.getItems().addAll("All", "Workshop", "Seminar", "Concert");
        cmbFilterType.setValue("All");

        colUserId.setCellValueFactory(new PropertyValueFactory<>("userID"));
        colUserName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colUserEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colUserType.setCellValueFactory(new PropertyValueFactory<>("type"));
        tblUsers.setItems(userObs);

        colEventId.setCellValueFactory(new PropertyValueFactory<>("eventId"));
        colEventTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        colEventDate.setCellValueFactory(new PropertyValueFactory<>("dateTime"));
        colEventLocation.setCellValueFactory(new PropertyValueFactory<>("location"));
        colEventCapacity.setCellValueFactory(new PropertyValueFactory<>("capacity"));
        colEventStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        colEventType.setCellValueFactory(new PropertyValueFactory<>("eventType"));
        colEventSpecific.setCellValueFactory(new PropertyValueFactory<>("specificInfo"));
        tblEvents.setItems(eventObs);

        cmbEventType.valueProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal == null) {
                txtEventSpecific.setPromptText("Type-specific field");
            } else if (newVal.equals("Workshop")) {
                txtEventSpecific.setPromptText("Topic");
            } else if (newVal.equals("Seminar")) {
                txtEventSpecific.setPromptText("Speaker Name");
            } else if (newVal.equals("Concert")) {
                txtEventSpecific.setPromptText("Age Restriction");
            }
        });

        colBookingId.setCellValueFactory(new PropertyValueFactory<>("bookingId"));
        colBookingUser.setCellValueFactory(new PropertyValueFactory<>("userName"));
        colBookingEvent.setCellValueFactory(new PropertyValueFactory<>("eventTitle"));
        colBookingStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        tblBookings.setItems(bookingObs);

        eventManagement.setBookingManager(bookingManager);
        eventManagement.setWaitlistManager(waitlistManager);

        FileManager.loadUsers(userFunctions);
        FileManager.loadEvents(eventManagement);
        FileManager.loadBookings(bookingManager, userFunctions, eventManagement);

        refreshUserTable();
        refreshEventTable();
        refreshBookingTable();

        showPanel("users");
        setStatus("Data loaded successfully.");
    }

    @FXML public void showUsers()    { showPanel("users"); }
    @FXML public void showEvents()   { showPanel("events"); }
    @FXML public void showBookings() { showPanel("bookings"); }
    @FXML public void showWaitlist() { showPanel("waitlist"); }

    private void showPanel(String name) {
        panelUsers.setVisible(false);    panelUsers.setManaged(false);
        panelEvents.setVisible(false);   panelEvents.setManaged(false);
        panelBookings.setVisible(false); panelBookings.setManaged(false);
        panelWaitlist.setVisible(false); panelWaitlist.setManaged(false);
        switch (name) {
            case "users"    -> { panelUsers.setVisible(true);    panelUsers.setManaged(true); }
            case "events"   -> { panelEvents.setVisible(true);   panelEvents.setManaged(true); }
            case "bookings" -> { panelBookings.setVisible(true); panelBookings.setManaged(true); }
            case "waitlist" -> { panelWaitlist.setVisible(true); panelWaitlist.setManaged(true); }
        }
    }

    @FXML
    public void handleAddUser() {
        String id    = txtUserId.getText().trim();
        String name  = txtUserName.getText().trim();
        String email = txtUserEmail.getText().trim();
        String type  = cmbUserType.getValue();
        if (id.isEmpty() || name.isEmpty() || email.isEmpty() || type == null) {
            setStatus("Please fill in all user fields ASAP !!!");
            return;
        }
        User created = userFunctions.createUser(id, name, email, type);
        if (created != null) {
            refreshUserTable();
            txtUserId.clear(); txtUserName.clear(); txtUserEmail.clear();
            cmbUserType.getSelectionModel().clearSelection();
            setStatus("User '" + name + "' added successfully.");
        } else {
            setStatus("Error: User ID already exists.");
        }
    }

    @FXML
    public void handleCreateEvent() {
        String id       = txtEventId.getText().trim();
        String title    = txtEventTitle.getText().trim();
        String date     = txtEventDate.getText().trim();
        String location = txtEventLocation.getText().trim();
        String capStr   = txtEventCapacity.getText().trim();
        String specific = txtEventSpecific.getText().trim();
        String type     = cmbEventType.getValue();
        if (id.isEmpty() || title.isEmpty() || date.isEmpty() || location.isEmpty() || capStr.isEmpty() || type == null) {
            setStatus("Please fill in all event fields.");
            return;
        }
        int cap;
        try { cap = Integer.parseInt(capStr); }
        catch (NumberFormatException e) { setStatus("Capacity must be a number."); return; }
        Event newEvent;
        switch (type) {
            case "Workshop" -> newEvent = new Workshop(id, title, date, location, cap, specific);
            case "Seminar"  -> newEvent = new Seminar(id, title, date, location, cap, specific);
            case "Concert"  -> newEvent = new Concert(id, title, date, location, cap, specific);
            default -> { setStatus("Select a valid event type."); return; }
        }
        eventManagement.createEvent(newEvent);
        refreshEventTable();
        setStatus("Event '" + title + "' created.");
    }

    @FXML
    public void handleCancelEvent() {
        Event selected = tblEvents.getSelectionModel().getSelectedItem();
        if (selected == null) { setStatus("Select an event in the table first."); return; }
        eventManagement.cancelEvent(selected.getEventId());
        refreshEventTable();
        refreshBookingTable();
        setStatus("Event '" + selected.getTitle() + "' cancelled.");
    }

    @FXML
    public void handleSearchEvents() {
        String query      = txtSearchTitle.getText().trim();
        String filterType = cmbFilterType.getValue();
        eventObs.clear();
        for (Event e : eventManagement.getEvents()) {
            boolean titleMatch = e.getTitle().toLowerCase().contains(query.toLowerCase());
            boolean typeMatch  = filterType == null || filterType.equals("All")
                    || e.getClass().getSimpleName().equalsIgnoreCase(filterType);
            if (titleMatch && typeMatch) eventObs.add(e);
        }
        setStatus("Showing " + eventObs.size() + " result(s).");
    }

    @FXML
    public void handleClearSearch() {
        txtSearchTitle.clear();
        cmbFilterType.setValue("All");
        refreshEventTable();
        setStatus("Search cleared.");
    }

    @FXML
    public void handleCreateBooking() {
        String userId  = txtBookingUserId.getText().trim();
        String eventId = txtBookingEventId.getText().trim();
        User  user  = userFunctions.getUser(userId);
        Event event = eventManagement.getEvent(eventId);
        if (user == null)  {
            setStatus("User ID not found: " + userId);   return; }
        if (event == null) {
            setStatus("Event ID not found: " + eventId); return; }
        bookingManager.createBooking(user, event);
        refreshBookingTable();
        setStatus("Booking processed for " + user.getname() + " at " + event.getTitle());
    }

    @FXML
    public void handleCancelBooking() {
        String idStr = txtCancelBookingId.getText().trim();
        if (idStr.isEmpty()) {
            setStatus("Enter a Booking ID to cancel."); return; }
        bookingManager.cancelBooking(idStr);
        refreshBookingTable();
        setStatus("Booking " + idStr + " cancelled. Waitlist updated if applicable.");
    }

    @FXML
    public void handleViewWaitlist() {
        String eventId = txtWaitlistEventId.getText().trim();
        if (eventId.isEmpty()) { setStatus("Enter an Event ID."); return; }
        lstWaitlist.getItems().clear();
        int pos = 1;
        for (Booking b : bookingManager.getBookings()) {
            if (b.getEvent().getEventId().equals(eventId) && b.getStatus().equals("Waitlisted")) {
                lstWaitlist.getItems().add(pos + ". " + b.getUser().getname()
                        + " (" + b.getUser().getUserID() + ")");
                pos++;
            }
        }
        if (pos == 1) lstWaitlist.getItems().add("No waitlisted users for this event.");
        setStatus("Waitlist for event " + eventId + " loaded.");
    }

    @FXML
    public void handleSave() {
        FileManager.saveUsers(userFunctions.getUsers());
        FileManager.saveEvents(eventManagement.getEvents());
        FileManager.saveBookings(bookingManager.getBookings());
        setStatus("Data saved.");
    }

    private void refreshUserTable() {
        userObs.clear();
        userObs.addAll(userFunctions.getUsers());
    }
    private void refreshEventTable() {
        eventObs.clear();
        eventObs.addAll(eventManagement.getEvents());
    }
    private void refreshBookingTable() {
        bookingObs.clear();
        bookingObs.addAll(bookingManager.getBookings());
    }
    private void setStatus(String msg) {
        if (lblStatus != null) lblStatus.setText(msg);
    }
}