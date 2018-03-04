package afekaton.afekatontests.persistance;

import afekaton.afekatontests.models.courses.Course;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by ps3to_000 on 04-Mar-18.
 */
public interface CourseRepository extends CrudRepository<Course, Integer> {
}
