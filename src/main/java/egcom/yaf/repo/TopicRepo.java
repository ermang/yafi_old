package egcom.yaf.repo;

import egcom.yaf.entity.Topic;
import org.springframework.data.repository.CrudRepository;

public interface TopicRepo extends CrudRepository<Topic, Long> {
}
