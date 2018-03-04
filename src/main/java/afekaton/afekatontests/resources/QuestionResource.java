package afekaton.afekatontests.resources;

import afekaton.afekatontests.models.questions.Question;
import afekaton.afekatontests.persistance.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.QueryParam;

@RestController
@RequestMapping("question")
public class QuestionResource {

    @Autowired private QuestionRepository questionRepository;

    @PostMapping
    public Question createQuestion(@RequestBody Question question){
        return questionRepository.save(question);
    }

    @GetMapping("upvote")
    public int upvoteQuestion(@QueryParam("questionId") final int questionId){
        Question question = questionRepository.findById(questionId).get();
        question.upVote();
        questionRepository.save(question);
        return question.getUpvotes();
    }

    @GetMapping("downvote")
    public int downvoteQuestion(@QueryParam("questionId") final int questionId){
        Question question = questionRepository.findById(questionId).get();
        question.downVote();
        questionRepository.save(question);
        return question.getDownvotes();
    }
}
