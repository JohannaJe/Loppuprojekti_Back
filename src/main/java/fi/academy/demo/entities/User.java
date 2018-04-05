package fi.academy.demo.entities;

import javax.persistence.*;
//import fi.academy.forumdemo.entities.UserRole;

@Entity
public class User {
    @Id
    @GeneratedValue
    private int id;

    private String username;
    private String password;
    private String email;
    private int active;



    public User() {
    }

    public User(String username, String password, String email, int active) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.active = active;
    }

    public User(User user) {
        this.active = user.getActive();
        this.id = user.getId();
        this.username = getUsername();
        this.password = getPassword();
        this.email = user.getEmail();
    }

    public User(String username, String password, String email, int active, String role) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.active = active;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }


    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", active=" + active +
                '}';
    }
}
