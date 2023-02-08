package sample.controllers;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import sample.DatabaseHandler;
import sample.Question;
import sample.Answer;

public class ControllerTextAnswer {

    private static int idTest;

    public void setIdTest(int idTest) {
        this.idTest = idTest;
    }

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField QuestionField, AnswerField;

    @FXML
    private Button AddQuestion, DeleteAnswer, NewAnswer;

    @FXML
    private TableView<Answer> TableAnswers;

    @FXML
    private TableColumn<Answer, String> Answers;

   private ObservableList<Answer> masterData = FXCollections.observableArrayList();

    @FXML
    void initialize() {

        ControllerNewQuestion Cnq = new ControllerNewQuestion();
        ControllerTextAnswer Cta = new ControllerTextAnswer();
        idTest = Cnq.getIdTest();
        Cta.setIdTest(idTest);
        DatabaseHandler dbHandler = new DatabaseHandler();

        TableAnswers.setItems(masterData);
        Answers.setCellValueFactory(new PropertyValueFactory<>("nameAnswer"));

        NewAnswer.setOnAction(actionEvent -> {
            String ans = AnswerField.getText().trim();
            Answer answer = new Answer(ans);
            masterData.add(answer);
            TableAnswers.setItems(masterData);
        });

        AddQuestion.setOnAction(actionEvent -> {
            String question = QuestionField.getText().trim();
            String type = "Текстовый ответ";
            String answer = AnswerField.getText().trim();
            Question quesText = new Question(question, type, answer);
            try {
                dbHandler.insertQuestion(quesText, idTest);
                dbHandler.insertTextAnswer(quesText, masterData, idTest);
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
