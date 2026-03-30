import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;

public class MainController {
    private UserFunctions userFunctions       = new UserFunctions();
    private WaitlistManager waitlistManager   = new WaitlistManager();
    private BookingManager bookingManager     = new BookingManager(waitlistManager);
    private EventManagement eventManagement   = new EventManagement();
}