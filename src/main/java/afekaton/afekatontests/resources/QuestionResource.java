package afekaton.afekatontests.resources;

import afekaton.afekatontests.models.members.ApplicationUser;
import afekaton.afekatontests.models.questions.Answer;
import afekaton.afekatontests.models.questions.Question;
import afekaton.afekatontests.models.questions.Vote;
import afekaton.afekatontests.persistance.CategoryRepository;
import afekaton.afekatontests.persistance.QuestionRepository;
import afekaton.afekatontests.persistance.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.core.Response;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("question")
public class QuestionResource {

    @Autowired private QuestionRepository questionRepository;

    @Autowired private CategoryRepository categoryRepository;

    @Autowired private UserRepository userRepository;

    @PostMapping
    public Question postQuestion(@RequestBody @Validated Question question, HttpServletRequest request, Principal principal){
        if (!categoryRepository.existsById(question.getCategory().getId())){
            categoryRepository.save(question.getCategory());
        }
        question.setAuthor(userRepository.findByUsername(principal.getName()));
        return questionRepository.save(question);
    }

    @GetMapping
    public List<Question> getQuestions(Principal principal){
        return (List<Question>) questionRepository.findAll();// questionRepository.findByAuthorUsername(principal.getName());
    }

    @GetMapping("answers")
    public List<Answer> getAnswersForQuestion(@RequestParam("questionId") final int questionId){
        return questionRepository.findById(questionId).get().getAnswers();
    }

    @PostMapping("vote")
    public void voteForQuestion(@RequestBody Vote newVote){
        ApplicationUser applicationUser = newVote.getApplicationUser();
        Question question = questionRepository.findById(newVote.getId()).get();

        for (Vote userVote : applicationUser.getRatings()) {
            Question rated = (Question) userVote.getRated();
            if (question.getId() == rated.getId()){
                throw new NotAuthorizedException(Response.status(Response.Status.UNAUTHORIZED).build());
            }
        }

        applicationUser = userRepository.findById(applicationUser.getId()).get();

        if (newVote.getUpvote()) {
            question.upVote();
        } else {
            question.downVote();
        }

        applicationUser.getRatings().add(newVote);

        questionRepository.save(question);
        userRepository.save(applicationUser);
    }
}
