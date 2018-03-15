package afekaton.afekatontests;

import afekaton.afekatontests.models.members.AfekaRole;
import afekaton.afekatontests.models.members.ApplicationUser;
import afekaton.afekatontests.models.members.Department;
import afekaton.afekatontests.models.questions.Message;
import afekaton.afekatontests.persistance.MessageRepository;
import afekaton.afekatontests.persistance.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Date;


@Component
public class MyCommandLineRunner implements CommandLineRunner{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MessageRepository messageRepository;

    private ApplicationUser createUser(String email){
        ApplicationUser user = new ApplicationUser();
        user.setEmail(email);
        user.setUsername(email.split("@")[0]);
        user.setPassword("1");
        return user;
    }

    @Override
    public void run(String... strings) throws Exception {
        ApplicationUser user = createUser("ShaharR@afeka.ac.il");
        ApplicationUser user2 = createUser("RoyG@afeka.ac.il");
        ApplicationUser user3 = createUser("AmirZ@afeka.ac.il");
        ApplicationUser user4 = createUser("OfirA@afeka.ac.il");

        RestTemplate restTemplate = new RestTemplate();
        restTemplate.postForLocation("http://localhost:8080/users/sign-up", user);
        restTemplate.postForLocation("http://localhost:8080/users/sign-up", user2);
        restTemplate.postForLocation("http://localhost:8080/users/sign-up", user3);
        restTemplate.postForLocation("http://localhost:8080/users/sign-up", user4);
    }

}
