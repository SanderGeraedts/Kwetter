package domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Hashtag {

    @Id
    @GeneratedValue
    private Long id;
    private String tag;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public Hashtag() {
    }

    public Hashtag(Long id, String tag) {
        this.id = id;
        this.tag = tag;
    }
}
