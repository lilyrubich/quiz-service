package sample;

public class Question {

    private String questionText;
    private String questionType;
    private String Answer;

    public String getAnswer() {
        return Answer;
    }

    public void setAnswer(String answer) {
        this.Answer = answer;
    }

    public String getQuestionText() {
        return questionText;
    }

    public String getQuestionType() {
        return questionType;
    }

    //открытый вопрос
    public Question(String questionText, String questionType, String Answer) {
        this.questionText = questionText;
        this.questionType = questionType;
        this.Answer = Answer;
    }

    public Question(String questionText, String questionType) {
        this.questionText = questionText;
        this.questionType = questionType;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public void setQuestionType(String questionText) {
        this.questionType = questionType;
    }

}
