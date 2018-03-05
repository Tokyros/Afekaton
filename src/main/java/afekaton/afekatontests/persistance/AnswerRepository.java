package afekaton.afekatontests.persistance;

import afekaton.afekatontests.models.questions.Answer;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by ps3to_000 on 05-Mar-18.
 */
public interface AnswerRepository extends CrudRepository<Answer, Integer> {
}
