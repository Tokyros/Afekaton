package afekaton.afekatontests.resources;

import afekaton.afekatontests.models.members.ApplicationUser;
import afekaton.afekatontests.persistance.UserRepository;
import afekaton.afekatontests.security.user.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.core.Response;
import java.security.Principal;
import java.util.List;


@RestController
@RequestMapping("users")
public class UserResource {

    @Autowired private UserRepository userRepository;
    @Autowired private BCryptPasswordEncoder bCryptPasswordEncoder;

    @GetMapping
    public List<ApplicationUser> getAllUsers(){
        return userRepository.findAll();
    }

    @PostMapping("/sign-up")
    public ApplicationUser signUp(@RequestBody @Validated ApplicationUser applicationUser){
        applicationUser.setUsername(applicationUser.getEmail().split("@")[0]);
        applicationUser.setPassword(bCryptPasswordEncoder.encode(applicationUser.getPassword()));
        return userRepository.save(applicationUser);
    }

    @GetMapping("whoAmI")
    public ApplicationUser whoAmI(Principal principal){
        return userRepository.findByUsername(principal.getName());
    }
}
