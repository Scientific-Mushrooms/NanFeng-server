package cloud.module.classroom.classroomMember;


import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "classroom_member")
@Data
public class ClassroomMember {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator( name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String classroomMemberId;

    private String classroomId;

    private String studentId;

    private String role;

    private Date date;
}
