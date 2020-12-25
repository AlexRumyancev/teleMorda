package my.project.TeleMorda.module;

import org.springframework.lang.NonNull;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;

@Entity
//@Table(name = "users")
public class MyUser {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @NonNull
    @Column(unique=true)
    private String myName;

    private String password;

    @Column(unique=true)
    private String token;

    private Timestamp lastGetList;

    private Timestamp lastOnline;

    public List<MyUser> getContacts() {
        return contacts;
    }

    public void setContacts(List<MyUser> contacts) {
        this.contacts = contacts;
    }

    @OneToMany
    private List<MyUser> contacts;

    public MyUser(String name, String password) {
        this.myName = name;
        this.password = password;
    }

    public MyUser() {

    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getMyName() {
        return myName;
    }

    public String getPassword() {
        return password;
    }

    public void setMyName(String myName) {
        this.myName = myName;
    }

    public Long getId() {
        return id;
    }

    public Timestamp getLastGetList() {
        return lastGetList;
    }

    public Timestamp getLastOnline() {
        return lastOnline;
    }
}
