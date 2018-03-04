package afekaton.afekatontests.models.questions;

import afekaton.afekatontests.models.members.Member;

import javax.persistence.*;
import java.util.List;

@Entity
public class Question {
    @Id
    @GeneratedValue
    private int id;

    private String question;

    private int upvotes;
    private int downvotes;

    @OneToOne
    private Member askingMember;
    @OneToMany
    private List<Answer> answers;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Member getAskingMember() {
        return askingMember;
    }

    public void setAskingMember(Member askingMember) {
        this.askingMember = askingMember;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public int getUpvotes() {
        return upvotes;
    }

    public void setUpvotes(int upvotes) {
        this.upvotes = upvotes;
    }

    public int getDownvotes() {
        return downvotes;
    }

    public void setDownvotes(int downvotes) {
        this.downvotes = downvotes;
    }

    public void upVote(){
        upvotes++;
    }

    public void downVote(){
        downvotes++;
    }
}
