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

    @Override
    public void run(String... strings) throws Exception {

    }

}
