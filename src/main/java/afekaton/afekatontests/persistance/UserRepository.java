package afekaton.afekatontests.persistance;

import afekaton.afekatontests.models.members.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {
}
