package kr.co.dinner41.vo;

import java.sql.Timestamp;
import java.util.Objects;

public class QnAVO {
    private int id;
    private QnATypeVO type;
    private UserVO user;
    private UserVO manager;
    private String title;
    private String content;
    private Timestamp questionDate;
    private String answerContent;
    private Timestamp answerDate;

    public QnAVO() {
    }

    public QnAVO(int id, QnATypeVO type, UserVO user, UserVO manager, String title, String content, Timestamp questionDate, String answerContent, Timestamp answerDate) {
        this.id = id;
        this.type = type;
        this.user = user;
        this.manager = manager;
        this.title = title;
        this.content = content;
        this.questionDate = questionDate;
        this.answerContent = answerContent;
        this.answerDate = answerDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public QnATypeVO getType() {
        return type;
    }

    public void setType(QnATypeVO type) {
        this.type = type;
    }

    public UserVO getUser() {
        return user;
    }

    public void setUser(UserVO user) {
        this.user = user;
    }

    public UserVO getManager() {
        return manager;
    }

    public void setManager(UserVO manager) {
        this.manager = manager;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Timestamp getQuestionDate() {
        return questionDate;
    }

    public void setQuestionDate(Timestamp questionDate) {
        this.questionDate = questionDate;
    }

    public String getAnswerContent() {
        return answerContent;
    }

    public void setAnswerContent(String answerContent) {
        this.answerContent = answerContent;
    }

    public Timestamp getAnswerDate() {
        return answerDate;
    }

    public void setAnswerDate(Timestamp answerDate) {
        this.answerDate = answerDate;
    }

    @Override
    public String toString() {
        return "QnAVO{" +
                "id=" + id +
                ", type=" + type +
                ", user=" + user +
                ", manager=" + manager +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", questionDate=" + questionDate +
                ", answerContent='" + answerContent + '\'' +
                ", answerDate=" + answerDate +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        QnAVO qnAVO = (QnAVO) o;
        return id == qnAVO.id &&
                Objects.equals(type, qnAVO.type) &&
                Objects.equals(user, qnAVO.user) &&
                Objects.equals(manager, qnAVO.manager) &&
                Objects.equals(title, qnAVO.title) &&
                Objects.equals(content, qnAVO.content) &&
                Objects.equals(questionDate, qnAVO.questionDate) &&
                Objects.equals(answerContent, qnAVO.answerContent) &&
                Objects.equals(answerDate, qnAVO.answerDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, type, user, manager, title, content, questionDate, answerContent, answerDate);
    }
}
