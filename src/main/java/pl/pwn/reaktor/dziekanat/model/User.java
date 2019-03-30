package pl.pwn.reaktor.dziekanat.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String login;

    private String password;

    @Enumerated(EnumType.STRING) //rola bedzie enumem
    private RoleEnum role;

    private boolean active;

    @OneToOne
    @JoinColumn(name = "nasz_student", nullable = true) //podaję nazwę kolumny po której bedzie łączył te tabele. Przy onetoone trzeba zrobić joincolun. tak samo onetomany. Many to many - join table!
    private Student student;

    public User(String login, String password, RoleEnum role, boolean active) {
        this.login = login;
        this.password = password;
        this.role = role;
        this.active = active;
    }
}
