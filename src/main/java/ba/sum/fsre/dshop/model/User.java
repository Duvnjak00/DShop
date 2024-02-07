package ba.sum.fsre.dshop.model;

import javax.persistence.*;
import javax.validation.constraints.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(nullable = false)
    private String firstname;

    private String lastname;
    @Column(nullable = false, unique = true)
    @Email(message = "{errors.invail_demail}")
    private String email;

    private String password;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_role",
            joinColumns = {@JoinColumn(name = "USER_ID", referencedColumnName = "ID")},
                inverseJoinColumns = {@JoinColumn( name ="ROLE_ID", referencedColumnName = "ID")}
    )
    private List<Role> roles;

    public User() {
    }

    public User (User user){
        this.firstname = user.getFirstname();
        this.lastname = user.getLastname();
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.roles = user.getRoles();
    }


}
