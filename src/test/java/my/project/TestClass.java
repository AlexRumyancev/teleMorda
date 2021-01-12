package my.project;

import my.project.TeleMorda.Application;
import my.project.TeleMorda.module.MyMessage;
import my.project.TeleMorda.module.MyUser;
import my.project.TeleMorda.repositories.MessageRepository;
import my.project.TeleMorda.repositories.UserRepository;
import my.project.TeleMorda.service.ServerUtilites;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

@SpringBootTest(classes = Application.class)
public class TestClass {

    MyUser user1 = new MyUser();
    MyUser user2 = new MyUser();
    MyMessage mess1 = new MyMessage();

    @Autowired
    private ServerUtilites sui;

    @Autowired
    private UserRepository ur;

    @Autowired
    private MessageRepository mr;

    @BeforeEach
    public void init() {
        user1.setMyName("telesyn");
        ur.save(user1);
        user2.setMyName("Test");
        ur.save(user2);
        mess1 = new MyMessage(user1, user2, "testMessage");

    }

    @Test
    public void whenSendMessageThenGetIt() {
        sui.send(Optional.of(user1), Optional.of(mess1));
        Optional<List<MyMessage>> result = sui.recieve(Optional.of(user2));
        result.ifPresent(myMessages -> myMessages.stream().map(MyMessage::getText).forEach(System.out::println));
    }

    @AfterEach
    public void finish() {
        mr.delete(mess1);
        ur.delete(user1);
        ur.delete(user2);
    }


}
