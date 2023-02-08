package sample.controllers;

import java.io.IOException;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import sample.DatabaseHandler;
import sample.Test;


import javafx.collections.transformation.SortedList;
import javafx.scene.control.TextField;
import javafx.scene.image.*;

public class ControllerMainWindow {

    private static int idTest;

    public static int getIdTest() {
        return idTest;
    }

    @FXML
    private ResourceBundle resources;

    @FXML
    private Button NewTest, newTest, Update, DeleteTest;

    @FXML
    private TableColumn<Test, String> name, description, discipline, level, semester, questionCount;

    @FXML
    private TableView<Test> TableTests;

    @FXML
    private TextField searchField;

    @FXML
    private ImageView image;

    private ObservableList<Test> masterData = FXCollections.observableArrayList();

    @FXML
    void initialize() {

        DatabaseHandler dbHandler = new DatabaseHandler();
        masterData = dbHandler.tableTest();
        TableTests.setItems(masterData);
        name.setCellValueFactory(new PropertyValueFactory<>("nametest"));
        description.setCellValueFactory(new PropertyValueFactory<>("description"));
        discipline.setCellValueFactory(new PropertyValueFactory<>("discipline"));
        level.setCellValueFactory(new PropertyValueFactory<>("level"));
        semester.setCellValueFactory(new PropertyValueFactory<>("semester"));

        //TableTests.getColumns().addAll(questionCount);
        //questionCount.setCellValueFactory(new PropertyValueFactory<>(""));

        //Test rowData = row.getItem();
        //idTest = dbHandler.selectIdTest(rowData);

        NewTest.setOnAction(event -> {
            openNewScene("/sample/view/StartCreateTest.fxml");
        });

        newTest.setOnAction(event -> {
            openNewScene("/sample/view/StartCreateTest.fxml");
        });

        //фиктивное удаление
        DeleteTest.setOnAction(event -> {
            Test selectedTest = TableTests.getSelectionModel().getSelectedItem();
            dbHandler.deleteTest(selectedTest);
            //обновление таблицы
            TableTests.setItems(dbHandler.tableTest());

            //masterData.remove(selectedTest);
            //TableTests.setItems(masterData);
        });

        //обновление таблицы
        Update.setOnAction(event -> {
            TableTests.setItems(dbHandler.tableTest());
        });
        //двойной клик
        TableTests.setRowFactory(tv -> {
            TableRow<Test> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    Test rowData = row.getItem();
                    idTest = dbHandler.selectIdTest(rowData);
                    System.out.println("Double click on: " + rowData.getNametest() + idTest);
                    openNewScene("/sample/view/CreateTestMain.fxml");
                }
            });
            return row;
        });

        FilteredList<Test> filteredData = new FilteredList<>(masterData, p -> true);
        searchField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(test -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                if (test.getDescription().toLowerCase().contains(lowerCaseFilter) ||
                        test.getDiscipline().toLowerCase().contains(lowerCaseFilter) ||
                        test.getLevel().toLowerCase().contains(lowerCaseFilter) ||
                        test.getSemester().toLowerCase().contains(lowerCaseFilter) ||
                        test.getNametest().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                }
                return false;
            });
        });
        SortedList<Test> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(TableTests.comparatorProperty());
        TableTests.setItems(sortedData);
    }

    public void openNewScene(String window) {
        NewTest.getScene().getWindow().hide();

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


