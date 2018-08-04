package cloud.module.instructor.instructorComment;


import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "instructor_comment")
@Data
public class InstructorComment {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator( name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String instructorCommentId;

    private String instructorId;

    private String courseId;

    // 打分高低
    private boolean highMark;

    // 上课有趣
    private boolean fun;

    // 思路清晰
    private boolean clear;

    private Date date;

}
