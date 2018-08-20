package egcom.yaf.repo;

import egcom.yaf.entity.Thread;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ThreadRepo extends CrudRepository<Thread, Long> {

    List<Thread> findAllByTopicName(String name);
}
