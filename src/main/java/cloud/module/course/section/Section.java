package cloud.module.course.section;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "Section")
@Data
public class Section {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator( name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String sectionId;

    private String courseId;

    private String instructorId;

    private String code;

    private Integer enrolledStudentNum;

    private Integer maxStudentNum;

    private String location;

    private String term;

    private String time;

}
