package egcom.yafi.repo;

import egcom.yafi.entity.Thread;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ThreadRepo extends PagingAndSortingRepository<Thread, Long> {

    @EntityGraph(attributePaths = {"yafiUser", "topic"}, type=EntityGraph.EntityGraphType.LOAD)
    Page<Thread> findAllByTopicNameOrderByCreatedOnAsc(String name, Pageable pageable);

    Page<Thread> findAllByYafiUserUsernameOrderByCreatedOnAsc(String username, Pageable pageable);

    @EntityGraph(attributePaths = {"yafiUser", "topic"} ,type = EntityGraph.EntityGraphType.FETCH) //TODO: LEARN THIS
    Page<Thread> findFirst25ByOrderByCreatedOn_Desc(Pageable pageable);

}
