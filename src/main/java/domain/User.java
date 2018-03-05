package domain;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "KwetterUser")
@NamedQuery(name = "User.allUser", query = "SELECT u FROM User u")
public class User {

    @Id
    @GeneratedValue
    @Column(name = "ID")
    private Long id;
    private String name;
    private String email;
    private String passwordHash;
    private String description;
    private String avatar;          //maybe change to Image, don't know yet
    private UserRole role;

    @ManyToMany
    @JoinTable(
            name = "FOLLOWS",
            joinColumns = @JoinColumn(name = "UserId", referencedColumnName = "ID"),
            inverseJoinColumns = @JoinColumn(name = "Follows", referencedColumnName = "ID")
    )
    @JsonbTransient
    private List<User> following;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public List<User> getFollowing() {
        return following;
    }

    public void setFollowing(List<User> following) {
        this.following = following;
    }

    public void addFollowing(User following) {
        this.following.add(following);
    }

    public User() {

    }

    public User(String name, String email, String passwordHash, String description, String avatar) {
        this.name = name;
        this.email = email;
        this.passwordHash = passwordHash;
        this.description = description;
        this.avatar = avatar;
        this.role = UserRole.USER;
    }

    public User(String name, String email, String passwordHash, String description, String avatar, UserRole role) {
        this.name = name;
        this.email = email;
        this.passwordHash = passwordHash;
        this.description = description;
        this.avatar = avatar;
        this.role = role;
    }
}
