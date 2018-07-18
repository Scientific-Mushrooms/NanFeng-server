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

    private String code;

    private String name;

    private String instructorName;

    private String instructorId;

    private String introduction;

    private String credit;

    private String avatar;

    private Integer ratingNum;

    private Integer likeNum;

    private Integer usefulNum;

    private Integer easyNum;

}
