package domain;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@NamedQueries({
        @NamedQuery(
                name = "Notification.allNotificationsFromUser",
                query = "SELECT n FROM Notification n WHERE n.user = :id ORDER BY n.createdAt DESC"
        ),
        @NamedQuery(
                name = "Notification.allUnreadNotificationsFromUser",
                query = "SELECT n FROM Notification n WHERE n.user = :id AND n.read = FALSE ORDER BY n.createdAt DESC"
        ),
})
public class Notification {

    @Id
    @GeneratedValue
    private Long id;
    private String message;

    @ManyToOne
    @JsonbTransient
    private User user;
    @Column(name = "userRead")
    private Boolean read;
    private LocalDateTime createdAt;

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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Boolean getRead() {
        return read;
    }

    public void setRead(Boolean read) {
        this.read = read;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Notification() {
    }

    public Notification(String message, User user) {
        this.message = message;
        this.user = user;
        this.read = false;
        this.createdAt = LocalDateTime.now();
    }

    public Notification(String message, User user, Boolean read, LocalDateTime createdAt) {
        this.message = message;
        this.user = user;
        this.read = read;
        this.createdAt = createdAt;
    }
}
