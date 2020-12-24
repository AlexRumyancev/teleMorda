package my.project.TeleMorda.service;

import my.project.TeleMorda.module.MyMessage;
import my.project.TeleMorda.module.MyUser;

import java.util.List;
import java.util.Optional;

public interface ServerUtilites {

    void send(Optional<MyUser> user, Optional<MyMessage> message);
    Optional<List<MyMessage>> recieve(Optional<MyUser> user);
}
