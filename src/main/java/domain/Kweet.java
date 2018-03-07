package domain;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Kweet {

    @Id
    @GeneratedValue
    private Long id;
    private String message;
    private Date createdAt;
    private User creator;
    private Integer hearts;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public Integer getHearts() {
        return hearts;
    }

    public void setHearts(Integer hearts) {
        this.hearts = hearts;
    }

    public Kweet() {

    }

    public Kweet(String message, Date createdAt, User creator, Integer hearts) {
        this.id = id;
        this.message = message;
        this.createdAt = createdAt;
        this.creator = creator;
        this.hearts = hearts;
    }
}
