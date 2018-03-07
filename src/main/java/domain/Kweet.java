package domain;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Entity
@NamedQueries({
        @NamedQuery(
                name = "Kweet.allKweets",
                query = "SELECT k FROM Kweet k ORDER BY k.createdAt DESC"
        ),
        @NamedQuery(
                name = "Kweet.allKweetsFromUser",
                query = "SELECT k FROM Kweet k WHERE k.creator = :id ORDER BY k.createdAt DESC"
        ),
})
public class Kweet {

    @Id
    @GeneratedValue
    private Long id;
    private String message;
    private LocalDateTime createdAt;

    @ManyToOne
    private User creator;

    @ManyToMany
    private List<User> hearts;

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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public List<User> getHearts() {
        return hearts;
    }

    public void setHearts(List<User> hearts) {
        this.hearts = hearts;
    }

    public void addHeart(User heart) {
        this.hearts.add(heart);
    }

    public Kweet() {

    }

    public Kweet(String message, User creator, List<User> hearts) {
        this.id = id;
        this.message = message;
        this.createdAt = LocalDateTime.now();
        this.creator = creator;
        this.hearts = hearts;
    }

    public Kweet(String message, LocalDateTime createdAt, User creator, List<User> hearts) {
        this.id = id;
        this.message = message;
        this.createdAt = createdAt;
        this.creator = creator;
        this.hearts = hearts;
    }
}
