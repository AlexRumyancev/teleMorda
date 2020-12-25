package my.project.TeleMorda.repositories;

import my.project.TeleMorda.module.MyMessage;
import my.project.TeleMorda.module.MyUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MessageRepository extends CrudRepository<MyMessage, Long> {
    List<MyMessage> findAllByMyUser(MyUser user);
}
