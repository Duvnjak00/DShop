package ba.sum.fsre.muzika.model;

import jakarta.persistence.*;

@Entity
@Table(name="music")
public class Music {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
   private int id;
    @Column(unique = true, nullable = false, length = 50)
    private String name;
    @Column(unique = true, nullable = false, length = 50)
    private String author;
    @Column(unique = true, nullable = false, length = 50)
    private String link;

    public Music(int id, String name, String author, String link) {
        super();
        this.id = id;
        this.name = name;
        this.author = author;
        this.link = link;
    }
    public Music( ) {
        super();
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
