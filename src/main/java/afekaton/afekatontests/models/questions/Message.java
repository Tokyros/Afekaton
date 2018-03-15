package afekaton.afekatontests.models.questions;

import afekaton.afekatontests.models.members.ApplicationUser;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Entity
@Inheritance
@DiscriminatorColumn(
        discriminatorType = DiscriminatorType.INTEGER,
        name = "message_type_id",
        columnDefinition = "TINYINT(1)"
)
public class Message {
    @Id
    @GeneratedValue
    private Integer messageId;

    @Lob
    @NotEmpty
    private String messageContent;
    @OneToOne
    private ApplicationUser messageAuthor;

    private Date creationDate;
    private Date updateDate;

    @OneToMany
    List<Message> messageComments;

    @JsonIgnore
    @ElementCollection
    @MapKeyColumn(name="name")
    @Column(name="value")
    @CollectionTable(name="example_attributes", joinColumns=@JoinColumn(name="example_id"))
    private Map<ApplicationUser, Integer> userRatings = new HashMap<>();

    private Integer rating;

    public Integer getMessageId() {
        return messageId;
    }

    public void setMessageId(Integer messageId) {
        this.messageId = messageId;
    }

    public String getMessageContent() {
        return messageContent;
    }

    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent;
    }

    public ApplicationUser getMessageAuthor() {
        return messageAuthor;
    }

    public void setMessageAuthor(ApplicationUser messageAuthor) {
        this.messageAuthor = messageAuthor;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public List<Message> getMessageComments() {
        return messageComments;
    }

    public void setMessageComments(List<Message> messageComments) {
        this.messageComments = messageComments;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public Map<ApplicationUser, Integer> getUserRatings() {
        return userRatings;
    }

    public void setUserRatings(Map<ApplicationUser, Integer> userRatings) {
        this.userRatings = userRatings;
    }
}
