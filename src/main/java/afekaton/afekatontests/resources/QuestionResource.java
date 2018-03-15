package afekaton.afekatontests.resources;

import afekaton.afekatontests.models.questions.Answer;
import afekaton.afekatontests.models.questions.Message;
import afekaton.afekatontests.models.questions.Question;
import afekaton.afekatontests.persistance.AnswerRepository;
import afekaton.afekatontests.persistance.CourseRepository;
import afekaton.afekatontests.persistance.QuestionRepository;
import afekaton.afekatontests.persistance.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Response;
import java.security.Principal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("question")
public class QuestionResource {

    @Autowired private QuestionRepository questionRepository;

    @Autowired private AnswerRepository answerRepository;

    @Autowired private UserRepository userRepository;

    @Autowired private CourseRepository courseRepository;

    @PostMapping
    public Question postQuestion(@RequestBody @Validated Question question, Principal principal){
        question.setMessageAuthor(userRepository.findByUsername(principal.getName()));
        question.setRelatedCourse(courseRepository.findByName(question.getRelatedCourse().getName()));
        return questionRepository.save(question);
    }

    @PostMapping("{questionId}/comment")
    public void commentQuestion(@PathVariable("questionId") final Integer questionId, @RequestBody @Validated Answer answer, Principal principal){
        Optional<Question> byId = questionRepository.findById(questionId);
        if (byId.isPresent()){
            answer.setMessageAuthor(userRepository.findByUsername(principal.getName()));
            answerRepository.save(answer);
            Question question = byId.get();
            question.getMessageComments().add(answer);
            questionRepository.save(question);
        } else {
            throw new NotFoundException("No message for ID=" + questionId);
        }
    }

    @GetMapping("{questionId}")
    public Message getMessage(@PathVariable("questionId") final Integer questionId){
        return questionRepository.findById(questionId).get();
    }

    @GetMapping
    public List<Question> getQuestions(Principal principal){
        return (List<Question>) questionRepository.findAll();// questionRepository.findByAuthorUsername(principal.getName());
    }
}
