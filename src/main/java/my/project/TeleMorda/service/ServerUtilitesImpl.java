package my.project.TeleMorda.service;

import my.project.TeleMorda.exception.MessageIsNullException;
import my.project.TeleMorda.exception.UserNotFoundException;
import my.project.TeleMorda.module.MyMessage;
import my.project.TeleMorda.module.MyUser;
import my.project.TeleMorda.repositories.MessageRepository;
import my.project.TeleMorda.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ServerUtilitesImpl implements ServerUtilites {

    @Autowired
    private UserRepository ur;

    @Autowired
    private MessageRepository mr;

    @Override
    public void send(Optional<MyUser> user, Optional<MyMessage> message) {
        user.orElseThrow(() -> new UserNotFoundException());
        mr.save(message.orElseThrow(() -> new MessageIsNullException()));
    }

    @Override
    public Optional<List<MyMessage>> recieve(Optional<MyUser> user) {
        return mr.findAllByMyUser(user.orElseThrow(() -> new UserNotFoundException()));
    }
}
