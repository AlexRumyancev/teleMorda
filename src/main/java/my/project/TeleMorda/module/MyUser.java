package my.project.TeleMorda.module;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
//@Table(name = "users")
public class MyUser {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String myLogin;

    private String myName;

    private String password;

    private String token;

    private Timestamp lastGetList;

    private Timestamp lastOnline;

    public Long getUserId() {
        return id;
    }

    public String getMyLogin() {
        return myLogin;
    }

    public void setMyLogin(String myLogin) {
        this.myLogin = myLogin;
    }

    public String getMyName() {
        return myName;
    }

    public void setMyName(String myName) {
        this.myName = myName;
    }

    public Timestamp getLastGetList() {
        return lastGetList;
    }

    public void setLastGetList(Timestamp lastGetList) {
        this.lastGetList = lastGetList;
    }

    public Timestamp getLastOnline() {
        return lastOnline;
    }

    public void setLastOnline(Timestamp lastOnline) {
        this.lastOnline = lastOnline;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
