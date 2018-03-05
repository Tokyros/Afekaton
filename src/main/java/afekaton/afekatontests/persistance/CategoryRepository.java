package afekaton.afekatontests.persistance;

import afekaton.afekatontests.models.questions.Category;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by ps3to_000 on 05-Mar-18.
 */
public interface CategoryRepository extends CrudRepository<Category, Integer> {
}
