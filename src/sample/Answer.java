package sample;

public class Answer {

    private String nameAnswer;
    private String nameAnswer1;
    private String status;

    public String getNameAnswer1() {
        return nameAnswer1;
    }

    public void setNameAnswer1(String nameAnswer1) {
        this.nameAnswer1 = nameAnswer1;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getNameAnswer() {
        return nameAnswer;
    }

    public void setNameAnswer(String nameAnswer) {
        this.nameAnswer = nameAnswer;
    }

    public Answer(String nameAnswer) {
        this.nameAnswer = nameAnswer;
    }

    public Answer(String nameAnswer, String status){
        this.nameAnswer = nameAnswer;
        this.status=status;
    }

    public Answer(String nameAnswer, String nameAnswer1, String status){
        this.nameAnswer = nameAnswer;
        this.nameAnswer1 = nameAnswer1;
        this.status = status;
    }

}

