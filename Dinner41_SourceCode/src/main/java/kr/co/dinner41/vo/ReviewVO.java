package kr.co.dinner41.vo;

import java.sql.Timestamp;
import java.util.Objects;

public class ReviewVO {
    private int id;
    private StoreVO store;
    private UserVO user;
    private String content;
    private int score;
    private Timestamp date;
    private int order_id;

    public ReviewVO() {
    }

    public ReviewVO(int id, StoreVO store, UserVO user, String content, int score, Timestamp date, int order_id) {
        this.id = id;
        this.store = store;
        this.user = user;
        this.content = content;
        this.score = score;
        this.date = date;
        this.order_id = order_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public StoreVO getStore() {
        return store;
    }

    public void setStore(StoreVO store) {
        this.store = store;
    }

    public UserVO getUser() {
        return user;
    }

    public void setUser(UserVO user) {
        this.user = user;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReviewVO reviewVO = (ReviewVO) o;
        return id == reviewVO.id &&
                score == reviewVO.score &&
                order_id == reviewVO.order_id &&
                Objects.equals(store, reviewVO.store) &&
                Objects.equals(user, reviewVO.user) &&
                Objects.equals(content, reviewVO.content) &&
                Objects.equals(date, reviewVO.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, store, user, content, score, date, order_id);
    }

    @Override
    public String toString() {
        return "ReviewVO{" +
                "id=" + id +
                ", store=" + store +
                ", user=" + user +
                ", content='" + content + '\'' +
                ", score=" + score +
                ", date=" + date +
                ", order_id=" + order_id +
                '}';
    }
}
