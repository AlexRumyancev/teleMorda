package my.project.TeleMorda.service;

import my.project.TeleMorda.modele.MyMessage;
import my.project.TeleMorda.modele.MyUser;

import java.util.List;
import java.util.Optional;

public interface ServerUtilites {

    void send(Optional<MyUser> user, Optional<MyMessage> message);
    Optional<List<MyMessage>> recieve(Optional<MyUser> user);
    boolean isOnline(String login);
    void setOnline(String login);
}
