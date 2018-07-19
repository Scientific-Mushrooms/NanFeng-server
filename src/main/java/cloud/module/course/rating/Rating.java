package cloud.module.course.courseComment;


import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "CourseComment")
@Data
public class CourseComment {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator( name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String courseCommentId;

    private String courseId;

    private Boolean like;

    private Boolean easy;

    private Boolean useful;

    private String content;

    private Date createTime;
}
