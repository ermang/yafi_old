package egcom.yafi.repo;

import egcom.yafi.entity.Thread;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ThreadRepo extends CrudRepository<Thread, Long> {

    List<Thread> findAllByTopicNameOrderByCreatedOnDesc(String name);

    List<Thread> findAllByUserUsernameOrderByCreatedOnDesc(String username);
}
