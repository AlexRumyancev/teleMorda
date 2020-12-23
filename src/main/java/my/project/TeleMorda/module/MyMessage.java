package my.project.TeleMorda.module;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "queue_messages")
public class MyMessage {

    @Id
    private Long id;

    @ManyToOne
    private MyUser myUser;

    private String text;

    public MyMessage(MyUser user, String message) {
        this.myUser = user;
        this.text = message;
    }
}
