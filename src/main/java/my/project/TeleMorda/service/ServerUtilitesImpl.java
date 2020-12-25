package my.project.TeleMorda.service;

import my.project.TeleMorda.exception.MessageIsNullException;
import my.project.TeleMorda.exception.UserEmptyException;
import my.project.TeleMorda.exception.UserNotFoundException;
import my.project.TeleMorda.module.MyMessage;
import my.project.TeleMorda.module.MyUser;
import my.project.TeleMorda.repositories.MessageRepository;
import my.project.TeleMorda.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.*;

@Service
public class ServerUtilitesImpl implements ServerUtilites {

    @Autowired
    private UserRepository ur;

    @Autowired
    private MessageRepository mr;

    private Map<String, Boolean> contacts = new HashMap<>();

    @PostConstruct
    private void initContacts() {
        ur.findAll().forEach((x) -> contacts.put(x.getMyName(), false));
    }

    @Override
    public void send(Optional<MyUser> user, Optional<MyMessage> message) {
        user.orElseThrow(UserNotFoundException::new);
        mr.save(message.orElseThrow(MessageIsNullException::new));
    }

    @Override
    public Optional<List<MyMessage>> recieve(Optional<MyUser> user) {
        return Optional.ofNullable(mr.findAllByMyUser(user.orElseThrow(UserNotFoundException::new)));
    }

    @Override
    public boolean isOnline(String login) {
        return Optional.ofNullable(contacts.get(Optional.ofNullable(login).orElseThrow(UserEmptyException::new)))
                .orElseThrow(UserNotFoundException::new);
    }

    @Override
    public void setOnline(String login) {
        contacts.put(Optional.ofNullable(login).orElseThrow(UserEmptyException::new), true);
    }
}
