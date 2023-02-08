package sample.controllers;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import sample.DatabaseHandler;
import sample.User;

public class ControllerRegistration {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField NameField;

    @FXML
    private TextField LastNameField;

    @FXML
    private Button RegistrationButton;

    @FXML
    private RadioButton RadioButtonCreator;

    @FXML
    private RadioButton RadioButtonUser;

    @FXML
    private TextField LoginField;

    @FXML
    private TextField PasswordField;

    @FXML
    private TextField PasswordFieldRepeat;

    @FXML
    void initialize() {
        RegistrationButton.setOnAction(event -> {
            signUpNewUser();
        });

       // if (RadioButtonUser.isSelected()) RadioButtonCreator.isSelected();
    }

    private void signUpNewUser() {

        DatabaseHandler dbHandler = new DatabaseHandler();

        String firstName = NameField.getText();
        String lastName = LastNameField.getText();
        String login = LoginField.getText();
        String password = PasswordField.getText();
        String status = "";
        if(RadioButtonCreator.isSelected())
            status = "1";
        else status = "0";

        User user = new User(firstName, lastName, login, password, status);

        try {
            dbHandler.signUpUser(user);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}

