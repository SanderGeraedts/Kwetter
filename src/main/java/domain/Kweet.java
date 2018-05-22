package domain;

import javax.json.Json;
import javax.json.JsonObject;
import javax.persistence.*;
import java.net.URI;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

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
    private Set<User> hearts;

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

    public Set<User> getHearts() {
        return hearts;
    }

    public Integer getNumberOfHearts() {
        return hearts.size();
    }

    public void setHearts(Set<User> hearts) {
        this.hearts = hearts;
    }

    public void addHeart(User heart) {
        this.hearts.add(heart);
    }

    public void removeHeart(User heart) {
        this.hearts.remove(heart);
    }

    public Kweet() {

    }

    public Kweet(String message, User creator) {
        this.id = id;
        this.message = message;
        this.createdAt = LocalDateTime.now();
        this.creator = creator;
        this.hearts = new HashSet<User>();
    }

    public Kweet(String message, LocalDateTime createdAt, User creator, Set<User> hearts) {
        this.id = id;
        this.message = message;
        this.createdAt = createdAt;
        this.creator = creator;
        this.hearts = hearts;
    }

    public JsonObject toJson(URI self, String base) {
        return Json.createObjectBuilder()
                .add("id", this.id)
                .add("message", this.message)
                .add("createdAt", this.createdAt.toString())
                .add("_links", Json.createObjectBuilder()
                        .add("self", Json.createObjectBuilder()
                                .add("rel", "self")
                                .add("href", self.toString())
                        )
                        .add("creator", Json.createObjectBuilder()
                                .add("rel", "creator")
                                .add("href", base + "users/" + this.creator.getId())
                        )
                )
                .build();
    }
}
