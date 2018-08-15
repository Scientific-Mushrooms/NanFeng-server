package cloud.module.classroom.assignment.discussion.discussionPost;


import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "discussion_post")
@Data
public class DiscussionPost {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator( name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String discussionPostId;

    private String studentId;

    private String discussionId;

    private String content;

    private Date date = new Date();

}
