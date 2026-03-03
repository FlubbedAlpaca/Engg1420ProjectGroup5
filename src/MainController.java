

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class MainController {

    // 1. Link the FXML UI elements to Java variables
    @FXML private TextField txtInput1;
    @FXML private TextField txtInput2;
    @FXML private TextField txtInput3;
    @FXML private Combopackage com.example;

    import javafx.collections.FXCollections;
    import javafx.collections.ObservableList;
    import javafx.event.ActionEvent;
    import javafx.fxml.FXML;
    import javafx.scene.control.*;
    import javafx.scene.control.cell.PropertyValueFactory;

    public class MainController {

        @FXML private TextField txtInput1;
        @FXML private TextField txtInput2;
        @FXML private TextField txtInput3;
        @FXML private ComboBox<String> cmbType;

        @FXML private TableView<User> tblUsers;
        @FXML private TableColumn<User, String> colUserId;
        @FXML private TableColumn<User, String> colUserName;
        @FXML private TableColumn<User, String> colUserEmail;

        private ObservableList<User> userList = FXCollections.observableArrayList();

        @FXML
        public void initialize() {
            cmbType.getItems().addAll("Student", "Staff", "Guest");

            colUserId.setCellValueFactory(new PropertyValueFactory<>("userID"));
            colUserName.setCellValueFactory(new PropertyValueFactory<>("name"));
            colUserEmail.setCellValueFactory(new PropertyValueFactory<>("email"));

            tblUsers.setItems(userList);
        }

        @FXML
        public void handleSubmit(ActionEvent event) {
            String id = txtInput1.getText();
            String name = txtInput2.getText();
            String email = txtInput3.getText();
            String type = cmbType.getValue();

            if (id.isEmpty() || name.isEmpty() || type == null) {
                System.out.println("Please fill in all fields!");
                return;
            }

            User newUser = null;
            if (type.equals("Student")) {
                newUser = new Student(id, name, email);
            } else if (type.equals("Staff")) {
                newUser = new Staff(id, name, email);
            } else if (type.equals("Guest")) {
                newUser = new Guest(id, name, email);
            }

            if (newUser != null) {
                userList.add(newUser);

                txtInput1.clear();
                txtInput2.clear();
                txtInput3.clear();
                cmbType.getSelectionModel().clearSelection();
            }
        }
    }<?xml version="1.0" encoding="UTF-8"?>
    <?import javafx.scene.control.*?>
    <?import javafx.scene.layout.*?>

    <BorderPane xmlns="http://javafx.com/javafx/17.0.12" xmlns:fx="http://javafx.com/fxml/1" fx:controller="com.example.MainController" prefHeight="400.0" prefWidth="600.0">
       <left>
          <VBox prefHeight="400.0" prefWidth="149.0" BorderPane.alignment="CENTER">
             <children>
                <Button mnemonicParsing="false" prefHeight="25.0" prefWidth="170.0" text="USER MANAGEMENT"/>
                <Button mnemonicParsing="false" prefHeight="25.0" prefWidth="196.0" text="EVENT MANAGEMENT"/>
                <Button mnemonicParsing="false" prefHeight="25.0" prefWidth="179.0" text="BOOKING MANAGEMENT"/>
                <Button mnemonicParsing="false" prefHeight="25.0" prefWidth="201.0" text="WAITLIST MANAGEMENT"/>
                <TextField fx:id="txtInput1" promptText="User ID"/>
                <TextField fx:id="txtInput2" promptText="Name"/>
                <TextField fx:id="txtInput3" promptText="Email"/>
                <ComboBox fx:id="cmbType" prefWidth="165.0"/>
                <Button mnemonicParsing="false" prefHeight="25.0" prefWidth="219.0" text="SUBMIT/ADD" onAction="#handleSubmit"/>
             </children>
          </VBox>
       </left>
       <center>
          <VBox prefHeight="200.0" prefWidth="100.0" BorderPane.alignment="CENTER">
             <children>
                <TableView fx:id="tblUsers" prefHeight="200.0" prefWidth="200.0" VBox.vgrow="ALWAYS">
                  <columns>
                    <TableColumn fx:id="colUserId" prefWidth="75.0" text="USER ID"/>
                    <TableColumn fx:id="colUserName" prefWidth="116.43" text="NAME"/>
                    <TableColumn fx:id="colUserEmail" prefWidth="104.14" text="EMAIL"/>
                  </columns>
                </TableView>
             </children>
          </VBox>
       </center>
    </BorderPane>Box<String> cmbType;

    @FXML private TableView<User> tblUsers;
    @FXML private TableColumn<User, String> colUserId;
    @FXML private TableColumn<User, String> colUserName;
    @FXML private TableColumn<User, String> colUserEmail;

    // JavaFX uses ObservableLists to automatically update the UI when data changes
    private ObservableList<User> userList = FXCollections.observableArrayList();

    // 2. The initialize() method runs automatically when the UI loads
    @FXML
    public void initialize() {
        // Populate the combo box
        cmbType.getItems().addAll("Student", "Staff", "Guest");

        // Tell the table columns which "getter" methods to use from your User class.
        // "userID" looks for getUserID(), "name" looks for getname()
        colUserId.setCellValueFactory(new PropertyValueFactory<>("userID"));
        colUserName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colUserEmail.setCellValueFactory(new PropertyValueFactory<>("email"));

        // Attach our list to the table
        tblUsers.setItems(userList);
    }

    // 3. This method runs when you click the "SUBMIT/ADD" button
    @FXML
    public void handleSubmit(ActionEvent event) {
        String id = txtInput1.getText();
        String name = txtInput2.getText();
        String email = txtInput3.getText();
        String type = cmbType.getValue();

        if (id.isEmpty() || name.isEmpty() || type == null) {
            System.out.println("Please fill in all fields!");
            return;
        }

        // Create the right kind of user based on the dropdown
        User newUser = null;
        if (type.equals("Student")) {
            newUser = new Student(name, id, email);
        } else if (type.equals("Staff")) {
            newUser = new Staff(name, id, email);
        } else if (type.equals("Guest")) {
            newUser = new Guest(name, id, email);
        }

        if (newUser != null) {
            userList.add(newUser); // Adding to the list automatically updates the table!

            // Clear the text fields after submitting
            txtInput1.clear();
            txtInput2.clear();
            txtInput3.clear();
            cmbType.getSelectionModel().clearSelection();
        }
    }
}