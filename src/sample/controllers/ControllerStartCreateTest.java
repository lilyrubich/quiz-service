package sample.controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import sample.DatabaseHandler;
import sample.Test;

public class ControllerStartCreateTest {

    ObservableList<String> levelFieldList = FXCollections.observableArrayList("Бакалавриат", "Магистратура", "Специалитет", "Аспирантура");
    ObservableList<String> semesterFieldList = FXCollections.observableArrayList("1", "2", "3", "4", "5", "6", "7", "8", "9", "10");

    private static int idTest;
    public static int getIdTest() {
        return idTest;
    }

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField NameTestField;

    @FXML
    private TextField DescriptionField;

    @FXML
    private TextField DisciplineField;

    @FXML
    private ChoiceBox SemesterField;

    @FXML
    private ComboBox<String> LevelField;

    @FXML
    private Button Create;

    @FXML
    public void initialize() {
        LevelField.setItems(levelFieldList);
        SemesterField.setItems(semesterFieldList);
        DatabaseHandler dbHandler = new DatabaseHandler();

        Create.setOnAction(actionEvent -> {
            String nameTest = NameTestField.getText().trim();
            String description = DescriptionField.getText().trim();
            String discipline = DisciplineField.getText().trim();
            String level = (String) LevelField.getValue();
            String semester = (String) SemesterField.getValue();

            //System.out.println(semester);

            Test test = new Test(nameTest, description, discipline, level, semester);
            //System.out.println(nameTest);
            try {
                idTest = dbHandler.insertTest(test);
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

            openNewScene("/sample/view/CreateTestMain.fxml");
        });
    }

    public void openNewScene(String window){
        Create.getScene().getWindow().hide();
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
        stage.show();
    }
}
