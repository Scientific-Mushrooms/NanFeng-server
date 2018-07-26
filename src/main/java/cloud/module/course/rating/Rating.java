package cloud.module.course.rating;


import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "Rating")
@Data
public class Rating {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator( name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String ratingId;

    private String userId;

    private String courseId;

    private Boolean enjoy;

    private Boolean easy;

    private Boolean useful;

    private String comment;

    private Date date;

}
