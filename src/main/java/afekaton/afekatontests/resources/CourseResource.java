package afekaton.afekatontests.resources;

import afekaton.afekatontests.models.courses.Course;
import afekaton.afekatontests.persistance.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("course")
public class CourseResource {

    @Autowired
    CourseRepository courseRepository;

    @GetMapping
    public List<Course> getAllCourses(){
        return (List<Course>) courseRepository.findAll();
    }
}
