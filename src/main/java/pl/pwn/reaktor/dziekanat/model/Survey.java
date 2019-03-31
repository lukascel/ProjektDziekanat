package pl.pwn.reaktor.dziekanat.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
@Data
@NoArgsConstructor

@Entity
@Table(name = "survey")
public class Survey {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, name="imie")
    private String firstName;

    @Column(name="nazwisko", nullable = false)
    private String lastName;

    private String mail;

    private String phone;

    private Boolean java;

    private Boolean python;

    private Boolean other;

    @Column(name = "other_desc")
    private String otherDesc;

    private String language;

    private String course;

    @Column(name = "student_id")
    private int studentId;

    public Survey(String firstName, String lastName, String mail, String phone, Boolean java, Boolean python, Boolean other, String otherDesc, String languege, String course, int studentId) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.mail = mail;
        this.phone = phone;
        this.java = java;
        this.python = python;
        this.other = other;
        this.otherDesc = otherDesc;
        this.language = languege;
        this.course = course;
        this.studentId = studentId;
    }
}
