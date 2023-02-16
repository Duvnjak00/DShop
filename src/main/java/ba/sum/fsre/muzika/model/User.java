package ba.sum.fsre.muzika.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.util.Set;

@Entity
@Table(name="users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message="Molimo unesite Vašu email adresu.")
    @Email(message = "Unesite ispravnu email adresu.")
    @Column(unique = true, nullable = false, length = 50)
    private String email;

    @NotBlank(message="Molimo unesite Vašu lozinku.")
    @Column(nullable = false)
    private String password;
    @NotBlank(message="Molimo ponovite Vašu lozinku.")
    private String passwordRepeat;

    private boolean passwordsEqual;

    public void setPasswordsEqual(boolean passwordsEqual) {
        this.passwordsEqual = passwordsEqual;
    }

    public String getPasswordRepeat() {
        return passwordRepeat;
    }

    public void setPasswordRepeat(String passwordRepeat) {
        this.passwordRepeat = passwordRepeat;
    }


    @Size(min=2, max=30, message="Vaše ime mora biti između 2 i 30 znakova duljine.")
    @Column(nullable = false, length = 30)
    private String firstname;


    @Size(min=2, max=30, message="Vaše prezime mora biti između 2 i 30 znakova duljine.")
    @Column(nullable = false, length = 30)
    private String lastname;

    public User() {}

    public User(Long id, String email, String password, String firstname, String lastname) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    @AssertTrue(message="Lozinke se moraju podudarati.")
    public boolean isPasswordsEqual () {
        try {
            return this.password.equals(this.passwordRepeat);
        } catch (NullPointerException e) {
            return false;
        }
    }

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Music> myMusic;

    public Set<Music> getMyMusic() {
        return myMusic;
    }

    public void setMyMusic(Set<Music> myMusic) {
        this.myMusic = myMusic;
    }

    public void addMyMusic (Music m) {
        this.myMusic.add(m);
    }

    public void removeMyMusic (Music m) {
        this.myMusic.remove(m);
    }
}