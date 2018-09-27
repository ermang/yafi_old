package egcom.yafi.repo;

import egcom.yafi.dto.TopicDTO;
import egcom.yafi.entity.Thread;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface ThreadRepo extends PagingAndSortingRepository<Thread, Long> {

    Page<Thread> findAllByTopicNameOrderByCreatedOnAsc(String name, Pageable pageable);

    Page<Thread> findAllByUserUsernameOrderByCreatedOnAsc(String username, Pageable pageable);

    @EntityGraph(attributePaths = {"user", "topic"} ,type = EntityGraph.EntityGraphType.FETCH) //TODO: LEARN THIS
    List<Thread> findFirst25ByOrderByCreatedOn_Desc();

    //TopicDTO findAllDistinct
}
