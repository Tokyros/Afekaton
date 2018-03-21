package afekaton.afekatontests.models.questions;

import afekaton.afekatontests.models.members.ApplicationUser;
import org.hibernate.annotations.*;
import org.hibernate.annotations.CascadeType;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.validation.constraints.NotEmpty;
import java.util.*;
import java.util.function.BinaryOperator;

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

    private Date creationDate = new Date();
    private Date updateDate = new Date();
    private Integer correctAnswerId;

    @OneToMany
    @Cascade(CascadeType.DELETE)
    List<Message> messageComments;

    @ElementCollection
    @MapKeyColumn(name="name")
    @Column(name="value")
    @CollectionTable(name="example_attributes", joinColumns=@JoinColumn(name="example_id"))
    private Map<String, Integer> userRatings = new HashMap<>();

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
        Optional<Integer> reduce = getUserRatings().values().stream().reduce(new BinaryOperator<Integer>() {
            @Override
            public Integer apply(Integer integer, Integer integer2) {
                return integer + integer2;
            }
        });

        return reduce.isPresent() ? reduce.get() : 0;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public Map<String, Integer> getUserRatings() {
        return userRatings;
    }

    public void setUserRatings(Map<String, Integer> userRatings) {
        this.userRatings = userRatings;
    }

    public Integer getCorrectAnswerId() {
        return correctAnswerId;
    }

    public void setCorrectAnswerId(Integer correctAnswerId) {
        this.correctAnswerId = correctAnswerId;
    }
}
