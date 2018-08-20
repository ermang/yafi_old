package egcom.yafi.repo;

import egcom.yafi.entity.Topic;
import org.springframework.data.repository.CrudRepository;

public interface TopicRepo extends CrudRepository<Topic, Long> {
}
