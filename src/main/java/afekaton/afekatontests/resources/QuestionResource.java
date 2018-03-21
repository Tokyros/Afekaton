package afekaton.afekatontests.resources;

import afekaton.afekatontests.models.comparators.QuestionComparators;
import afekaton.afekatontests.models.courses.Course;
import afekaton.afekatontests.models.members.AfekaRole;
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

import javax.ws.rs.NotFoundException;
import javax.ws.rs.QueryParam;
import java.security.Principal;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@RestController
@RequestMapping("questions")
public class QuestionResource {

    @Autowired private QuestionRepository questionRepository;

    @Autowired private AnswerRepository answerRepository;

    @Autowired private UserRepository userRepository;

    @Autowired private CourseRepository courseRepository;

    @PostMapping
    public Question postQuestion(@RequestBody @Validated Question question, Principal principal){
        question.setMessageAuthor(userRepository.findByUsername(principal.getName()));
        if (question.getRelatedCourse().getId() > 0){
            Optional<Course> course = courseRepository.findById(question.getRelatedCourse().getId());
            if (!course.isPresent()) throw new NotFoundException("Course with id="+question.getRelatedCourse().getId() + " not found");
            question.setRelatedCourse(course.get());
        } else {
            question.setRelatedCourse(courseRepository.findByName(question.getRelatedCourse().getName()));
        }
        question.setUpdateDate(new Date());
        return questionRepository.save(question);
    }

    @DeleteMapping("{questionId}")
    public void deleteQuestion(@PathVariable("questionId") final Integer questionId){
        Optional<Question> question = questionRepository.findById(questionId);
        if (question.isPresent()) {
            questionRepository.delete(question.get());
        } else {
            throw new NotFoundException("Could not find question with questionId=" + questionId);
        }
    }

    @DeleteMapping("{questionId}/{answerId}")
    public void deleteAnswer(@PathVariable("questionId") final Integer questionId, @PathVariable("answerId") final Integer answerId){
        Optional<Question> question = questionRepository.findById(questionId);
        if (question.isPresent()) {
            Optional<Answer> answer = answerRepository.findById(answerId);
            question.get().getMessageComments().remove(answer.get());
            if (question.get().getCorrectAnswerId() != null && question.get().getCorrectAnswerId().equals(answerId)){
                question.get().setCorrectAnswerId(null);
            }
            questionRepository.save(question.get());
            answerRepository.delete(answer.get());
        } else {
            throw new NotFoundException("Could not find question with questionId=" + questionId);
        }
    }

    @PostMapping("{questionId}/comment")
    public Question commentQuestion(@PathVariable("questionId") final Integer questionId, @RequestBody String answerContent, Principal principal){
        Optional<Question> questionOptional = questionRepository.findById(questionId);
        if (questionOptional.isPresent()){

            Answer answer = new Answer();
            answer.setMessageContent(answerContent);
            answer.setMessageAuthor(userRepository.findByUsername(principal.getName()));
            answer.setQuestionId(questionId);
            answerRepository.save(answer);

            Question question = questionOptional.get();
            question.getMessageComments().add(answer);
            questionRepository.save(question);
            return question;
        } else {
            throw new NotFoundException("No message for ID=" + questionId);
        }
    }

    @GetMapping("{questionId}/upVote")
    public void upVoteQuestion(@PathVariable("questionId") final Integer questionId, Principal principal){
        Question question = questionRepository.findById(questionId).get();
        ApplicationUser applicationUser = userRepository.findByUsername(principal.getName());
        voteAnswer(question, applicationUser, 1);
        questionRepository.save(question);
    }

    @GetMapping("{questionId}/downVote")
    public void downVoteQuestion(@PathVariable("questionId") final Integer questionId, Principal principal){
        Question question = questionRepository.findById(questionId).get();
        ApplicationUser applicationUser = userRepository.findByUsername(principal.getName());
        voteAnswer(question, applicationUser, -1);
        questionRepository.save(question);
    }

    private void voteAnswer(Question question, ApplicationUser applicationUser, int vote) {
        if (question.getUserRatings().get(applicationUser.getUsername()) != null && question.getUserRatings().get(applicationUser.getUsername()) == vote*-1){
            question.getUserRatings().remove(applicationUser.getUsername());
        } else {
            question.getUserRatings().put(applicationUser.getUsername(), vote);
        }
    }

    @GetMapping("{questionId}/markCorrect/{answerId}")
    public void markCorrectAnswer(@PathVariable("questionId") final Integer questionId, @PathVariable("answerId") final Integer answerId, Principal principal){
        Question question = questionRepository.findById(questionId).get();
        if (question.getCorrectAnswerId() != null && question.getCorrectAnswerId().equals(answerId)){
            question.setCorrectAnswerId(null);
        } else {
            question.setCorrectAnswerId(answerId);
        }
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
                    for (Message message : question.getMessageComments()) {
                        if (message.getMessageContent().toLowerCase().contains(query)) return true;
                    }
                    return question.getMessageAuthor().getUsername().contains(query) || question.getMessageContent().contains(query) || question.getRelatedCourse().getName().contains(query);
                }
            }).collect(Collectors.toList());
        }

        questions.sort(QuestionComparators.sortByCreationDate);

        questions.forEach(new Consumer<Question>() {
            @Override
            public void accept(Question question) {
                question.getMessageComments().sort(new Comparator<Message>() {
                    @Override
                    public int compare(Message o1, Message o2) {
                        if (o1.getMessageAuthor().getAfekaRole().equals(AfekaRole.TEACHER) && !o2.getMessageAuthor().getAfekaRole().equals(AfekaRole.TEACHER)) return -1;
                        if (!o1.getMessageAuthor().getAfekaRole().equals(AfekaRole.TEACHER) && o2.getMessageAuthor().getAfekaRole().equals(AfekaRole.TEACHER)) return 1;
                        return o1.getUpdateDate().compareTo(o2.getUpdateDate());
                    }
                });
            }
        });

        return questions;
    }
}
