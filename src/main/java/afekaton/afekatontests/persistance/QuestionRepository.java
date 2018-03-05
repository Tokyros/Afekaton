package afekaton.afekatontests.persistance;

import afekaton.afekatontests.models.members.User;
import afekaton.afekatontests.models.questions.Question;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Map;

/**
 * Created by ps3to_000 on 04-Mar-18.
 */
public interface QuestionRepository extends CrudRepository<Question, Integer> {


}
