package my.project.TeleMorda.module;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;

@Entity
public class MyUser {

    @Id
    private Long userId;

    private Timestamp lastGetList;

    private Timestamp lastOnline;

}
