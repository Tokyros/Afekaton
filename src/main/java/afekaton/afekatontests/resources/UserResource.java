package afekaton.afekatontests.resources;

import afekaton.afekatontests.models.members.User;
import afekaton.afekatontests.persistance.MemberRepository;
import afekaton.afekatontests.persistance.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("user")
public class UserResource {

    @Autowired private UserRepository userRepository;

    @Autowired private MemberRepository memberRepository;

    @GetMapping
    public List<User> getAllUsers(){
        return (List<User>) userRepository.findAll();
    }

    @PostMapping
    public User createUser(@RequestBody User user){
        if (!memberRepository.existsById(user.getMember().getId())){
            memberRepository.save(user.getMember());
        }
        return userRepository.save(user);
    }
}
