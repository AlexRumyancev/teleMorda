package my.project.TeleMorda.module;

import org.springframework.lang.NonNull;

import javax.persistence.*;

@Entity
@Table(name = "queue_messages")
public class MyMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @NonNull
    @ManyToOne
    private MyUser myUser;

    private String text;

    public MyMessage(MyUser user, String message) {
        this.myUser = user;
        this.text = message;
    }

    public MyMessage() {

    }

    public Long getId() {
        return id;
    }

    public MyUser getMyUser() {
        return myUser;
    }

    public void setMyUser(MyUser myUser) {
        this.myUser = myUser;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
