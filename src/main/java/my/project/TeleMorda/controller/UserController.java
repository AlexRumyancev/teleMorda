package my.project.TeleMorda.controller;

import my.project.TeleMorda.module.MyUser;
import my.project.TeleMorda.repositories.UserRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.UUID;

@RestController
//@CrossOrigin("*")
public class UserController {

    @Autowired
    private UserRepository ur;

    @PostMapping("/admin/adduser")
    public String addNewUser(@RequestBody MyUser user) {
        String result = UUID.randomUUID().toString();
        if (user == null) {
            new RuntimeException("Передан пустой юзер");
        }
        MyUser saveUser = user;
        saveUser.setToken(result);
        saveUser.setLastOnline(new Timestamp(System.currentTimeMillis()));
        try {
            ur.save(saveUser);
        } catch (Exception e) {
            new RuntimeException("Ошибка записи юзера");
        }
        return result;
    }

    @PostMapping("/admin/token")
    public String getToken(@RequestParam("username") final String username, @RequestParam("password") final String password){
        String token = ur.login(username,password).map((x) -> x.getToken()).get();
        if(StringUtils.isEmpty(token)){
            return "no token found";
        }
        return token;
    }
}