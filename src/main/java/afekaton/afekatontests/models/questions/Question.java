package afekaton.afekatontests.models.questions;

import afekaton.afekatontests.models.members.ApplicationUser;
import afekaton.afekatontests.models.members.Department;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Question extends Rated {

    @OneToOne
    private Category category;

    @NotNull(message = "חובה למלא גוף שאלה")
    @NotEmpty(message = "חובה למלא גוף שאלה")
    @Lob
    private String question;

    @OneToOne
    private ApplicationUser author;

    @OneToMany
    private List<Answer> answers = new ArrayList<>();

    @OneToOne
    private Answer correctAnswer;

    @Enumerated(EnumType.STRING)
    private Department relatedDepartement;

    private int rating;

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

    public ApplicationUser getAuthor() {
        return author;
    }

    public void setAuthor(ApplicationUser author) {
        this.author = author;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
