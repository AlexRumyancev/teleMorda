package my.project.TeleMorda.controller;

import my.project.TeleMorda.exception.MessageIsNullException;
import my.project.TeleMorda.exception.UserNotFoundException;
import my.project.TeleMorda.module.MyMessage;
import my.project.TeleMorda.module.MyUser;
import my.project.TeleMorda.repositories.MessageRepository;
import my.project.TeleMorda.repositories.UserRepository;
import my.project.TeleMorda.service.ServerUtilites;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@RestController
public class ServerController {

    @Autowired
    private UserRepository ur;

    @Autowired
    private ServerUtilites su;

    @Autowired
    private MessageRepository mr;

    @GetMapping("/tm/login")
    public void doLogin(Principal principal) {
        Optional<MyUser> user = ur.findByLogin(principal.getName());
        su.setOnline(user.orElseThrow(UserNotFoundException::new).getMyName());
    }

    @GetMapping("/tm/contacts")
    public List getContacs() {
        return (List) ur.findAll();
    }

    @GetMapping("/tm/recieve")
    public List<MyMessage> getMessages(Principal principal) {
        Optional<MyUser> user = ur.findByLogin(principal.getName());
        return mr.findAllByMyUser(user.orElseThrow(UserNotFoundException::new));
    }

    @PostMapping("/tm/send")
    public void sendMessage(MyMessage message) {
        mr.save(Optional.ofNullable(message).orElseThrow(MessageIsNullException::new));
    }
}
