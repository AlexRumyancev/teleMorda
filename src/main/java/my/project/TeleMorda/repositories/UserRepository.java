package my.project.TeleMorda.repositories;

import my.project.TeleMorda.module.MyUser;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository <MyUser, Long> {

    Optional<MyUser> findByMyLoginAndMyName(String login, String name);

    @Query(value = "SELECT u FROM MyUser u where u.myName = ?1 and u.password = ?2 ")
    Optional<MyUser> login(String username,String password);

    Optional<MyUser> findByToken(String token);
}
