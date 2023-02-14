package ba.sum.fsre.muzika.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="mymusic")
public class MyMusicList {

    @Id
    private int id;
    private String name;
    private String author;
    private String link;


    public MyMusicList(int id, String name, String author, String link) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.link = link;
    }

    public MyMusicList() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }


}
