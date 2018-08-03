package epam.zlobich.task6.entity.entitybd;

public class Question {

    private int id;
    private String user;
    private String askedQuestion;
    private String answer;

    public String getUser() {
        return user;
    }

    public int getId() {
        return id;
    }

    public String getAnswer() {
        return answer;
    }

    public String getAskedQuestion() {
        return askedQuestion;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public void setAskedQuestion(String askedQuestion) {
        this.askedQuestion = askedQuestion;
    }
}
