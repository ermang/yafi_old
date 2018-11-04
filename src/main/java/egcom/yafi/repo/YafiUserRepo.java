package egcom.yafi.repo;

import egcom.yafi.entity.YafiUser;
import org.springframework.data.repository.CrudRepository;

public interface YafiUserRepo extends CrudRepository<YafiUser, Long> {

    YafiUser findByUsername(String name);
}
