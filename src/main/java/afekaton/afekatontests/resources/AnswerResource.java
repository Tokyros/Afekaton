package afekaton.afekatontests.resources;

import afekaton.afekatontests.models.members.ApplicationUser;
import afekaton.afekatontests.models.questions.Answer;
import afekaton.afekatontests.persistance.AnswerRepository;
import afekaton.afekatontests.persistance.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.NotFoundException;
import java.security.Principal;
import java.util.Date;
import java.util.Optional;


@RestController
@RequestMapping("answers")
public class AnswerResource {
    @Autowired private AnswerRepository answerRepository;

    @Autowired private UserRepository userRepository;

    @PostMapping
    public Answer createAnswer(@RequestBody @Validated Answer newAnswer, Principal principal){
        newAnswer.setMessageAuthor(userRepository.findByUsername(principal.getName()));
        if (newAnswer.getCreationDate() == null) newAnswer.setCreationDate(new Date());
        newAnswer.setUpdateDate(new Date());
        return answerRepository.save(newAnswer);
    }

    @GetMapping("{answerId}/upVote")
    public void upVoteAnswer(@PathVariable("answerId") final Integer answerId, Principal principal){
        Answer question = answerRepository.findById(answerId).get();
        ApplicationUser applicationUser = userRepository.findByUsername(principal.getName());

        voteAnswer(question, applicationUser, 1);

        answerRepository.save(question);
    }

    @GetMapping("{answerId}/downVote")
    public void downVoteAnswer(@PathVariable("answerId") final Integer answerId, Principal principal){
        Answer question = answerRepository.findById(answerId).get();
        ApplicationUser applicationUser = userRepository.findByUsername(principal.getName());
        voteAnswer(question, applicationUser, -1);
        answerRepository.save(question);
    }

    private void voteAnswer(Answer answer, ApplicationUser applicationUser, int vote) {
        if (answer.getUserRatings().get(applicationUser.getUsername()) != null && answer.getUserRatings().get(applicationUser.getUsername()) == vote*-1){
            answer.getUserRatings().remove(applicationUser.getUsername());
        } else {
            answer.getUserRatings().put(applicationUser.getUsername(), vote);
        }
    }

    @PostMapping("{answerId}/comment")
    public void commentAnswer(@PathVariable("answerId") final Integer answerId, @RequestBody String answerContent, Principal principal){
        Optional<Answer> answerOptional = answerRepository.findById(answerId);
        if (answerOptional.isPresent()){

            Answer comment = new Answer();
            comment.setMessageAuthor(userRepository.findByUsername(principal.getName()));
            comment.setMessageContent(answerContent);
            comment.setQuestionId(answerId);
            answerRepository.save(comment);

            Answer commentedQuestion = answerOptional.get();
            commentedQuestion.getMessageComments().add(comment);
            answerRepository.save(commentedQuestion);
        } else {
            throw new NotFoundException("No message for ID=" + answerId);
        }
    }
}
