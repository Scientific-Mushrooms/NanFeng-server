package cloud.module.classroom.assignment;


import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "assignment")
@Data
public class Assignment {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator( name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String assignmentId;

    private String classroomId;

    private String instructorId;

    private String name;

    private String type;

    private String status;

    private Date startDate;

    private Date deadline;
}
