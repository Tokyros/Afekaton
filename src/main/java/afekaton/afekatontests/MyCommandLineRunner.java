package afekaton.afekatontests;

import afekaton.afekatontests.models.members.AfekaRole;
import afekaton.afekatontests.models.members.Department;
import afekaton.afekatontests.models.members.User;
import afekaton.afekatontests.persistance.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class MyCommandLineRunner implements CommandLineRunner{

    @Autowired
    private UserRepository userRepository;

    @Override
    public void run(String... strings) throws Exception {
        User shahar = new User();
        shahar.setUsername("ShaharR");
        shahar.setPassword("".toCharArray());
        shahar.setDepartment(Department.SOFTWARE);
        shahar.setAfekaRole(AfekaRole.STUDENT);
        shahar.setEmail("ShaharR@afeka.ac.il");
        userRepository.save(shahar);

        User roy = new User();
        roy.setUsername("RoyG");
        roy.setPassword("".toCharArray());
        roy.setDepartment(Department.SOFTWARE);
        roy.setAfekaRole(AfekaRole.STUDENT);
        roy.setEmail("RoyG@Afeka.ac.il");
        userRepository.save(roy);
    }
}
