package egcom.yafi.repo;

import egcom.yafi.entity.Topic;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TopicRepo extends JpaRepository<Topic, Long> {

    Optional<Topic> findByName(String topicName);
}
