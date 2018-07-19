package cloud.common.User;

import javax.persistence.*;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;


@Entity
@Table(name = "user")
@Data
public class User {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator( name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String userId;

    private String email;

    private String password;

    private String nickName;

    private String avatarPath;

    private String avatarId;

    private String instructorId;

}