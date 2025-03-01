package com.vector.crudapp.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;


@Entity
@Table(name="student")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @JsonProperty("firstname")  // Map JSON "firstname" -> Java variable
    @Column(name="first_name")
    private String firstname;

    @JsonProperty("lastname")
    @Column(name="last_name")
    private String lastname;

    @JsonProperty("email")
    @Column(name="email")
    private String email;


//constructors

    public Student(){

    }

    public Student(String firstname, String lastname, String email) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
    }

    //getter setter

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    //to string

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
