package afekaton.afekatontests.resources;

import afekaton.afekatontests.models.members.Student;
import afekaton.afekatontests.models.members.User;
import afekaton.afekatontests.persistance.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.Consumes;
import javax.ws.rs.core.MediaType;
import java.awt.*;
import java.util.List;

@RestController
public class RegistrationResource {

    @Autowired
    StudentRepository studentRepository;

    @GetMapping("student")
    public List<Student> getStudents(){
        return (List<Student>) studentRepository.findAll();
    }

    @PostMapping("student")
    @Consumes(MediaType.APPLICATION_JSON)
    public Student createStudent(@RequestBody Student student){
        return studentRepository.save(student);
    }
}
