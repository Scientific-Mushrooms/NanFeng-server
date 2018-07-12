package cloud.module.course;


import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "Course")
@Data
public class Course {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator( name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String courseId;

    private String courseCode;

    private String courseName;

    private String courseProf;

    private String courseIntro;

    private Date startDate;

    private Date endDate;
}
