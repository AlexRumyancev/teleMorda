package my.project.TeleMorda.module;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;

@Entity
public class MyUser {

    @Id
    private Long id;

    private String myLogin;

    private String myName;

    private Timestamp lastGetList;

    private Timestamp lastOnline;

    public Long getUserId() {
        return id;
    }

    public void setUserId(Long userId) {
        this.id = userId;
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
}
