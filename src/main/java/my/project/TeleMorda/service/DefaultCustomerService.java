package my.project.TeleMorda.service;

import my.project.TeleMorda.module.MyUser;
import my.project.TeleMorda.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class DefaultCustomerService {

    @Autowired
    private UserRepository ur;

    public String login(String username, String password) {
        Optional<MyUser> customer = ur.login(username,password);
        if(customer.isPresent()){
            String token = UUID.randomUUID().toString();
            MyUser custom= customer.get();
            custom.setToken(token);
            ur.save(custom);
            return token;
        }

        return "";
    }

    public Optional findByToken(String token) {
        Optional customer= ur.findByToken(token);
        if(customer.isPresent()){
            MyUser customer1 = (MyUser) customer.get();
            User user= new User(customer1.getMyName(), customer1.getPassword(), true, true, true, true,
                    AuthorityUtils.createAuthorityList("USER"));
            return Optional.of(user);
        }
        return  Optional.empty();
    }

}

