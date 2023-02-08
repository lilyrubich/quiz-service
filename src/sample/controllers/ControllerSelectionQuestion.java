package sample.controllers;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import sample.Answer;
import sample.DatabaseHandler;
import sample.Question;

public class ControllerSelectionQuestion {

    private static int idTest;

    public void setIdTest(int idTest) {
        this.idTest = idTest;
    }

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button NewAnswer, DeleteAnswer, AddQuestion;

    @FXML
    private TextField AnswerField, QuestionField;

    @FXML
    private CheckBox Correct;

    @FXML
    private TableView<Answer> TableAnswers;

    @FXML
    private TableColumn<Answer, String> Answers;

    @FXML
    private TableColumn<Answer, String> Status;

    private ObservableList<Answer> masterData = FXCollections.observableArrayList();

    @FXML
    void initialize() {
        ControllerNewQuestion Cnq = new ControllerNewQuestion();
        ControllerSelectionQuestion Csq = new ControllerSelectionQuestion();
        idTest = Cnq.getIdTest();
        Csq.setIdTest(idTest);
        DatabaseHandler dbHandler = new DatabaseHandler();

        TableAnswers.setItems(masterData);
        Answers.setCellValueFactory(new PropertyValueFactory<>("nameAnswer"));
        Status.setCellValueFactory(new PropertyValueFactory<>("status"));

        NewAnswer.setOnAction(actionEvent -> {
            String ans = AnswerField.getText().trim();
            ///проверка на правильность варианта ответа
            String cor = "Не верный";
            if (Correct.isSelected()) {
                cor = "Верный";
            }
            Answer answer = new Answer(ans, cor);
            masterData.add(answer);
            TableAnswers.setItems(masterData);
        });

        AddQuestion.setOnAction(actionEvent -> {
            String question = QuestionField.getText().trim();
            String type = "Выборка";
            String answer = AnswerField.getText().trim();
            Question quesText = new Question(question, type, answer);
            try {
                dbHandler.insertQuestion(quesText, idTest);
                dbHandler.insertSelectionAnswer(quesText, masterData, idTest);
                AddQuestion.getScene().getWindow().hide();
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });

        DeleteAnswer.setOnAction(actionEvent -> {
            Answer selectedAns = TableAnswers.getSelectionModel().getSelectedItem();
            masterData.remove(selectedAns);
            TableAnswers.setItems(masterData);
        });
    }
}

