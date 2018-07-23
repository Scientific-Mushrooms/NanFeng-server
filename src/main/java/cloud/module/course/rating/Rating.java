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

    private String courseId;

    private String instructorId;

//    private Boolean like;

    private Boolean easy;

    private Boolean useful;

    private String content;

    private Date createTime;
}
