package jm.task.core.jdbc.model;

import javax.persistence.*;

@Entity
@Table(name="userTable")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "age")
    private byte age;
    @Column(name = "name")
    private String name;
    @Column(name = "lastname")
    private String lastName;

    public void setAge(byte age) {
        this.age = age;
    }

    public void setLastname(String lastname) {
        this.lastName = lastname;
    }

    public String getLastname() {
        return lastName;
    }

    public User(Long id, String name, String lastname, byte age) {
        this.id = id;
        this.name = name;
        this.lastName = lastname;
        this.age = age;
    }

    public User() {

    }

    public User(String name, String lastName, Byte age) {
        this.name = name;
        this.lastName = lastName;
        this.age = age;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Byte getAge() {
        return age;
    }

    public void setAge(Byte age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "[ user ID = " + id
                + ", userName = " + name
                + ", userLastName = " + lastName
                + ", user age = " + age + "]";
    }
}
