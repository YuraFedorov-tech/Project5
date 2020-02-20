package ru.javamentor.model;
/*
 *
 *@Data 08.02.2020
 *@autor Fedorov Yuri
 *@project UserAdmin
 *
 */

import javax.persistence.*;

@Entity
@Table(name = "myUsers")
public class User {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;
    @Column(name = "password")
    private String password;
    @Column(name = "age")
    private int age;

    public User(String name, String password, int age, String role) {
        this.name = name;
        this.password = password;
        this.age = age;
        this.role = role;
    }

    public User(Long id, String name, String password, int age, String role) {
        this.name = name;
        this.password = password;
        this.age = age;
        this.role = role;
        this.id = id;
    }

    @Column(name = "role")
    String role;

    public User() {
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
