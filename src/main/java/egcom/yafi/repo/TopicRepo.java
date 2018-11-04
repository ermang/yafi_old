package egcom.yafi.repo;

import egcom.yafi.entity.Topic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TopicRepo extends JpaRepository<Topic, Long> {

    Optional<Topic> findByName(String topicName);

    @Query(value = "SELECT * FROM topic JOIN " +
            "(SELECT topic_id, created_on FROM thread group by topic_id, created_on order by created_on desc limit 5)"
            + " Y ON topic.id=topic_id order by Y.created_on desc", nativeQuery = true)
    List<Topic> readMostRecentlyUpdatedTopics();
}
