package kr.co.dinner41.command;

public class ReviewInsertCommand {
    private String score;
    private String content;

    public ReviewInsertCommand(){}

    public ReviewInsertCommand(String score, String content) {
        this.score = score;
        this.content = content;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "ReviewInsertCommand{" +
                "score='" + score + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
