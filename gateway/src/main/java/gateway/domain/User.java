package gateway.domain;

import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "username")
    String username;
    @Column(name = "password")
    private String password;
    @Column(name = "firstname")
    private String firstname;
    @Column(name = "surname")
    private String surname;
    @Column(name = "lastUpdated")
    private Date lastUpdated;



    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(
                    name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "role_id", referencedColumnName = "id"))
    private Collection<Role> roles;

    @Column(name = "accessToken")
    String accessToken;
    @Column(name = "refreshToken")
    String refreshToken;

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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    public Collection<Role> getRoles() {
        return roles;
    }

    /*
    public Collection<String> getStringRoles() {
        Collection<String> r=new ArrayList<>();
        for ( final Role role : roles ) {
            Hibernate.initialize(role.getName());
            r.add(role.getName());
        }
        return r;
    }
    */
    public void setRoles(Collection<Role> roles) {
        this.roles = roles;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public User(String username, String password, String firstname, String surname, Date lastUpdated) {
        this.username = username;
        this.password = password;
        this.firstname = firstname;
        this.surname = surname;
        this.lastUpdated = lastUpdated;
        this.roles= (Collection) Arrays.asList();
    }
    public User() {

    }
    public User(String username, String password, String firstname, String surname) {
        this.username = username;
        this.password = password;
        this.firstname = firstname;
        this.surname = surname;
        this.lastUpdated=new Date();
    }

}
