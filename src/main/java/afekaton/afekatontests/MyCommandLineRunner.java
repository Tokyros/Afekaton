package afekaton.afekatontests;

import afekaton.afekatontests.models.members.AfekaRole;
import afekaton.afekatontests.models.members.ApplicationUser;
import afekaton.afekatontests.models.members.Department;
import afekaton.afekatontests.models.questions.Message;
import afekaton.afekatontests.models.questions.Question;
import afekaton.afekatontests.persistance.CourseRepository;
import afekaton.afekatontests.persistance.MessageRepository;
import afekaton.afekatontests.persistance.QuestionRepository;
import afekaton.afekatontests.persistance.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Date;


@Component
public class MyCommandLineRunner implements CommandLineRunner{

    @Autowired private UserRepository userRepository;

    @Autowired private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void run(String... strings) throws Exception {
        ApplicationUser shahar = new ApplicationUser();
        shahar.setUsername("ShaharR");
        shahar.setEmail("ShaharR@af");
        shahar.setPassword(bCryptPasswordEncoder.encode("123456"));
        shahar.setAfekaRole(AfekaRole.STUDENT);
        shahar.setDepartment(Department.SOFTWARE);
        userRepository.save(shahar);


        ApplicationUser rony = new ApplicationUser();
        rony.setUsername("Rony");
        rony.setEmail("Rony@af");
        rony.setPassword(bCryptPasswordEncoder.encode("123456"));
        rony.setAfekaRole(AfekaRole.TEACHER);
        rony.setDepartment(Department.MEDICAL);
        userRepository.save(rony);
    }

}
