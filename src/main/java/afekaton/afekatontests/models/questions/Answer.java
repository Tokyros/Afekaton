package afekaton.afekatontests.models.questions;

import afekaton.afekatontests.models.members.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

@Entity
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
    private String answer;
    @OneToOne
    private User author;
    @OneToOne
    @JsonIgnore
    private Question question;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
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
