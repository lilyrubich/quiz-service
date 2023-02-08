package sample.controllers;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class ControllerNewQuestion {

    private static int idTest;

    public void setIdTest(int idTest) {
        this.idTest = idTest;
    }

    public static int getIdTest() {
        return idTest;
    }

    private static String typeQuestion;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button Choice, Match, TextAnswer, Matrix, Ranging, Wheel;

    @FXML
    void initialize() {

        ControllerCreateTestMain Cctm = new ControllerCreateTestMain();
        ControllerNewQuestion Cnq = new ControllerNewQuestion();
        idTest = Cctm.getIdTest();
        Cnq.setIdTest(idTest);

        TextAnswer.setOnAction(event -> {
            typeQuestion = "Текстовый ответ";
            openNewScene("/sample/view/TextAnswer.fxml");
        });

        Choice.setOnAction(actionEvent -> {
            typeQuestion = "Выборка";
            openNewScene("/sample/view/SelectionQuestion.fxml");
        });

        Match.setOnAction(actionEvent -> {
            typeQuestion = "Сопоставление";
            openNewScene("/sample/view/MatchQuestion.fxml");
        });

        Matrix.setOnAction(actionEvent -> {
            typeQuestion="Матрица";
            openNewScene("/sample/view/MatrixQuestion.fxml");
        });
        Ranging.setOnAction(actionEvent -> {
            typeQuestion="Ранжирование";
            openNewScene("/sample/view/RangQuestion.fxml");
        });
    }

    @FXML
    public void openNewScene(String window) {
        TextAnswer.getScene().getWindow().hide();
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
