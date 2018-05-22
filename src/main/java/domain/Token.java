package domain;

import javax.persistence.*;

@Entity
@Table(name = "AuthToken")
@NamedQueries({
        @NamedQuery(name = "Token.getUser",
                query = "SELECT u FROM User u, Token t WHERE u.id = t.user.id AND t.key = :key")
        ,
        @NamedQuery(name = "Token.getTokenByUserId",
                query = "SELECT t FROM User u, Token t WHERE u.id = t.user.id AND t.user.id = :userId"
        ),
})
public class Token {

    @Id
    @GeneratedValue
    @Column(name = "ID")
    private Long id;
    private String key;

    @ManyToOne
    private User user;

    public Token() {
    }

    public Token(String key, User user) {
        this.key = key;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
