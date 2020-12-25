package my.project.TeleMorda.controller;

import my.project.TeleMorda.exception.UserNotFoundException;
import my.project.TeleMorda.module.MyMessage;
import my.project.TeleMorda.module.MyUser;
import my.project.TeleMorda.repositories.UserRepository;
import my.project.TeleMorda.service.ServerUtilites;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
public class ServerController {

    @Autowired
    private UserRepository ur;

    @Autowired
    private ServerUtilites su;

    @GetMapping("/tm/login")
    public void doLogin(Principal principal) {
        Optional<MyUser> user = ur.findByMyName(principal.getName());
        su.setOnline(user.orElseThrow(UserNotFoundException::new).getMyName());
    }

    @GetMapping("/tm/contacts")
    public List<Map<String, String>> getContacs() {
        return null;
    }

    @GetMapping("/tm/recieve")
    public List<MyMessage> getMessages() {
        return null;
    }

    @PostMapping("/tm/send")
    public void sendMessage(MyMessage message) {

    }
}
