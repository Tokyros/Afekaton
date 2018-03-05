package afekaton.afekatontests.resources;

import afekaton.afekatontests.models.members.User;
import afekaton.afekatontests.models.questions.Answer;
import afekaton.afekatontests.models.questions.Question;
import afekaton.afekatontests.persistance.CategoryRepository;
import afekaton.afekatontests.persistance.QuestionRepository;
import afekaton.afekatontests.persistance.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.QueryParam;
import java.util.List;

@RestController
@RequestMapping("question")
public class QuestionResource {

    @Autowired private QuestionRepository questionRepository;

    @Autowired private CategoryRepository categoryRepository;

    @PostMapping
    public Question postQuestion(@RequestBody Question question){
        if (!categoryRepository.existsById(question.getCategory().getId())){
            categoryRepository.save(question.getCategory());
        }
        return questionRepository.save(question);
    }

    @GetMapping
    public List<Question> getQuestions(){
        return (List<Question>) questionRepository.findAll();
    }

    @GetMapping("answers")
    public List<Answer> getAnswersForQuestion(@RequestParam("questionId") final int questionId){
        return questionRepository.findById(questionId).get().getAnswers();
    }
}
