package sample.controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import sample.DatabaseHandler;
import sample.User;

public class Controller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button LoginSignUpButton;

    @FXML
    private TextField LoginField;

    @FXML
    private TextField PasswordField;

    @FXML
    private Button AuthSignInButton;

    @FXML
    void initialize() {

        AuthSignInButton.setOnAction(actionEvent -> {
            String loginText = LoginField.getText().trim();
            String passwordText = PasswordField.getText().trim();

            if (!loginText.equals("") && !passwordText.equals(""))
                loginUser(loginText, passwordText);
            else
                System.out.println("Login and password is empty");
        });

        LoginSignUpButton.setOnAction(actionEvent -> {
            openNewScene("/sample/view/Registration.fxml");
        });

    }

    private void loginUser(String loginText, String passwordText) {
        DatabaseHandler dbHandler = new DatabaseHandler();
        User user = new User();
        user.setLogin(loginText);
        user.setPassword(passwordText);
        ResultSet result = dbHandler.getUser(user);

        int count = 0;
        try {
            while (result.next()) {
                count++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (count > 0) {
            //System.out.println("Success");
            openNewScene("/sample/view/MainWindow.fxml");
        }
    }

    public void openNewScene(String window) {
        LoginSignUpButton.getScene().getWindow().hide();

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(window));

        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setTitle("TestCreator");
        stage.getIcons().add(new Image("file:src/imageview/edit.png"));
        stage.setScene(new Scene(root));
        stage.showAndWait();
    }
}