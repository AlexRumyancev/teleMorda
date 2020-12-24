package my.project.TeleMorda.controller;

import my.project.TeleMorda.module.MyUser;
import my.project.TeleMorda.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserProfileController {

    @Autowired
    private UserRepository ur;

    @GetMapping(value = "/api/users/user/{id}",produces = "application/json")
    public MyUser getUserDetail(@PathVariable Long id){
        return ur.findById(id).orElse(null);
    }
}