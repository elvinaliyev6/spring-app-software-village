package az.softwarevillage.book.model;

import az.softwarevillage.book.enums.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

@Entity
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String email;
    private String password;
    private String firstName;
    private String lastName;

    @Column(name = "phone")
    private String phone;

    @Column(name = "status")
    @ColumnDefault(value = "1")
    private Integer status;

    @Enumerated(EnumType.STRING)
    private Role role;

    public User(User user) {
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.role = user.getRole();
        this.firstName = user.getFirstName();
        this.lastName =user.getLastName();
        this.id = user.getId();
        this.password = user.getPassword();
    }
}
