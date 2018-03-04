package afekaton.afekatontests.models.members;

import javax.persistence.Entity;

@Entity
public class Faculty extends Member {
    private FacultyJob job;

    public FacultyJob getJob() {
        return job;
    }

    public void setJob(FacultyJob job) {
        this.job = job;
    }
}
