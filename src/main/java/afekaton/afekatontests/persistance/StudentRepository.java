package afekaton.afekatontests.persistance;

import afekaton.afekatontests.models.members.Student;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by ps3to_000 on 04-Mar-18.
 */
public interface StudentRepository extends CrudRepository<Student, Integer> {
}
