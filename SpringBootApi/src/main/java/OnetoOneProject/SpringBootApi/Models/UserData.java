package OnetoOneProject.SpringBootApi.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;


import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "usertable")
public class UserData {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "username")
    @NotNull
    @Size(min = 1, max = 100, message = "User Name cannot be less than 1 or more than 100")
    private String username;

    @Column(name = "password")
    @NotNull
    @Size(min = 1, max = 100, message = "Password cannot be less than 1 or more than 100")
    private String password;

    @Column(name = "role")
    private String role;

    @Column(name = "created")
    private Timestamp timestamp;

//@JsonIgnore
    @OneToOne(mappedBy = "user")
    private Person person;

    public UserData() {
    }

    public UserData(String username, String password, String role, Timestamp timestamp, Person person) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.timestamp = timestamp;
        this.person = person;
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp() {
        this.timestamp = new Timestamp(System.currentTimeMillis());
    }


    @Override
    public String toString() {
        return "UserData{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                ", timestamp=" + timestamp +
                ", person=" + person +
                '}';
    }


}
