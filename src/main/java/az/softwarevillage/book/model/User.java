package az.softwarevillage.book.model;

import az.softwarevillage.book.enums.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

@Entity
@Table(name = "users")
@Data
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

}
