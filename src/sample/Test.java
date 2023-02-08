package sample;

public class Test {
    private String nametest;
    private String description;
    private String discipline;
    private String level;
    private String semester;

    public Test() {
    }

    public Test(String nametest, String description, String discipline, String level, String semester) {
        this.nametest = nametest;
        this.description = description;
        this.discipline = discipline;
        this.level = level;
        this.semester = semester;
    }

    public String getNametest() {
        return nametest;
    }

    public void setNametest(String nametest) {
        this.nametest = nametest;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDiscipline() {
        return discipline;
    }

    public void setDiscipline(String discipline) {
        this.discipline = discipline;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }
}
