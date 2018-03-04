package afekaton.afekatontests.models.questions;

import afekaton.afekatontests.models.members.Member;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Answer {
    @Id
    @GeneratedValue
    private int id;
    @OneToOne
    private Member answeringMember;
    private String answer;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Member getAnsweringMember() {
        return answeringMember;
    }

    public void setAnsweringMember(Member answeringMember) {
        this.answeringMember = answeringMember;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
