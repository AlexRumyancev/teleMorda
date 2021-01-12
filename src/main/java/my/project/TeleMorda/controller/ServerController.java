package my.project.TeleMorda.controller;

import my.project.TeleMorda.constants.Constants;
import my.project.TeleMorda.exception.MessageIsNullException;
import my.project.TeleMorda.exception.UserNotFoundException;
import my.project.TeleMorda.module.MyMessage;
import my.project.TeleMorda.module.MyUser;
import my.project.TeleMorda.repositories.MessageRepository;
import my.project.TeleMorda.repositories.UserRepository;
import my.project.TeleMorda.service.ServerUtilites;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.*;

@RestController
//@CrossOrigin("*")
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

    @GetMapping("/tm/time")
    public Integer getRefreshTime() {
        return Constants.ONLINE_REFRESH;
    }

    @GetMapping("/tm/contacts")
    public List<Map<String, Object>> getContacs(Principal principal) {
        List<Map<String, Object>> result = new ArrayList<>();
        for(MyUser user: ur.findAll()) {
            if (!user.getMyName().equals(principal.getName())) {
                Map<String, Object> resUser = new HashMap<>();
                resUser.put("login", user.getMyName());
                resUser.put("online", (System.currentTimeMillis() - user.getLastOnline().getTime()) > Constants.DELAY_ONLINE_REFRESH);
                result.add(resUser);
            }
        }
        return result ;
    }

    @GetMapping("/tm/recieve")
    public List<Map<String, Object>> getMessages(Principal principal) {
        Optional<MyUser> user = ur.findByLogin(principal.getName());
        List<Map<String, Object>> result = new ArrayList<>();
        for(MyMessage message: mr.findAllByToUser(user.orElseThrow(UserNotFoundException::new))) {
            Map<String, Object> resMes = new HashMap<>();
            resMes.put("from", message.getFromUser().getMyName());
            resMes.put("message", message.getText());
            resMes.put("created", message.getCreated());
            result.add(resMes);
        }
        return result ;
    }

    @PostMapping("/tm/send")
    public void sendMessage(MyMessage message) {
        mr.save(Optional.ofNullable(message).orElseThrow(MessageIsNullException::new));
    }
}
