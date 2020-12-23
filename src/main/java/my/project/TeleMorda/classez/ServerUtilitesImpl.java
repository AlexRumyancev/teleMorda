package my.project.TeleMorda.classez;

import my.project.TeleMorda.module.MyMessage;
import my.project.TeleMorda.module.MyUser;
import my.project.TeleMorda.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ServerUtilitesImpl implements ServerUtilites {

    @Autowired
    private UserRepository ur;
    @Override
    public void send(MyUser user, MyMessage message) {

    }

    @Override
    public List<MyMessage> recieve(MyUser user) {
        return null;
    }

    @Override
    public boolean checkExistUser(MyUser user) {
        return false;
    }
}
