package OnetoOneProject.SpringBootApi.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.Range;


@Entity
@Table(name = "Persontable")
public class Person {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    @NotNull
    @Size(min = 1, max = 100 , message = "Name cannot be less than 1 or more than 100")
    private String name;

    @Column(name = "age")
    @NotNull
    @Range(min = 1 , max = 100, message = "Age cannot be less than 1 or more than 100")
    private int age;

    @Column(name = "email")
    @NotNull
    @Email
    private String email;

    @Column(name = "address")
    @NotNull
    private String address;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "userid", referencedColumnName = "id")
    @NotNull
    private UserData user;

    public Person() {
    }

    public Person(String name, int age, String address, String email, UserData user) {
        this.name = name;
        this.age = age;
        this.address = address;
        this.email = email;
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UserData getUserData() {
        return user;
    }

    public void setUserData(UserData user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", user=" + user +
                '}';
    }


}
