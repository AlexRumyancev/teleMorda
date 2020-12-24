package my.project.TeleMorda.controller;

import my.project.TeleMorda.repositories.UserRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TokenController {

    @Autowired
    private UserRepository ur;

    @PostMapping("/token")
    public String getToken(@RequestParam("username") final String username, @RequestParam("password") final String password){
        String token = ur.login(username,password).map((x) -> x.getToken()).get();
        if(StringUtils.isEmpty(token)){
            return "no token found";
        }
        return token;
    }
}