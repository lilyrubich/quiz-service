package sample.controllers;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import sample.Answer;
import sample.DatabaseHandler;
import sample.Question;

public class ControllerMatchQuestion {

    private static int idTest;

    public void setIdTest(int idTest) {
        this.idTest = idTest;
    }

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button AddQuestion, NewAnswer, NewAnswer1, DeleteAnswer;

    @FXML
    private TextField AnswerField, AnswerField1, QuestionField;

    @FXML
    private TableView<Answer> TableAnswers;

    @FXML
    private TableColumn<Answer, String> Answers1, Answers2, status;

    @FXML
    private CheckBox Correct;

    private ObservableList<Answer> masterData = FXCollections.observableArrayList();

    @FXML
    void initialize() {
        ControllerNewQuestion Cnq = new ControllerNewQuestion();
        ControllerMatchQuestion Cmq = new ControllerMatchQuestion();
        idTest = Cnq.getIdTest();
        Cmq.setIdTest(idTest);
        DatabaseHandler dbHandler = new DatabaseHandler();

        TableAnswers.setItems(masterData);
        Answers1.setCellValueFactory(new PropertyValueFactory<>("nameAnswer"));
        Answers2.setCellValueFactory(new PropertyValueFactory<>("nameAnswer1"));
        status.setCellValueFactory(new PropertyValueFactory<>("status"));

        NewAnswer.setOnAction(actionEvent -> {
            String ans = AnswerField.getText().trim();
            String ans1 = AnswerField1.getText().trim();
            ///проверка на правильность варианта ответа
            String cor = "Не верно";
            if (Correct.isSelected()) {
                cor = "Верно";
            }
            Answer answer = new Answer(ans, ans1, cor);
            masterData.add(answer);
            TableAnswers.setItems(masterData);
        });

        AddQuestion.setOnAction(actionEvent -> {
            String question = QuestionField.getText().trim();
            String type = "Сопоставление";
            //String answer = AnswerField.getText().trim();
            //String answer1 = AnswerField1.getText().trim();
            Question quesText = new Question(question, type);
            try {
                dbHandler.insertQuestion(quesText, idTest);
                dbHandler.insertMatchQuestion(quesText, masterData, idTest);
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

