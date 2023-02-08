package sample.controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import sample.Const;
import sample.DatabaseHandler;
import sample.Question;
import sample.Test;

public class ControllerCreateTestMain {

    private static int idTest;

    public void setIdTest(int idTest) {
        this.idTest = idTest;
    }

    public static int getIdTest() {
        return idTest;
    }

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button NewQuestion, newTest, DeleteQuestion, Update, listTest;

    public TableView<Question> TableQuestions;
    public TableColumn<Question, String> question_text;
    public TableColumn<Question, String> type;

    @FXML
    private Label nameTest, description, discipline, level, semester;

    @FXML
    void initialize() {

        nameTest.setText("DDDDD");
        ControllerStartCreateTest Csct = new ControllerStartCreateTest();
        ControllerCreateTestMain Cctm = new ControllerCreateTestMain();
        ControllerMainWindow Cmw = new ControllerMainWindow();
        idTest = Csct.getIdTest();
        if (idTest != 0) {
            Cctm.setIdTest(idTest);
        } else {
            idTest = Cmw.getIdTest();
            Cctm.setIdTest(idTest);
        }

        DatabaseHandler dbHandler = new DatabaseHandler();
        TableQuestions.setItems(dbHandler.tableQuestion(idTest));
        question_text.setCellValueFactory(new PropertyValueFactory<>("questionText"));
        type.setCellValueFactory(new PropertyValueFactory<>("questionType"));

        //для вывода данных о тесте
        Test selTest = dbHandler.selectTest(idTest);
        nameTest.setText(selTest.getNametest());
        description.setText(selTest.getDescription());
        discipline.setText(selTest.getDiscipline());
        level.setText(selTest.getLevel());
        semester.setText(selTest.getSemester());

        NewQuestion.setOnAction(event -> {
            openNewScene("/sample/view/NewQuestion.fxml");
        });

        newTest.setOnAction(event -> {
            NewQuestion.getScene().getWindow().hide();
            openNewScene("/sample/view/StartCreateTest.fxml");
        });

        DeleteQuestion.setOnAction(event -> {
            Question selectedQues = TableQuestions.getSelectionModel().getSelectedItem();
            dbHandler.deleteQuestion(selectedQues, idTest);
            //обновление таблицы
            TableQuestions.setItems(dbHandler.tableQuestion(idTest));
        });

        //обновление таблицы
        Update.setOnAction(event -> {
            TableQuestions.setItems(dbHandler.tableQuestion(idTest));
        });

        listTest.setOnAction(event -> {
            NewQuestion.getScene().getWindow().hide();
            openNewScene("/sample/view/MainWindow.fxml");
        });
        //двойной клик пока не надо,
        /*TableQuestions.setRowFactory(tv -> {
            TableRow<Question> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    Question rowData = row.getItem();
                    //idQues = dbHandler.selectIdTest(rowData);
                    //dbHandler.sel(rowData);
                    System.out.println("Double click on: " + rowData.getQuestionText() +idTest);
                }
            });
            return row;
        });*/
    }

    @FXML
    public void openNewScene(String window) {
        //NewQuestion.getScene().getWindow().hide();
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

