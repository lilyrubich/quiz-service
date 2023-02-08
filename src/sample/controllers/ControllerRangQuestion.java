package sample.controllers;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import sample.DatabaseHandler;
import sample.Question;

public class ControllerRangQuestion {

    private static int idTest;

    public void setIdTest(int idTest) {
        this.idTest = idTest;
    }

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button AddQuestion;

    @FXML
    private TextField AnswerField1;

    @FXML
    private Button NewAnswer1;

    @FXML
    private TextField line1, line2, line3, line4, line5, line6;

    @FXML
    private ChoiceBox lineCount;

    @FXML
    private Button down1, down2, down3, down4, down5, down6, up1, up2, up3, up4, up5, up6;

    @FXML
    private TextField QuestionField;

    ObservableList<String> CountList = FXCollections.observableArrayList("2", "3", "4", "5", "6");
    ArrayList<String> lines = new ArrayList<>();

    @FXML
    void initialize() {
        ControllerNewQuestion Cnq = new ControllerNewQuestion();
        ControllerRangQuestion Crq = new ControllerRangQuestion();
        idTest = Cnq.getIdTest();
        Crq.setIdTest(idTest);
        DatabaseHandler dbHandler = new DatabaseHandler();
        lineCount.setItems(CountList);

        lineCount.getSelectionModel().selectedIndexProperty().addListener(
                (ObservableValue<? extends Number> ov, Number old_val, Number new_val) -> {
                    int a = (int) new_val+2;
                    System.out.println(a);
                    switch (a) {
                        case 1:
                            line2.setVisible(false);
                            up2.setVisible(false);
                            down2.setVisible(false);
                            line3.setVisible(false);
                            up3.setVisible(false);
                            down3.setVisible(false);
                            line4.setVisible(false);
                            up4.setVisible(false);
                            down4.setVisible(false);
                            line5.setVisible(false);
                            up5.setVisible(false);
                            down5.setVisible(false);
                            line6.setVisible(false);
                            up6.setVisible(false);
                            down6.setVisible(false);
                            break;
                        case 2:
                            line2.setVisible(true);
                            up2.setVisible(true);
                            down2.setVisible(true);
                            line3.setVisible(false);
                            up3.setVisible(false);
                            down3.setVisible(false);
                            line4.setVisible(false);
                            up4.setVisible(false);
                            down4.setVisible(false);
                            line5.setVisible(false);
                            up5.setVisible(false);
                            down5.setVisible(false);
                            line6.setVisible(false);
                            up6.setVisible(false);
                            down6.setVisible(false);
                            break;
                        case 3:
                            line2.setVisible(true);
                            up2.setVisible(true);
                            down2.setVisible(true);
                            line3.setVisible(true);
                            up3.setVisible(true);
                            down3.setVisible(true);
                            line4.setVisible(false);
                            up4.setVisible(false);
                            down4.setVisible(false);
                            line5.setVisible(false);
                            up5.setVisible(false);
                            down5.setVisible(false);
                            line6.setVisible(false);
                            up6.setVisible(false);
                            down6.setVisible(false);
                            break;
                        case 4:
                            line2.setVisible(true);
                            up2.setVisible(true);
                            down2.setVisible(true);
                            line3.setVisible(true);
                            up3.setVisible(true);
                            down3.setVisible(true);
                            line4.setVisible(true);
                            up4.setVisible(true);
                            down4.setVisible(true);
                            line5.setVisible(false);
                            up5.setVisible(false);
                            down5.setVisible(false);
                            line6.setVisible(false);
                            up6.setVisible(false);
                            down6.setVisible(false);
                            break;
                        case 5:
                            line2.setVisible(true);
                            up2.setVisible(true);
                            down2.setVisible(true);
                            line3.setVisible(true);
                            up3.setVisible(true);
                            down3.setVisible(true);
                            line4.setVisible(true);
                            up4.setVisible(true);
                            down4.setVisible(true);
                            line5.setVisible(true);
                            up5.setVisible(true);
                            down5.setVisible(true);
                            line6.setVisible(false);
                            up6.setVisible(false);
                            down6.setVisible(false);
                            break;
                        case 6:
                            line2.setVisible(true);
                            up2.setVisible(true);
                            down2.setVisible(true);
                            line3.setVisible(true);
                            up3.setVisible(true);
                            down3.setVisible(true);
                            line4.setVisible(true);
                            up4.setVisible(true);
                            down4.setVisible(true);
                            line5.setVisible(true);
                            up5.setVisible(true);
                            down5.setVisible(true);
                            line6.setVisible(true);
                            up6.setVisible(true);
                            down6.setVisible(true);
                            break;
                    }
                }
        );

        up1.setOnAction(actionEvent -> {
            if (!line3.isVisible()) {
                swapLines(line1, line2);
            } else {
                if (!line4.isVisible()) {
                    swapLines(line1, line3);
                } else {
                    if (!line5.isVisible()) {
                        swapLines(line1, line4);
                    } else {
                        if (!line6.isVisible()) {
                            swapLines(line1, line5);
                        } else {
                            swapLines(line1, line6);
                        }
                    }
                }
            }
        });
        up2.setOnAction(actionEvent -> {
            swapLines(line2, line1);
        });
        up3.setOnAction(actionEvent -> {
            swapLines(line3, line2);
        });
        up4.setOnAction(actionEvent -> {
            swapLines(line4, line3);
        });
        up5.setOnAction(actionEvent -> {
            swapLines(line5, line4);
        });
        up6.setOnAction(actionEvent -> {
            swapLines(line6, line5);
        });

        down1.setOnAction(actionEvent -> {
            swapLines(line1, line2);
        });
        down2.setOnAction(actionEvent -> {
            if (!line3.isVisible()) {
                swapLines(line1, line2);
            } else
                swapLines(line3, line2);
        });
        down3.setOnAction(actionEvent -> {
            if (!line4.isVisible()) {
                swapLines(line1, line3);
            } else swapLines(line4, line3);
        });
        down4.setOnAction(actionEvent -> {
            if (!line5.isVisible()) {
                swapLines(line1, line4);
            } else swapLines(line5, line4);
        });
        down5.setOnAction(actionEvent -> {
            if (!line6.isVisible()) {
                swapLines(line1, line5);
            } else swapLines(line6, line5);
        });
        down6.setOnAction(actionEvent -> {
            swapLines(line1, line6);
        });

        AddQuestion.setOnAction(actionEvent -> {
            String ans1 = line1.getText().trim();
            String ans2 = line2.getText().trim();
            lines.add(ans1);
            lines.add(ans2);
            if (line3.isVisible()) {
                String ans3 = line3.getText().trim();
                lines.add(ans3);
                if (line4.isVisible()) {
                    String ans4 = line4.getText().trim();
                    lines.add(ans4);
                    if (line5.isVisible()) {
                        String ans5 = line5.getText().trim();
                        lines.add(ans5);
                        if (line6.isVisible()) {
                            String ans6 = line6.getText().trim();
                            lines.add(ans6);
                        }
                    }
                }
            }

            try {
                String question = QuestionField.getText().trim();
                String type = "Ранжирование";
                Question quesText = new Question(question, type);
                dbHandler.insertQuestion(quesText, idTest);
                dbHandler.insertRangQuestion(quesText, lines, idTest);
                AddQuestion.getScene().getWindow().hide();
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });
    }

    public void swapLines(TextField firstLine, TextField secondLine) {
        String swapHelp = firstLine.getText();
        firstLine.setText(secondLine.getText());
        secondLine.setText(swapHelp);
    }
}
