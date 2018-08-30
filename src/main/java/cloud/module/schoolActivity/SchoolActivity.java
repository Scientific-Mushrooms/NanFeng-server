/*package cloud.module.schoolActivity;


import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "SchoolActivity")
@Data
public class SchoolActivity {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator( name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")

    private String schoolActivityId;

    private String avatarId;

    private String type;

    private String content;

    private Date date = new Date();
}*/
