package afekaton.afekatontests.resources;

import afekaton.afekatontests.models.questions.Answer;
import afekaton.afekatontests.persistance.AnswerRepository;
import afekaton.afekatontests.persistance.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("answer")
public class AnswerResource {

    @Autowired private AnswerRepository answerRepository;

    @PostMapping
    public Answer postAnswer(@RequestBody Answer answer){
        return answerRepository.save(answer);
    }
}
