package kr.co.dinner41.command;

public class QnAAnswerCommand {
    private String content;

    public QnAAnswerCommand(){}

    public QnAAnswerCommand(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
