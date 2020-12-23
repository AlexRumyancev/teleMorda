package my.project.TeleMorda.classez;

import my.project.TeleMorda.module.MyMessage;
import my.project.TeleMorda.module.MyUser;

import java.util.List;

public interface ServerUtilites {

    void send(MyUser user, MyMessage message);
    List<MyMessage> recieve(MyUser user);
    boolean checkExistUser(MyUser user);
}
