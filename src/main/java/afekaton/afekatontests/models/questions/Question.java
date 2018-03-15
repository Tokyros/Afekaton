package afekaton.afekatontests.models.questions;

import afekaton.afekatontests.models.courses.Course;
import afekaton.afekatontests.models.members.ApplicationUser;
import afekaton.afekatontests.models.members.Department;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@DiscriminatorValue("2")
public class Question extends Message {

    @OneToOne
    private Course relatedCourse;

    @Enumerated(EnumType.STRING)
    private Department relatedDepartement;

    public Course getRelatedCourse() {
        return relatedCourse;
    }

    public void setRelatedCourse(Course relatedCourse) {
        this.relatedCourse = relatedCourse;
    }

    public Department getRelatedDepartement() {
        return relatedDepartement;
    }

    public void setRelatedDepartement(Department relatedDepartement) {
        this.relatedDepartement = relatedDepartement;
    }
}
