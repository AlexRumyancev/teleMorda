package my.project.TeleMorda.service;

import my.project.TeleMorda.constants.Constants;
import my.project.TeleMorda.exception.MessageIsNullException;
import my.project.TeleMorda.exception.UserEmptyException;
import my.project.TeleMorda.exception.UserNotFoundException;
import my.project.TeleMorda.module.MyMessage;
import my.project.TeleMorda.module.MyUser;
import my.project.TeleMorda.repositories.MessageRepository;
import my.project.TeleMorda.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.sql.Timestamp;
import java.util.*;

@Service
public class ServerUtilitesImpl implements ServerUtilites {

    private static final Logger LOG = LoggerFactory.getLogger(ServerUtilitesImpl.class);

    @Autowired
    private UserRepository ur;

    @Autowired
    private MessageRepository mr;

    @Autowired
    private ThreadPoolTaskScheduler taskScheduler;

    private Map<String, Boolean> contacts = new HashMap<>();

    @PostConstruct
    private void initContacts() {
        ur.findAll().forEach((x) -> contacts.put(x.getMyName(), false));
        taskScheduler.scheduleWithFixedDelay(new Runnable() {
            @Override
            public void run() {
                ur.findAll().forEach((x) -> contacts.put(x.getMyName(),
                        (System.currentTimeMillis() - x.getLastOnline().getTime()) < Constants.DELAY_ONLINE_REFRESH));
                LOG.info("Обновляем онлайн статус");
            }
        }, new Date(), Constants.ONLINE_REFRESH);
    }

    @Override
    public void send(Optional<MyUser> user, Optional<MyMessage> message) {
        user.orElseThrow(UserNotFoundException::new);
        mr.save(message.orElseThrow(MessageIsNullException::new));
    }

    @Override
    public Optional<List<MyMessage>> recieve(Optional<MyUser> user) {
        return Optional.ofNullable(mr.findAllByToUser(user.orElseThrow(UserNotFoundException::new)));
    }

    @Override
    public boolean isOnline(String login) {
        return Optional.ofNullable(contacts.get(Optional.ofNullable(login).orElseThrow(UserEmptyException::new)))
                .orElseThrow(UserNotFoundException::new);
    }

    @Override
    public void setOnline(String login) {
        contacts.put(Optional.ofNullable(login).orElseThrow(UserEmptyException::new), true);
        MyUser user = ur.findByLogin(login).orElseThrow(UserNotFoundException::new);
        user.setLastOnline(new Timestamp(System.currentTimeMillis()));
        ur.save(user);
    }
}
