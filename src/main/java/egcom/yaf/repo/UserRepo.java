package egcom.yaf.repo;

import egcom.yaf.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepo extends CrudRepository<User, Long> {

    User findByUsername(String name);
}
