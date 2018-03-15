package afekaton.afekatontests.models.questions;

import afekaton.afekatontests.models.members.ApplicationUser;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class Message {
    @Id
    @GeneratedValue
    private Integer messageId;

    private String messageContent;
    @OneToOne
    private ApplicationUser messageAuthor;

    private Date creationDate;
    private Date updateDate;

    @OneToMany
    List<Message> messageComments;

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
}
