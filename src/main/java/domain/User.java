package domain;

import boundary.rest.KweetResource;
import boundary.rest.UserResource;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.*;
import java.net.URI;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "KwetterUser")
@NamedQueries({
        @NamedQuery(name = "User.allUser",
                query = "SELECT u FROM User u"
        ),
        @NamedQuery(name = "User.findUserByEmail",
                query = "SELECT u FROM User u WHERE u.email = :email"
        ),
        @NamedQuery(name = "User.findUserByCredentials",
                query = "SELECT u FROM User u WHERE u.email = :email AND u.password = :password"
        ),
})
public class User {

    @Id
    @GeneratedValue
    @Column(name = "ID")
    private Long id;
    private String name;
    private String email;

    //    @JsonbTransient
    @Column(name = "USERPASSWORD")
    private String password;
    private String description;
    private String avatar;          //maybe change to Image, don't know yet
    private UserRole role;

    @ManyToMany
    @JsonbTransient
    private List<User> following;

    @ManyToMany(mappedBy = "following")
    @JsonbTransient
    private List<User> followers;

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public List<User> getFollowers() {
        return followers;
    }

    public void setFollowers(List<User> followers) {
        this.followers = followers;
    }

    public User() {
    }

    public User(String name, String email, String password, String description, String avatar) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.description = description;
        this.avatar = avatar;
        this.role = UserRole.USER;

        this.followers = new ArrayList<>();
        this.following = new ArrayList<>();
    }

    public User(String name, String email, String password, String description, String avatar, UserRole role) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.description = description;
        this.avatar = avatar;
        this.role = role;

        this.followers = new ArrayList<>();
        this.following = new ArrayList<>();
    }

    public JsonObject toJson(URI self) {

        return Json.createObjectBuilder()
                .add("id", this.id)
                .add("name", this.name)
                .add("email", this.email)
                .add("description", this.description)
                .add("avatar", this.avatar)
                .add("role", this.role.toString())
                .add("_links", Json.createObjectBuilder()
                        .add("self", Json.createObjectBuilder()
                                .add("rel", "self")
                                .add("href", self.toString())
                        )
                        .add("followers", Json.createObjectBuilder()
                                .add("rel", "followers")
                                .add("href", self.toString() + "/followers")
                        )
                        .add("follows", Json.createObjectBuilder()
                                .add("rel", "follows")
                                .add("href", self.toString() + "/follows")
                        )
                )
                .build();
    }
}
