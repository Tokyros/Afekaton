package afekaton.afekatontests.models.questions;

import afekaton.afekatontests.models.members.ApplicationUser;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

@Entity
public class Answer extends Rated {


    private String answer;
    @OneToOne
    private ApplicationUser author;
    @OneToOne
    @JsonIgnore
    private Question question;

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public ApplicationUser getAuthor() {
        return author;
    }

    public void setAuthor(ApplicationUser author) {
        this.author = author;
    }

    @JsonIgnore
    public Question getQuestion() {
        return question;
    }

    @JsonProperty("question")
    public void setQuestion(Question question) {
        this.question = question;
    }
}
