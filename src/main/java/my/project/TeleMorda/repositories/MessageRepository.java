package my.project.TeleMorda.repositories;

import my.project.TeleMorda.module.MyMessage;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends CrudRepository<MyMessage, Long> {
}
