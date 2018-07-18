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

    private String courseProfessorName;

    private String courseProfessorId;

    private String courseIntroduction;

    private String courseLocation;

    private String courseCredit;

    private String courseAvatar;


    private Integer courseRatingNum;

    private Integer courseLikeNum;

    private Integer courseUsefulNum;

    private Integer courseEasyNum;


    private Date courseStartDate;

    private Date courseEndDate;
}
