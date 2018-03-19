package afekaton.afekatontests.resources;

import afekaton.afekatontests.models.comparators.QuestionComparators;
import afekaton.afekatontests.models.members.ApplicationUser;
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
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import java.security.Principal;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

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
        question.setRelatedCourse(courseRepository.findById(question.getRelatedCourse().getId()).get());
        if (question.getCreationDate() == null) question.setCreationDate(new Date());
        question.setUpdateDate(new Date());
        return questionRepository.save(question);
    }

    @GetMapping("{questionId}/delete")
    public void deleteQuestion(@PathVariable("questionId") final Integer questionId){
        questionRepository.delete(questionRepository.findById(questionId).get());
    }

    @PostMapping("{questionId}/comment")
    public Question commentQuestion(@PathVariable("questionId") final Integer questionId, @RequestBody String answerContent, Principal principal){
        Optional<Question> byId = questionRepository.findById(questionId);
        if (byId.isPresent()){
            Answer answer = new Answer();
            answer.setMessageContent(answerContent);
            answer.setMessageAuthor(userRepository.findByUsername(principal.getName()));
            answer.setCreationDate(new Date());
            answer.setUpdateDate(new Date());
            answerRepository.save(answer);
            Question question = byId.get();
            question.getMessageComments().add(answer);
            questionRepository.save(question);
            return question;
        } else {
            throw new NotFoundException("No message for ID=" + questionId);
        }
    }

    @GetMapping("{questionId}/upvote")
    public void upvoteQuestion(@PathVariable("questionId") final Integer questionId, Principal principal){
        Question question = questionRepository.findById(questionId).get();
        ApplicationUser byUsername = userRepository.findByUsername(principal.getName());
        question.getUserRatings().put(byUsername.getUsername(), 1);
        questionRepository.save(question);
    }

    @GetMapping("{questionId}/downvote")
    public void downvoteQuestion(@PathVariable("questionId") final Integer questionId, Principal principal){
        Question question = questionRepository.findById(questionId).get();
        ApplicationUser byUsername = userRepository.findByUsername(principal.getName());
        question.getUserRatings().put(byUsername.getUsername(), -1);
        questionRepository.save(question);
    }

    @GetMapping("{questionId}")
    public Message getMessage(@PathVariable("questionId") final Integer questionId){
        return questionRepository.findById(questionId).get();
    }

    @GetMapping
    public List<Question> getQuestions(@QueryParam("query") String query, Principal principal){
        List<Question> questions = (List<Question>) questionRepository.findAll();

        if (query != null && !query.isEmpty()){
            questions = questions.stream().filter(new Predicate<Question>() {
                @Override
                public boolean test(Question question) {
                    return question.getMessageAuthor().getUsername().contains(query) || question.getMessageContent().contains(query) || question.getRelatedCourse().getName().contains(query);
                }
            }).collect(Collectors.toList());

        }

        questions.sort(QuestionComparators.sortByCreationDate);

        return questions;
    }
}
