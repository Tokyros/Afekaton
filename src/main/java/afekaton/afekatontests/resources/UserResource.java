package afekaton.afekatontests.resources;

import afekaton.afekatontests.models.members.User;
import afekaton.afekatontests.persistance.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sun.net.www.protocol.http.AuthenticationHeader;

import javax.ws.rs.HeaderParam;
import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.core.Response;
import java.util.List;


@RestController
@RequestMapping("user")
public class UserResource {

    @Autowired private UserRepository userRepository;

    @GetMapping("login")
    public User authenticate(@RequestParam("username") final String username, @RequestParam("password") final String password){
        for (User user : userRepository.findAll()) {
            if (user.getUsername().equals(username) && new String(user.getPassword()).equals(password)) return user;
        }
        throw new NotAuthorizedException(Response.status(Response.Status.UNAUTHORIZED).build());
    }

    @GetMapping
    public List<User> getAllUsers(){
        return (List<User>) userRepository.findAll();
    }

    @PostMapping
    public User createUser(@RequestBody User user){
        user.setUsername(user.getEmail().split("@")[0]);
        return userRepository.save(user);
    }
}
