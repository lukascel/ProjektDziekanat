package pl.pwn.reaktor.dziekanat.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "Student")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

// unikalny, niepowtarzalny generuje sie automatycznie skomplikowany kod i przypisuje sie do tego miejsca. Ale nie koresponduje łatwo z bazą danych
//    @Type(type = "uuid-binary")
//    @Column (nullable = false, unique = true)
//    private UUID index;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Embedded
    private Address address;
}
