package egcom.yafi.repo;

import egcom.yafi.entity.Thread;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface ThreadRepo extends PagingAndSortingRepository<Thread, Long> {

    List<Thread> findAllByTopicNameOrderByCreatedOnDesc(String name);

    List<Thread> findAllByUserUsernameOrderByCreatedOnAsc(String username, Pageable pageable);
}
