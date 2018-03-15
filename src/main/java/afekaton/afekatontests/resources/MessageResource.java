package afekaton.afekatontests.resources;

import afekaton.afekatontests.models.questions.Message;
import afekaton.afekatontests.persistance.MessageRepository;
import afekaton.afekatontests.resources.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.SecurityContext;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("message")

public class MessageResource {

    @Autowired private MessageRepository messageRepository;

    @GetMapping
    public List<Message> getMessages(){
        return (List<Message>) messageRepository.findAll();
    }


    @GetMapping("{messageId}")
    public Message getMessage(@PathVariable("messageId") final Integer messageId){
        try {
            return messageRepository.findById(messageId).get();
        } catch (NoSuchElementException e) {
            throw new NotFoundException("Message with id=" + messageId + " not found", e);
        }
    }
}
