package egcom.yafi.repo;

import egcom.yafi.entity.Topic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TopicRepo extends JpaRepository<Topic, Long> {

    Optional<Topic> findByName(String topicName);

    @Query(value = "select * from topic where id in (select distinct topic_id from thread where CAST(created_on as DATE) = CAST(now() as DATE))", nativeQuery = true)
    List<Topic> readMostRecentlyUpdatedTopics();
}
