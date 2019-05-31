package gateway.adaptors.models;

import javax.persistence.*;
import java.util.Date;


public class User {

    public User() { }

    public User(String username, String password, String firstname, String surname, Date lastUpdated) {
        this.username = username;
        this.password = password;
        this.firstname = firstname;
        this.surname = surname;
        this.lastUpdated = lastUpdated;
    }

    public User(String username, String password, String firstname, String surname) {
        this.username = username;
        this.password = password;
        this.firstname = firstname;
        this.surname = surname;
        this.lastUpdated=new Date();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;


    @Column(name = "firstname")
    private String firstname;


    @Column(name = "surname")
    private String surname;


    @Column(name = "lastUpdated")
    private Date lastUpdated;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Date getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(Date lastUpdated) {
        this.lastUpdated = lastUpdated;
    }
}
