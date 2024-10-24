package ru.kata.spring.boot_security.demo.model;

import lombok.*;
import javax.persistence.*;
import java.util.Set;

@Getter @Setter
@NoArgsConstructor
@ToString

@Entity
@Table(name = "person")
public class Person {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "lastname")
    private String lastName;

    @Column(name = "username", unique = true, nullable = false)
    private String userName;

    @Column(name = "password", nullable = false)
    private String password;

    @ManyToMany(mappedBy = "persons",
            cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @ToString.Exclude
    private Set<Role> roles;

    public Person(String name, String lastName, String userName, String password) {
        this.name = name;
        this.lastName = lastName;
        this.userName = userName;
        this.password = password;
    }
}
