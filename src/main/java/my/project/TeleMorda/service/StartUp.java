package my.project.TeleMorda.service;

import my.project.TeleMorda.controller.UserController;
import my.project.TeleMorda.module.MyUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Random;

@Component
public class StartUp {

    @Autowired
    private UserController uc;

    @PostConstruct
    public void initSheduler() {
         this.DefineJob();
    }

    private void DefineJob() {
//        addTelesynUser();
    }

    private void addRandomUser() {
        Random rnd = new Random();
        MyUser user = new MyUser("user" + rnd.nextInt(999),  "123");
        String result = uc.addNewUser(user);
        System.out.println(result);
    }

    private void addTelesynUser() {
        MyUser user = new MyUser("telesyn",  "123");
        String result = uc.addNewUser(user);
        System.out.println(result);
    }
}
