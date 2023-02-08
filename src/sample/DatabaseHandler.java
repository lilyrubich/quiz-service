package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.util.ArrayList;

import static sample.Const.*;

public class DatabaseHandler extends Configs {
    Connection dbConnection;

    public Connection getDbConnection()
            throws ClassNotFoundException, SQLException {
        String connectionString = "jdbc:mysql://" + dbHost + ":" + dbPort + "/"
                + dbName + "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";

        Class.forName("com.mysql.jdbc.Driver");

        dbConnection = DriverManager.getConnection(connectionString, dbUser, dbPass);
        return dbConnection;
    }

    //ДОБАВЛЕНИЕ НОВОГО ПОЛЬЗОВАТЕЛЯ
    public void signUpUser(User user) throws SQLException, ClassNotFoundException {
        String insert = "INSERT INTO " + USER_TABLE + "(" + USER_FIRSTNAME + "," + USER_LASTNAME + ","
                + USER_LOGIN + "," + USER_PASSWORD + "," + USER_STATUS + ")" + "VALUES(?,?,?,?,?)";

        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(insert);
            prSt.setString(1, user.getFirstname());
            prSt.setString(2, user.getLastname());
            prSt.setString(3, user.getLogin());
            prSt.setString(4, user.getPassword());
            prSt.setString(5, user.getStatus());

            prSt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    //АВТОРИЗАЦИЯ
    public ResultSet getUser(User user) {
        ResultSet resSet = null;
        String select = "SELECT * FROM " + USER_TABLE + " WHERE " + USER_LOGIN + "=? AND " + USER_PASSWORD + "=?";
        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(select);
            prSt.setString(1, user.getLogin());
            prSt.setString(2, user.getPassword());
            resSet = prSt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return resSet;
    }

    /*public int getQuestionCount(Test test, int idTest){
        ResultSet resSet = null;
        String select = "SELECT COUNT(*) FROM " + QUESTION_TABLE + " WHERE " + TEST_ID_TEST + "=?";
        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(select);
            prSt.setString(1, user.getLogin());
            resSet = prSt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return 0;
    }*/

    //ДОБАВЛЕНИЕ ТЕСТА
    public int insertTest(Test test) throws SQLException, ClassNotFoundException {
        ResultSet resSet = null;
        String insert = "INSERT INTO " + TEST_TABLE + "(" + TEST_NAME + "," + TEST_DESCRIPTION + ","
                + TEST_DISCIPLINE + "," + TEST_LEVEL + "," + TEST_SEMESTER + ")" + "VALUES(?,?,?,?,?)";
        String select = "SELECT " + TEST_ID + " FROM " + TEST_TABLE + " WHERE " + TEST_NAME + "=?";
        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(insert);
            prSt.setString(1, test.getNametest());
            prSt.setString(2, test.getDescription());
            prSt.setString(3, test.getDiscipline());
            prSt.setString(4, test.getLevel());
            prSt.setString(5, test.getSemester());
            prSt.executeUpdate();
            prSt.close();
            prSt = getDbConnection().prepareStatement(select);
            prSt.setString(1, test.getNametest());
            resSet = prSt.executeQuery();
            prSt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        resSet.first();
        int resultId = resSet.getInt(TEST_ID);
        return resultId;
    }


    public void insertQuestion(Question ques, int idTest) throws SQLException, ClassNotFoundException {

        String insertQues = "INSERT INTO " + QUESTION_TABLE + "(" + QUESTION_TEXT + "," +
                QUESTION_TYPE + "," + TEST_ID_TEST + ")" + "VALUES(?,?,?)";
        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(insertQues);
            prSt.setString(1, ques.getQuestionText());
            prSt.setString(2, ques.getQuestionType());
            prSt.setString(3, String.valueOf(idTest));
            prSt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void insertRangQuestion(Question ques, ArrayList<String> ans, int idTest){
        int i=0;
        ResultSet res = null;
        String selectIdQues = "SELECT " + QUESTION_ID + " FROM " + QUESTION_TABLE + " WHERE " +
                QUESTION_TEXT + "=?" + " AND " + TEST_ID_TEST + "=?" + ";";
        String insertAns = "INSERT INTO " + RANG_TABLE + " (" + RANG_ANSWER
                + "," + RANG_POSITION + "," + QUESTION_ID_QUESTION + ") " + "VALUES (?,?,?);";
        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(selectIdQues);
            prSt.setString(1, ques.getQuestionText());
            prSt.setString(2, String.valueOf(idTest));
            res = prSt.executeQuery();
            res.first();

            for (String answer: ans) {
                prSt = getDbConnection().prepareStatement(insertAns);
                prSt.setString(1, answer);
                prSt.setString(2, String.valueOf(i));
                prSt.setString(3, res.getString(QUESTION_ID));
                prSt.executeUpdate();
                i++;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    //еще не опробовано
    ///////////?????????????????????????
    public void insertMatchQuestion(Question ques, ObservableList<Answer> list, int idTest) {
        int i = 0, status = 0;
        ResultSet res = null;
        String selectIdQues = "SELECT " + QUESTION_ID + " FROM " + QUESTION_TABLE + " WHERE " +
                QUESTION_TEXT + "=?" + " AND " + TEST_ID_TEST + "=?" + ";";
        String insertAns = "INSERT INTO " + MATCH_TABLE + " (" + MATCH_ANSWER1
                + "," + MATCH_ANSWER2 + "," + QUESTION_ID_QUESTION + "," + MATCH_STATUS + ") " + "VALUES (?,?,?,?);";
        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(selectIdQues);
            prSt.setString(1, ques.getQuestionText());
            prSt.setString(2, String.valueOf(idTest));
            res = prSt.executeQuery();
            res.first();
            while (i < list.size()) {
                prSt = getDbConnection().prepareStatement(insertAns);
                prSt.setString(1, list.get(i).getNameAnswer());
                prSt.setString(2, list.get(i).getNameAnswer1());
                if (list.get(i).getStatus().equals("Верно")) {
                    status = 1;
                } else {
                    status = 0;
                }
                prSt.setString(3, res.getString(QUESTION_ID));
                prSt.setString(4, String.valueOf(status));
                prSt.executeUpdate();
                i++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    public void insertTextAnswer(Question ques, ObservableList<Answer> list, int idTest) {
        int i = 0;
        ResultSet res = null;
        String selectIdQues = "SELECT " + QUESTION_ID + " FROM " + QUESTION_TABLE + " WHERE " +
                QUESTION_TEXT + "=?" + " AND " + TEST_ID_TEST + "=?" + ";";
        String insertAns = "INSERT INTO " + TEXT_ANSWER_TABLE + "(" + TEXT_ANSWER_CORRECT
                + "," + QUESTION_ID_QUESTION + ")" + "VALUES(?,?)";
        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(selectIdQues);
            prSt.setString(1, ques.getQuestionText());
            prSt.setString(2, String.valueOf(idTest));
            res = prSt.executeQuery();
            res.first();
            while (i < list.size()) {
                prSt = getDbConnection().prepareStatement(insertAns);
                prSt.setString(1, list.get(i).getNameAnswer());
                prSt.setString(2, res.getString(QUESTION_ID));
                prSt.executeUpdate();
                i++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void insertSelectionAnswer(Question ques, ObservableList<Answer> list, int idTest) {
        int i = 0, status = 0;
        ResultSet res = null;
        String selectIdQues = "SELECT " + QUESTION_ID + " FROM " + QUESTION_TABLE + " WHERE " +
                QUESTION_TEXT + "=?" + " AND " + TEST_ID_TEST + "=?" + ";";
        String insertAns = "INSERT INTO " + SELECTION_TABLE + "(" + SELECTION_VERSION + "," +
                SELECTION_STATUS + "," + QUESTION_ID_QUESTION + ")" + "VALUES(?,?,?)";
        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(selectIdQues);
            prSt.setString(1, ques.getQuestionText());
            prSt.setString(2, String.valueOf(idTest));
            res = prSt.executeQuery();
            res.first();
            while (i < list.size()) {
                prSt = getDbConnection().prepareStatement(insertAns);
                prSt.setString(1, list.get(i).getNameAnswer());

                if (list.get(i).getStatus().equals("Верный")) {
                    status = 1;
                } else {
                    status = 0;
                }
                prSt.setString(2, String.valueOf(status));
                prSt.setString(3, res.getString(QUESTION_ID));
                prSt.executeUpdate();
                i++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    //select id test
    public int selectIdTest(Test test) {
        int id = 0;
        ResultSet res = null;
        String select = "SELECT " + TEST_ID + " FROM " + Const.TEST_TABLE + " WHERE " + Const.TEST_NAME + "=?" + ";";
        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(select);
            prSt.setString(1, test.getNametest());
            res = prSt.executeQuery();
            res.first();
            id = res.getInt(TEST_ID);
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        return id;
    }

    //select id question
    public int selectIdQues(Question ques, int idTest) {
        int idQues = 0;
        ResultSet res = null;
        String select = "SELECT " + QUESTION_ID + " FROM " + QUESTION_TABLE + " WHERE " +
                QUESTION_TEXT + "=?" + " AND " + TEST_ID_TEST + "=?" + ";";
        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(select);
            prSt.setString(1, ques.getQuestionText());
            prSt.setString(2, String.valueOf(idTest));
            res = prSt.executeQuery();
            res.first();
            idQues = res.getInt(QUESTION_ID);
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        return idQues;
    }

    //select questions
    public ObservableList<Question> tableQuestion(int id) {
        ResultSet resSet = null;
        ObservableList<Question> res = FXCollections.observableArrayList();
        String select = "SELECT * FROM " + Const.QUESTION_TABLE + " WHERE " + TEST_ID_TEST + "=?";
        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(select);
            prSt.setString(1, String.valueOf(id));
            resSet = prSt.executeQuery();
            String quesText, quesType;
            while (resSet.next()) {
                quesText = resSet.getString("question_text");
                quesType = resSet.getString("type");
                res.add(new Question(quesText, quesType));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return res;
    }

    //select tests
    public ObservableList<Test> tableTest() {
        ResultSet resSet = null;
        ObservableList<Test> res = FXCollections.observableArrayList();
        String select = "SELECT * FROM " + Const.TEST_TABLE;
        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(select);
            resSet = prSt.executeQuery();
            String nameTest, description, discipline, level, semester;
            while (resSet.next()) {
                nameTest = resSet.getString("name");
                description = resSet.getString("description");
                discipline = resSet.getString("discipline");
                level = resSet.getString("level");
                semester = resSet.getString("semester");
                res.add(new Test(nameTest, description, discipline, level, semester));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return res;
    }

    //select test
    public Test selectTest(int idTest) {
        ResultSet resSet = null;
        Test res = new Test();
        String select = "SELECT * FROM " + Const.TEST_TABLE + " WHERE " + TEST_ID + "=?";
        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(select);
            prSt.setString(1, String.valueOf(idTest));
            resSet = prSt.executeQuery();
            resSet.first();
            String nameTest, description, discipline, level, semester;
            nameTest = resSet.getString("name");
            description = resSet.getString("description");
            discipline = resSet.getString("discipline");
            level = resSet.getString("level");
            semester = resSet.getString("semester");
            res = new Test(nameTest, description, discipline, level, semester);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return res;
    }

    /////////////////////////////////////////переделать/////////////////////////////////////////////////////////////////
    //delete questions
    ////////////////
    //баг: при удалении выборки при наличинии идентичного названия вопроса удаляются и ее ответы тоже
    public void deleteQuestion(Question question, int idTest) {
        String tableAns = null;
        switch (question.getQuestionType()) {
            case "Текстовый ответ":
                tableAns = TEXT_ANSWER_TABLE;
                break;
            case "Выборка":
                tableAns = SELECTION_TABLE;
                break;
            case "Сопоставление":
                tableAns = MATCH_TABLE;
                break;
            case "Ранжирование":
                tableAns = RANG_TABLE;
            case "Матрица":
                tableAns = MATRIX_TABLE;
            // и так далее , потом доделать в соответствии с названиями таблиц
        }
        ResultSet res = null;
        int idQues = 0;
        String select = "SELECT " + QUESTION_ID + " FROM " + QUESTION_TABLE + " WHERE " +
                TEST_ID_TEST + "=?" + " AND " + QUESTION_TEXT + "=?" + ";";
        String delete1 = "DELETE FROM " + tableAns + " WHERE " + QUESTION_ID_QUESTION + "=?";
        String delete2 = "DELETE FROM " + QUESTION_TABLE + " WHERE " + QUESTION_ID + "=?";
        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(select);
            prSt.setString(1, String.valueOf(idTest));
            prSt.setString(2, question.getQuestionText());
            res = prSt.executeQuery();
            res.first();
            prSt = getDbConnection().prepareStatement(delete1);
            prSt.setString(1, res.getString(QUESTION_ID));
            prSt.executeUpdate();
            prSt = getDbConnection().prepareStatement(delete2);
            prSt.setString(1, res.getString(QUESTION_ID));
            prSt.executeUpdate();
            prSt.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }

    //delete tests
    //НАДО ДОБАВИТЬ В МАССИВ answers все остальные виды вопросов
    public void deleteTest(Test test) {

        ResultSet resIdQues = null, resIdTest = null;
        String tabledelete = null;
        String answers[] = new String[]{TEXT_ANSWER_TABLE, SELECTION_TABLE};

        String select = "SELECT " + TEST_ID + " FROM " + TEST_TABLE + " WHERE " + TEST_NAME + "=?" + ";";

        String select1 = "SELECT " + QUESTION_ID + " FROM " + QUESTION_TABLE + " WHERE " + TEST_ID_TEST + "=?;";

        String deleteQues = "DELETE FROM " + QUESTION_TABLE + " WHERE " + QUESTION_ID + "=?;";

        String delete = "DELETE FROM " + TEST_TABLE + " WHERE " + TEST_ID + "=?";
        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(select);
            prSt.setString(1, test.getNametest());
            resIdTest = prSt.executeQuery();
            resIdTest.first();  //ПОЛУЧИЛИ 1 ТЕСТ АЙДИ

            prSt = getDbConnection().prepareStatement(select1);
            prSt.setString(1, resIdTest.getString(TEST_ID));
            resIdQues = prSt.executeQuery(); //ТУТ ПОЛУЧИЛИ ВСЕ АЙДИ ВОПРОСОВ
            resIdQues.first();

            if (resIdQues.first()) {
                do {
                    for (int i = 0; i < answers.length; i++) {  //удаляем все ответы
                        tabledelete = answers[i];

                        String join = "DELETE " + tabledelete + " FROM " + tabledelete + " INNER JOIN " + QUESTION_TABLE +
                                " ON " + QUESTION_ID_QUESTION + "=" + QUESTION_ID + " WHERE " + QUESTION_ID_QUESTION + "=?;";

                        prSt = getDbConnection().prepareStatement(join);
                        prSt.setString(1, resIdQues.getString(QUESTION_ID));
                        prSt.executeUpdate();
                    }
                    prSt = getDbConnection().prepareStatement(deleteQues); //удаляем сам вопрос
                    prSt.setString(1, resIdQues.getString(QUESTION_ID));
                    prSt.executeUpdate();

                } while (resIdQues.next());
            }
            prSt = getDbConnection().prepareStatement(delete);
            prSt.setString(1, resIdTest.getString(TEST_ID));
            prSt.executeUpdate();
            prSt.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }
}

