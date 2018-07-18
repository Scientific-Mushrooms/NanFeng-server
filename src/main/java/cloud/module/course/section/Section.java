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

    private String courseName;

    private String professorId;

    private String professorName;

    private Integer currentStudentNum;

    private Integer maxStudentNum;

    private Date startDate;

    private Date endDate;
}
