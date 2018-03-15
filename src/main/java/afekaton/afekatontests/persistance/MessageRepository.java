package afekaton.afekatontests.persistance;

import afekaton.afekatontests.models.questions.Message;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by ps3to_000 on 14-Mar-18.
 */
public interface MessageRepository extends CrudRepository<Message, Integer> {
}
