package my.project.TeleMorda.modele;

import org.springframework.lang.NonNull;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

@Entity
@Table(name = "queue_messages")
public class MyMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @NonNull
    @ManyToOne
    private MyUser fromUser;

    @NonNull
    @ManyToOne
    private MyUser toUser;

    private String text;

    @NonNull
    @Column(unique=true)
    private Timestamp created;

    public MyMessage(MyUser fromUser, MyUser toUser, String message) {
        this.fromUser = fromUser;
        this.toUser = toUser;
        this.text = message;
        this.created = new Timestamp(new Date().getTime());
    }

    public MyMessage() {

    }

    public Long getId() {
        return id;
    }

    @NonNull
    public MyUser getFromUser() {
        return fromUser;
    }

    @NonNull
    public MyUser getToUser() {
        return toUser;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @NonNull
    public Timestamp getCreated() {
        return created;
    }
}
