package sample.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import sample.DatabaseHandler;

public class ControllerMatrixQuestion {

    private static int idTest;

    public void setIdTest(int idTest) {
        this.idTest = idTest;
    }

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button AddQuestion, ok;

    @FXML
    private ChoiceBox lineCount;

    @FXML
    private ChoiceBox columnCount;

    @FXML
    private TextField QuestionField, column1, column2, column3, column4, line1, line2, line3, line4;

    @FXML
    private CheckBox a11, a12, a13, a14, a21, a22, a23, a24, a31, a32, a33, a34, a41, a42, a43, a44;

    ObservableList<String> CountList = FXCollections.observableArrayList("2", "3", "4");

    @FXML
    void initialize() {
        lineCount.setItems(CountList);
        columnCount.setItems(CountList);
        ControllerNewQuestion Cnq = new ControllerNewQuestion();
        ControllerMatrixQuestion Cmq = new ControllerMatrixQuestion();
        idTest = Cnq.getIdTest();
        Cmq.setIdTest(idTest);
        DatabaseHandler dbHandler = new DatabaseHandler();

        ok.setOnAction(actionEvent -> {
            String a = (String) lineCount.getValue();
            String b = (String) columnCount.getValue();
            switch (a) {
                case "2":
                    if (b.equals("2")) {
                        column3.setVisible(false);
                        column4.setVisible(false);
                        line3.setVisible(false);
                        line4.setVisible(false);
                        a13.setVisible(false);
                        a14.setVisible(false);
                        a23.setVisible(false);
                        a24.setVisible(false);
                        a31.setVisible(false);
                        a32.setVisible(false);
                        a33.setVisible(false);
                        a34.setVisible(false);
                        a41.setVisible(false);
                        a42.setVisible(false);
                        a43.setVisible(false);
                        a44.setVisible(false);
                    }
                    if (b.equals("3")) {
                        column3.setVisible(true);
                        column4.setVisible(false);
                        line3.setVisible(false);
                        line4.setVisible(false);
                        a13.setVisible(true);
                        a23.setVisible(true);
                        a14.setVisible(false);
                        a24.setVisible(false);
                        a31.setVisible(false);
                        a32.setVisible(false);
                        a33.setVisible(false);
                        a34.setVisible(false);
                        a41.setVisible(false);
                        a42.setVisible(false);
                        a43.setVisible(false);
                        a44.setVisible(false);
                    }
                    if (b.equals("4")) {
                        column3.setVisible(true);
                        column4.setVisible(true);
                        line3.setVisible(false);
                        line4.setVisible(false);
                        a13.setVisible(true);
                        a23.setVisible(true);
                        a14.setVisible(true);
                        a24.setVisible(true);
                        a31.setVisible(false);
                        a32.setVisible(false);
                        a33.setVisible(false);
                        a34.setVisible(false);
                        a41.setVisible(false);
                        a42.setVisible(false);
                        a43.setVisible(false);
                        a44.setVisible(false);
                    }
                    break;
                case "3":
                    if (b.equals("2")) {
                        line3.setVisible(true);
                        line4.setVisible(false);
                        column3.setVisible(false);
                        column4.setVisible(false);
                        a13.setVisible(false);
                        a14.setVisible(false);
                        a23.setVisible(false);
                        a24.setVisible(false);
                        a31.setVisible(true);
                        a32.setVisible(true);
                        a33.setVisible(false);
                        a34.setVisible(false);
                        a41.setVisible(false);
                        a42.setVisible(false);
                        a43.setVisible(false);
                        a44.setVisible(false);
                    }
                    if (b.equals("3")) {
                        line3.setVisible(true);
                        line4.setVisible(false);
                        column4.setVisible(false);
                        column3.setVisible(true);
                        a13.setVisible(true);
                        a14.setVisible(false);
                        a23.setVisible(true);
                        a24.setVisible(false);
                        a31.setVisible(true);
                        a32.setVisible(true);
                        a33.setVisible(true);
                        a34.setVisible(false);
                        a41.setVisible(false);
                        a42.setVisible(false);
                        a43.setVisible(false);
                        a44.setVisible(false);
                    }
                    if (b.equals("4")) {
                        line3.setVisible(true);
                        line4.setVisible(false);
                        column3.setVisible(true);
                        column4.setVisible(true);
                        a13.setVisible(true);
                        a14.setVisible(true);
                        a23.setVisible(true);
                        a24.setVisible(true);
                        a31.setVisible(true);
                        a32.setVisible(true);
                        a33.setVisible(true);
                        a34.setVisible(true);
                        a41.setVisible(false);
                        a42.setVisible(false);
                        a43.setVisible(false);
                        a44.setVisible(false);
                    }
                    break;
                case "4":
                    if (b.equals("2")) {
                        column3.setVisible(false);
                        column4.setVisible(false);
                        line3.setVisible(true);
                        line4.setVisible(true);
                        a13.setVisible(false);
                        a14.setVisible(false);
                        a23.setVisible(false);
                        a24.setVisible(false);
                        a31.setVisible(true);
                        a32.setVisible(true);
                        a33.setVisible(false);
                        a34.setVisible(false);
                        a41.setVisible(true);
                        a42.setVisible(true);
                        a43.setVisible(false);
                        a44.setVisible(false);
                    }
                    if (b.equals("3")) {
                        line3.setVisible(true);
                        line4.setVisible(true);
                        column3.setVisible(true);
                        column4.setVisible(false);
                        a13.setVisible(true);
                        a14.setVisible(false);
                        a23.setVisible(true);
                        a24.setVisible(false);
                        a31.setVisible(true);
                        a32.setVisible(true);
                        a33.setVisible(true);
                        a34.setVisible(false);
                        a41.setVisible(true);
                        a42.setVisible(true);
                        a43.setVisible(true);
                        a44.setVisible(false);
                    }
                    if (b.equals("4")) {
                        line3.setVisible(true);
                        line4.setVisible(true);
                        column3.setVisible(true);
                        column4.setVisible(true);
                        a13.setVisible(true);
                        a14.setVisible(true);
                        a23.setVisible(true);
                        a24.setVisible(true);
                        a31.setVisible(true);
                        a32.setVisible(true);
                        a33.setVisible(true);
                        a34.setVisible(true);
                        a41.setVisible(true);
                        a42.setVisible(true);
                        a43.setVisible(true);
                        a44.setVisible(true);
                    }
                    break;
            }

        });
    }
}
