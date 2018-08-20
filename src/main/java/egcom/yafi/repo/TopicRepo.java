package egcom.yafi.repo;

import egcom.yafi.entity.Topic;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicRepo extends JpaRepository<Topic, Long> {
}
